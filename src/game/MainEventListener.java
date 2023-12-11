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

public class MainEventListener implements GLEventListener, MouseMotionListener, MouseListener {
    int[][] state = new int[6][7];

    Optional<Integer> hoveredOnColumn = Optional.empty();

    final double CELL_SIZE = 80;

    JButton stop = new JButton();
    JLabel jLabel = new JLabel();

    int seconds = (int) System.currentTimeMillis() / 1000;
    int seconds2 = (int) System.currentTimeMillis() / 1000;
    int diffSecond = 0;
    int minutes = 0;

    boolean onGame = true;
    boolean stopTime = true;

    int currentPlayer = Constants.CELL_YELLOW;

    MainEventListener(GameMode mode, Level level) {
        System.out.println(mode);
        System.out.println(level);

        for (int y = 0; y < state.length; y++) {
            int[] row = state[y];
            for (int x = 0; x < row.length; x++) {
                state[y][x] = Constants.CELL_EMPTY;
            }
        }
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 1280.0, 0.0, 720.0, -1.0, 1.0);

        stop.setFocusable(true);
        stop.setFocusTraversalKeysEnabled(true);
        stop.setText("| |");
        stop.setFont(new Font("Bold", 18, 72));
        stop.setForeground(Color.yellow);
        stop.setBackground(Color.BLACK);
        stop.setBounds(20, 20, 100, 100);
        stop.addMouseListener(this);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

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
                diffSecond = (seconds2 - seconds);
            } else {
                seconds = (int) System.currentTimeMillis() / 1000;
                diffSecond = diffSecond + (seconds2 - seconds);
            }
        }
//       <--- End Time --->
        for (int y = 0; y < state.length; y++) {
            final int[] row = state[y];
            for (int x = 0; x < row.length; x++) {
                int cell = row[x];

                gl.glColor3d(1, 1, 1);
                if (hoveredOnColumn.orElse(-1) == x) {
                    gl.glColor3d(1, 1, 0);
                    drawTri(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, CELL_SIZE + 6.5 * CELL_SIZE), CELL_SIZE);
                }

                Vector cellPos = new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, y * CELL_SIZE + 1.5 * CELL_SIZE);
                drawRect(gl, cellPos, CELL_SIZE);
                gl.glColor3d(1, 1, 1);

                boolean drawCoin = cell != Constants.CELL_EMPTY;
                if (drawCoin) {
                    if (cell == Constants.CELL_YELLOW) {
                        gl.glColor3d(1, 1, 0);
                    } else if (cell == Constants.CELL_RED) {
                        gl.glColor3d(1, 0, 0);
                    }

                    drawCircle(gl, cellPos.add(new Vector(40, 40)), CELL_SIZE / 2.5);
                }
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
        if (e.getSource() == stop) {
            onGame = !onGame;
            stopTime = !stopTime;
        }

        if (hoveredOnColumn.isPresent()) {
            for (int y = 0; y < state.length; y++) {
                if (state[y][hoveredOnColumn.get()] == Constants.CELL_EMPTY) {
                    state[y][hoveredOnColumn.get()] = currentPlayer;
                    switchPlayers();
                    if (MatrixCalc.MatrixWin(state)) {
                    }
                    break;
                }
            }
        }
    }

    void switchPlayers() {
        this.currentPlayer = this.currentPlayer == Constants.CELL_RED ? Constants.CELL_YELLOW : Constants.CELL_RED;
    }

    void drawCircle(GL gl, Vector pos, double radius) {
        gl.glBegin(GL.GL_POLYGON);
        for (double a = 0; a < Math.toRadians(360); a += Math.toRadians(1)) {
            double x1 = radius * (Math.cos(a)) + pos.getX();
            double y1 = radius * (Math.sin(a)) + pos.getY();
            gl.glVertex2d(x1, y1);
        }
        gl.glEnd();
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
