package com.psywerx.dh;

public class SoundButton extends Button {
    SoundButton() {
        s.size = new float[] { 0.15f, 0.1f, 1 };
        s.position = new float[] { -0.4f, -0.45f, -0.6f };
        s.texture.sprite = new int[] { 36, 53 };
        s.texture.startSprite = new int[] { 36, 53 };
        s.texture.size = new int[] { 3, 3 };
    }
    @Override
    public void draw() {
        if(!Sound.enabled){
            s.texture.sprite[0] = 39;
            s.texture.startSprite[0] = 39;
        } 
        else{
            s.texture.sprite[0] = 36;
            s.texture.startSprite[0] = 36;
            
        }
        super.draw();
    }
    @Override
    public boolean onUp(float x, float y) {
	// The sound button should be silent if we are turning the sound off.
	Sound.enabled = !Sound.enabled;
	boolean ret = super.onUp(x, y);
	Sound.enabled = !Sound.enabled;
	return ret;
    }
}
