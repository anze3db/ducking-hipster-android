package com.psywerx.dh;

public class PlayButton extends Button {
    PlayButton() {
        s.size = new float[] { 0.2f, 0.2f * (3f / 6f), 1 };
        s.position = new float[] { 0.0f, -0.25f, -0.6f };
        s.texture.sprite = new int[] { 0, 53 };
        s.texture.startSprite = new int[] { 0, 53 };
        s.texture.size = new int[] { 6, 3 };
    }
}
