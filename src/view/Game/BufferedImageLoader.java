package view.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {

    public BufferedImage loadImage(String path) throws IOException {

        BufferedImage image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
