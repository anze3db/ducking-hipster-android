package com.psywerx.dh;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
    public static boolean enabled = true;

    static int coin;
    static int hit;
    static int select;
    static int mad;

    private static MediaPlayer bg = MediaPlayer.create(MyRenderer.context,
	    R.raw.dh);
    private static SoundPool sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);



    public static void load() {
	coin = sounds.load(MyRenderer.context, R.raw.coin, 1);
	hit = sounds.load(MyRenderer.context, R.raw.hit, 5);
	select = sounds.load(MyRenderer.context, R.raw.select, 5);
	mad = sounds.load(MyRenderer.context, R.raw.mad, 5);
	bg.setAudioStreamType(AudioManager.STREAM_MUSIC);
	bg.setLooping(true);
    }

    public static void pauseGame() {
	sounds.autoPause();
	bg.pause();
    }

    public static void resumeGame() {
	sounds.autoResume();
	if(Game.gameCreated && enabled){
	    L.wtf("STARTED!!!");
	    bg.start();
	}
    }
    
    public static void toggleSound() {
	Sound.enabled = !Sound.enabled;
    }
    public static void play(int sound, int loop){
	if(enabled)
	    sounds.play(sound, 1.0f, 1.0f, 0, loop, 1f);
    }
    
    public static void play(int sound){
	play(sound, 0);
    }

    public static void gameEnd() {
	bg.pause();
	bg.seekTo(0);
    }

}
