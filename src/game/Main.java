package game;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Main() {
        super("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GLCanvas canvas = new GLCanvas();
        MainEventListener el = new MainEventListener();
        canvas.addGLEventListener(el);
        canvas.addMouseMotionListener(el);
        canvas.setBounds(0, 0, 1280, 720);

        Container contentPanel = getContentPane();

        // add button on top of the canvas
        // JButton btn = new JButton();
        // btn.setText("Hello");
        // btn.setBounds(200, 200, 300, 200);
        // contentPanel.add(btn);

        contentPanel.add(canvas);

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
