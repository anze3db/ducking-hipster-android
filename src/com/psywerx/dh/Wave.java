package com.psywerx.dh;


public class Wave {
    //   pos1           pos2      ... pos7
    // { {speed, type}, {speed,type}, ... }
    WavePosition[] positions = new WavePosition[7];
    static String[] end = new String[]{"8", "x"};
    
    public Wave() {
    }
    public Wave(char t){
	((MainActivity)MyRenderer.context).unlockAchievement(R.string.ach_ultimate);
        for (int j = 0; j < positions.length; j++) {
            positions[j] = new WavePosition(end);
        }
    }
}

class WavePosition{

    float speed;
    char type;
    float direction;
    
    WavePosition(String[] params){
        speed = params[1].equals("_") ? 0 : Float.parseFloat(params[0]);
        type = params[1].toCharArray()[0];
        if(params.length > 2){
            direction = Float.parseFloat(params[2])/150;
        }
    }
}