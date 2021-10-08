
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args){
        new Main();
    }

    RotatingPlanev6 panel = new RotatingPlanev6();
    Main(){
        
        this.add(panel);

        //this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        /* getLineCoordinates(); */
    }


    /* int leftY = 250;
    int leftX = 200;
    int rightX = 300;
    int rightY = 250;
    int size=15;
    int rightPointDepth=0;
    long prevTime = java.lang.System.nanoTime();
    int[] lineCoordinates = new int[rightX-leftX];
    Ball ball = new Ball();
    void getLineCoordinates(){
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>500000000){
                prevTime=currentTime;
                ypos++;
                repaint();

            }
            repaint();
        }
    }

    int ypos=200;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.setPaint(Color.blue);
        gtd.drawOval(200, ypos, 15, 15);
        ball.draw(g);
    }
    public void graphics3D(Point topLeft, Point bottomRight, Point depth, Graphics g){

    } */
}