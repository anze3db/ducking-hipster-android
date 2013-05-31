package com.psywerx.dh;

import com.google.analytics.tracking.android.EasyTracker;

public class Enemy extends PersonSprite {

    protected float r = 0.01f+Game.rand.nextFloat()/100;
    protected Texture colTexture = new Texture();
    protected Square col = new Square();
    protected float timeDead;
    protected boolean score = false;
    protected boolean nearMiss = false;
    protected Square bubble = new Square();
    protected Texture bubbleTex = new Texture();
    protected float BUBBLE_SCALE = 0.002f;

    public Enemy() {
        reset();
    }
    void reset(){
        s.texture.spriteOffset = (int)(Math.random() * 12) + 4;
        s.texture.updateSprite();
        
        speed[1] = 0.01f;
        resize(0.2f);
        position[2] = 1;
        position[1] = -2f;
        removeMe = false;
        
        colTexture.enabled = true;
        colTexture.sprite = new int[]{9,6};
        colTexture.startSprite = new int[]{9,6};
        colTexture.size = new int[]{2,2};
        colTexture.anim = new int[]{1,0,1,2,1};
        colTexture.animSpeed = 0.5f;
        
        bubbleTex.enabled = true;
        bubbleTex.sprite = new int[]{9,3};
        bubbleTex.startSprite = new int[]{9,3};
        bubbleTex.size = new int[]{3,3};
        bubbleTex.anim = new int[]{0,1};
        bubbleTex.animSpeed = 0.1f;
        
        bubble.texture = bubbleTex;
        bubble.size = new float[]{0.15f, 0.15f, 0.15f};
        
        col.texture = colTexture;
        col.size = new float[]{0.2f, 0.2f, 0.25f};
        timeDead = 0f;
        score = false;
        nearMiss = false;
    }

    @Override
    public void tick(float theta) {
        super.tick(theta);
        if (position[1] > 10 || (SceneGraph.activeObjects.size() > 20 && position[1] > 5)) {
            // This needs to get moved:
            removeMe = true;
            Game.preloadedEnemies.push(this);
        }
        if(bb.position[1]+bb.size[1]/2 > Game.player1.sPosition){
            position[2] += speed[1] * theta * 0.112f;
            position[1] -= speed[1]*0.1f;
            if(!score){
        	if(!Game.player1.dead){
        	    Game.top.increaseScore(1);
        	    if(nearMiss) {
        		nearHit();
        	    }
        	}
                score = true;
            }
        }
        col.position[0] = -100f;
        col.position[1] = -100f;
        col.position[2] = 0f;
        bubble.position[0] = position[0] - 0.05f;
        bubble.position[1] = position[1] + 0.4f;
        bubble.position[2] = position[2] - 0.01f;
        
        if(bubble.size[1] < 0.18f){
            BUBBLE_SCALE = Math.abs(BUBBLE_SCALE);
        }
        else if(bubble.size[1] > 0.22f){
            BUBBLE_SCALE = -Math.abs(BUBBLE_SCALE);
        }
        bubble.size[1] += BUBBLE_SCALE;
        bubble.size[0] += BUBBLE_SCALE/10;
        bubble.texture.update(theta);
        if (Utils.areColliding(this.bb, Game.player1.bb)) {
	    if (!Game.player1.dead) {
		if (Game.sound) {
		    Game.hit.seekTo(0);
		    Game.hit.start();
		}
		timeDead = 0;
            }
            else{
                timeDead+=theta;
            }
            if(timeDead > 2000){
                EasyTracker.getTracker().sendEvent("Game", "End", "Score", (long)Game.top.score);
                ((MainActivity)MyRenderer.context).newScore(Game.top.score);
                Game.state = 'E';
            }
            
            // Bug where player is in front of the collision:
            if(Game.player1.position[2]<position[2]){
                
                Game.player1.position[2] -= (0.01f + Game.player1.position[2] - position[2]);
                Game.player1.position[1] = position[1] + 0.01f;
            }
	    
            Game.player1.dead = true;
            Game.player1.move(0,  speed[1]*theta*0.05f, 0);
            Game.player1.position[2] += speed[1] * theta * 0.112f;
            Game.player1.position[1] -= speed[1]*0.1f;
            col.position = position.clone();
            col.position[0] += (Game.player1.position[0] - position[0])/2;
            col.position[2] -= 0.01f;
            
            
            
            col.texture.update(theta);
        } else if (Utils.areColliding(this.bb, Game.player1.bbClose)){
            if(!nearMiss){
        	    if(score){
        		nearHit();
        	    } 
        	    nearMiss = true;
            }
        }
        this.move(0, speed[1]*theta*0.05f, speed[1] * theta * 0.001f);
    }
    private void nearHit() {
	Game.top.increaseScore(5);
	if (Game.sound) {
	    Game.mad.seekTo(0);
	    Game.mad.start();
	}
	((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_nearMiss);
	((MainActivity)MyRenderer.context).incrementAchievement(R.string.ach_nearMiss100, 1);
    }
    @Override
    public void draw(){
        super.draw();
        col.draw();
        if(score && nearMiss && !Game.player1.dead){
            bubble.draw();
        }
//        bb.draw();
        
        
    }
}
