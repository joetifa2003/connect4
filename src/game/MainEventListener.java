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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

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
    JLabel jLabel = new JLabel();

    int seconds = (int) System.currentTimeMillis() / 1000;
    int seconds2 = (int) System.currentTimeMillis() / 1000;
    int diffSecond = 0;
    int minutes = 0;

    boolean onGame = true;
    boolean stopTime = true;

    CellState currentPlayer = CellState.YELLOW;

    GameMode mode;
    Level level;

    CellState computerPlayer = CellState.RED;
    CellState humanPlayer = CellState.YELLOW;
    int maxDepth;

    Random rndm = new Random();

    MainEventListener(GameMode mode, Level level) {
        System.out.println(mode);
        this.mode = mode;
        this.level = level;
        switch (level) {
            case HARD -> maxDepth = 5;
            case MEDIUM -> maxDepth = 3;
            case EASY -> maxDepth = 0;
        }
        System.out.println(maxDepth);

        resetGame();
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
            final CellState[] row = state[y];
            for (int x = 0; x < row.length; x++) {
                CellState cell = row[x];

                gl.glColor3d(1, 1, 1);
                if (hoveredOnColumn.orElse(-1) == x) {
                    gl.glColor3d(1, 1, 0);
                    drawTri(gl, new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, CELL_SIZE + 6.5 * CELL_SIZE), CELL_SIZE);
                }

                Vector cellPos = new Vector(x * CELL_SIZE + 4.5 * CELL_SIZE, y * CELL_SIZE + 1.5 * CELL_SIZE);
                drawRect(gl, cellPos, CELL_SIZE);
                gl.glColor3d(1, 1, 1);

                boolean drawCoin = cell != CellState.EMPTY;
                if (drawCoin) {
                    if (cell == CellState.YELLOW) {
                        gl.glColor3d(1, 1, 0);
                    } else if (cell == CellState.RED) {
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

        if (mode == GameMode.SINGLE) {
            singlePlayerPlay();
        } else {
            multiPlayerPlay();
        }
    }

    void switchPlayers() {
        this.currentPlayer = this.currentPlayer == CellState.RED ? CellState.YELLOW : CellState.RED;
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

    void resetGame() {
        for (int y = 0; y < state.length; y++) {
            CellState[] row = state[y];
            for (int x = 0; x < row.length; x++) {
                state[y][x] = CellState.EMPTY;
            }
        }
    }

    void multiPlayerPlay() {
        if (hoveredOnColumn.isPresent()) {
            for (int y = 0; y < state.length; y++) {
                if (state[y][hoveredOnColumn.get()] == CellState.EMPTY) {
                    state[y][hoveredOnColumn.get()] = currentPlayer;

                    if (MatrixCalc.MatrixWin(state)) {
                        JOptionPane.showMessageDialog(null, this.currentPlayer + " WON!");
                        resetGame();
                    }
                    switchPlayers();
                    break;
                }
            }
        }
    }

    void singlePlayerPlay() {
        if (hoveredOnColumn.isPresent()) {
            for (int y = 0; y < state.length; y++) {
                if (state[y][hoveredOnColumn.get()] == CellState.EMPTY) {
                    state[y][hoveredOnColumn.get()] = humanPlayer;

                    if (MatrixCalc.MatrixWin(state)) {
                        JOptionPane.showMessageDialog(null, humanPlayer + " WON!");
                        resetGame();
                        return;
                    }

                    aiPlay();
                    if (MatrixCalc.MatrixWin(state)) {
                        JOptionPane.showMessageDialog(null, computerPlayer + " WON!");
                        resetGame();
                    }
                    break;
                }
            }
        }
    }

    void aiPlay() {
        if (level == Level.EASY) {
            ArrayList<Integer> moves = getAvailableMoves();
            makeMove(moves.get(rndm.nextInt(0, moves.size() - 1)), computerPlayer);
        } else {
            int move = findBestMove();
            makeMove(move, computerPlayer);
        }
    }

    int evaluateBoard() {
        int score = 0;

        final int ROWS = 6;
        final int COLS = 7;

        // Evaluate rows
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                score += evaluateLine(state[row][col], state[row][col + 1], state[row][col + 2], state[row][col + 3]);
            }
        }

        // Evaluate columns
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                score += evaluateLine(state[row][col], state[row + 1][col], state[row + 2][col], state[row + 3][col]);
            }
        }

        // Evaluate diagonals (positive slope)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                score += evaluateLine(state[row][col], state[row + 1][col + 1], state[row + 2][col + 2], state[row + 3][col + 3]);
            }
        }

        // Evaluate diagonals (negative slope)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                score += evaluateLine(state[row][col], state[row - 1][col + 1], state[row - 2][col + 2], state[row - 3][col + 3]);
            }
        }

        return score;
    }


    private int evaluateLine(CellState cell1, CellState cell2, CellState cell3, CellState cell4) {
        int score = 0;

        int playerCount = 0;
        int opponentCount = 0;
        int emptyCount = 0;

        CellState[] cells = {cell1, cell2, cell3, cell4};

        for (CellState cell : cells) {
            if (cell == humanPlayer) {
                playerCount++;
            } else if (cell == computerPlayer) {
                opponentCount++;
            } else {
                emptyCount++;
            }
        }

        // Winning state for the player
        if (playerCount == 4) {
            score += 10000;
        }
        // Three in a row for the player
        else if (playerCount == 3 && emptyCount == 1) {
            score += 100;
        }
        // Two in a row for the player
        else if (playerCount == 2 && emptyCount == 2) {
            score += 10;
        }

        // Winning state for the opponent
        if (opponentCount == 4) {
            score -= 10000;
        }
        // Three in a row for the opponent
        else if (opponentCount == 3 && emptyCount == 1) {
            score -= 100;
        }
        // Two in a row for the opponent
        else if (opponentCount == 2 && emptyCount == 2) {
            score -= 10;
        }

        return score;
    }

    public int minimax(int depth, boolean maximizingPlayer) {
        int score;

        int result = evaluateBoard(); // Implement your own board evaluation function

        if (depth == 0 || isGameOver()) {
            return result;
        }

        if (maximizingPlayer) {
            score = Integer.MIN_VALUE;
            for (int move : getAvailableMoves()) {
                makeMove(move, humanPlayer);
                score = Math.max(score, minimax(depth - 1, false));
                undoMove(move);
            }
            return score;
        } else {
            score = Integer.MAX_VALUE;
            for (int move : getAvailableMoves()) {
                makeMove(move, computerPlayer);
                score = Math.min(score, minimax(depth - 1, true));
                undoMove(move);
            }
            return score;
        }
    }

    public int findBestMove() {
        int bestMove = -1;
        int bestScore = Integer.MAX_VALUE;

        for (Integer move : getAvailableMoves()) {
            makeMove(move, computerPlayer);
            int score = minimax(maxDepth, true);
            undoMove(move);

            if (score < bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    ArrayList<Integer> getAvailableMoves() {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (state[y][x] == CellState.EMPTY) {
                    moves.add(x);
                    break;
                }
            }
        }

        return moves;
    }

    boolean isGameOver() {
        return MatrixCalc.MatrixWin(state);
    }

    void makeMove(int col, CellState v) {
        for (int y = 0; y < 6; y++) {
            if (state[y][col] == CellState.EMPTY) {
                state[y][col] = v;
                return;
            }
        }
    }

    void undoMove(int col) {
        for (int y = 5; y >= 0; y--) {
            if (state[y][col] != CellState.EMPTY) {
                state[y][col] = CellState.EMPTY;
                return;
            }
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
