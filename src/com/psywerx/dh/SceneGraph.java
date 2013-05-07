package com.psywerx.dh;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SceneGraph {
    static List<Drawable> activeObjects = new LinkedList<Drawable>();
    private static Iterator<Drawable> dws;
    private static Drawable d;

    static void draw() {
        for (int i = 0; i < activeObjects.size(); ++i) {
            activeObjects.get(i).draw();
        }
    }

    static void tick(float theta) {
        dws = activeObjects.iterator();
        while (dws.hasNext()) {
            d = dws.next();
            d.tick(theta);
            if(d.removeMe){
                dws.remove();
            }
        }
        Collections.sort(activeObjects, new Comparator<Drawable>() {
            @Override
            public int compare(Drawable d1, Drawable d2) {
                if((d1 instanceof Enemy || d1 instanceof Item) && 
                   (d2 instanceof Enemy || d2 instanceof Item) && 
                    d1.position[1] == d2.position[1] && d1.position[0] == d2.position[0]){
                  L.e("OMG THEY ARE THE SAME" + d1.toString());
                }
                return (d1.position[1] <= d2.position[1]) ? 1 : -1;
            }
        });
    }
}
