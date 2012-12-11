package com.psywerx.dh;

public class Background extends Drawable {
    private Square bg;
    private Square bg2;

    public Background() {
        
        this.position = new float[] { 0, -6, 3f };
        
        bg = new Square();
        bg.size = new float[] { 2.5f, 2.5f * 4.2f, 0f };
        bg.color = new float[] { 0, 0, 0, 1 };
        bg.position = new float[] { -0.4f, -6, 3f };
        bg.texture.enabled = true;
        bg.texture.sprite = new int[] { 32, 0 };
        bg.texture.size = new int[] { 15, 63 };
        
        
        bg2 = new Square();
        bg2.size = new float[] { 2.5f, 2.5f * 4.2f, 0f };
        bg2.color = new float[] { 0, 0, 0, 1 };
        bg2.position = new float[] { -0.4f, -6, 3.01f };
        bg2.texture.enabled = true;
        bg2.texture.sprite = new int[] { 47, 0 };
        bg2.texture.size = new int[] { 15, 63 };

    }

    public void draw() {
        bg2.draw();
        bg.draw();
    };

    public void tick(float theta) {
        bg.position[1] += theta * 0.00001;
        if (bg.position[1] > 8)
            bg.position[1] = -9;
        bg2.position[1] += theta * 0.0001;
        if (bg2.position[1] > 8)
            bg2.position[1] = -9;
    };

}
