package snake;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
  public static BufferedImage loadImage(String url) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(url));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }
}
