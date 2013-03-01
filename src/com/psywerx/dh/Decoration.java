package com.psywerx.dh;

public class Decoration extends PersonSprite {
    private float r = 0.001f+Game.rand.nextFloat()/1000;
    public Decoration(){
        reset();
    }
    void reset() {
        s.texture.spriteOffset = (int)(Math.random() * 0) + 0;
        s.texture.anim = new int[]{0,1,2,1,0};
        s.texture.sprite = new int[]{9,15};
        s.texture.startSprite = s.texture.sprite;
        s.texture.size   = new int[]{1,1};
        s.texture.animSpeed = 0.5f;
        s.texture.updateSprite();
        
        speed[1] = 0.01f;
        resize(0.1f);
        
        position[2] = 1f - r;
        position[1] = -2f;
        removeMe = false;
        
    }
    
    @Override
    public void tick(float theta){
        s.texture.update(theta);
        bb.position[1] = position[1]-0.1f;
        if (position[1] > 10) {
            // Remove yourself:
            removeMe = true;
        }
        
        if (Utils.areColliding(this, Game.player1)) {
            s.texture.anim = new int[]{3};
        }
        if(bb.position[1]+bb.size[1]/2 > Game.player1.sPosition){
            position[2] += speed[1] * theta * 0.074f;
        }
        this.move(0, speed[1]*theta*0.05f, 0);
    }
}
