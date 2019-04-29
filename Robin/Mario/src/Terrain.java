import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public abstract class Terrain extends JPanel implements ActionListener {

    // Creating images for single objects
    protected Image rz_background = new ImageIcon("Background.png").getImage(); // Background Image


    Image obj = rz_background; // Temporary Image reference

    final private int BKMIN_X = 1000, BKMAX_X = 10000; // Min and Max of
    // background
    public int bk_x = 695; // background x and y coordinates
    public int bk_y = 535;
    public int rz_x = 600; // character x and y coordinates
    public int rz_y = 615;

    static int direction = 0; // 0=still 1=up , 2=right , 3=left , 4=down

    static boolean moveableRight = true; // variable for collision detection
    static boolean moveableLeft = true;
    static boolean moveableDown = false;
    boolean jumpright = true;

    static boolean jump = false; // For jump
    private Timer time;

    int run = 0;

}