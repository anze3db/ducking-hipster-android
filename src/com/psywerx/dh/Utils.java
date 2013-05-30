package com.psywerx.dh;

public class Utils {

    public static boolean areColliding(Square d1, Square d2) {
        for (int i = 0; i < 2; ++i) {
            
            if (d1.position[i] + (d1.size[i]) < d2.position[i] - (d2.size[i]) ||
                d1.position[i] - (d1.size[i]) > d2.position[i] + (d2.size[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean areClose(PersonSprite d1, PersonSprite d2){
	
	
	return false;
    }
}
