package com.psywerx.dh;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
    public static boolean enabled = false;
    public static enum sound {COIN, HIT, SELECT, MAD};
    
    static MediaPlayer background = MediaPlayer.create(MyRenderer.context, R.raw.dh);
    static SoundPool sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    public static void load() {
	background.setLooping(true);
    }
    public static void reset() {
	background.seekTo(0);
    }
    public static void pauseGame() {
	if(background != null){
	    background.pause();
	}
    }
    public static void resumeGame() {
	if (background != null && Game.sound) {
	    try {
		background.prepare();
		background.start();
	    } catch (IllegalStateException e) {
	    } catch (IOException e) {
	    }
	}
    }
    
    
}
