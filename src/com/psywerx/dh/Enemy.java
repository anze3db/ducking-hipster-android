package com.psywerx.dh;

import android.util.Log;


public class Enemy extends PersonSprite {

    private float r = 0.01f+Game.rand.nextFloat()/100;
    private Texture colTexture = new Texture();
    private Texture original = s.texture;

    public Enemy() {
        s.texture.spriteOffset = 1;
        s.texture.updateSprite();
        speed[1] = 0.01f;
        this.resize(0.2f);
        this.position[2] = 1f - r;
        bound = new float[] { 0.4f, 0.7f };
        
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

        if (position[1] > 5) {
            position[1] = -2f;
            resize(0.2f);
            position[2] = 1f - r;
            speed[1] = 0.01f;
            s.texture = original;
        }
        if(position[1] > -1){
            //resize(Math.max(0, size[1] - size[1]*speed[1]*0.3f));
        }
        if(t.position[1]+t.size[1]/2 > Game.player1.startPosition){
            position[2] += theta * 0.0005f;
//            position[2] = 1f + r;
        }
//        
        if (Utils.areColliding(this, Game.player1)) {
            Game.player1.dead = true;
            Game.player1.move(0,  speed[1], 0);
            Game.player1.position[2] += theta * 0.0005f;
            
            //this.position[2] = 1f + r;
            
        }

        this.move(0, speed[1], 0);
    }
}
