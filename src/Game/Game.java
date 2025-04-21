package Game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //Removed Score for now, if time may add coin collecting to the game, right now score serves no purpose
    private int level; 
    private boolean isGameOver;  
    private Player player;
    private List<Obstacle> obstacleList;

    //Constructor, also initilaze player and obstacles
    public Game(Player player) {
        this.player = player;
        this.obstacleList = new ArrayList<>();
        this.level = 1;  
        this.isGameOver = false;
    }

    //Start game
    public void startGame() {
        System.out.println("Game started.");
        spawnObstacles();
    }

    //End game
    public void endGame() {
        System.out.println("Game over!");
        isGameOver = true;
    }

    //Add obstacles at pre set locations on map
    public void spawnObstacles() {
        obstacleList.add(new Obstacle(10, 0, 2, 2));  
        obstacleList.add(new Obstacle(20, 0, 2, 2));
    }

    public void updateGameState() {
        if (isGameOver) {
            return; 
        }

        //Check for collisions to end game and exit loop
        for (Obstacle o : obstacleList) {
            if (player.isCollidingWithObstacle(o)) {
                endGame();  
                break; 
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    //Returns level, (will delete if I don't make a 2nd level)
    public int getLevel() {
        return level;
    }
}
