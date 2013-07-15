package com.psywerx.dh;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.example.games.basegameutils.BaseGameActivity;

public class MainActivity extends BaseGameActivity {

    private MyGLSurfaceView mGLView;
    // request codes we use when invoking an external activity
    final int RC_RESOLVE = 5000, RC_UNUSED = 5001;
    public boolean isSignedIn(){
	
	return mHelper.isSignedIn();
    }
    public void login(){
	runOnUiThread(new Runnable() {
	    
	    @Override
	    public void run() {
		if(mHelper.isSignedIn()){
		    signOut();
		    Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();
		    
		}
		else{
		    beginUserInitiatedSignIn();
		}
		
	    }
	});
    }
    public void showAchievements(){
	runOnUiThread(new Runnable() {

	    @Override
	    public void run() {
		startActivityForResult(getGamesClient().getAchievementsIntent(), RC_UNUSED);
	    }

	});
    }
    public void unlockAchievement(final int id){
	runOnUiThread(new Runnable() {

	    @Override
	    public void run() {
		if (isSignedIn()) {
		    getGamesClient().unlockAchievement(getString(id));
		}
	    }

	});
    }
    public void incrementAchievement(final int id, final int amount){
	runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
		if (isSignedIn()) {
		    getGamesClient().incrementAchievement(getString(id), amount);
		}
	    }
	});
    }
    
    
    public void showScores() {
	runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
		startActivityForResult(getGamesClient().getLeaderboardIntent(getString(R.string.leaderboard)), RC_UNUSED);
	    }

	});
    }

    public void newScore(final int score) {
	runOnUiThread(new Runnable() {

	    @Override
	    public void run() {
		if (isSignedIn()) {

		    getGamesClient().submitScore(
			    getString(R.string.leaderboard), score);

		}
	    }
	});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	
	super.onCreate(savedInstanceState);

	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	mGLView = new MyGLSurfaceView(this);

	setContentView(mGLView);

	EasyTracker.getInstance().setContext(this);
	
    }
    private void pauseGame() {
	Sound.pauseGame();

	if (Game.state != 'G')
	    return;
	Game.prevState = Game.state;
	
    }

    private void resumeGame() {
	MyRenderer.prev = System.currentTimeMillis();
	Sound.resumeGame();
	
    }

    @Override
    protected void onStop() {
	super.onStop();

	pauseGame();
	EasyTracker.getInstance().activityStop(this);

    }

    @Override
    protected void onPause() {
	
	super.onPause();

	pauseGame();
	EasyTracker.getInstance().activityStop(this);
	mGLView.onPause();
    }

    @Override
    protected void onResume() {
	super.onResume();
	mGLView.onResume();
	resumeGame();
	EasyTracker.getInstance().activityStart(this);
    }

    @Override
    protected void onStart() {
	super.onStart();
	resumeGame();
	EasyTracker.getInstance().activityStart(this);
    }

    @Override
    public void onSignInFailed() {
    }

    @Override
    public void onSignInSucceeded() {
    }
}

class MyGLSurfaceView extends GLSurfaceView {

    private Context c;

    public MyGLSurfaceView(Context context) {
	super(context);
	c = context;
	// Set the Renderer for drawing on the GLSurfaceView
	setEGLContextClientVersion(2);
	setRenderer(new MyRenderer(context));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
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
		((MainActivity)c).showScores();
	    }
	    else if(Game.achievementsButton.onUp(position, positionY)){
		((MainActivity)c).showAchievements();
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
	    else if(Game.continueButton.onUp(position, positionY)){
		Game.state = 'G';
		Sound.resumeGame();
	    }
	    else if(Game.signInButton.onUp(position, positionY)){
		((MainActivity)c).login();
	    }

	    Game.moving = false;
	    Game.position = 0;
	    Game.player1.direction[0] = 0f;
	}
	return true;
    }
}

class MyRenderer implements GLSurfaceView.Renderer {

    static protected Context context;
    static protected MainActivity ma;
    static public long prev;

    public MyRenderer(Context context) {
	MyRenderer.context = context;
	MyRenderer.ma = (MainActivity)context;
    }

    @Override
    public void onDrawFrame(GL10 unused) {
	
	Game.isSignedIn = MyRenderer.ma.isSignedIn();

	long now = System.currentTimeMillis();

	Game.draw();
	Game.tick((float) (now - prev));
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