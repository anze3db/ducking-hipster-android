package com.psywerx.dh;

public class SignInButton extends Button {
    SignInButton() {
        s.size = new float[] { 0.7f, 0.7f * (3f / 31f), 1 };
        s.position = new float[] { 0.0f, -0.7f, -0.6f };
        s.texture.sprite = new int[] { 0, 59 };
        s.texture.startSprite = new int[] { 0, 59 };
        s.texture.size = new int[] { 30, 4 };
    }
    @Override
    public void onDown(float x, float y){
	super.onDown(x, y);
	s.texture.startSprite[1] = 59;
        s.texture.updateSprite();
    }
    
    @Override
    public boolean onUp(float x, float y){
	boolean res = super.onUp(x, y);
	s.texture.startSprite[1] = 59;
        s.texture.updateSprite();
        return res;
    }
    @Override
    public void draw(){
	if(Game.isSignedIn){
	    s.texture.startSprite[0] = 30;
	} else {
	    s.texture.startSprite[0] = 0;
	}
	s.texture.updateSprite();
	super.draw();
    }
}
