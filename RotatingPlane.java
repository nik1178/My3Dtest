
import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

public class RotatingPlane extends JPanel implements Runnable{
    RotatingPlane(){
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

    int leftY = 500;
    int leftX = 750;
    int rightX = 1500;
    int rightY = 300;
    int startSize=5;
    double speed = 100;
    int targetLength=rightX-leftX; //what you want the furthest away to be
    int rightPointDepth=0; //variable used only for calculating
    long prevTime = java.lang.System.nanoTime();
    ArrayList<Integer> lineCoordinatesX = new ArrayList<>();
    ArrayList<Integer> lineCoordinatesY = new ArrayList<>();
    ArrayList<Integer> lineCoordinatesZ = new ArrayList<>();
    //int[][] lineCoordinates = new int[rightX-leftX][3]; // 0=X, 1=Y, 2=Z(depth)
    int counter = 0;
    void drawRotatingPlane(){
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>5000000){
                prevTime=currentTime;
                /* Start of algorithm */
                
                lineCoordinatesX.clear();
                lineCoordinatesY.clear();
                lineCoordinatesZ.clear();

                lineCoordinatesX.add(leftX);
                lineCoordinatesY.add(leftY);
                lineCoordinatesZ.add(0);

                lineCoordinatesX.add(leftX+(int)(Math.cos(counter/10000.0*speed)*targetLength));
                lineCoordinatesY.add(rightY);
                lineCoordinatesZ.add((int)(Math.sin(counter/10000.0*speed)*targetLength));

                
                for(int i=1; i<targetLength-1; i+=1){
                    lineCoordinatesX.add(lineCoordinatesX.get(0)+(int)((lineCoordinatesX.get(1)-lineCoordinatesX.get(0))*(double)i/targetLength));
                    lineCoordinatesY.add(lineCoordinatesY.get(0)+(int)((lineCoordinatesY.get(1)-lineCoordinatesY.get(0))*(double)i/targetLength));
                    lineCoordinatesZ.add((int)(lineCoordinatesZ.get(0)+lineCoordinatesZ.get(1)*(double)i/targetLength));
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
        for(int i=0; i<lineCoordinatesX.size(); i++){
            if(lineCoordinatesX.get(i)==0 && lineCoordinatesY.get(i)==0 && lineCoordinatesZ.get(i)==0) continue;

            int blueTint = 255/2 - lineCoordinatesZ.get(i)*255/targetLength/2;
            if(blueTint>255)blueTint=255;
            else if(blueTint<0)blueTint=0;
            gtd.setPaint(new Color(0,0,blueTint));
            
            int drawSize = (int)((1-(double)lineCoordinatesZ.get(i)/targetLength)*startSize);
            gtd.fillOval(lineCoordinatesX.get(i), lineCoordinatesY.get(i), drawSize, drawSize);
        }
    }
}
