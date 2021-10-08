
import java.util.*;
import javax.swing.*;

import java.awt.event.*;

import java.awt.*;

public class RotatingPlanev6 extends JPanel implements Runnable, MouseWheelListener, MouseMotionListener, MouseListener{
    RotatingPlanev6(){
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
        drawRotatingPlane();
    }

    /* Developing notes 
    *  Horizon is 5km away. Every 100 depth coordinates = 1m aka. every Z coordinate is 1cm. 5km*1000m*100cm = 500000 maximum depth by default.
        This value should work in the same way as changing the focal length on a camera.
    *  TODO: 
    *
    */


    //int[] horizonPoint = {1920/2, 1080/2, 500000}; //X,Y,Z
    int[] horizonPoint = {this.getWidth()/2, this.getWidth()/2, 500}; //X,Y,Z
    int startSize=5;
    long prevTime = java.lang.System.nanoTime();
    int[] lineCoordinates = new int[6]; // 0=X1, 1=Y1, 2=Z1, 3=X2, 4=Y2, 5=Z2(depth)
    ArrayList<int[]> allLines = new ArrayList<>(); // 0=X1, 1=Y1, 2=Z1, 3=X2, 4=Y2, 5=Z2(depth)

    /* int rotationalPointX = 1920/2;
    int rotationalPointY = 1080/2;
    int rotationalPointZ = 0; */

    void drawRotatingPlane(){

        addCube(0, 0, 0, 50);
        //addCube(1920/2-50, 1080/2, 0, 20);
        //addCube(1920/2+50, 1080/2, 0, 20);
        //addCube(1920/2+50, 1080/2, -1000, 20);
        //addCube(200, 200, 0, 100);
        while(true){
            long currentTime=java.lang.System.nanoTime();
            if(currentTime-prevTime>50000000){
                prevTime=currentTime;
                /* Start of algorithm */
                


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

        //old incorrect rendering version
        /* //top plane
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
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1+radius, z1-radius); //back line */

        //new gigachad version
        //back plane
        addLine(x1-radius, y1+radius, z1+radius, x1+radius, y1+radius, z1+radius); //bottom line
        addLine(x1-radius, y1-radius, z1+radius, x1+radius, y1-radius, z1+radius); //top line
        addLine(x1-radius, y1-radius, z1+radius, x1-radius, y1+radius, z1+radius); //left line
        addLine(x1+radius, y1-radius, z1+radius, x1+radius, y1+radius, z1+radius); //right line

        //side planes
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1-radius, z1+radius); //top right line
        addLine(x1-radius, y1-radius, z1-radius, x1-radius, y1-radius, z1+radius); //top left line
        addLine(x1+radius, y1+radius, z1-radius, x1+radius, y1+radius, z1+radius); //bottom right line
        addLine(x1-radius, y1+radius, z1-radius, x1-radius, y1+radius, z1+radius); //bottom left line 

        //front plane
        addLine(x1-radius, y1-radius, z1-radius, x1+radius, y1-radius, z1-radius); //top line
        addLine(x1-radius, y1+radius, z1-radius, x1+radius, y1+radius, z1-radius); //bottom line
        addLine(x1-radius, y1-radius, z1-radius, x1-radius, y1+radius, z1-radius); //left line
        addLine(x1+radius, y1-radius, z1-radius, x1+radius, y1+radius, z1-radius); //right line

    }

    int counter = 0;

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

            //rotation

            //int maxDistanceFromCenter = (int)Math.sqrt(50*50 + 50*50 + 50*50);
            //int maxHorizontalDistanceFromCenter = (int)Math.sqrt(50*50 + 50*50);

            /* if(allLines.get(i)[0]<1920/2+maxDistanceFromCenter && allLines.get(i)[2]>0) allLines.get(i)[0]++;
            else allLines.get(i)[0]--;
            if(allLines.get(i)[2]>-50 && allLines.get(i)[0]>1920/2) allLines.get(i)[2]++;
            else allLines.get(i)[2]--;
            */
            int[] coordinatesCopy = allLines.get(i).clone();

            /* if(coordinatesCopy[0]<maxHorizontalDistanceFromCenter+rotationalPointX) */

            /* coordinatesCopy[0] = rotationalPointX + (int) ((allLines.get(i)[0] - rotationalPointX) * Math.cos(counter/10.0-3.14*(allLines.get(i)[0] - rotationalPointX)/maxHorizontalDistanceFromCenter*(allLines.get(i)[2] - rotationalPointZ)/maxHorizontalDistanceFromCenter));
            //coordinatesCopy[1] = rotationalPointY + (int) ((allLines.get(i)[1] - rotationalPointY) * Math.cos(counter/10.0-3.14*(allLines.get(i)[1] - rotationalPointY)/maxHorizontalDistanceFromCenter));
            //coordinatesCopy[2] = rotationalPointZ + (int) ((allLines.get(i)[2] - rotationalPointZ) * Math.sin(counter/10.0));
            coordinatesCopy[3] = rotationalPointX + (int) ((allLines.get(i)[3] - rotationalPointX) * Math.cos(counter/10.0-3.14*(allLines.get(i)[3] - rotationalPointX)/maxHorizontalDistanceFromCenter*(allLines.get(i)[2] - rotationalPointZ)/maxHorizontalDistanceFromCenter));
            //coordinatesCopy[4] = rotationalPointY + (int) ((allLines.get(i)[4] - rotationalPointY) * Math.cos(counter/10.0-3.14*(allLines.get(i)[4] - rotationalPointY)/maxHorizontalDistanceFromCenter));
            //coordinatesCopy[5] = rotationalPointZ + (int) ((allLines.get(i)[5] - rotationalPointZ) * Math.sin(counter/10.0-3.14*(allLines.get(i)[5] - rotationalPointZ)/maxHorizontalDistanceFromCenter)); */

            /* coordinatesCopy[0] = rotationalPointX + (int) ((allLines.get(i)[0] - rotationalPointX) * Math.cos(counter/10.0));
            coordinatesCopy[1] = rotationalPointY + (int) ((allLines.get(i)[1] - rotationalPointY) * Math.cos(counter/10.0));
            coordinatesCopy[2] = rotationalPointZ + (int) ((allLines.get(i)[2] - rotationalPointZ) * Math.sin(counter/10.0));
            coordinatesCopy[3] = rotationalPointX + (int) ((allLines.get(i)[3] - rotationalPointX) * Math.cos(counter/10.0));
            coordinatesCopy[4] = rotationalPointY + (int) ((allLines.get(i)[4] - rotationalPointY) * Math.cos(counter/10.0));
            coordinatesCopy[5] = rotationalPointZ + (int) ((allLines.get(i)[5] - rotationalPointZ) * Math.sin(counter/10.0)); */

            /* if(allLines.get(i)[0]<rotationalPointX+50 && reachedZero)allLines.get(i)[0]++;
            else allLines.get(i)[0]--; */

            /* coordinatesCopy[0] = 
            coordinatesCopy[2] = 
            coordinatesCopy[3] = 
            coordinatesCopy[5] =  */

            double heading = Math.toRadians(counter);
            MyMatrix3 headingTransform = new MyMatrix3(new double[] {
                Math.cos(heading), 0, Math.sin(heading),
                0, 1, 0,
                -Math.sin(heading), 0, Math.cos(heading)
            });
            double pitch = Math.toRadians(counter);
            MyMatrix3 pitchTransform = new MyMatrix3(new double[] {
                    1, 0, 0,
                    0, Math.cos(pitch), Math.sin(pitch),
                    0, -Math.sin(pitch), Math.cos(pitch)
                });
            MyMatrix3 transform = headingTransform/* .multiply(pitchTransform) */;
            coordinatesCopy = transform.transform(coordinatesCopy).clone();

            coordinatesCopy[0] += 960;
            coordinatesCopy[1] += 540;
            coordinatesCopy[3] += 960;
            coordinatesCopy[4] += 540;

            //rotation end

            //find actual horizontal distance between dots, then multiply that by the relative Z distance, then add the original X back and you have your new X distance
            int newX1 = (int)((horizonPoint[0]-coordinatesCopy[0])*(double)coordinatesCopy[2]/horizonPoint[2]+coordinatesCopy[0]); 
            int newY1 = (int)((horizonPoint[1]-coordinatesCopy[1])*(double)coordinatesCopy[2]/horizonPoint[2]+coordinatesCopy[1]); 
            int newX2 = (int)((horizonPoint[0]-coordinatesCopy[3])*(double)coordinatesCopy[5]/horizonPoint[2]+coordinatesCopy[3]); //System.out.println((double)coordinatesCopy[5]/horizonPoint[2]);
            int newY2 = (int)((horizonPoint[1]-coordinatesCopy[4])*(double)coordinatesCopy[5]/horizonPoint[2]+coordinatesCopy[4]);

            //now we have the new coordinates of the dot, so we have to place them into a usable format
            int[] depthLineCoordinates = new int[6];
            depthLineCoordinates[0] = newX1;
            depthLineCoordinates[1] = newY1;
            depthLineCoordinates[2] = coordinatesCopy[2];
            depthLineCoordinates[3] = newX2;
            depthLineCoordinates[4] = newY2;
            depthLineCoordinates[5] = coordinatesCopy[5];

            /* int[] depthLineCoordinates = coordinatesCopy.clone(); */


            // Turn coordinates from previous table of lines to displayable dots (old version)
            //int lineLength = (int) Math.sqrt( Math.pow(allLines.get(i)[3]-allLines.get(i)[0],2) + Math.pow(allLines.get(i)[4]-allLines.get(i)[1],2) + Math.pow(allLines.get(i)[5]-allLines.get(i)[2],2));
            
            //new version
            int lineLength = (int) Math.sqrt( Math.pow(depthLineCoordinates[3]-depthLineCoordinates[0],2) + Math.pow(depthLineCoordinates[4]-depthLineCoordinates[1],2) );
            //System.out.println(allLines.get(i)[3]);
            //gtd.translate(1920/2, 1080/2);
            int[] finalDotCoordinates = new int[3];
            for(int j=0; j<lineLength; j+=1){
                //old system without perspective
                /* finalDotCoordinates[j][0] = allLines.get(i)[0]+(int)((allLines.get(i)[3]-allLines.get(i)[0])*(double)j/lineLength);
                finalDotCoordinates[j][1] = allLines.get(i)[1]+(int)((allLines.get(i)[4]-allLines.get(i)[1])*(double)j/lineLength);
                finalDotCoordinates[j][2] = allLines.get(i)[2]+(int)((allLines.get(i)[5]-allLines.get(i)[2])*(double)j/lineLength); */

                //new system with perspective
                finalDotCoordinates[0] = depthLineCoordinates[0]+(int)((depthLineCoordinates[3]-depthLineCoordinates[0])*(double)j/lineLength);
                finalDotCoordinates[1] = depthLineCoordinates[1]+(int)((depthLineCoordinates[4]-depthLineCoordinates[1])*(double)j/lineLength);
                finalDotCoordinates[2] = depthLineCoordinates[2]+(int)((depthLineCoordinates[5]-depthLineCoordinates[2])*(double)j/lineLength);


                if(finalDotCoordinates[0]==0 && finalDotCoordinates[1]==0 && finalDotCoordinates[2]==0) continue;
                //if(finalDotCoordinates[2]>horizonPoint[2]) continue;


                //int blueTint = 255/2 - finalDotCoordinates[2]/1; //System.out.println(finalDotCoordinates[2]);
                int alphaValue = 255 - (int)((double)finalDotCoordinates[2]/horizonPoint[2]*255);
                int brightness = 0;
                int blueTint = 255;
                if(alphaValue>255) {
                    brightness = alphaValue-255;
                    alphaValue = 255;
                }
                else if(alphaValue<0) {
                    blueTint-=alphaValue;
                    alphaValue = 0;
                }
                if(blueTint<0) blueTint=0;
                if(brightness>255) brightness=255;
                if(brightness<0) brightness=0;
                if(blueTint>255) blueTint=255;
                gtd.setPaint(new Color(brightness,brightness,blueTint,alphaValue));
                
                //int drawSize = (int)((1-(double)finalDotCoordinates[2]/lineLength)*startSize);
                //int drawSize = startSize;
                int drawSize = startSize - (int)((double)finalDotCoordinates[2]/horizonPoint[2]*startSize);

                gtd.fillOval(finalDotCoordinates[0], finalDotCoordinates[1], drawSize, drawSize);

            }
        }
        counter++;
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        int notches = e.getWheelRotation();
        if(notches<0){
            horizonPoint[2]-=50;
            if(horizonPoint[2]<10) horizonPoint[2] = 10;
        } else{
            horizonPoint[2]+=50;
        }
    }
    int[] startMousePos = new int[2];
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

        horizonPoint[0] += startMousePos[0] - e.getX(); 
        horizonPoint[1] += startMousePos[1] - e.getY();
        startMousePos[0] = e.getX();
        startMousePos[1] = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    // MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        startMousePos[0] = e.getX();
        startMousePos[1] = e.getY();
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
class MyMatrix3 {
    double[] values;
    MyMatrix3(double[] values) {
        this.values = values;
    }
    MyMatrix3 multiply(MyMatrix3 other) {
        double[] result = new double[9];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int i = 0; i < 3; i++) {
                    result[row * 3 + col] +=
                        this.values[row * 3 + i] * other.values[i * 3 + col];
                }
            }
        }
        return new MyMatrix3(result);
    }
    public int[] transform(int[] in) {
        int[] out = {
            (int)(in[0] * values[0] + in[0] * values[3] + in[0] * values[6]),
            (int)(in[1] * values[1] + in[1] * values[4] + in[1] * values[7]),
            (int)(in[2] * values[2] + in[2] * values[5] + in[2] * values[8]),
            (int)(in[3] * values[0] + in[3] * values[3] + in[3] * values[6]),
            (int)(in[4] * values[1] + in[4] * values[4] + in[4] * values[7]),
            (int)(in[5] * values[2] + in[5] * values[5] + in[5] * values[8])
        };
        return out;
    }
}