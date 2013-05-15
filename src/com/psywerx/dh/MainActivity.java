package com.psywerx.dh;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

//import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity {

    private MyGLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mGLView = new MyGLSurfaceView(this);
        
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(mGLView);
        
//        EasyTracker.getInstance().setContext(this);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        
//        EasyTracker.getInstance().activityStop(this);
        
        Game.prevState = Game.state;
        Game.state = 'A';
        if(Game.mp != null)
            Game.mp.pause();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        
//        EasyTracker.getInstance().activityStart(this);
        
        Game.state = Game.prevState;
        if(Game.mp != null && Game.sound){
            Game.mp.start();
        }
    }
}

class MyGLSurfaceView extends GLSurfaceView {
    
    private Context c;

    public MyGLSurfaceView(Context context){
        super(context);
        c = context;
        // Set the Renderer for drawing on the GLSurfaceView
        setEGLContextClientVersion(2);
        setRenderer(new MyRenderer(context));
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        
        float x = e.getX();
        float y = e.getY();
        float position = (x * 2f / Game.WIDTH - 1.0f);
        float positionY =1f-(y*2/(float)Game.HEIGHT);
        

        if(e.getPointerCount() > 1){
          Game.player1.direction[0] = 0;
        }
        else{
          float pointerPos = e.getX();
          pointerPos = (pointerPos * 2f / Game.WIDTH - 1.0f);
          Game.player1.direction[0] = pointerPos > 0 ? 1 : -1;
        }
        Game.position = Game.player1.position[0];
        
        
        switch(e.getAction()){

        case MotionEvent.ACTION_DOWN:
            if(Game.state == 'M'){
                Game.playButton.onDown(position, positionY);
                Game.soundButton.onDown(position, positionY);
            }
            if(Game.state == 'E'){
                Game.restartButton.onDown(position, positionY);
                Game.shareButton.onDown(position, positionY);
            }
            else if(Game.state == 'P'){
                Game.continueButton.onDown(position, positionY);
                Game.soundButton.onDown(position, positionY);
            }
            else if(Game.state == 'G'){
              Game.pauseButton.onDown(position, positionY);
            }
            if(Game.pauseButton.canTrigger) break;
            Game.moving = true;
            Game.position = position;
            
            Game.player1.direction[0] = position;
            
            
            break;
            
        case MotionEvent.ACTION_MOVE:
            if(Game.pauseButton.canTrigger) break;
            break;
        case MotionEvent.ACTION_UP:
            if(Game.state == 'G' && Game.pauseButton.onUp(position, positionY))  Game.state = 'P';
            if(Game.state == 'M'){
                if(Game.playButton.onUp(position, positionY)) Game.reset();
                if(Game.soundButton.onUp(position, positionY))  toggleSound();
            }
            else if(Game.state == 'P'){
                if(Game.continueButton.onUp(position, positionY))  Game.state = 'G';
                if(Game.soundButton.onUp(position, positionY))  toggleSound();
            }
            else if(Game.state == 'E'){
                if(Game.restartButton.onUp(position, positionY))  Game.reset();
                if(Game.shareButton.onUp(position, positionY)) share();
            }
            Game.moving = false;
            Game.position = 0;
            Game.player1.direction[0] = 0f;
        }
        return true;
    }
    private void toggleSound() {
        Game.sound = !Game.sound;
        
    }
    private void share() {
        String message = String.format("I've reached level %d with a score of %d in #duckinghipster for Android!", Game.levelHints.progress+1, Game.top.score);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        c.startActivity(Intent.createChooser(share, "Share your score"));
    }
    
    
    
    
}

class MyRenderer implements GLSurfaceView.Renderer{

    static protected Context context;
    private long prev;

    public MyRenderer(Context context) {
        MyRenderer.context = context;
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        
        long now = System.currentTimeMillis();
        
        Game.draw();
        Game.tick((float)(now - prev));
        prev = now;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Game.HEIGHT = height;
        Game.WIDTH = width;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GlProgram program = new GlProgram(context);
        Game.create(program);
        prev = System.currentTimeMillis();
    }
    
    
    
}