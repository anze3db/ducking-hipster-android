package com.psywerx.dh;

public class Player extends PersonSprite {

    boolean dead;
    float[] startPosition;
    float sPosition = 0.330f;
    public Square bbClose;
    public boolean powerupGloves = false;
    public boolean powerupMagnet = false;
    private Square gloves;
    private Square magnet;
    private Enemy killer = null;
    public boolean GOD_MODE = false;
    

    public Player(){
        
        position[1] += 0.50; 
        position[0] = -0.5f;
        position[2] = 1.1f;
        startPosition = position.clone();  
        
        gloves = new Square();
        gloves.size = new float[]{0.20f, 0.20f, 0.2f};
        gloves.color = new float[]{0f,0f,0f,1f};
        gloves.texture.enabled = true;
        gloves.texture.sprite = new int[]{9,15};
        gloves.texture.startSprite = gloves.texture.sprite;
        gloves.texture.size   = new int[]{3,2};
        gloves.texture.anim = new int[]{0, 1, 2, 1};
        gloves.position = position.clone();
        gloves.position[2] -= 0.01f;
        gloves.position[1] -= 0.07f;
        
        magnet = new Square();
        magnet.size = new float[]{0.20f, 0.20f, 0.2f};
        magnet.texture.enabled = true;
        magnet.texture.sprite = new int[]{9,18};
        magnet.texture.startSprite = magnet.texture.sprite;
        magnet.texture.size   = new int[]{2,2};
        magnet.texture.anim = new int[]{0};
        magnet.position = position.clone();
        magnet.position[2] -= 0.01f;
        magnet.position[1] -= 0.07f;
        
        
        bbClose = new Square();
        bbClose.color = new float[]{0f,0f,1f,0f};
        bbClose.texture.enabled = false;
        
        bbClose.size[0] = bb.size[0] * 1.1f;
        bbClose.size[1] = bb.size[1] * 5f;

        bbClose.position[0] = bb.position[0];
        bbClose.position[1] = bb.position[1];
        
    }
    
    void resetPlayer(){
	killer = null;
        position = startPosition;
        move(0,0,0); // hack that resets all the other boxes;
        dead = false;
    }
    void nextSkin(){
	if(s.texture.startSprite[1] > 10) return;
        s.texture.startSprite[1] += 3;
    }
    
    @Override
    public void tick(float theta){
        
        super.tick(theta);
        if(powerupGloves){
            powerupGloves = Game.top.powerUpCnt(theta);
            
        }
        if(powerupMagnet){
            powerupMagnet = Game.top.powerUpCnt(theta);
            
        }
        gloves.texture.update(theta);
        magnet.texture.update(theta);
        speed = new float[]{theta*(direction[0]) * 0.3f + 0.7f*speed[0], 0f};
        
        if(direction[0] == 0 && colide == 0) this.speed[0] = 0;
        if(direction[1] == 0 && colide == 0) this.speed[1] = 0;
        if(this.direction[0] != 0 && this.direction[1] != 0 && this.colide == 0){
            this.speed[0] = 0;
            this.speed[1] = 0;
        }
        if(this.position[0] < -1.0 && speed[0] < 0){
            this.speed[0] = 0;
        }
        if(this.position[0] > 1.0 && speed[0] > 0){
            this.speed[0] = 0;
        }

        if(dead){
            // You can't move if you're dead...
            position[1] -= killer.speed[1] * 0.1f;
	    position[2] += killer.speed[1] * theta * 0.112f;
	    move(0, killer.speed[1] * theta * 0.05f, killer.speed[1] * theta * 0.001f);
        }
        else{
            move(speed[0]/400, 0,0);
        }
        
        bbClose.position[0] = bb.position[0];
        bbClose.position[1] = bb.position[1];
        bbClose.position[2] = bb.position[2];
        gloves.position[0] = bb.position[0];
        magnet.position[0] = bb.position[0];
        magnet.position[2] = bb.position[2]-0.0001f;
        magnet.position[1] = bb.position[1]-0.0701f;
    
    }
    
    public void powerup(Item.TYPE type){
	switch(type){
	case GLOVES:
	    if(!dead)
		powerupGloves = true;
	    break;
	case MAGNET:
	    if(!dead)
		powerupMagnet = true;
	    break;
	case COIN:
	    break;
	default:
	    break;
	}
    }
    
    @Override
    public void draw(){
	super.draw();
	if(powerupGloves) gloves.draw();
	if(powerupMagnet) magnet.draw();
//	bb.draw();
//	
//	bbClose.draw();
	
	
    }

    public void kill(Enemy enemy) {
	killer = enemy;
	dead = true;
    }
}
