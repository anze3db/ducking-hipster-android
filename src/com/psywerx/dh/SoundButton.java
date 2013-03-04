package com.psywerx.dh;

public class SoundButton extends Button {
    SoundButton() {
        s.size = new float[] { 0.15f, 0.1f, 1 };
        s.position = new float[] { -0.4f, -0.45f, -0.6f };
        s.texture.sprite = new int[] { 40, 53 };
        s.texture.startSprite = new int[] { 40, 53 };
        s.texture.size = new int[] { 3, 3 };
    }
    @Override
    public void draw() {
        if(!Game.sound){
            s.texture.sprite[0] = 43;
            s.texture.startSprite[0] = 43;
        } 
        else{
            s.texture.sprite[0] = 40;
            s.texture.startSprite[0] = 40;
            
        }
        super.draw();
    }
}
