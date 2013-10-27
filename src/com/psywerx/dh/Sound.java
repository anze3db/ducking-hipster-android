package com.psywerx.dh;

import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {
    public static boolean enabled;

    static int coin;
    static int hit;
    static int select;
    static int mad;

    private static MediaPlayer bg;
    private static SoundPool sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    
    

    public static void load() {
	coin = sounds.load(MyRenderer.context, R.raw.coin, 1);
	hit = sounds.load(MyRenderer.context, R.raw.hit, 5);
	select = sounds.load(MyRenderer.context, R.raw.select, 5);
	mad = sounds.load(MyRenderer.context, R.raw.mad, 5);
	bg = MediaPlayer.create(MyRenderer.context,
		    R.raw.dh);
	
	bg.setAudioStreamType(AudioManager.STREAM_MUSIC);
	bg.setLooping(true);
	enabled = Game.settings.getBoolean("sound", true);
    }

    public static void pauseGame() {
	sounds.autoPause();
	if(bg != null && bg.isPlaying())
	    bg.pause();
    }

    public static void resumeGame() {
	sounds.autoResume();
	if(Game.state == 'G' && enabled){
	    if(bg == null) bg = MediaPlayer.create(MyRenderer.context, R.raw.dh);
	    bg.start();
	}
    }
    
    public static void toggleSound() {
	Editor editor = Game.settings.edit();
	editor.putBoolean("sound", !Sound.enabled);
	editor.commit();
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
	if(Sound.enabled){
	    bg.seekTo(0);
	}
    }

}
