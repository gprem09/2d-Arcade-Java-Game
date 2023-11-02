package group10;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <code>Gamepanel</code>
 * holds and runs the game instance.
 */
public class Gamepanel extends JPanel implements Runnable {
    // CONSTANTS for screen sizing
    static int tileSize = 32;
    static int tileCols = 25;
    static int tileRows = 20;
    static int screenWidth = tileSize * tileCols;
    static int screenHeight = tileSize * tileRows;

    double FPS = 5;
    double timer = 0;
    boolean just_died = true;

    Boolean collected_all_rewards = false;

    // Required for game
    Thread gameThread;
    KeyboardHandler keyboard;

    // Game entities
    Player player;
    ArrayList<Entity> barrierList = new ArrayList<>();
    ArrayList<Entity> punishList = new ArrayList<>();
    ArrayList<Enemy> movingEnemies = new ArrayList<>();
    ArrayList<BonusReward> bonusRewards = new ArrayList<>();
    ArrayList<BasicReward> basicRewards = new ArrayList<>();
    ArrayList<Entity> entityList;
    BufferedImage bg;
    WinTile wintile = new WinTile(21 * tileSize, 9 * tileSize);
    BufferedImage winscreen;
    BufferedImage deathscreen;

    /*
    IMPORTANT NOTE:
    Please use a number from
    0 - tileCols and multiply by tileSize for x, and
    0 - tileRows and multiply by tileSize for y.
    Since the game is supposed to be tiled/cell based like a grid.
    Ignoring this will break collision, and will stop the game from functioning properly.
     */

    int defaultX = 2 * tileSize, defaultY = 2 * tileSize;

    /**
     * Class Constructor.
     * Initializes everything before the game starts.
     */
    Gamepanel() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.keyboard = new KeyboardHandler();
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        this.setVisible(true);
        this.entityList = new ArrayList<>();
        this.setUpEntities();
        this.player = new Player(defaultX, defaultY, this.keyboard);
        try {
            this.bg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/bg_1.png")));
            this.winscreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/winscreen.png")));
            this.deathscreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/deathscreen.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>setUpEntities</code>
     * Sets up all entities in the game.
     */
    private void setUpEntities () {
        setUpBarriers();
        setUpRewards();
        setupEnemies();
    }
    /**
     * <code>setUpBarriers</code>
     * Sets up all barriers in the game.
     */
    private void setUpBarriers () {
        setUpWalls();
        setupChairs();
        setupDesks();
        setupTables();
        setupBookshelf();
        this.entityList.addAll(barrierList);
    }
    /**
     * <code>setUpWalls</code>
     * Sets up all walls in the game.
     */
    private void setUpWalls () {
        for (int i = 0; i < tileCols; i++) {
            barrierList.add(new Wall(tileSize * i, 0));
        }
        for (int i = 0; i < tileCols; i++) {
            barrierList.add(new Wall(tileSize * i, screenHeight-tileSize));
        }
        for (int i = 1; i < tileRows-1; i++) {
            barrierList.add(new Wall(0, tileSize * i));
        }
        for (int i = 1; i < tileRows-1; i++) {
            barrierList.add(new Wall(screenWidth - tileSize, tileSize * i));
        }
    }
    /**
     * <code>setUpChairs</code>
     * Sets up all chairs in the game.
     */
    private void setupChairs () {
        this.barrierList.add(new Chair( 6 * tileSize, 3 * tileSize));
        this.barrierList.add(new Chair( 6 * tileSize, 6 * tileSize));
        this.barrierList.add(new Chair( 6 * tileSize, 9 * tileSize));
        this.barrierList.add(new Chair( 6 * tileSize, 12 * tileSize));
        this.barrierList.add(new Chair( 6 * tileSize, 15 * tileSize));
        this.barrierList.add(new Chair( 6 * tileSize, 18 * tileSize));

        this.barrierList.add(new Chair( 9 * tileSize, 3 * tileSize));
        this.barrierList.add(new Chair( 9 * tileSize, 6 * tileSize));
        this.barrierList.add(new Chair( 9 * tileSize, 9 * tileSize));
        this.barrierList.add(new Chair( 9 * tileSize, 12 * tileSize));
        this.barrierList.add(new Chair( 9 * tileSize, 15 * tileSize));
        this.barrierList.add(new Chair( 9 * tileSize, 18 * tileSize));

        this.barrierList.add(new Chair( 12 * tileSize, 3 * tileSize));
        this.barrierList.add(new Chair( 12 * tileSize, 6 * tileSize));
        this.barrierList.add(new Chair( 12 * tileSize, 9 * tileSize));
        this.barrierList.add(new Chair( 12 * tileSize, 12 * tileSize));
        this.barrierList.add(new Chair( 12 * tileSize, 15 * tileSize));
        this.barrierList.add(new Chair( 12 * tileSize, 18 * tileSize));

        this.barrierList.add(new Chair( 15 * tileSize, 3 * tileSize));
        this.barrierList.add(new Chair( 15 * tileSize, 6 * tileSize));
        this.barrierList.add(new Chair( 15 * tileSize, 9 * tileSize));
        this.barrierList.add(new Chair( 15 * tileSize, 12 * tileSize));
        this.barrierList.add(new Chair( 15 * tileSize, 15 * tileSize));
        this.barrierList.add(new Chair( 15 * tileSize, 18 * tileSize));

        this.barrierList.add(new Chair( 18 * tileSize, 3 * tileSize));
        this.barrierList.add(new Chair( 18 * tileSize, 6 * tileSize));
        this.barrierList.add(new Chair( 18 * tileSize, 9 * tileSize));
        this.barrierList.add(new Chair( 18 * tileSize, 12 * tileSize));
        this.barrierList.add(new Chair( 18 * tileSize, 15 * tileSize));
        this.barrierList.add(new Chair( 18 * tileSize, 18 * tileSize));
    }
    /**
     * <code>setUpDesks</code>
     * Sets up all desks in the game.
     */
    private void setupDesks () {
        this.barrierList.add(new Desk(5 * tileSize, 3 * tileSize));
        this.barrierList.add(new Desk(5 * tileSize, 6 * tileSize));
        this.barrierList.add(new Desk(5 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(5 * tileSize, 12 * tileSize));
        this.barrierList.add(new Desk(5 * tileSize, 15 * tileSize));
        this.barrierList.add(new Desk(5 * tileSize, 18 * tileSize));

        this.barrierList.add(new Desk(8 * tileSize, 3 * tileSize));
        this.barrierList.add(new Desk(8 * tileSize, 6 * tileSize));
        this.barrierList.add(new Desk(8 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(8 * tileSize, 12 * tileSize));
        this.barrierList.add(new Desk(8 * tileSize, 15 * tileSize));
        this.barrierList.add(new Desk(8 * tileSize, 18 * tileSize));

        this.barrierList.add(new Desk(11 * tileSize, 3 * tileSize));
        this.barrierList.add(new Desk(11 * tileSize, 6 * tileSize));
        this.barrierList.add(new Desk(11 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(11 * tileSize, 12 * tileSize));
        this.barrierList.add(new Desk(11 * tileSize, 15 * tileSize));
        this.barrierList.add(new Desk(11 * tileSize, 18 * tileSize));

        this.barrierList.add(new Desk(14 * tileSize, 3 * tileSize));
        this.barrierList.add(new Desk(14 * tileSize, 6 * tileSize));
        this.barrierList.add(new Desk(14 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(14 * tileSize, 12 * tileSize));
        this.barrierList.add(new Desk(14 * tileSize, 15 * tileSize));
        this.barrierList.add(new Desk(14 * tileSize, 18 * tileSize));

        this.barrierList.add(new Desk(17 * tileSize, 3 * tileSize));
        this.barrierList.add(new Desk(17 * tileSize, 6 * tileSize));
        this.barrierList.add(new Desk(17 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(17 * tileSize, 12 * tileSize));
        this.barrierList.add(new Desk(17 * tileSize, 15 * tileSize));
        this.barrierList.add(new Desk(17 * tileSize, 18 * tileSize));

        this.barrierList.add(new Desk(2 * tileSize, 7 * tileSize));
        this.barrierList.add(new Desk(2 * tileSize, 8 * tileSize));
        this.barrierList.add(new Desk(2 * tileSize, 9 * tileSize));
        this.barrierList.add(new Desk(2 * tileSize, 10 * tileSize));
        this.barrierList.add(new Desk(2 * tileSize, 11 * tileSize));

        for(int i = 5; i <= tileRows-6; i++) {
            this.barrierList.add(new Desk( (tileCols-3) * tileSize, i * tileSize));
        }
    }
    /**
     * <code>setUpTables</code>
     * Sets up all tables in the game.
     */
    private void setupTables () {
        this.barrierList.add(new Table(7 * tileSize, 3 * tileSize));
        this.barrierList.add(new Table(7 * tileSize, 9 * tileSize));
        this.barrierList.add(new Table(7 * tileSize, 15 * tileSize));

        this.barrierList.add(new Table(16 * tileSize, 3 * tileSize));
        this.barrierList.add(new Table(16 * tileSize, 9 * tileSize));
        this.barrierList.add(new Table(16 * tileSize, 15 * tileSize));

        this.barrierList.add(new Table(10 * tileSize, 9 * tileSize));
        this.barrierList.add(new Table(13 * tileSize, 9 * tileSize));
    }
    /**
     * <code>setUpBookshelf</code>
     * Sets up all bookshelves in the game.
     */
    private void setupBookshelf () {
        //this.barrierList.add(new Bookshelf( tileSize, tileSize));
        for(int i = 1; i <= tileRows-2; i++) {
            this.barrierList.add(new Bookshelf( tileSize, i * tileSize));
        }
        for(int i = 2; i <= tileRows-2; i++) {
            this.barrierList.add(new Bookshelf( (tileCols-2) * tileSize, i * tileSize));
        }
        for(int i = 2; i < tileCols-1; i++) {
            this.barrierList.add(new Bookshelf( i * tileSize,  tileSize));
        }
        for(int i = 2; i < tileCols-1; i++) {
            this.barrierList.add(new Bookshelf( i * tileSize, (tileRows-2) * tileSize));
        }
    }
    /**
     * <code>setUpEnemies</code>
     * Sets up all enemies and punishments in the game.
     */
    private void setupEnemies () {
        this.punishList.add(new Punishment(7 * tileSize, 6 * tileSize));
        this.punishList.add(new Punishment(7 * tileSize, 12 * tileSize));
        this.punishList.add(new Punishment(16 * tileSize, 6 * tileSize));
        this.punishList.add(new Punishment(16 * tileSize, 12 * tileSize));

        this.movingEnemies.add(new Enemy(23 * tileSize,  2 * tileSize));
        this.movingEnemies.add(new Enemy(23 * tileSize,  17 * tileSize));
        this.movingEnemies.add(new Enemy(2 * tileSize,  18 * tileSize));
        this.movingEnemies.add(new Enemy(12 * tileSize,  18 * tileSize));

        this.entityList.addAll(punishList);
        this.entityList.addAll(movingEnemies);
    }
    /**
     * <code>setUpRewards</code>
     * Sets up all rewards in the game.
     */
    private void setUpRewards () {
        this.basicRewards.add(new BasicReward(17 * tileSize, 4 * tileSize));
        this.basicRewards.add(new BasicReward(5 * tileSize, 5 * tileSize));
        this.basicRewards.add(new BasicReward(13 * tileSize, 8 * tileSize));
        this.basicRewards.add(new BasicReward(8 * tileSize, 13 * tileSize));
        this.basicRewards.add(new BasicReward(15 * tileSize, 16 * tileSize));
        this.basicRewards.add(new BasicReward(18 * tileSize, 10 * tileSize));
        this.entityList.addAll(basicRewards);

        this.bonusRewards.add(new BonusReward(20 * tileSize, 2 * tileSize));
        this.entityList.addAll(bonusRewards);
    }
    /**
     * <code>startGameThread</code>
     * initializes and starts the thread running the game.
     */
    public void startGameThread () {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * <code>resetGame</code>
     * resets the game to default state.
     */
    private void resetGame () {
        // resets enemies, returns player to start. does not change rewards
        entityList.removeAll(movingEnemies);
        entityList.removeAll(punishList);
        entityList.removeAll(basicRewards);
        entityList.removeAll(bonusRewards);
        entityList.clear();
        entityList.addAll(barrierList);
        movingEnemies.clear();
        punishList.clear();
        basicRewards.clear();
        bonusRewards.clear();
        setupEnemies();
        setUpRewards();
        collected_all_rewards = false;
        wintile.on = false;
        player = new Player(defaultX, defaultY, keyboard);
        timer = 0;
    }
    /**
     * <code>update_panel</code>
     * updates all entities.
     */
    public void update_panel() {
        // call entity update functions
        for (Enemy a : movingEnemies) {
            a.update(barrierList, player);
        }
        for (BonusReward b: bonusRewards) {
            b.update(barrierList);
        }
        player.update(entityList); // after so if an enemy moves to same spot after, player still dies.
        // check for collected rewards
        collectRewards();
    }
    /**
     * <code>paintComponent</code>
     * refreshes the screen with the current game state.
     * @param mainGFX the graphics used to draw on the screen.
     * @see java.awt.Graphics
     * @see java.awt.Graphics2D
     */
    public void paintComponent(Graphics mainGFX) {

        super.paintComponent(mainGFX);
        Graphics2D GFX = (Graphics2D) mainGFX;
        if (wintile.on) {
            GFX.setColor(Color.WHITE);
            GFX.drawImage(winscreen, 0, 0, screenWidth, screenHeight, null);
            GFX.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            GFX.drawString("Time: " + (int) timer  + " seconds", screenWidth/3, screenHeight/2);
            GFX.drawString("Score: " + player.score, screenWidth/3, screenHeight/2+50);
            return;
        }
        else if (!player.alive) {
            GFX.drawImage(deathscreen, 0, 0, screenWidth, screenHeight, null);
            return;
        }
        GFX.drawImage(bg, 0, 0, screenWidth, screenHeight, null);
        // call entity draw
        for (Entity a : entityList) {
            a.draw(GFX);
        }

        player.draw(GFX);
        GFX.setColor(Color.BLACK);
        GFX.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        GFX.drawString("Timer: " + (int) timer, 32, 28);
        GFX.drawString("Score: " + player.score, screenWidth/2, 28);
        GFX.dispose();
    }
    /**
     * <code>collectRewards</code>
     * manages the collected and current rewards.
     */
    public void collectRewards() {
        // allow player to pickup the rewards
        ArrayList<Reward> collectedRewards = new ArrayList<>();
        for (Reward rwd : basicRewards) {
            if (rwd.collected) {
                collectedRewards.add(rwd);
            }
        }
        for (Reward rwd : bonusRewards) {
            if (rwd.collected) {
                collectedRewards.add(rwd);
            }
        }
        // remove collected coins from the board
        basicRewards.removeAll(collectedRewards);
        bonusRewards.removeAll(collectedRewards);
        entityList.removeAll(collectedRewards);
        if (basicRewards.size() == 0 && !collected_all_rewards) {
            entityList.add(wintile);
        }
    }

    /**
     * <code>run</code>
     * the game loop for both updating and refreshing the screen.
     */
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long prev = System.nanoTime();
        long curr;
        this.requestFocusInWindow();
        while (gameThread != null){
            curr = System.nanoTime();
            delta += (curr - prev) / drawInterval;
            prev = curr;
            if (delta >= 1) {
                if (keyboard.restart) {
                    resetGame();
                    keyboard.restart = false;
                    just_died = false;
                }
                if (player.alive && !wintile.on) {
                    update_panel();
                    timer += delta * drawInterval/1000000000;
                }
                repaint();
                delta--;
            }
        }
    }
}