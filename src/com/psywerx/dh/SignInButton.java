package com.psywerx.dh;

public class SignInButton extends Button {
    SignInButton() {
        s.size = new float[] { 0.7f, 0.7f * (3f / 31f), 1 };
        s.position = new float[] { 0.0f, -0.7f, -0.6f };
        s.texture.sprite = new int[] { 0, 59 };
        s.texture.startSprite = new int[] { 0, 59 };
        s.texture.size = new int[] { 31, 4 };
    }
}
