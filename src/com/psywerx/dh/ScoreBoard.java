package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class ScoreBoard {
    
    private Text scoreText;
    int score;
    float[] id = new float[16];
    
    
    public ScoreBoard(){
       
        score = 0;
        scoreText = new Text(String.format("%07d", score));
        scoreText.setSize(new float[]{0.06f, 0.05f});
        scoreText.move(new float[]{0.5f, 0.9f, -0.5f});
    }
    
    public void increaseScore(int amount){
        score += amount;
        scoreText.update(String.format("%07d", score));
    }
    
    public void draw() {
        
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                id, 0);
        scoreText.draw();
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                Game.projection, 0);
    };

    public void tick(float theta) {
        
    };

}
