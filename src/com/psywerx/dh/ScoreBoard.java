package com.psywerx.dh;

public class ScoreBoard extends Drawable {
    
    private Square top;
    private Text scoreText;
    private int score;
    
    
    public ScoreBoard(){
        top = new Square();
        top.size = new float[]{2f, 2f/8.3f, 1};
        top.color = new float[]{0,0,0,1};
        top.position = new float[]{0, 2.76f, 2f};
        top.texture.enabled = true;
        top.texture.sprite = new int[]{0,14};
        top.texture.size = new int[]{25,3};
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
        top.draw();
        scoreText.draw();
    };

    public void tick(float theta) {
    };

}
