import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {
    private BufferedImage spritesheet;

    //esse metodo construtor pega a spritesheet toda
    public Spritesheet(String path) {
        try {
            spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //esse metodo recorta a spritesheet
    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
