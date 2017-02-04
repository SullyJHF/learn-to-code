package levels;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Wall;
import snake.Screen;

public class Outline extends Level {
  private List<Entity> walls;
  public Outline() {
    walls = new ArrayList<Entity>();
  }

  @Override
  protected List<Entity> getWalls() {
    for (int i = 0; i < Screen.HEIGHT / Screen.CELL_SIZE; i++) {
      for(int j = 0; j < Screen.WIDTH / Screen.CELL_SIZE; j++) {
        if(i == 0 || i == (Screen.HEIGHT / Screen.CELL_SIZE) - 1) {
          walls.add(new Wall(j * Screen.CELL_SIZE, i * Screen.CELL_SIZE));
        } else if(j == 0 || j == (Screen.WIDTH / Screen.CELL_SIZE) - 1){
          walls.add(new Wall(j * Screen.CELL_SIZE, i * Screen.CELL_SIZE));
        }
      }
    }
    return walls;
  }
}
