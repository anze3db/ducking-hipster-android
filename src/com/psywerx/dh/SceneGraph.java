package com.psywerx.dh;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SceneGraph {
    static List<Drawable> activeObjects = Collections.synchronizedList(new LinkedList<Drawable>());

    static void draw() {

        for (Drawable d : activeObjects) {
            d.draw();
        }
    }

    static void tick(float theta) {
        Iterator<Drawable> dws = activeObjects.iterator();
        while (dws.hasNext()) {
            Drawable d = dws.next();
            d.tick(theta);
            if(d.removeMe){
                L.i("REMOVING");
                dws.remove();
            }
        }
        Collections.sort(activeObjects, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable d1, Drawable d2) {
                return (d1.position[2] - d2.position[2]) < 0 ? 1 : -1;
            }
        });
    }
}
