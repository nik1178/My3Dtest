import javax.swing.*;

public class JFrameTest extends JFrame{
    public static void main(String[] args) {
        new JFrameTest();
    }

    JFrameTest(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400,400,500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
