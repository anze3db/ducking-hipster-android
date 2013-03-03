package com.psywerx.dh;

public class Utils {

    public static boolean areColliding(PersonSprite d1, PersonSprite d2) {
        for (int i = 0; i < 2; ++i) {
            if (d1.bb.position[i] + (d1.bb.size[i]) < d2.bb.position[i] - (d2.bb.size[i]) ||
                d1.bb.position[i] - (d1.bb.size[i]) > d2.bb.position[i] + (d2.bb.size[i])) {
                return false;
            }
        }
        return true;
    }
}
