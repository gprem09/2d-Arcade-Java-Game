package group10;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelpPanel extends JPanel implements ActionListener{
    BufferedImage helpscreen;
    KeyboardHandler key;
    Window w;
    Mainmenu m;

    HelpPanel(Mainmenu main, Window win) {
        this.setLayout(null);
        this.setPreferredSize(new DimensionUIResource(Gamepanel.screenWidth, Gamepanel.screenHeight));
        this.setVisible(true);
        this.setBackground(Color.white);
        try {
            this.helpscreen = ImageIO.read(getClass().getResourceAsStream("/backgrounds/helpscreen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int image_w = Gamepanel.screenWidth;
        int image_h = Gamepanel.screenHeight;
        int hints = Image.SCALE_DEFAULT;
        Image scaled_img = helpscreen.getScaledInstance(image_w, image_h, hints);
        ImageIcon icon = new ImageIcon(scaled_img);
        JLabel picLabel = new JLabel(icon);
        add(picLabel);
        m = main;
        key = new KeyboardHandler();
        this.addKeyListener(key);



    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(helpscreen, 0, 0, Gamepanel.screenWidth, Gamepanel.screenHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(key.restart) {
            w.switchPanel(this, m);
        }
    }
}
