package com.psywerx.dh;

public class RestartButton extends Button {
    RestartButton() {
        s.size = new float[] { 0.35f, 0.35f * (3f / 9f), 1 };
        s.position = new float[] { 0.0f, -0.1f, -0.6f };
        s.texture.sprite = new int[] { 17, 53 };
        s.texture.startSprite = new int[] { 17, 53 };
        s.texture.size = new int[] { 9, 3 };
    }
}
