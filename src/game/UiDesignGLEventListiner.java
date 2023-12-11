package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class UiDesignGLEventListiner extends JFrame {


    Mouse m1 = new Mouse();

    Mouse m2 = new Mouse();
    JFrame jFrame = new JFrame();

    JButton buttonStart, buttonInfo, buttonCancel, //page1
            buttonSinglePlayer, buttonMultiPlayer, buttonCancel1,   //page2
            buttonEasy, buttonMedium, buttonHard, buttonCancel2;        //page3

    ImageIcon image;
    JLabel displayField = new JLabel();
    GLCanvas glcanvas = new GLCanvas();


    JPanel jPanel = new JPanel();


    UiDesignGLEventListiner() {

        jFrame.setVisible(true);
        jFrame.setSize(1520, 1020);
        jFrame.setTitle("8BALL");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonInfo = new JButton();
        buttonStart = new JButton();
        buttonCancel = new JButton();
        buttonCancel1 = new JButton();
        buttonCancel2 = new JButton();
        buttonEasy = new JButton();
        buttonMedium = new JButton();
        buttonHard = new JButton();
        buttonSinglePlayer = new JButton();
        buttonMultiPlayer = new JButton();

        try {


            image = new ImageIcon(getClass().getResource("..//Assets//connect4.jpg"));
            displayField = new JLabel(image);
            jFrame.add(displayField);
        } catch (Exception e) {
            System.out.println("Image cannot be Found");
        }
        displayField.setVisible(false);


        buttonStart.setOpaque(false);
        buttonStart.setContentAreaFilled(false);
        buttonStart.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//traingle3.png"));
            buttonStart.setIcon(new ImageIcon(img));
            System.out.println("k");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        buttonCancel.setOpaque(false);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setBorderPainted(false);
        buttonCancel1.setOpaque(false);
        buttonCancel1.setContentAreaFilled(false);
        buttonCancel1.setBorderPainted(false);
        buttonCancel2.setOpaque(false);
        buttonCancel2.setContentAreaFilled(false);
        buttonCancel2.setBorderPainted(false);

        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//cancel.png"));
            buttonCancel.setIcon(new ImageIcon(img));
            buttonCancel1.setIcon(new ImageIcon(img));
            buttonCancel2.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonInfo.setOpaque(false);
        buttonInfo.setContentAreaFilled(false);
        buttonInfo.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//i.png"));
            buttonInfo.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        buttonSinglePlayer.setOpaque(false);
        buttonSinglePlayer.setContentAreaFilled(false);
        buttonSinglePlayer.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//2v2.jpg"));
            buttonSinglePlayer.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        buttonMultiPlayer.setOpaque(false);
        buttonMultiPlayer.setContentAreaFilled(false);
        buttonMultiPlayer.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//computer.jpg"));
            buttonMultiPlayer.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }


        buttonEasy.setOpaque(false);
        buttonEasy.setContentAreaFilled(false);
        buttonEasy.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//Easy.png"));
            buttonEasy.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonMedium.setOpaque(false);
        buttonMedium.setContentAreaFilled(false);
        buttonMedium.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//Medium.png"));
            buttonMedium.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        buttonHard.setOpaque(false);
        buttonHard.setContentAreaFilled(false);
        buttonHard.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("..//Assets//Hard.png"));
            buttonHard.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        GamePlay(m1.s1, m1.s2, m1.s3);


    }


    public void GamePlay(String s1, String s2, String s3) {

        if (s1.equals("page1")  ) {

            buttonStart.setVisible(true);
            buttonInfo.setVisible(true);
            buttonCancel.setVisible(true);
            displayField.setVisible(true);

            displayField.setLayout(null);
            displayField.setBackground(Color.BLACK);


            buttonSinglePlayer.setVisible(false);
            buttonMultiPlayer.setVisible(false);

            buttonCancel1.setVisible(false);
            buttonEasy.setVisible(false);
            buttonMedium.setVisible(false);
            buttonHard.setVisible(false);
            buttonCancel2.setVisible(false);


            displayField.add(buttonStart);
            displayField.add(buttonInfo);
            displayField.add(buttonCancel);

            buttonStart.setBounds(1000, 600, 230, 200);
            buttonInfo.setBounds(750, 30, 250, 200);
            buttonCancel.setBounds(0, 30, 250, 200);


            buttonStart.addMouseListener(m1);
            buttonInfo.addMouseListener(m1);
            buttonCancel.addMouseListener(m1);

        }


        if (s1.equals("page2")  ) {
            displayField.setVisible(true);

            buttonStart.setVisible(false);
            buttonInfo.setVisible(false);
            buttonCancel.setVisible(false);
            buttonSinglePlayer.setVisible(true);
            buttonMultiPlayer.setVisible(true);
            buttonCancel1.setVisible(true);
            buttonEasy.setVisible(false);
            buttonMedium.setVisible(false);
            buttonHard.setVisible(false);
            buttonCancel2.setVisible(false);
            displayField.setLayout(null);

            displayField.setBackground(Color.RED);


            displayField.add(buttonSinglePlayer);
            displayField.add(buttonMultiPlayer);

            displayField.add(buttonCancel1);
            buttonSinglePlayer.setBounds(1050, 650, 250, 200);
            buttonMultiPlayer.setBounds(750, 650, 250, 200);

            buttonCancel1.setBounds(0, 30, 250, 200);


            buttonSinglePlayer.addMouseListener(m2);
            buttonMultiPlayer.addMouseListener(m2);
            buttonCancel1.addMouseListener(m2);


        }
         if (s1.equals("page3")  ) {
            displayField.setVisible(true);


            buttonStart.setVisible(false);
            buttonInfo.setVisible(false);
            buttonCancel.setVisible(false);
            buttonSinglePlayer.setVisible(false);
            buttonMultiPlayer.setVisible(false);

            buttonCancel1.setVisible(false);
            buttonEasy.setVisible(true);
            buttonMedium.setVisible(true);
            buttonHard.setVisible(true);
            buttonCancel2.setVisible(true);

            displayField.setLayout(null);


            displayField.setBackground(Color.RED);

            displayField.add(buttonEasy);
            displayField.add(buttonMedium);
            displayField.add(buttonHard);
            displayField.add(buttonCancel2);

            buttonEasy.setBounds(750, 250, 250, 100);
            buttonMedium.setBounds(750, 450, 250, 100);
            buttonHard.setBounds(750, 650, 250, 100);
            buttonCancel2.setBounds(0, 30, 250, 200);


            buttonEasy.addMouseListener(m2);
            buttonMedium.addMouseListener(m2);
            buttonHard.addMouseListener(m2);
            buttonCancel2.addMouseListener(m2);
        }
        if (s2 .equals( "MultiPlayer")) {


            displayField.setVisible(false);


        }

        if (s2.equals("SinglePlayer")  && s3.equals("Easy")) {


            displayField.setVisible(false);
        }

        if (s2.equals("SinglePlayer")  && s3 .equals("Hard") ) {

            displayField.setVisible(false);

        }

        if (s2.equals("SinglePlayer")  && s3 .equals("Hard") ) {


            displayField.setVisible(false);

        }
        if (s1 .equals( "Info")) {


            buttonInfo.setVisible(false);
            displayField.add(jPanel);
            jPanel.setBounds(300, 100, 800, 800);
            jPanel.setLayout(null);
            jPanel.setBackground(Color.BLACK);


            JLabel helpMessage1 = new JLabel();

            jPanel.add(helpMessage1);
            helpMessage1.setBounds(100, 100, 400, 400);


            helpMessage1.setFont(new Font("Welcome to Our Game!", Font.ITALIC, 18));
            helpMessage1.setVerticalAlignment(1);
            helpMessage1.setHorizontalAlignment(0);

            String s = "<html>How To Play?<br><br>1 -" +
                    " Click With Mouse In The Place You Want To Play In<br><br>2 -" +
                    " To Win You Should Connect 4 Pieces Diagonally, Vertically Or Horizontally<br><br>3 - " +
                    "Enjoy Our Game! :)</html>";

            helpMessage1.setForeground(Color.red);
            helpMessage1.setBackground(Color.blue);
            helpMessage1.setText(s);


        }
    }


    private class Mouse implements MouseListener, ActionListener {

        String s1 = "page1";
        String s2 = "";
        String s3 = "";


        @Override
        public void mouseClicked(MouseEvent e) {


            if (e.getSource() == buttonStart) {
                s1 = "page2";

            }
            if (e.getSource() == buttonCancel) {


                System.exit(EXIT_ON_CLOSE);


            }
            if (e.getSource() == buttonSinglePlayer) {
                s1 = "page3";
                s2 = "SinglePlayer";

            }
            if (e.getSource() == buttonMultiPlayer) {
                s1 = "page3";
                s2 = "MultiPlayer";
            }

            if (e.getSource() == buttonCancel1) {
                s1 = "page1";
            }

            if (e.getSource() == buttonCancel2) {
                s1 = "page2";
                s2 = "";
                s3 = "";
            }


            if (e.getSource() == buttonEasy) {
                s3 = "Easy";
            }
            if (e.getSource() == buttonMedium) {
                s3 = "Medium";
            }
            if (e.getSource() == buttonHard) {
                s3 = "Hard";
            }


            GamePlay(s1, s2, s3);
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == buttonInfo) {
                buttonInfo.setBackground(new Color(0, 0, 255));
                buttonInfo.setForeground(new Color(0, 255, 0));
                s1 = "Info";

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == buttonInfo) {
                buttonInfo.setBackground(new Color(0, 255, 0));
                buttonInfo.setForeground(new Color(0, 0, 255));
                s1 = "";
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonInfo) {
                glcanvas = null;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}

