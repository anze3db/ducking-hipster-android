package com.psywerx.dh;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SceneGraph {
    static List<Drawable> activeObjects = Collections.synchronizedList(new LinkedList<Drawable>());

    static void draw() {
        Collections.sort(activeObjects, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable d1, Drawable d2) {
                return (d1.position[2] - d2.position[2]) < 0 ? 1 : -1;
            }
        });
        for (Drawable d : activeObjects) {
            d.draw();
        }
    }

    static void tick(float theta) {
        for (Drawable d : activeObjects) {
            d.tick(theta);
        }
    }
}
