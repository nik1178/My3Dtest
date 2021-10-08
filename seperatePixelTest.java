import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import javax.swing.*;

public class seperatePixelTest extends JFrame{
    public static void main(String[] args) throws AWTException, IOException {
        new seperatePixelTest();
    }


    Robot r = new Robot();
    JLabel iconLabel = new JLabel();
    JTextField textField;

    seperatePixelTest() throws AWTException, IOException{

        BufferedImage bi;

        bi = r.createScreenCapture(new Rectangle(1920/2-150,35,300,35));
        ImageIcon ii = new ImageIcon(bi);
        iconLabel.setIcon(ii);

        this.add(iconLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        int counter = 0;
        while(true){

            BufferedImage blackSquare = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

            int recx = 1920/2-150;
            int recy = 35;

            BufferedImage bi1 = r.createScreenCapture(new Rectangle(recx,recy,300,35));

            for (int x = 0; x < bi.getWidth(); x++) {
                for (int y = 0; y < bi.getHeight(); y++) {
                    if (bi.getRGB(x, y) != bi1.getRGB(x, y)){
                        System.out.println("shit" + counter);
                        x=bi.getWidth();
                        y=bi.getHeight();
                        counter++;
                    }
                }
            }

            bi=bi1;

            PointerInfo pointer = MouseInfo.getPointerInfo();
            int x = (int) pointer.getLocation().getX();
            int y = (int) pointer.getLocation().getY();
            //bi.getGraphics().drawImage(blackSquare, x-recx, y-recy, null);

            ii = new ImageIcon(bi);
            iconLabel.setIcon(ii);
        }
            
    }
}
