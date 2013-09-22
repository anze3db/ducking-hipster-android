package com.psywerx.dh;

public class DonateButton extends Button {
    DonateButton() {
        s.size = new float[] { 0.35f, 0.35f * (3f / 9f), 1 };
        s.position = new float[] { 0.2f, -0.45f, -0.6f };
        s.texture.sprite = new int[] { 17, 44 };
        s.texture.startSprite = new int[] { 17, 44 };
        s.texture.size = new int[] { 9, 3 };
    }
    @Override
    public void onDown(float x, float y) {
        if (x > s.position[0] - s.size[0] && x < s.position[0] + s.size[0] && y > s.position[1] - s.size[1]
                && y < s.position[1] + s.size[1]) {
            s.texture.startSprite[1] = 47;
            s.texture.updateSprite();
            canTrigger = true;
        }
        else{
            canTrigger = false;
        }

    }
    @Override
    public boolean onUp(float x, float y) {
        s.texture.startSprite[1] = 44;
        s.texture.updateSprite();
        if (x > s.position[0] - s.size[0] && x < s.position[0] + s.size[0] && y > s.position[1] - s.size[1]
                && y < s.position[1] + s.size[1]) {
            if(canTrigger){
        	Sound.play(Sound.select);
            }
            boolean ret = canTrigger;
            canTrigger = false;
            return ret;
        }
        else{
          canTrigger = false;
        }
        return false;
    }
}
