package levels;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Wall;

public class Level extends ArrayList<Wall> {
  private Level instance;

  public List<Entity> loadLevel(String levelString) {
    switch (levelString) {
    case ("outline"):
      instance = new Outline();
      break;
    }

    return instance.getWalls();
  }

  protected List<Entity> getWalls() {
    return null;
  }
}
