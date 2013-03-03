package com.psywerx.dh;

public class LevelHints extends Hint {

    int progress = 0;
    
    LevelHints(){
        s.size = new float[]{0.9f, 0.9f*(2f/19f), 1};
        s.texture.sprite = new int[]{41,39};
        s.texture.startSprite = new int[]{41,36};
        s.texture.size = new int[]{19,2};
    }
    public void nextHint() {
        progress++;
        s.texture.sprite = new int[]{41,39+progress*2};
        s.texture.startSprite = new int[]{41,36+progress*2};
        active = true;
    }
    public void reset(){
        progress = 0;
    }
}
