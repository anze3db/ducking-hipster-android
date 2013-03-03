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
    private float[] textUv = new float[8];
    private float[] charWidth = new float[2];

    void setSpriteFromChar(char c) {
        int charIndex = (int) c - 32;
        sprite = new int[] { (charIndex % NUM_SPRITES), (int) Math.floor(charIndex / NUM_SPRITES) };
    }

    float[] getTextureUV() {
        charWidth[0] = size[0] / 64.0f;
        charWidth[1] = size[1] / 64.0f;
        float u = (sprite[0] / (float) size[0]);
        float v = (sprite[1] / (float) size[1]);
        charWidth[0] += 0.00006f;
        charWidth[1] += 0.00006f;
        textUv[0] = (u + 1) * charWidth[0];
        textUv[1] = (v + 1) * charWidth[1];
        textUv[2] = u * charWidth[0];
        textUv[3] = (v + 1) * charWidth[1];
        textUv[4] = (u + 1f) * charWidth[0];
        textUv[5] = v * charWidth[1];
        textUv[6] = u * charWidth[0];
        textUv[7] = v * charWidth[1];
        return textUv;
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
