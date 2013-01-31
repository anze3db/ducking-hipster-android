package com.psywerx.dh;


public class PersonSprite extends Drawable {
    public  float[] direction = {0,0,0};
    protected Square s;
    protected float[] velocity = {0,0,0};
    protected float[] speed = {0,0};
    protected int colide = 1;
    protected float acum = 0;
    protected float rotation = 0;
    

    protected int spriteOffset = 0; //0 - player, 0 > - monsters 
    Square t;
    
    public PersonSprite(){
        s = new Square();
        size = new float[]{0.2f, 0.2f, 0.2f};
        s.size = size;
        s.color = new float[]{0f,0f,0f,1f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{0,6};
        s.texture.startSprite = s.texture.sprite;
        s.texture.size   = new int[]{3,3};
        s.position = position;
        s.position[2] = 1f;
        
        t = new Square();
        t.color = new float[]{1f,0f,0f,1f};
        t.texture.enabled = false;
        t.position[0] = position[0];
        t.position[1] = position[1];
        t.size[0] = size[0] * 0.5f;
        t.size[1] = size[0] * 0.15f;
    }
    protected void resize(float newSize){
        size = new float[]{newSize, newSize, 0.2f};
        s.size = size;
        t.size[0] = size[0] * 0.5f;
        t.size[1] = size[1] * 0.15f;
    }
    protected void move(float x, float y, float z){
        
        position = Utils.add(position, x,y,z);
        s.position = position;
        t.position[0] = position[0];
        t.position[1] = position[1]-0.2f;
        t.position[2] = position[2];
    }
    
    @Override
    public void tick(float theta){
        s.texture.update(theta);
    }
    
    @Override
    public void draw(){
//        t.draw();
        s.draw();
    }
}