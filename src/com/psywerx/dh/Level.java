package com.psywerx.dh;

import java.io.InputStream;
import java.util.LinkedList;

import android.content.res.Resources;
import android.util.Log;

class Levels {

    int[] loadLevels = { R.raw.level1 };
    Level[] levels = new Level[loadLevels.length];

    public Levels() {
        for (int i = 0; i < levels.length; i++) {
            try {
                Resources res = MyRenderer.context.getResources();
                InputStream in_s = res.openRawResource(loadLevels[i]);
                byte[] b = new byte[in_s.available()];
                in_s.read(b);
                String[] lines = new String(b).split("\n");
                Level lvl = new Level();
                for (String l : lines) {
                    Wave w = new Wave();
                    String[] positions = l.split(" ");
                    for (int j = 0; j < positions.length; j++) {
                        w.positions[j] = new WavePosition(positions[j].split(","));
                    }
                    lvl.waves.add(w);
                }
                levels[i] = lvl;

            } catch (Exception e) {
                // e.printStackTrace();
                Log.e("smotko", "Level could not be read.");
                e.printStackTrace();
            }
        }
    }
}

class Level {

    void reset(){
        currentWave = 0;
        acum = 0;
    }
    
    final float WAVE_PERIOD = 1100;
    
    LinkedList<Wave> waves = new LinkedList<Wave>();
    int currentWave = 0;
    float acum = 0;
    
    
    void tick(float theta) {
        acum += theta;
        if(acum > WAVE_PERIOD - 100 * Game.levelHints.progress){
            acum = 0;
            nextWave();
        }
    }


    void nextWave() {
        Wave w;
        if(waves.size() == currentWave)
            w = new Wave('x');
        else {
            w = waves.get(currentWave++);
        }
        int x = (int) (Math.random()*w.positions.length);
        for(int i = 0; i < w.positions.length; i++){
            switch(w.positions[i].type){
            case 'x':
                if(i == x) break;
                Enemy ex = Game.preloadedEnemies.pop();
                ex.reset();
                ex.speed[1] = w.positions[i].speed/500f + (Game.levelHints.progress/2f)/100f;
                ex.position[0] = (i-3)/2.9f;
                ex.move(0, 0, -1.0f*currentWave/10000.0f+(float)Game.rand.nextDouble()/100000.0f);
                SceneGraph.activeObjects.add(ex);
                break;
            case 'c':
        	if(Math.random() < 0.2) break;
                Item item = Game.preloadedItems.pop();
                item.reset();
                item.speed[1] = w.positions[i].speed/500f + (Game.levelHints.progress/2f)/100f;
                item.position[0] = (i-3)/2.9f;               
                item.move(0, 0, (float)Game.rand.nextDouble()/100.0f);
                SceneGraph.activeObjects.add(item);
                break;
            case '?':
                if(Math.random() < 0.5) break;
            case 'e':
                Enemy e = Game.preloadedEnemies.pop();
                e.reset();
                e.speed[1] = w.positions[i].speed/500f + (Game.levelHints.progress/2f)/100f;
                e.move(0, 0, (float)Game.rand.nextDouble()/100.0f);
                e.direction = w.positions[i].direction;e.position[0] = (i-3)/2.9f;
                SceneGraph.activeObjects.add(e);
                break;
            case 'h':
                Game.levelHints.nextHint();
                Game.player1.nextSkin();
                Game.num_changed++;
                
                if(Game.num_changed == 1){
          	  ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_scarf);
          	  if(Game.num_picked_up == 0){
          	      ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_toocool);
          	  }
                }
                if(Game.num_changed == 2){
          	  ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_hat);
          	  
                }
                if(Game.num_changed == 3){
          	  ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_glasses);
          	  
                }
                if(Game.num_changed == 4){
          	  ((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_night);
          	  
                }
                break;
            case 'l':
              Game.level += 1;
              Game.level %= Game.program.textures.length;
              break;
            }
        }
        
    }
}