package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public Image loadImage(String path) throws IOException {

        BufferedImage image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
