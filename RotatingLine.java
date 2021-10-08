
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class RotatingLine extends JPanel implements Runnable{
    RotatingLine(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1500,900));
        this.setBackground(Color.black);
        Thread thisThread = new Thread(this);
        thisThread.start();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        drawRotatingLine();
    }

    int leftY = 500;
    int leftX = 750;
    int rightX = 1500;
    int rightY = 300;
    int startSize=5;
    double speed = 100;
    int targetLength=rightX-leftX; //what you want the furthest away to be
    int rightPointDepth=0; //variable used only for calculating
    long prevTime = java.lang.System.nanoTime();
    int[][] lineCoordinates = new int[rightX-leftX][3]; // 0=X, 1=Y, 2=Z(depth)
    void drawRotatingLine(){
        int counter = 0;
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>5000000){
                prevTime=currentTime;
                /* Start of algorithm */
                
                lineCoordinates[0][0] = leftX;
                lineCoordinates[0][1] = leftY;
                lineCoordinates[0][2] = 0;

                lineCoordinates[targetLength-1][0] = leftX+(int)(Math.cos(counter/10000.0*speed)*targetLength);
                lineCoordinates[targetLength-1][1] = rightY;
                lineCoordinates[targetLength-1][2] = (int)(Math.sin(counter/10000.0*speed)*targetLength);

                for(int i=1; i<targetLength-1; i+=10){
                    lineCoordinates[i][0] = lineCoordinates[0][0]+(int)((lineCoordinates[targetLength-1][0]-lineCoordinates[0][0])*(double)i/targetLength);
                    lineCoordinates[i][1] = lineCoordinates[0][1]+(int)((lineCoordinates[targetLength-1][1]-lineCoordinates[0][1])*(double)i/targetLength);
                    lineCoordinates[i][2] = (int)(lineCoordinates[0][2]+lineCoordinates[targetLength-1][2]*(double)i/targetLength);
                }

                counter++;

                /* End of algorithm */
                repaint();
            }
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.setPaint(Color.blue);
        for(int i=0; i<lineCoordinates.length; i++){
            if(lineCoordinates[i][0]==0 && lineCoordinates[i][1]==0 && lineCoordinates[i][2]==0) continue;
            int blueTint = 255/2 - lineCoordinates[i][2]*255/targetLength/2;
            gtd.setPaint(new Color(0,0,blueTint));
            
            int drawSize = (int)((1-(double)lineCoordinates[i][2]/targetLength)*startSize);
            gtd.fillOval(lineCoordinates[i][0], lineCoordinates[i][1], drawSize, drawSize);
        }
    }
}
