package game;

import helper.Vector;

import javax.media.opengl.GL;

public class Renderer {
    public static void drawTexture(GL gl, int texture, Vector size) {
        final float sw = (float) size.getX();
        final float sh = (float) size.getY();

        gl.glEnable(GL.GL_BLEND);    // Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture);

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0, 0, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(sw, 0, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(sw, sh, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0, sh, -1.0f);
        gl.glEnd();
        gl.glDisable(GL.GL_BLEND);
    }

    public static void drawTexture(GL gl, int texture, Vector size, Vector pos) {
        gl.glPushMatrix();
        gl.glTranslated(pos.getX(), pos.getY(), 0);
        drawTexture(gl, texture, size);
        gl.glPopMatrix();
    }
}
