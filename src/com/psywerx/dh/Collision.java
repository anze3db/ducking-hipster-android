package com.psywerx.dh;

public class Collision extends Drawable {

    private Square s;
    boolean enabled;

    public Collision(){
	s = new Square();
        size = new float[]{0.2f, 0.2f, 0.2f};
        s.size = new float[] { 0.2f, 0.2f, 0.25f };
        s.color = new float[]{0f,0f,0f,1f};
        s.position = position;
        
	s.texture.enabled = true;
	s.texture.sprite = new int[] { 9, 6 };
	s.texture.startSprite = new int[] { 9, 6 };
	s.texture.size = new int[] { 2, 2 };
	s.texture.anim = new int[] { 1, 0, 1, 2, 1 };
	s.texture.animSpeed = 0.5f;
	
    }
    @Override
    public void draw(){
	if(enabled) s.draw();
    }
    @Override
    public void tick(float theta){
	if(enabled) {
	    s.tick(theta);
	    s.texture.update(theta);
	}
    }

}
