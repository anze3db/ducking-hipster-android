package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Menu extends Drawable {

    private Square s;
    private float[] id = new float[16];
    private Square high;
    private float acum = 0;
    
    
    public Menu(){
        
	s = new Square();
        s.size = new float[]{0, 1.0f*(22f/29f), 1};
        s.color = new float[]{0,0,0,1};
        s.position = new float[]{0.0f, 0.0f, -0.5f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{41,0};
        s.texture.startSprite = new int[]{41,0};
        s.texture.size = new int[]{22,29};
        s.texture.anim = new int[]{0,};
        
        
        
        high = new Square();
        high.size = new float[]{0.3f, 0.3f*(4f/7f), 1};
        high.color = new float[]{0,0,0,1};
        high.position = new float[]{0.7f, 0.6f, -0.55f};
        high.texture.enabled = true;
        high.texture.sprite = new int[]{10,44};
        high.texture.startSprite = new int[]{10,44};
        high.texture.size = new int[]{7,4};
        high.texture.anim = new int[]{0,};
        
            
    }
    
    public void tick(float theta){
	acum += theta;
        switch(Game.state){
        case 'G':
            s.size[0] = 0f; s.size[1] = 0f;
            break;
        default:
            s.size[0] = s.size[0]*0.9f + 0.1f*1.0f;
            s.size[1] = 1.3f*(22f/29f);
        }
        high.size[0] = (float)Math.sin(acum/100)*0.005f+0.3f;
        high.size[1] = (float)Math.cos(acum/100)*0.005f+0.3f*(4f/7f);
    }

    public void draw(){
        
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                id, 0);
        switch(Game.state){
        case 'M':
            s.texture.sprite[1] = 0;
            s.texture.startSprite[1] = 0;
            break;
        case 'P':
            s.texture.sprite[1] = 0;
            s.texture.startSprite[1] = 0;
            break;
        case 'E':
            s.texture.sprite[1] = 0;
            s.texture.startSprite[1] = 0;
            break;
        }
        s.draw();
        if(Game.isHighScore)
            high.draw();
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                Game.projection, 0);
    }
}
