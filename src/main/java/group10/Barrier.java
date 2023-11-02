package group10;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Barrier extends Entity{
    String img_path;
    String barrier_type = "";
    Barrier(int x, int y, String barrier_type) {
        this.x = x;
        this.y = y;
        this.img_path = "/barriers/";
        if (barrier_type == "chair") {
            this.img_path = this.img_path+barrier_type+(System.currentTimeMillis() % 4);
        }
        else if (barrier_type == "table") {
            this.img_path = this.img_path+barrier_type+(System.currentTimeMillis() % 6);
        }
        else {
            this.img_path = this.img_path+barrier_type;
        }
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream((img_path+".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
