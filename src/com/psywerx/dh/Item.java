package com.psywerx.dh;


public class Item extends Enemy {

    private boolean pickedUp = false;
    private float wubbleScale = 0.001f;
    enum TYPE { COIN, GLOVES, MAGNET, EXTRA_COINS };
    private TYPE type = TYPE.COIN;
    private TYPE[] specials = { TYPE.GLOVES, TYPE.MAGNET, TYPE.EXTRA_COINS };
    private static float[] large = {3f, 0.22f, 0.25f };
    private static float[] normal = { 0.12f, 0.12f, 0.25f };

    
    public Item(){
	super();
	canReplace = false;
    }
    
    @Override
    public void reset() {

	speed[1] = 0.01f;
	position[2] = 1;
	position[1] = -2f;
	removeMe = false;

	timeDead = 0f;
	score = false;

	s.texture.enabled = true;
	s.texture.sprite = new int[] { 9, 10 };
	s.texture.startSprite = new int[] { 9, 10 };
	s.texture.size = new int[] { 2, 2 };
	s.texture.anim = new int[] { 1, 1 };
	s.texture.animSpeed = 0f;
	s.texture.spriteOffset = 0;
	s.size = new float[] { 0.1f, 0.1f, 0.25f };

	pickedUp = false;
	bb.size = normal;
	
	type = TYPE.COIN;

    }

    protected void setSpecial(){
	s.texture.sprite = new int[] { 9, 8 };
	s.texture.startSprite = new int[] { 9, 8 };
	type = specials[Game.rand.nextInt(specials.length)];
    }
    
    public void removeMe(){
	removeMe = true;
	Game.preloadedItems.push(this);
    }
    
    @Override
    public void tick(float theta) {
	if (position[1] > 20) {
	    removeMe();
	}
	if(Game.player1.powerupMagnet){
	    bb.size = large;
	}
	else{
	    bb.size = normal;
	}
	if (s.size[1] < 0.09f) {
	    wubbleScale = Math.abs(wubbleScale);
	} else if (s.size[1] > 0.11f) {
	    wubbleScale = -Math.abs(wubbleScale);
	}
	s.size[0] += wubbleScale / 10;
	s.size[1] += wubbleScale;

	if (Utils.areColliding(this.bb, Game.player1.bb) && !pickedUp) {
	    pickedUp = true;
	    Sound.play(Sound.coin);
	    pickedUp();
	}
	if (pickedUp) {
	    position[2] -= theta * 0.00212f;
	    position[1] += theta * 0.0005f;
	    position[0] = 0.9f * position[0];
	    s.texture.sprite[0] = 11;
	    s.texture.startSprite[0] = 11;
	} else if (bb.position[1] - bb.size[1]/2 > Game.player1.sPosition) {
	    position[2] += speed[1] * theta * 0.112f;
	    position[1] -= speed[1] * 0.1f;
	}
	s.texture.update(theta);

	this.move(0, speed[1] * theta * 0.05f, speed[1] * theta * 0.001f);

    }
    
    private void pickedUp(){
	switch(type){
	case COIN:
	    Game.top.increaseScore(10);
	    ((MainActivity) MyRenderer.context)
	    .unlockAchievement(R.string.ach_bitcoin);
	    ((MainActivity) MyRenderer.context).incrementAchievement(
		    R.string.ach_bitcoin10, 1);
	    ((MainActivity) MyRenderer.context).incrementAchievement(
		    R.string.ach_maniac, 1);
	    ((MainActivity) MyRenderer.context).incrementAchievement(
		    R.string.ach_master, 1);
	    Game.num_picked_up++;
	    break;
	case GLOVES:
	    Game.player1.powerup(type);
	    break;
	case MAGNET:
	    Game.player1.powerup(type);
	    break;
	case EXTRA_COINS:
	    
	    for(int i = 0; i < SceneGraph.activeObjects.size(); i++){
		Drawable object = SceneGraph.activeObjects.get(i);
		if(object instanceof Enemy){
		    Enemy e = (Enemy)object;
		    if(!e.canReplace) continue;
		    Item item = Game.preloadedItems.pop();
		    item.reset();
		    item.speed = e.speed.clone();
		    item.position = e.position.clone();
		    SceneGraph.toAdd.add(item);
		    Game.powerupCoin = true;
		    e.removeMe();
		}
	    }
	    break;
	}
    }
}
