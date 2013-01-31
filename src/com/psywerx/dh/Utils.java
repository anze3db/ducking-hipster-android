package com.psywerx.dh;

public class Utils {
    public static float[] add(float[] a, float[] b) {
        if (a.length != b.length)
            throw new IllegalArgumentException("Arguments should be of same size");
        float[] c = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static float[] add(float[] a, float x, float y, float z) {
        float[] b = new float[] { x, y, z };
        return Utils.add(a, b);
    }

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
