package com.psywerx.dh;


public class Enemy extends PersonSprite {

    private float r = 0.01f+Game.rand.nextFloat()/100;

    public Enemy() {
        spriteOffset = 1;
        updateSprite();
        speed[1] = 0.01f;
        this.resize(0.2f);
        this.position[2] = 1f - r;
        bound = new float[] { 0.4f, 0.7f };
    }

    @Override
    public void tick(float theta) {
        super.tick(theta);

        if (position[1] > 2) {
            position[1] = -2f;
            resize(0.2f);
            position[2] = 1f - r;
            animSpeed = 2f;
            speed[1] = 0.01f;
        }
        if(position[1] > -1){
            resize(Math.max(0, size[1] - size[1]*speed[1]*0.3f));
        }
        if(t.position[1]+t.size[1]/2 > Game.player1.t.position[1]- Game.player1.t.size[1]/2){
            position[2] = 1f + r;
        }
//        
        if (Utils.areColliding(this, Game.player1)) {
            speed[1] = 0.03f;
            animSpeed = 10f;
        }

        this.move(0, speed[1], 0);
    }
}
