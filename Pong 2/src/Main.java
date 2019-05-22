import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Pong");
        Pong g = new Pong();
        frame.setContentPane(g);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}