package game;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PlayButtonGLEventListener implements GLEventListener , MouseListener , MouseMotionListener {
    int x , y  ;
    GLCanvas glc;

    public void setGLCanvas(GLCanvas glc){
        this.glc = glc;
    }
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-1280, -820, 1280, 820);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1280, 1280, -820, 820, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(1.0f);
        System.out.println(x+ " " + y);
        if((Math.sqrt(Math.pow(x - (1280 / 2) , 2) + Math.pow(y - (820 / 2) , 2)) <= 200) )
            gl.glColor3f(0.32f, 0.76f, 1.0f);
        else
            gl.glColor3f(1.0f , 1.0f , 1.0f);


        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(-100 , 150);
        gl.glVertex2i(-100 , -150);

        gl.glVertex2i(-100 , -150);
        gl.glVertex2i(150 , 0);

        gl.glVertex2i(150 , 0);
        gl.glVertex2i(-100 , 150);
        gl.glEnd();

            gl.glColor3f(1.0f , 1.0f , 1.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        circle(0 , 0 ,400 , gl);
        gl.glEnd();

        gl.glBegin(GL.GL_LINE_LOOP);
        circle(0 , 0 ,300 , gl);
        gl.glEnd();


    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
    private void circle(double Xc, double Yc, int r, GL gl) {
        for (int i = 0; i < 360; i++) {
            double th = (2.0 * Math.PI * i) / 360;
            double x = r * Math.cos(th);
            double y = r * Math.sin(th);
            gl.glVertex2d(x + Xc, y + Yc);
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        Component c = e.getComponent() ;
        double width = c.getWidth();
        double height = c.getHeight();

        this.x = (int)((x/width) * 1280);
        this.y = (int)((y/height) * 820 );
        this.y = 820 - this.y ;


        glc.repaint();
    }
}
