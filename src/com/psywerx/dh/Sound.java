package com.psywerx.dh;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
    public static boolean enabled = true;

    static int coin;
    static int hit;

    static MediaPlayer background = MediaPlayer.create(MyRenderer.context,
	    R.raw.dh);
    static SoundPool sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

    static int select;

    static int mad;


    public static void load() {
	background.setLooping(true);
	coin = sounds.load(MyRenderer.context, R.raw.coin, 1);
	hit = sounds.load(MyRenderer.context, R.raw.hit, 5);
	select = sounds.load(MyRenderer.context, R.raw.select, 5);
	mad = sounds.load(MyRenderer.context, R.raw.mad, 5);
    }

    public static void reset() {
	background.seekTo(0);
    }

    public static void pauseGame() {
	background.pause();
    }

    public static void resumeGame() {
	if(enabled && Game.state == 'G'){
	    background.start();
	}
    }
    
    public static void toggleSound() {
	Sound.enabled = !Sound.enabled;
    }
    public static void play(int sound){
	if(enabled)
	    sounds.play(sound, 1.0f, 1.0f, 0, 0, 1f);
    }

}
