package com.psywerx.dh;

public class Background extends Drawable {
    
    private short NUM_CLOUDS = 15;
    private Square bg;
    private Square bgc;
    private Square[] clouds = new Square[NUM_CLOUDS];
    private float[] speeds = new float[NUM_CLOUDS];

    public Background() {
        
        
        position = new float[] { 0, -8, 3f };
        
        bgc = new Square();
        bgc.color = new float[] {1, 0, 0, 1};
        bgc.size = new float[] { 23f, 60f, 0f };
        bgc.position = new float[] { 0f, 11f, 31f };
        bgc.texture.enabled = true;
        bgc.texture.sprite = new int[]{25,13};
        bgc.texture.size = new int[] { 1, 1 };
        
        
        bg = new Square();
        bg.color = new float[] { 0, 0, 0, 1 };
        bg.size = new float[] { 23f, 23f*(14f/25f), 0f };
        bg.position = new float[] { 0f, 21f, 30f };
        
        bg.texture.enabled = true;
        bg.texture.sprite = new int[]{15,0};
        
        bg.texture.size = new int[] { 25, 14 };
        
        for(int i = 0; i < NUM_CLOUDS; i++){
          speeds[i] = (float)(Math.random()-0.5);
          clouds[i] = new Square();
          clouds[i].color = new float[] { 0, 0, 0, 1 };
          clouds[i].size = new float[] { 1f, 1, 0f };
          clouds[i].position = new float[] { (float)(Math.random()-0.5)*30, 29-i, 29f-i };
          
          clouds[i].texture.enabled = true;
          int off = (int)(Math.random()*2);
          clouds[i].texture.sprite = new int[]{9+off*2,12};
          clouds[i].texture.startSprite = new int[]{9+off*2,12};
          clouds[i].texture.size = new int[] { 2, 2 };

          clouds[i].texture.anim = new int[]{0,1};
          clouds[i].texture.animSpeed = 0.01f * (float)Math.random();
        }
        
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
	bgc.draw();
        for(int i = 0; i < NUM_CLOUDS; i++){
          clouds[i].draw();
        }
    };

    public void tick(float theta) {
      for(int i = 0; i < NUM_CLOUDS; i++){
        clouds[i].texture.update(theta);
        clouds[i].position[0] += speeds[i]/100;
        if(clouds[i].position[0] > 15 && speeds[i] > 0){
          speeds[i] *= -1;
        }
        else if(clouds[i].position[0] < -15 && speeds[i] < 0){
          speeds[i] *= -1;
        }
      }
    };

}
