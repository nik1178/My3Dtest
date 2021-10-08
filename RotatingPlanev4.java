
import java.util.*;
import javax.swing.*;

import java.awt.*;

public class RotatingPlanev4 extends JPanel implements Runnable{
    RotatingPlanev4(){
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

    /* Developing notes 
    *  Horizon is 5km away. Every 100 depth coordinates = 1m aka. every Z coordinate is 1cm. 5km*1000m*100cm = 500000 maximum depth by default.
        This value should work in the same way as changing the focal length on a camera.
    *  TODO: Make the horizon point moveable
    *
    */


    //int[] horizonPoint = {1920/2, 1080/2, 500000}; //X,Y,Z
    int[] horizonPoint = {this.getWidth()/2, this.getWidth()/2, 500}; //X,Y,Z
    int startSize=5;
    long prevTime = java.lang.System.nanoTime();
    int[] lineCoordinates = new int[6]; // 0=X1, 1=Y1, 2=Z1, 3=X2, 4=Y2, 5=Z2(depth)
    ArrayList<int[]> allLines = new ArrayList<>(); // 0=X1, 1=Y1, 2=Z1, 3=X2, 4=Y2, 5=Z2(depth)

    void drawRotatingPlane(){
        int counter = 0;

        /* lineCoordinates[0] = 50;
        lineCoordinates[1] = 50;
        lineCoordinates[2] = 0;
        lineCoordinates[3] = 50;
        lineCoordinates[4] = 50;
        lineCoordinates[5] = 10000; */
        /* if(allLines.size()<1) */ //allLines.add(lineCoordinates.clone());
        /* else allLines.set(0, lineCoordinates.clone()); */
        //addLine(50, 50, 0, 50, 50, 500000);
        //addLine(100, 50, 0, 100, 50, 50000);
        //addLine(50, 50, 0, 100, 50, 0);
        //addLine(50, 50, 50000, 100, 50, 50000);
        //addLine(50, 50, 0, 100, 50, 0);

        /* addLine(1920/2-50, 1080/2-50, 0, 1920/2-50, 1080/2-50, 200000);
        addLine(1920/2+50, 1080/2-50, 0, 1920/2+50, 1080/2-50, 200000);
        addLine(1920/2-50, 1080/2-50, 0, 1920/2+50, 1080/2-50, 0);
        addLine(1920/2-50, 1080/2-50, 200000, 1920/2+50, 1080/2-50, 200000); */

        addCube(1920/2, 1080/2, 0, 50);


        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>50000000){
                prevTime=currentTime;
                /* Start of algorithm */
                
                /* int lineAmount = 15;
                for(int i=0; i<lineAmount; i++){
                    lineCoordinates[0] = 50 + i*50;
                    lineCoordinates[1] = 50;
                    lineCoordinates[2] = 0;
                    lineCoordinates[3] = 400;
                    lineCoordinates[4] = 500;
                    lineCoordinates[5] = 0;
                    if(allLines.size()<lineAmount) allLines.add(lineCoordinates.clone());
                    else allLines.set(i, lineCoordinates.clone());
                    //System.out.println(i + " " + lineCoordinates[0] + " " + allLines.get(i)[0]);
                    /* if(i>0) {
                        System.out.println(0 + " " + allLines.get(0)[0]);
                        System.out.println(1 + " " + allLines.get(1)[0]);
                    } 
                } */

                /* lineCoordinates[0] = 50;
                lineCoordinates[1] = 70;
                lineCoordinates[2] = 100;
                lineCoordinates[3] = 400;
                lineCoordinates[4] = 50;
                lineCoordinates[5] = 100;
                if(allLines.size()<2) allLines.add(lineCoordinates.clone());
                else allLines.set(1, lineCoordinates.clone()); */

                /* System.out.println(0 + " " + allLines.get(0)[0]);
                System.out.println(1 + " " + allLines.get(1)[0]);
                System.out.println(); */

                /* for(int i=0; i<6; i++){
                    System.out.print(allLines.get(0)[i] + " ");
                }
                System.out.println(allLines.size() + "\n");
                for(int i=0; i<6; i++){
                    System.out.print(allLines.get(1)[i] + " ");
                }
                System.out.println();
                System.out.println(); */

                horizonPoint[0] = this.getWidth()/2;
                horizonPoint[1] = this.getHeight()/2;
                
                /* End of algorithm */
                repaint();
            }
        }
    }

    public void addLine(int x1, int y1, int z1, int x2, int y2, int z2){
        int[] temp = {x1, y1, z1, x2, y2, z2};
        allLines.add(temp.clone());
    }

    public void addCube(int x1, int y1, int z1, int radius){
        /* addLine(x1-radius, y1-radius, z1-radius, x1-radius, y1-radius, z1+radius);
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1-radius, z1+radius);
        addLine(x1-radius, y1-radius, z1+radius, x1-radius, y1-radius, z1+radius);
        addLine(x1+radius, y1-radius, z1+radius, x1+radius, y1-radius, z1-radius); */

        //top plane
        addLine(x1-radius, y1-radius, z1-radius, x1-radius, y1-radius, z1+radius); //left line
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1-radius, z1+radius); //right line
        addLine(x1-radius, y1-radius, z1-radius, x1+radius, y1-radius, z1-radius); //front line
        addLine(x1-radius, y1-radius, z1+radius, x1+radius, y1-radius, z1+radius); //back line

        //bottom plane
        addLine(x1+radius, y1+radius, z1-radius, x1+radius, y1+radius, z1+radius); //left line
        addLine(x1-radius, y1+radius, z1-radius, x1-radius, y1+radius, z1+radius); //right line 
        addLine(x1-radius, y1+radius, z1-radius, x1+radius, y1+radius, z1-radius); //front line
        addLine(x1-radius, y1+radius, z1+radius, x1+radius, y1+radius, z1+radius); //back line

        //left plane
        addLine(x1-radius, y1-radius, z1+radius, x1-radius, y1+radius, z1+radius); //front line
        addLine(x1-radius, y1-radius, z1-radius, x1-radius, y1+radius, z1-radius); //back line

        //right plane
        addLine(x1+radius, y1-radius, z1+radius, x1+radius, y1+radius, z1+radius); //front line
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1+radius, z1-radius); //back line
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.setPaint(Color.blue);
        for(int i=0; i<allLines.size() ; i++){


            /*  Adjust coordinates X and Y
                Project the point as if it was located on Z=0, then draw (calculate, not actually draw) a line from the new point and the horizon point.
                Afterwards calculate where the line reaches the original Z value and draw the dot on the new position. This should create perspective.
                We do this to the original dots then the rest of the dots that make the complete line are filled in as normal
            */

           /* int[] projectedZeroZ = new int[6];
            for(int j=0; j<allLines.get(i).length; j++){
                projectedZeroZ[j] = allLines.get(i)[j];
            }
            projectedZeroZ[2] = 0;
            projectedZeroZ[5] = 0; //make the value of Z = 0 */

            //find actual horizontal distance between dots, then multiply that by the relative Z distance, then add the original X back and you have your new X distance
            int newX1 = (int)((horizonPoint[0]-allLines.get(i)[0])*(double)allLines.get(i)[2]/horizonPoint[2]+allLines.get(i)[0]); 
            int newY1 = (int)((horizonPoint[1]-allLines.get(i)[1])*(double)allLines.get(i)[2]/horizonPoint[2]+allLines.get(i)[1]); 
            int newX2 = (int)((horizonPoint[0]-allLines.get(i)[3])*(double)allLines.get(i)[5]/horizonPoint[2]+allLines.get(i)[3]); //System.out.println((double)allLines.get(i)[5]/horizonPoint[2]);
            int newY2 = (int)((horizonPoint[1]-allLines.get(i)[4])*(double)allLines.get(i)[5]/horizonPoint[2]+allLines.get(i)[4]);

            //now we have the new coordinates of the dot, so we have to place them into a usable format
            int[] depthLineCoordinates = new int[6];
            depthLineCoordinates[0] = newX1;
            depthLineCoordinates[1] = newY1;
            depthLineCoordinates[2] = allLines.get(i)[2];
            depthLineCoordinates[3] = newX2;
            depthLineCoordinates[4] = newY2;
            depthLineCoordinates[5] = allLines.get(i)[5];


            // Turn coordinates from previous table of lines to displayable dots (old version)
            //int lineLength = (int) Math.sqrt( Math.pow(allLines.get(i)[3]-allLines.get(i)[0],2) + Math.pow(allLines.get(i)[4]-allLines.get(i)[1],2) + Math.pow(allLines.get(i)[5]-allLines.get(i)[2],2));
            
            //new version
            int lineLength = (int) Math.sqrt( Math.pow(depthLineCoordinates[3]-depthLineCoordinates[0],2) + Math.pow(depthLineCoordinates[4]-depthLineCoordinates[1],2) );
            //System.out.println(allLines.get(i)[3]);
            
            int[][] finalDotCoordinates = new int[lineLength][3];
            for(int j=0; j<lineLength; j+=1){
                //old system without perspective
                /* finalDotCoordinates[j][0] = allLines.get(i)[0]+(int)((allLines.get(i)[3]-allLines.get(i)[0])*(double)j/lineLength);
                finalDotCoordinates[j][1] = allLines.get(i)[1]+(int)((allLines.get(i)[4]-allLines.get(i)[1])*(double)j/lineLength);
                finalDotCoordinates[j][2] = allLines.get(i)[2]+(int)((allLines.get(i)[5]-allLines.get(i)[2])*(double)j/lineLength); */

                //new system with perspective
                finalDotCoordinates[j][0] = depthLineCoordinates[0]+(int)((depthLineCoordinates[3]-depthLineCoordinates[0])*(double)j/lineLength);
                finalDotCoordinates[j][1] = depthLineCoordinates[1]+(int)((depthLineCoordinates[4]-depthLineCoordinates[1])*(double)j/lineLength);
                finalDotCoordinates[j][2] = depthLineCoordinates[2]+(int)((depthLineCoordinates[5]-depthLineCoordinates[2])*(double)j/lineLength);


                if(finalDotCoordinates[j][0]==0 && finalDotCoordinates[j][1]==0 && finalDotCoordinates[j][2]==0) continue;


                //int blueTint = 255/2 - finalDotCoordinates[j][2]/1; //System.out.println(finalDotCoordinates[j][2]);
                int alphaValue = 255 - (int)((double)finalDotCoordinates[j][2]/horizonPoint[2]*255);
                int brightness = 0;
                if(alphaValue>255) {
                    brightness = alphaValue-255;
                    alphaValue = 255;
                }
                else if(alphaValue<0) {
                    alphaValue = 0;
                }
                gtd.setPaint(new Color(brightness,brightness,255,alphaValue));
                
                //int drawSize = (int)((1-(double)finalDotCoordinates[j][2]/lineLength)*startSize);
                //int drawSize = startSize;
                int drawSize = startSize - (int)((double)finalDotCoordinates[j][2]/horizonPoint[2]*startSize);

                gtd.fillOval(finalDotCoordinates[j][0], finalDotCoordinates[j][1], drawSize, drawSize);

            }
        }
    }
}
