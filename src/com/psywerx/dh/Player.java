package com.psywerx.dh;



public class Player extends Drawable {

    
    
    
    private Square s;
    private float[] velocity = {0,0,0};
    public  float[] direction = {0,0,0};
    private float[] speed = {0,0};
    private int colide = 1;

    public Player(){
        s = new Square();
        s.size = new float[]{0.2f, 0.2f, 0.2f};
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
        speed = Utils.add(speed, new float[]{theta*2, theta*2});
        if(direction[0] == 0 && colide == 0) this.speed[0] = 0;
        if(direction[1] == 0 && colide == 0) this.speed[1] = 0;
        if(this.direction[0] != 0 && this.direction[1] != 0 && this.colide == 0){
            this.speed[0] = 0;
            this.speed[1] = 0;
        }
        
    }
    @Override
    public void draw(){
        s.draw();
    }
}
