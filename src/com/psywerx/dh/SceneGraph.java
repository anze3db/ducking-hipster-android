package com.psywerx.dh;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SceneGraph {
    static List<Drawable> activeObjects = new LinkedList<Drawable>();
    static List<Drawable> behindObjects = new LinkedList<Drawable>();
    static LinkedList<Drawable> toAdd = new LinkedList<Drawable>();
    private static Iterator<Drawable> dws;
    private static Drawable d;

    static void draw() {
	try {

	    for (int i = 0; i < behindObjects.size(); i++) {
		behindObjects.get(i).draw();
	    }
	    Game.player1.draw();
	    for (int i = 0; i < activeObjects.size(); ++i) {
		activeObjects.get(i).draw();
	    }
	} catch (Exception e) {

	}
    }

    static void tick(float theta) {
	Game.player1.tick(theta);
	activeObjects.addAll(toAdd);
	toAdd.clear();
	dws = activeObjects.iterator();
	while (dws.hasNext()) {
	    d = dws.next();
	    d.tick(theta);
	    if (d.removeMe) {
		dws.remove();
	    }
	    else if(d.position[1] > Game.player1.position[1]){
		dws.remove();
		behindObjects.add(d);
	    }
	}
	dws = behindObjects.iterator();
	while (dws.hasNext()) {
	    d = dws.next();
	    d.tick(theta);
	    if (d.removeMe) {
		dws.remove();
	    }
	}
    }
}
