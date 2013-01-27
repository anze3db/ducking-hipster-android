package com.psywerx.dh;

import android.util.Log;

public class ScoreBoard extends PersonSprite {
    
    private Text scoreText;
    private int score;
    
    
    public ScoreBoard(){
        s = new Square();
        s.size = new float[]{0.1f, 0.1f, 1};
        s.color = new float[]{0,0,0,1};
        s.position = new float[]{0, 2.76f, 2f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{9,8};
        s.texture.startSprite = new int[]{9,8};
        s.texture.size = new int[]{1,1};
        s.texture.anim = new int[]{0,1,2,};

        s.texture.animSpeed = 1f;
        score = 1;
        scoreText = new Text(String.format("%07d", score));
        scoreText.setSize(new float[]{0.12f, 0.12f});
        scoreText.move(new float[]{1.3f, 2.740f, 1.99f});
    }
    
    public void increaseScore(int amount){
        score += amount;
        scoreText.update(String.format("%07d", score));
    }
    
    public void draw() {
        s.draw();
        scoreText.draw();
    };

    public void tick(float theta) {
        acum += theta*s.texture.animSpeed;
        if (acum > 200){
            acum = 0;
            s.texture.animState+=1;
            updateSprite();
        }
    };

}
