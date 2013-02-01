package com.psywerx.dh;


public class Wave {
    //   pos1           pos2      ... pos7
    // { {speed, type}, {speed,type}, ... }
    WavePosition[] positions = new WavePosition[7];
    
    public Wave() {
    }
}

class WavePosition{

    float speed;
    char type;
    
    WavePosition(String[] params){
        speed = params[1].equals("_") ? 0 : Float.parseFloat(params[0]);
        type = params[1].toCharArray()[0];
    }
}