package game;

import helper.Vector;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

enum CellState {
    EMPTY,
    RED,
    YELLOW
}

public class MainEventListener implements GLEventListener {
    CellState[][] state = new CellState[6][7];

    final double CELL_SIZE = 80;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 1280, 720);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 1280.0, 0.0, 720.0, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        for (int y = 0; y < state.length; y++) {
            final CellState[] row = state[y];
            for (int x = 0; x < row.length; x++) {
                drawRect(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, y * CELL_SIZE + 1.5 * CELL_SIZE), CELL_SIZE);
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    public void drawRect(GL gl, Vector pos, double size) {
        gl.glPushMatrix();
        gl.glTranslated(pos.x(), pos.y(), 0);
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2d(0, 0);
            gl.glVertex2d(size, 0);
            gl.glVertex2d(size, size);
            gl.glVertex2d(0, size);
        gl.glEnd();
        gl.glPopMatrix();
    }
}
