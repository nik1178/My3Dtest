
import javax.swing.*;
import java.awt.*;
public class TranslationTest extends JFrame{
    public static void main(String[] args){
        new TranslationTest();
    }

    TestPanel panel = new TestPanel();

    TranslationTest(){
        
        this.add(panel);

        //this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        /* getLineCoordinates(); */
    }
}
class TestPanel extends JPanel{
    TestPanel(){
        this.setPreferredSize(new Dimension(500,500));
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(50, 50);
        g2.translate(50, 50);
        g2.translate(50, 50);
        g2.translate(50, 50);
        g2.drawRect(50, 50, 50, 50);
    }
}