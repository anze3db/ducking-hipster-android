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
        float[] b = new float[]{x,y,z};
        return Utils.add(a, b);
    }
}
