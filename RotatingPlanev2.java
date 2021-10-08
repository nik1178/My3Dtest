
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class RotatingPlanev2 extends JPanel implements Runnable{
    RotatingPlanev2(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1500,900));
        this.setBackground(Color.black);
        Thread thisThread = new Thread(this);
        thisThread.start();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        drawRotatingPlane();
    }

    /* int leftY = 500;
    int leftX = 750;
    int rightX = 1500;
    int rightY = 300; */
    int startSize=5;
    //double speed = 100;
    //int targetLength=rightX-leftX; //what you want the furthest away to be
    //int rightPointDepth=0; //variable used only for calculating
    long prevTime = java.lang.System.nanoTime();
    int[][] lineCoordinates = new int[100][6]; // 0=X1, 1=Y1, 2=Z1, 3=X2, 4=Y2, 5=Z2(depth)
    void drawRotatingPlane(){
        int counter = 0;
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>500000){
                prevTime=currentTime;
                /* Start of algorithm */
                
                for(int i=0; i<lineCoordinates.length; i++){
                    lineCoordinates[i][0] = 50 + i*5;
                    lineCoordinates[i][1] = 50;
                    lineCoordinates[i][2] = 0;
                    lineCoordinates[i][3] = 400;
                    lineCoordinates[i][4] = 500;
                    lineCoordinates[i][5] = -300;
                }

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

            // Turn coordinates from previous table of lines to displayable dots
            int lineLength = (int) Math.sqrt( Math.pow(lineCoordinates[i][3]-lineCoordinates[i][0],2) + Math.pow(lineCoordinates[i][4]-lineCoordinates[i][1],2));
            int[][] dotCoordinates = new int[lineLength][3];

            for(int j=0; j<lineLength; j+=1){
                dotCoordinates[j][0] = lineCoordinates[i][0]+(int)((lineCoordinates[i][3]-lineCoordinates[i][0])*(double)j/lineLength);
                dotCoordinates[j][1] = lineCoordinates[i][1]+(int)((lineCoordinates[i][4]-lineCoordinates[i][1])*(double)j/lineLength);
                dotCoordinates[j][2] = lineCoordinates[i][2]+(int)((lineCoordinates[i][5]-lineCoordinates[i][2])*(double)j/lineLength);

                if(dotCoordinates[j][0]==0 && dotCoordinates[j][1]==0 && dotCoordinates[j][2]==0) continue;
                int blueTint = 255/2 - dotCoordinates[j][2]/10;
                if(blueTint>255) blueTint = 255;
                else if(blueTint<0) blueTint = 0;
                gtd.setPaint(new Color(0,0,blueTint));
                
                int drawSize = (int)((1-(double)dotCoordinates[j][2]/lineLength)*startSize);
            
                gtd.fillOval(dotCoordinates[j][0], dotCoordinates[j][1], drawSize, drawSize);

            }

        }

    }
}
