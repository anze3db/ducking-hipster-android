package com.psywerx.dh;

public class Texture {

    int NUM_SPRITES = 16;
    boolean enabled = true;
    int[] sprite = { 0, 0 };
    int[] size = { 1, 1 };

    public void setSpriteFromChar(char c) {
        int charIndex = (int)c - 32;
        sprite = new int[] { (charIndex % NUM_SPRITES),
                (int) Math.floor(charIndex / NUM_SPRITES) };
    }

    public float[] getTextureUV() {
        float[] charWidth = { size[0]/64.0f, size[1]/64.0f };
        float u = (sprite[0] / (float)size[0]);
        float v = (sprite[1] / (float)size[1]);
        return new float[] { (u + 1) * charWidth[0], (v + 1) * charWidth[1],
                u * charWidth[0], (v + 1) * charWidth[1],
                (u + 1f) * charWidth[0], v * charWidth[1], u * charWidth[0],
                v * charWidth[1] };
    }
}
