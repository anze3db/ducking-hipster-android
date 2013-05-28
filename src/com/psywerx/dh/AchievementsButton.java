package com.psywerx.dh;

public class AchievementsButton extends Button {
    AchievementsButton() {
        s.size = new float[] { 0.55f, 0.55f * (3f / 14f), 1 };
        s.position = new float[] { 0.0f, -0.2f, -0.6f };
        s.texture.sprite = new int[] { 45, 53 };
        s.texture.startSprite = new int[] { 45, 53 };
        s.texture.size = new int[] { 16, 3 };
    }
    
    @Override
    public boolean onUp(float x, float y){
	
	if(!Game.isSignedIn) return false;
	
	return super.onUp(x, y);
    }
    
    @Override
    public void draw(){
	if(!Game.isSignedIn){
	    s.texture.sprite[1] = 50;
	}
	else if(canTrigger){
	    s.texture.sprite[1] = 56;
	}
	else{
	    s.texture.sprite[1] = 53;
	}
	super.draw();
    }

}
