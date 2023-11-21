package com.rat6.game.font;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * The Font class implemented in LibGDX.
 * The reason I use it that way is because I didn't have much time,
 * so I did it as fast as I could.
 * */
public class Font {
    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];

    /**
     * @glyphsPerRow говорит о том, сколько глифов будет в строке,
     * @glyphWidth размер одного глифа
     * @glyphHeight размер одного глифа
     * */
    public Font(Texture texture,
                int offsetX, int offsetY,
                int glyphsPerRow, int glyphWidth, int glyphHeight) {
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        for(int i = 0; i < 96; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if(x == offsetX + glyphsPerRow * glyphWidth) {
                x = offsetX;
                y += glyphHeight;
            }
        }
    }

    public void drawText(SpriteBatch batch, String text, float x, float y) {
        drawText(batch, text, x, y, 1f);
    }

    public void drawText(SpriteBatch batch, String text, float x, float y, float scale) {
        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;
            TextureRegion glyph = glyphs[c];
            batch.draw(glyph, x + glyphWidth * scale * i, y, glyphWidth * scale , glyphHeight * scale);
        }
    }


    /**
     * Text in the center of the Rectangle
     * */
    public void drawText(SpriteBatch batch, String text, Rectangle rect) {
        int len = text.length();
        float totalWidth = glyphWidth * len;
        float startX = rect.x + (rect.width - totalWidth) / 2;
        float startY = rect.y + (rect.height - glyphHeight) / 2;

        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;
            TextureRegion glyph = glyphs[c];
            batch.draw(glyph, startX + glyphWidth * i, startY, glyphWidth, glyphHeight);
        }
    }



}