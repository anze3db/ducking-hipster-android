package com.psywerx.dh;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SceneGraph {
    public static LinkedList<Drawable> activeObjects = new LinkedList<Drawable>();

    public static void draw() {
        Collections.sort(activeObjects, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable d1, Drawable d2){
                return  (d1.position[2] - d2.position[2]) < 0 ? 1 : -1;
            }
        });
        for (Drawable d : activeObjects) {
            d.draw();
        }
    }

    public static void tick(float theta) {
        for (Drawable d : activeObjects) {
            d.tick(theta);
        }
    }
}
