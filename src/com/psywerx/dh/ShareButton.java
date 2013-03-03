package com.psywerx.dh;

public class ShareButton extends Button {
    ShareButton() {
        s.size = new float[] { 0.5f, 0.5f * (3f / 14f), 1 };
        s.position = new float[] { 0.0f, -0.35f, -0.6f };
        s.texture.sprite = new int[] { 26, 53 };
        s.texture.startSprite = new int[] { 26, 53 };
        s.texture.size = new int[] { 14, 3 };
    }

}
