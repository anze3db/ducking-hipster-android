package com.psywerx.dh;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class ScoreBoard {
    
    private Text scoreText;
    int score;
    float[] id = new float[16];
    private Text powerUp;
    private float powerUpCnt=5000;
    private int powerCnt=5;
    private Square pwrBg;
    private boolean pwrB = false;
    
    
    public ScoreBoard(){
       
        score = 0;
        scoreText = new Text(String.format("%07d", score));
        scoreText.setSize(new float[]{0.07f, 0.05f});
        scoreText.move(new float[]{0.2f, 0.9f, -0.5f});
        
        powerUp = new Text("5");
        powerUp.setSize(new float[]{0.05f, 0.03f});
        powerUp.move(new float[]{-0.2f, 0.9f, -0.5f});
        
        pwrBg = new Square();
        pwrBg.texture.enabled = true;
        pwrBg.texture.size = new int[] { 2, 2 };
        pwrBg.texture.anim = new int[] { 1, 1 };
        pwrBg.texture.animSpeed = 0f;
        pwrBg.texture.spriteOffset = 0;
        pwrBg.texture.sprite = new int[] { 11, 8 };
        pwrBg.texture.startSprite = new int[] { 11, 8 };
	pwrBg.position[1] = 1.85f; 
	pwrBg.position = new float[]{-0.2f, 0.895f, -0.4f};
	pwrBg.size[0] = 0.12f;
	pwrBg.size[1] = 0.08f; //new int[]{};
        //SceneGraph.activeObjects.add(pwrBg);
    }
    
    public void increaseScore(int amount){
        score += amount;
        scoreText.update(String.format("%07d", score));
    }
    public boolean powerUpCnt(float theta){
	powerUpCnt-=theta;
	pwrB = true;
	if(powerCnt - Math.floor(powerUpCnt/1000) > 1){
	    if(powerCnt > 1) powerCnt--;
	    powerUp.update(String.format("%1d", powerCnt-1));
	}
	
	if(powerUpCnt < 5){
            powerUpCnt = 5000;
            powerCnt = 5;
            pwrB = false;
            return false;
        }
        return true;
	
    }
    
    public void draw() {
        
        Matrix.setIdentityM(id, 0);
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                id, 0);
        scoreText.draw();
        if(pwrB){
            pwrBg.draw();
            powerUp.draw(); 
        } 
        GLES20.glUniformMatrix4fv(Game.program.projectionMatrixLoc, 1, false,
                Game.projection, 0);
    };

    public void tick(float theta) {
        
    };

}
