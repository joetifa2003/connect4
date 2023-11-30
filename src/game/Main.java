package game;

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
        add(canvas, BorderLayout.CENTER);

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
