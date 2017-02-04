package sprites;

import java.awt.image.BufferedImage;

import snake.ImageUtils;

public class Sprite {
  public String filepath;
  private BufferedImage img;

  public Sprite(String filepath) {
    this.filepath = filepath;
    this.img = ImageUtils.loadImage(filepath);
  }

  public BufferedImage getImg() {
    return img;
  }
}
