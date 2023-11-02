package group10;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * <code>Mainmenu</code>
 * the game main menu object to start/close the game.
 */
public class Mainmenu extends JPanel implements ActionListener{
    JButton start;
    JButton help;
    JButton exit;
    Window f;
    int screenWidth = Gamepanel.screenWidth;
    int screenHeight = Gamepanel.screenHeight;
    int xOffset = screenWidth/2;
    int yOffset = screenHeight/2 - 15;

    /**
     * Class Constructor with the window that holds the Mainmenu
     * @param f the JFrame which holds the Mainmenu
     */
    Mainmenu(Window f) {
        start = createButton(xOffset - 75, yOffset);
        help = createButton(xOffset - 75, yOffset + 57);
        exit = createButton(xOffset - 75, yOffset + 114);

        this.f = f;
        this.setLayout(null);
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setVisible(true);
        this.setBackground(Color.black);
        BufferedImage bgimg;
        JLabel bg = new JLabel();
        bg.setSize(Gamepanel.screenWidth, Gamepanel.screenHeight);
        bg.setBounds(0,0,Gamepanel.screenWidth, Gamepanel.screenHeight);
        try {
            bgimg = ImageIO.read(getClass().getResourceAsStream(("/backgrounds/mainmenu.png")));
            ImageIcon icon = new ImageIcon(bgimg.getScaledInstance(Gamepanel.screenWidth, Gamepanel.screenHeight, Image.SCALE_DEFAULT));
            bg.setIcon(icon);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.add(bg);
        this.add(start);
        this.add(help);
        this.add(exit);
        this.setComponentZOrder(bg, 3);
    }

    /**
     * <code>createButton</code>
     * creates a JButton in the given position with the input string.
     * @param x the x position on the screen.
     * @param y the y position on the screen.
     * @return the created JButton
     */
    private JButton createButton (int x, int y) {
        JButton button = new JButton();
        button.setBounds(x,y,145,46);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setBackground(Color.lightGray);
        button.setOpaque(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // act based on which button was pressed
        if (e.getSource() == start) {
            Gamepanel p = new Gamepanel();
            f.switchPanel(this, p);
            p.startGameThread();
        }
        else if (e.getSource() == help) {
            System.out.println("Display help");
            HelpPanel help_display = new HelpPanel(this, f);
            f.switchPanel(this, help_display);

        }
        else if (e.getSource() == exit) {
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        }

    }
}
