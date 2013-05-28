package com.psywerx.dh;

public class PauseButton extends Button {
    PauseButton() {
        s.size = new float[] { 0.15f, 0.1f, 1 };
        s.position = new float[]{-0.8f, 0.9f, -0.5f};
        s.texture.sprite = new int[] { 42, 53 };
        s.texture.startSprite = new int[] { 42, 53 };
        s.texture.size = new int[] { 3, 3 };
    }
}
