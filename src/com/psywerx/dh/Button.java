package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Button extends Drawable {
    protected Square s;
    public boolean canTrigger;
    float[] id = new float[16];

    Button() {
        s = new Square();
        s.size = new float[] { 0.2f, 0.2f * (3f / 6f), 1 };
        s.color = new float[] { 0, 0, 0, 1 };
        s.position = new float[] { 0.0f, -0.25f, -0.6f };
        s.texture.enabled = true;
        s.texture.size = new int[] { 6, 3 };
        s.texture.anim = new int[] { 0, };
    }

    public void onDown(float x, float y) {
        if (x > s.position[0] - s.size[0] && x < s.position[0] + s.size[0] && y > s.position[1] - s.size[1]
                && y < s.position[1] + s.size[1]) {
            s.texture.startSprite[1] = 56;
            s.texture.updateSprite();
            canTrigger = true;
        }
        else{
            canTrigger = false;
        }

    }

    public boolean onUp(float x, float y) {
        s.texture.startSprite[1] = 53;
        s.texture.updateSprite();
        if (x > s.position[0] - s.size[0] && x < s.position[0] + s.size[0] && y > s.position[1] - s.size[1]
                && y < s.position[1] + s.size[1]) {
            if(canTrigger){
        	Sound.play(Sound.select);
            }
            boolean ret = canTrigger;
            canTrigger = false;
            return ret;
        }
        else{
          canTrigger = false;
        }
        return false;
    }

    @Override
    public void tick(float theta) {

    }

    @Override
    public void draw() {
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false, id, 0);
        s.draw();
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false, Game.projection, 0);
    }
}
