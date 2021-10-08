import java.util.*;
import javax.swing.*;

import java.awt.event.*;

import java.awt.*;

public class ProjectionRenderingv1 extends JFrame{
    public static void main(String[] args){
        new ProjectionRenderingv1();
    }

    MyPanel panel = new MyPanel();

    ProjectionRenderingv1(){
        
        this.add(panel);

        //this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        /* getLineCoordinates(); */
    }
}

class MyPanel extends JPanel implements Runnable, MouseWheelListener, MouseMotionListener, MouseListener{
    MyPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1500,900));
        this.setBackground(Color.black);
        Thread thisThread = new Thread(this);
        thisThread.start();
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        completeProgram();
    }

    void completeProgram(){

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.setPaint(Color.blue);


    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub

    }
    int[] startMousePos = new int[2];
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    // MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
