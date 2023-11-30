package game;

import com.sun.opengl.util.FPSAnimator;

import javax.swing.*;
import javax.media.opengl.GLCanvas;
import java.awt.*;

public class Main extends JFrame {

    Main() {
        super("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GLCanvas canvas = new GLCanvas();
        MainEventListener el = new MainEventListener();
        canvas.addGLEventListener(el);
        canvas.addMouseMotionListener(el);
        add(canvas, BorderLayout.CENTER);
        new FPSAnimator(canvas, 60).start();

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
