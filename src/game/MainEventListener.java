package game;

import helper.Vector;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

enum CellState {
    EMPTY,
    RED,
    YELLOW
}


public class MainEventListener implements GLEventListener, MouseMotionListener, MouseListener {
    CellState[][] state = new CellState[6][7];

    Optional<Integer> hoveredOnColumn = Optional.empty();

    final double CELL_SIZE = 80;
    JButton stop = new JButton();


    int seconds = (int) System.currentTimeMillis() / 1000;
    int seconds2 = (int) System.currentTimeMillis() / 1000;
    int diffSecond = 0;
    int minutes = 0;
    JLabel jLabel = new JLabel();

    boolean onGame = true;

    boolean stopTime = true;

    int temp = 0;

    final double CELL_SIZE = 70;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 1280, 720);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 1280.0, 0.0, 720.0, -1.0, 1.0);
        JLabel jLabel;

        stop.setFocusable(true);
        stop.setFocusTraversalKeysEnabled(true);

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        //addButton stop
        stop.setText("| |");
        stop.setFont(new Font("Bold", 18, 72));
        stop.setForeground(Color.yellow);
        stop.setBackground(Color.BLACK);
        stop.setBounds(20, 20, 100, 100);
        stop.addMouseListener(this);


        // <---Edit Time --->
        if (onGame) {

            if ((diffSecond) == 60) {
                jLabel.setText(minutes + " : " + (seconds2 - seconds));
                minutes++;
                jLabel.setText(minutes + " : " + (0));
                seconds = (int) System.currentTimeMillis() / 1000;
            }

            if (stopTime) {
                jLabel.setText(minutes + " : " + diffSecond);
                seconds2 = (int) System.currentTimeMillis() / 1000;
                diffSecond = (temp + seconds2 - seconds);
            } else {
                seconds = (int) System.currentTimeMillis() / 1000;
                diffSecond = diffSecond + (seconds2 - seconds);


            }

        }
//       <--- End Time --->
        for (int y = 0; y < state.length; y++) {
            final CellState[] row = state[y];


            for (int x = 0; x < row.length; x++) {
                gl.glColor3d(1, 1, 1);
                if (hoveredOnColumn.orElse(-1) == x) {
                    gl.glColor3d(1, 1, 0);
                    drawTri(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, CELL_SIZE + 6.5 * CELL_SIZE), CELL_SIZE);
                }

                drawRect(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, y * CELL_SIZE + 1.5 * CELL_SIZE), CELL_SIZE);
                gl.glColor3d(1, 1, 1);

                gl.glColor3d(1, 1, 1);
                if (hoveredOnColumn.orElse(-1) == x) {
                    gl.glColor3d(1, 1, 0);
                    drawTri(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, CELL_SIZE + 6.5 * CELL_SIZE), CELL_SIZE);
                }
                drawRect(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, y * CELL_SIZE + 1.5 * CELL_SIZE), CELL_SIZE);
                gl.glColor3d(1, 1, 1);
            }
        }

    }

    public void drawRect(GL gl, Vector pos, double size) {
        gl.glPushMatrix();
        gl.glTranslated(pos.getX(), pos.getY(), 0);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2d(0, 0);
        gl.glVertex2d(size, 0);
        gl.glVertex2d(size, size);
        gl.glVertex2d(0, size);
        gl.glEnd();
        gl.glPopMatrix();
    }


    public void drawTri(GL gl, Vector pos, double size) {
        gl.glPushMatrix();
        gl.glTranslated(pos.getX(), pos.getY(), 0);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(0, size);
        gl.glVertex2d(size, size);
        gl.glVertex2d(size / 2, 5);
        gl.glEnd();
        gl.glPopMatrix();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        Vector mp = new Vector(e);
        mp = mp.setY(e.getComponent().getHeight() - mp.getY()); // transform screen to world cords

        this.hoveredOnColumn = Optional.empty();
        for (int x = 0; x < 7; x++) {
            double cellX = x * CELL_SIZE + 4.5 * CELL_SIZE;
            if (mp.getX() < cellX + CELL_SIZE && mp.getX() > cellX) {
                this.hoveredOnColumn = Optional.of(x);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == stop && onGame == true) {
            onGame = false;
            stopTime = false;
        } else if (e.getSource() == stop && onGame == false) {
            onGame = true;
            stopTime = true;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
