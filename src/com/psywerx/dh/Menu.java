package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Menu extends Drawable {

    private Square s;
    
    public Menu(){
        
        s = new Square();
        s.size = new float[]{0.6f, 0.6f*(11f/19f), 1};
        s.color = new float[]{0,0,0,1};
        s.position = new float[]{0.0f, -0.2f, -0.5f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{41,0};
        s.texture.startSprite = new int[]{41,0};
        s.texture.size = new int[]{19,11};
        s.texture.anim = new int[]{0,};
        
        
            
    }
    
    public void tick(float theta){
        switch(Game.state){
        case 'G':
            s.size = new float[]{0f, 0f};
            break;
        default:
            s.size = new float[]{s.size[0]*0.9f + 0.1f*0.6f, s.size[1]*0.9f + 0.1f*0.6f*(11f/19f), 1};
        }
    }
    
    public void draw(){
        
        float[] id = new float[16];
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                id, 0);
        switch(Game.state){
        case 'M':
            s.texture.sprite = new int[]{41,0};
            s.texture.startSprite = new int[]{41,0};
            break;
        case 'P':
            s.texture.sprite = new int[]{41,12};
            s.texture.startSprite = new int[]{41,12};
            break;
        case 'E':
            s.texture.sprite = new int[]{41,24};
            s.texture.startSprite = new int[]{41,24};
            break;
        }
        s.draw();
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                Game.projection, 0);
    }
}
