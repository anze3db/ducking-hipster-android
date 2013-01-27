package com.psywerx.dh;

public class PersonSprite extends Drawable {
    public  float[] direction = {0,0,0};
    protected Square s;
    protected float[] velocity = {0,0,0};
    protected float[] speed = {0,0};
    protected int colide = 1;
    protected float acum = 0;
    protected float animSpeed = 2;
    protected int animState = 0;
    protected int[] anim = {0, 1, 2, 1};
    protected float rotation = 0;
    

    protected int spriteOffset = 0; //0 - player, 0 > - monsters 
    Square t;
    
    public PersonSprite(){
        s = new Square();
        size = new float[]{0.3f*5/4f, 0.3f, 0.2f};
        s.size = size;
        s.color = new float[]{0f,0f,0f,1f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{0,28};
        s.texture.size   = new int[]{5,4};
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
        size = new float[]{newSize*5/4f, newSize, 0.2f};
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
    
    protected void updateSprite(){
        s.texture.sprite = new int[]{0+s.texture.size[0]*anim[animState%anim.length], 28+spriteOffset*4};
    }
    
    @Override
    public void tick(float theta){
        acum += theta*animSpeed;
        if (acum > 200){
            acum = 0;
            animState+=1;
            updateSprite();
        }
    }
    
    @Override
    public void draw(){
        //t.draw();
        s.draw();
    }
}
