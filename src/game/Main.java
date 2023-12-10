package game;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Main() {
        super("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean state = true;


        // setVisible(false);


        UserInterface(state);


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


    }

    public static void main(String[] args) {
        new Main();
    }


    public void UserInterface(boolean state) {

        //state UiDesign or GamePlay
        if (state) {

            UiDesign uiDesign = new UiDesign();
            setVisible(false);
        }


        if (!state) {

            Container contentPanel = getContentPane();
            Container contentPanel1 = getContentPane();
            Container contentPanel2 = getContentPane();
            GLCanvas canvas = new GLCanvas();
            MainEventListener el = new MainEventListener();
            canvas.addGLEventListener(el);
            canvas.addMouseMotionListener(el);
            add(canvas, BorderLayout.CENTER);
            new FPSAnimator(canvas, 60).start();


            // add button on Stop of the canvas
            contentPanel1.add(el.stop);
            contentPanel1.setFocusable(true);
            contentPanel1.setFocusTraversalKeysEnabled(true);
            contentPanel1.add(canvas);
            canvas.setFocusable(true);
            canvas.setFocusTraversalKeysEnabled(true);

            //addTimer
            JLabel jLabel1 = new JLabel("Time");
            el.jLabel = new JLabel(el.minutes + " : " + el.seconds);
            el.jLabel.setFont(new Font("Bold", 100, 72));
            jLabel1.setFont(new Font("Bold", 100, 72));
            jLabel1.setBounds(100, 170, 250, 200);
            el.jLabel.setBounds(100, 300, 250, 200);
            contentPanel2.setBackground(Color.BLACK);
            contentPanel.setBackground(Color.BLACK);
            jLabel1.setForeground(Color.WHITE);
            el.jLabel.setForeground(Color.WHITE);
            contentPanel.add(el.jLabel);
            contentPanel2.add(jLabel1);
            contentPanel.add(canvas);
            contentPanel2.add(canvas);


            //Frame:
            setSize(1320, 850);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }

    }


}
