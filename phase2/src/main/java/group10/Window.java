package group10;

import javax.swing.*;

/**
 * <code>Window</code>
 * the window object displayed to the user.
 */
public class Window extends JFrame{
    /**
     * Default constructor
     */
    Window () {
        String title = "Spirit";
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(title);
        this.setVisible(true);
        SoundHandler.RunMusic("phase2/src/main/resources/Audio/276game.wav");
    }

    /**
     * <code>switchPanel</code>
     * Changes the current JPanel in the frame.
     * @param old the current panel to be removed.
     * @param p the new panel to add.
     */
    public void switchPanel(JPanel old, JPanel p) {
        this.invalidate();
        this.remove(old);
        this.add(p);
        this.validate();
        this.repaint();
    }
}
