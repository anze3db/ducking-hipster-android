package com.psywerx.dh;

import android.util.Log;



public class Player extends Drawable {

    
    
    
    private Square s;
    private float[] velocity = {0,0,0};
    public  float[] direction = {0,0,0};
    private float[] speed = {0,0};
    private int colide = 1;
    private float acum = 0;
    private float animSpeed = 1;
    private int animState = 0;
    private int[] anim = {0, 1, 2, 1};
    private float rotation = 0;

    public Player(){
        s = new Square();
        s.size = new float[]{0.4f*5/4f, 0.4f, 0.2f};
        s.color = new float[]{0f,0f,0f,1f};
        s.texture.enabled = true;
        s.texture.sprite = new int[]{0,28};
        s.texture.size   = new int[]{5,4};
        s.position = position;
        s.position[2] = 1f;
    }
    
    public void move(float x, float y, float z){
        
        
        
        position = Utils.add(position, x,y,z);
        s.position = Utils.add(s.position, x,y,z);
    }
    
    @Override
    public void tick(float theta){
        
        acum += theta;
        if (acum > 200){
            acum = 0;
            animState+=1;
            s.texture.sprite = new int[]{0+s.texture.size[0]*anim[animState%anim.length], 28};
        }
        
        rotation = (direction[0] > 0f ? -25 : direction[0] < 0 ? 25f : 0f) * 0.4f + 0.6f*rotation;
        
        s.rot = new float[]{rotation, 0f, 1f, 0f};
        
        speed = new float[]{theta*direction[0], 0f};
        if(direction[0] == 0 && colide == 0) this.speed[0] = 0;
        if(direction[1] == 0 && colide == 0) this.speed[1] = 0;
        if(this.direction[0] != 0 && this.direction[1] != 0 && this.colide == 0){
            this.speed[0] = 0;
            this.speed[1] = 0;
        }
        if(this.position[0] < -1 && direction[0] < 0){
            this.speed[0] = 0;
        }
        if(this.position[0] > 1 && direction[0] > 0){
            this.speed[0] = 0;
        }
        this.move(speed[0]/500, 0, 0);
    }
    @Override
    public void draw(){
        s.draw();
    }
}
