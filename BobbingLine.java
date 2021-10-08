
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class BobbingLine extends JPanel implements Runnable{
    BobbingLine(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500,500));
        this.setBackground(Color.black);
        Thread thisThread = new Thread(this);
        thisThread.start();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        drawBobbingLine();
    }

    int leftY = 250;
    int leftX = 100;
    int rightX = 400;
    int rightY = 250;
    int startSize=30;
    int speed = 10;
    int targetPointDepth=30; //what you want the furthest away to be
    int rightPointDepth=0; //variable used only for calculating
    long prevTime = java.lang.System.nanoTime();
    int[][] lineCoordinates = new int[rightX-leftX][3]; // 0=X, 1=Y, 2=Z(depth)
    void drawBobbingLine(){
        int counter = 0;
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>50000000){
                prevTime=currentTime;
                /* Start of algorithm */
                
                for(int i=0; i<rightX-leftX; i++){
                    lineCoordinates[i][0] = leftX+i;
                    lineCoordinates[i][1] = leftY+(int)(rightPointDepth/2.0*((double)i/(rightX-leftX))); 
                    lineCoordinates[i][2] = (int)(rightPointDepth * (double)i / (rightX-leftX));
                }

                rightPointDepth=(int)((Math.cos(counter/100.0*speed)/2) *targetPointDepth + startSize/2);
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

            int blueTint = 255-lineCoordinates[i][2]*255/targetPointDepth;
            gtd.setPaint(new Color(0,0,blueTint));

            int drawSize = startSize-lineCoordinates[i][2];
            gtd.drawOval(lineCoordinates[i][0], lineCoordinates[i][1], drawSize, drawSize);
        }
    }
}
