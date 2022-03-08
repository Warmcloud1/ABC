import com.sun.deploy.util.LinkMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameJPanel extends JPanel {
    private Image img;
    private int x;
    private int y;
    private int dir = 0;

    //call back function 你無法知道何時觸發
    public class MyListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            move();
            changeDir(e.getX());
            //System.out.println(e.getX() + " " + e.getY());
        }
    }

    public GameJPanel() {
        x = 30;
        y = 250;
        dir = 0;
        try {
            this.img = ImageIO.read(getClass().getResource("/resources/airplane1.png")); // "/"開頭為目前檔案的相對路徑"/resources/airplane1.png"  或打C:/...
        } catch (IOException ex) {
            Logger.getLogger(GameJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.addMouseListener(new MyListener());
        this.addMouseMotionListener(new MyListener());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, x, y, this);
    }

    public void move() {
        if (dir == 0) {
            x += 4;
        } else {
            x -= 4;
        }
    }

    public void changeDir(int x) {
        if (this.x > x) {
            dir = 1;
        } else {
            dir = 0;
        }
    }
}
