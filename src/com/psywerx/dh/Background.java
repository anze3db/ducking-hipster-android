package com.psywerx.dh;

public class Background extends Drawable {
    private Square bg;
    private Square bg2;

    public Background() {
        
        
        this.position = new float[] { 0, -6, 3f };
        
        bg = new Square();
        bg.color = new float[] { 0, 0, 0, 1 };
        bg.size = new float[] { 30f, 30f*(35f/25f), 0f };
        bg.position = new float[] { -1f, -2f, 30f };
        
        bg.texture.enabled = true;
        bg.texture.sprite = new int[]{15,0};
        
        bg.texture.size = new int[] { 25, 35 };
//        bg2 = new Square();
//        bg2.size = new float[] { 2.5f, 2.5f * 4.2f, 0f };
//        bg2.color = new float[] { 0, 0, 0, 1 };
//        bg2.position = new float[] { -0.4f, -6, 3f };
//        bg2.texture.enabled = true;
//        bg2.texture.sprite = new int[] { 47, 0 };
//        bg2.texture.size = new int[] { 15, 63 };

    }

    public void draw() {
        bg.draw();
    };

    public void tick(float theta) {

    };

}
