package game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UiDesign extends JFrame implements ActionListener, MouseListener {

    int x = 620;
    int y = 50;

    int seconds = 0;
    int minutes = 0;
    Timer timer = new Timer(24, null);
    ImageIcon image = new ImageIcon();
    JLabel displayField = new JLabel();


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JButton btnStart = new JButton("Start");
    JButton btnExist = new JButton("×");
    JButton btnSound = new JButton("×");
    JButton btnSingle = new JButton("SinglePlayer");
    JButton btnMulti = new JButton("MultiPlayer");

    JButton btnEasy = new JButton("Easy");
    JButton btnMedium = new JButton("Medium ");
    JButton btnHard = new JButton("Hard");


    JLabel jLabelC = new JLabel("C");
    JLabel jLabelO = new JLabel("O");
    JLabel jLabelNE = new JLabel("NNE");
    JLabel jLabelC2 = new JLabel("C");
    JLabel jLabelT = new JLabel("T");
    JLabel jLabel4 = new JLabel("4");
    Container contentPanel = getContentPane();


    UiDesign() {
        timer.start();

        setSize(1280, 820);

        setLocation(100, 100);


        add(jPanel1);
        // add(jPanel2);
        this.setLayout(null);


        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBounds(0, 0, 1280, 820);
        jPanel1.setLayout(null);

        jPanel1.add(btnStart);
        jPanel1.addMouseListener(this);
        jPanel1.add(btnExist);
        jPanel1.add(jLabelC);
        jPanel1.add(jLabelO);
        jPanel1.add(jLabelNE);
        jPanel1.add(jLabelC2);
        jPanel1.add(jLabelT);
        jPanel1.add(jLabel4);

        btnExist.addActionListener(this);


        btnExist.setFont(new Font("Bold", Font.BOLD, 128));
        btnExist.setForeground(new Color(255, 0, 0));
        btnExist.setBackground(new Color(230, 230, 0));
        //   btnStart.setBounds(x, y, 100, 60);
        btnExist.setBounds(10, 10, 150, 100);
        jLabelC.setBounds(250, 10, 150, 100);
        jLabelO.setBounds(335, 10, 150, 100);
        jLabelNE.setBounds(430, 10, 280, 100);
        jLabelC2.setBounds(700, 10, 150, 100);
        jLabelT.setBounds(780, 10, 150, 100);
        jLabel4.setBounds(850, 10, 150, 100);

        jLabelC.setFont(new Font("Bold", Font.BOLD, 128));
        jLabelC.setForeground(new Color(230, 230, 0));

        jLabelO.setFont(new Font("Bold", Font.BOLD, 128));
        jLabelO.setForeground(new Color(0, 110, 205));

        jLabelNE.setFont(new Font("Bold", Font.BOLD, 128));
        jLabelNE.setForeground(new Color(230, 0, 0));

        jLabelC2.setFont(new Font("Bold", Font.BOLD, 128));
        jLabelC2.setForeground(new Color(230, 230, 0));

        jLabelT.setFont(new Font("Bold", Font.BOLD, 128));
        jLabelT.setForeground(new Color(230, 0, 0));

        jLabel4.setFont(new Font("Bold", Font.BOLD, 128));
        jLabel4.setForeground(new Color(0, 110, 205));

        setVisible(true);
        setResizable(false);
        y = 10;
        y++;


        jLabelC.setBounds(250, y + 10, 150, 100);

        if (y < 410) {
            y++;


        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnExist) {
            System.exit(EXIT_ON_CLOSE);
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {


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

