package levels;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Snake;
import entities.Wall;
import snake.Screen;

public class Outline extends Level {
  private List<Entity> walls;
  public Outline() {
    walls = new ArrayList<Entity>();
  }

  @Override
  protected List<Entity> getWalls() {
    for (int i = 0; i < Screen.HEIGHT / Snake.SIZE; i++) {
      for(int j = 0; j < Screen.WIDTH / Snake.SIZE; j++) {
        if(i == 0 || i == (Screen.HEIGHT / Snake.SIZE) - 1) {
          walls.add(new Wall(j * Snake.SIZE, i * Snake.SIZE));
        } else if(j == 0 || j == (Screen.WIDTH / Snake.SIZE) - 1){
          walls.add(new Wall(j * Snake.SIZE, i * Snake.SIZE));
        }
      }
    }
    return walls;
  }
}
