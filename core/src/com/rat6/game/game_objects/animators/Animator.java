package com.rat6.game.game_objects.animators;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Animator {
    TextureRegion getKeyFrame(float stateTime);
}
