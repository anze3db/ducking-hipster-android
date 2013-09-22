package com.psywerx.dh;

import android.view.MotionEvent;

public class Input {
    public static boolean update(MotionEvent e, MainActivity a){
	if(!Game.gameCreated) return true;
	float x = e.getX();
	float y = e.getY();
	float position = (x * 2f / Game.WIDTH - 1.0f);
	float positionY = 1f - (y * 2 / (float) Game.HEIGHT);

	if (e.getPointerCount() > 1) {
	    Game.player1.direction[0] = 0;
	} else {
	    float pointerPos = e.getX();
	    pointerPos = (pointerPos * 2f / Game.WIDTH - 1.0f);
	    int newDirection = pointerPos > 0 ? 1 : -1;
	    Game.player1.direction[0] = newDirection;
	}
	Game.position = Game.player1.position[0];

	switch (e.getAction()) {

	case MotionEvent.ACTION_DOWN:
	    if (Game.state == 'M') {
		Game.playButton.onDown(position, positionY);
	    }
	    if(Game.state == 'P'){
		Game.continueButton.onDown(position, positionY);
	    }
	    if(Game.state == 'E'){
		Game.restartButton.onDown(position, positionY);
	    }
	    if (Game.state != 'G') {
		Game.soundButton.onDown(position, positionY);
		Game.shareButton.onDown(position, positionY);
		Game.achievementsButton.onDown(position, positionY);
		Game.signInButton.onDown(position, positionY);
		Game.donateButton.onDown(position, positionY);
	    }
	    if (Game.state == 'G') {
		Game.pauseButton.onDown(position, positionY);
	    }
	    if (Game.pauseButton.canTrigger)
		break;
	    Game.moving = true;
	    Game.position = position;
	    break;

	case MotionEvent.ACTION_MOVE:
	    if (Game.pauseButton.canTrigger)
		break;
	    break;
	case MotionEvent.ACTION_UP:
	    if (Game.state == 'G' && Game.pauseButton.onUp(position, positionY)){
		Game.state = 'P';
		Sound.pauseGame();
	    }
	    else if(Game.shareButton.onUp(position, positionY)){
		a.showScores();
	    }
	    else if(Game.achievementsButton.onUp(position, positionY)){
		a.showAchievements();
	    }
	    else if(Game.soundButton.onUp(position, positionY)){
		
		Sound.toggleSound();
	    }
	    else if(Game.playButton.onUp(position, positionY)){
		Game.reset();
		Sound.resumeGame();
	    }
	    else if(Game.restartButton.onUp(position, positionY)){
		Game.reset();
		Sound.resumeGame();
	    }
	    else if(Game.donateButton.onUp(position, positionY)){
		a.showDonation();
	    }
	    else if(Game.continueButton.onUp(position, positionY)){
		Game.state = 'G';
		Sound.resumeGame();
	    }
	    else if(Game.signInButton.onUp(position, positionY)){
		a.login();
	    }

	    Game.moving = false;
	    Game.position = 0;
	    Game.player1.direction[0] = 0f;
	}
	return true;
    }
}
