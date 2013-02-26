package com.psywerx.dh;



public class Enemy extends PersonSprite {

    private float r = 0.01f+Game.rand.nextFloat()/100;
    private Texture colTexture = new Texture();
    private Texture original = s.texture;

    public Enemy() {
        reset();
    }
    void reset(){
        s.texture.spriteOffset = (int)(Math.random() * 12) + 4;
        s.texture.updateSprite();
        
        speed[1] = 0.01f;
        this.resize(0.2f);
        this.position[2] = 1f - r;
        position[1] = -2f;
        this.removeMe = false;
        
        colTexture.enabled = true;
        colTexture.sprite = new int[]{9,6};
        colTexture.startSprite = new int[]{9,6};
        colTexture.size = new int[]{2,2};
        colTexture.anim = new int[]{1,0,1,2,1};
        colTexture.animSpeed = 0.5f;
    }

    @Override
    public void tick(float theta) {
        super.tick(theta);

        if (position[1] > 10) {
            
            // This needs to get moved:
            if(Game.player1.dead){
                Game.player1.resetPlayer();
            }
            // Remove yourself:
            removeMe = true;
            Game.preloadedEnemies.push(this);
        }
        if(position[1] > -1){
            //resize(Math.max(0, size[1] - size[1]*speed[1]*0.3f));
        }
        if(bb.position[1]+bb.size[1]/2 > Game.player1.sPosition){
            position[2] += speed[1] * theta * 0.074f;
//            position[2] = 1f + r;
        }
//        
        if (Utils.areColliding(this, Game.player1)) {
            Game.player1.dead = true;
            Game.player1.move(0,  speed[1]*theta*0.05f, 0);
            Game.player1.position[2] += speed[1] * theta * 0.074f;
            //this.position[2] = 1f + r;
            
        }

        this.move(0, speed[1]*theta*0.05f, 0);
    }
}
