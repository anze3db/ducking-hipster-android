package com.psywerx.dh;


class Texture {

    int NUM_SPRITES = 16;
    boolean enabled = true;
    int[] sprite = { 0, 0 };
    int[] startSprite = { 0, 0 };
    int[] size = { 1, 1 };
    
    float animSpeed = 1;
    int animState = 0;
    int[] anim = {0, 1, 2, 1};
    int spriteOffset = 0;
    private float acum = 0;

    void setSpriteFromChar(char c) {
        int charIndex = (int) c - 32;
        sprite = new int[] { (charIndex % NUM_SPRITES), (int) Math.floor(charIndex / NUM_SPRITES) };
    }

    float[] getTextureUV() {
        float[] charWidth = { size[0] / 64.0f, size[1] / 64.0f };
        float u = (sprite[0] / (float) size[0]);
        float v = (sprite[1] / (float) size[1]);
        charWidth[0] += 0.00006f;
        charWidth[1] += 0.00006f;
        return new float[] { (u + 1) * charWidth[0], (v + 1) * charWidth[1], u * charWidth[0], (v + 1) * charWidth[1],
                (u + 1f) * charWidth[0], v * charWidth[1], u * charWidth[0], v * charWidth[1] };
    }
    
    
    void updateSprite(){
        sprite = new int[]{startSprite[0]+size[0]*anim[animState%anim.length], 
                                     startSprite[1]+spriteOffset*size[1]};
    }

    void update(float theta) {
        acum  += theta*animSpeed;
        if (acum > 100){
            acum = 0;
            animState+=1;
            updateSprite();
        }        
    }
}
