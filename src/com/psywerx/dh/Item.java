package com.psywerx.dh;

public class Item extends Enemy {
  
  private boolean pickedUp = false;
  
  @Override
  public void reset() {
    
    
    speed[1] = 0.01f;
    resize(0.15f);
    position[2] = 1;
    position[1] = -2f;
    removeMe = false;
    
    timeDead = 0f;
    score = false;
    
    
    
    
    s.texture.enabled = true;
    s.texture.sprite = new int[]{9,10};
    s.texture.startSprite = new int[]{9,10};
    s.texture.size = new int[]{2,2};
    s.texture.anim = new int[]{1,1};
    s.texture.animSpeed = 0f;
    s.texture.spriteOffset = 0;
    s.size = new float[]{0.1f, 0.1f, 0.25f};
  
    pickedUp = false;
    
    bb.size[0] = size[0]*1.2f;
    bb.size[1] = size[1]*1.2f;
    
  }

  @Override
  public void tick(float theta) {
    if (position[1] > 20) {
      // This needs to get moved:
      removeMe = true;
      Game.preloadedItems.push(this);
    }

    if (Utils.areColliding(this, Game.player1) && !pickedUp) {
      if(Game.sound){
	Game.coin.seekTo(0);
	Game.coin.start();
      }
      pickedUp = true;
      Game.top.increaseScore(10);
      ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_bitcoin);
      ((MainActivity)MyRenderer.context).incrementAchievement(R.string.ach_bitcoin10, 1);
    }
    if (pickedUp){
      position[2] -= theta * 0.00212f;
      position[1] += theta * 0.0005f;
      position[0] = 0.9f * position[0];
      s.texture.sprite = new int[]{11,10};
      s.texture.startSprite = new int[]{11,10};
    }
    else if (bb.position[1] + bb.size[1] / 2 > Game.player1.sPosition) {
      position[2] += speed[1] * theta * 0.112f;
      position[1] -= speed[1] * 0.1f;
    }
    col.position[0] = -100f;
    col.position[1] = -100f;
    col.position[2] = 0f;
    s.texture.update(theta);

    this.move(0, speed[1] * theta * 0.05f, speed[1] * theta * 0.001f);

  }
}
