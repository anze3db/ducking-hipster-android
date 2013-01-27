package com.psywerx.dh;




public class Player extends PersonSprite {





    public Player(){
        
        position[1] += 1; 
        bound = new float[]{0.6f, 0.1f};
        
    }
    
    
    @Override
    public void tick(float theta){
        
        super.tick(theta);
        
        rotation = (direction[0] > 0f ? -1 : direction[0] < 0 ? 1f : 0f) * 0.4f + 0.6f*rotation;
        
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
}
