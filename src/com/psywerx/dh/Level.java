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
    
    final float WAVE_PERIOD = 1000;
    
    LinkedList<Wave> waves = new LinkedList<Wave>();
    int currentWave = 0;
    float acum = 0;
    
    
    void tick(float theta) {
        acum += theta;
        if(acum > WAVE_PERIOD){
            acum = 0;
            nextWave();
        }
    }


    void nextWave() {
        if(waves.size() == currentWave)
            return;
        Wave w = waves.get(currentWave++);
        for(int i = 0; i < w.positions.length; i++){
            switch(w.positions[i].type){
            case 'e':
                Enemy e = Game.preloadedEnemies.pop();
                e.reset();
                e.speed[1] = w.positions[i].speed/500f;
                e.position[0] = (i-3)/2.9f;
                e.move(0, 0, -1.0f*currentWave/10000.0f+(float)Game.rand.nextDouble()/100000.0f);
                SceneGraph.activeObjects.add(e);
                break;
            case 'h':
                Game.player1.nextSkin();
                break;
            }
        }
        
    }
}