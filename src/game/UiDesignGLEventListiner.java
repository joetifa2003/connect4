package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UiDesignGLEventListiner extends JFrame {
    Mouse m1 = new Mouse();

    Mouse m2 = new Mouse();
    JFrame jFrame = new JFrame();


    JButton buttonStart, buttonInfo, buttonCancel, //page1
            buttonSinglePlayer, buttonMultiPlayer, buttonCancel1,   //page2
            buttonEasy, buttonMedium, buttonHard, buttonCancel2;        //page3

    ImageIcon image;

    GameMode gameMode;
    Level level;
    JLabel displayField = new JLabel();

    JPanel jPanel = new JPanel();


    UiDesignGLEventListiner(GameMode gameMode, Level level) {

        this.gameMode = gameMode;
        this.level = level;

    }


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

        GamePlay(m1.page, m1.mode, m1.level);
    }

    public void GamePlay(Page page, GameMode mode, Level level) {
        if (page == Page.PAGE_1) {
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

        if (page == Page.PAGE_2) {
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

        if (page == Page.PAGE_3) {
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

        if (mode == GameMode.MULTI) {

            jFrame.setVisible(false);
            Main main = new Main(GameMode.MULTI, Level.EASY, false);
        }
        if (mode == GameMode.SINGLE && level == Level.EASY) {
            jFrame.setVisible(false);
            Main main = new Main(GameMode.SINGLE, Level.EASY, false);
        }

        if (mode == GameMode.SINGLE && level == Level.MEDIUM) {
            jFrame.setVisible(false);
            Main main = new Main(GameMode.SINGLE, Level.MEDIUM, false);
        }

        if (mode == GameMode.SINGLE && level == Level.HARD) {
            jFrame.setVisible(false);
            Main main = new Main(GameMode.SINGLE, Level.HARD, false);


        }
        if (page == Page.PAGE_INFO) {
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

    enum Page {
        PAGE_1,
        PAGE_2,
        PAGE_3,
        PAGE_INFO
    }

    class Mouse implements MouseListener, ActionListener {
        Page page = Page.PAGE_1;
        GameMode mode = GameMode.EMPTY;
        Level level = Level.EMPTY;


        Mouse() {
        }

        Mouse(GameMode mode, Level level) {
            this.mode = mode;
            this.level = level;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == buttonStart) {
                page = Page.PAGE_2;
            }
            if (e.getSource() == buttonCancel) {
                System.exit(EXIT_ON_CLOSE);
            }
            if (e.getSource() == buttonSinglePlayer) {
                page = Page.PAGE_3;
                mode = GameMode.SINGLE;

            }
            if (e.getSource() == buttonMultiPlayer) {
                jFrame.setVisible(false);
                mode = GameMode.MULTI;

            }

            if (e.getSource() == buttonCancel1) {
                page = Page.PAGE_1;
            }

            if (e.getSource() == buttonCancel2) {
                page = Page.PAGE_2;
                mode = GameMode.EMPTY;
                level = Level.EMPTY;
            }

            if (e.getSource() == buttonEasy) {
                level = Level.EASY;
            }
            if (e.getSource() == buttonMedium) {
                level = Level.MEDIUM;
            }
            if (e.getSource() == buttonHard) {
                level = Level.HARD;
            }

            GamePlay(page, mode, level);
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == buttonInfo) {
                buttonInfo.setBackground(new Color(0, 0, 255));
                buttonInfo.setForeground(new Color(0, 255, 0));
                page = Page.PAGE_INFO;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == buttonInfo) {
                buttonInfo.setBackground(new Color(0, 255, 0));
                buttonInfo.setForeground(new Color(0, 0, 255));
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}

