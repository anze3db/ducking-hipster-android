package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Hint extends Drawable {
    
    protected Square s;
    protected boolean active = true;
    protected float timeout = 0;
    float[] id = new float[16];
    Hint(){
        s = new Square();
        s.size = new float[]{0.8f, 0.8f*(3f/19f), 1};
        s.color = new float[]{0,0,0,1};
        s.position = new float[]{0.0f, -1.5f, -0.5f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{41,36};
        s.texture.startSprite = new int[]{41,36};
        s.texture.size = new int[]{19,3};
        s.texture.anim = new int[]{0,};
    }
    
    public void tick(float theta){
        
        if(active){
            s.position[1] += theta*0.001f;
            timeout = 0f;
        }
        if(s.position[1] > 0){
            active = false;
            timeout += theta;
        }
        if(timeout > 3000)
            s.position[1] = -1.5f;
        
        if(Game.player1.dead){
            s.position[1] = -1.5f;
            active = false;
        }
    }
    public void draw(){
        
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                id, 0);
        s.draw();
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                Game.projection, 0);
    }
}
