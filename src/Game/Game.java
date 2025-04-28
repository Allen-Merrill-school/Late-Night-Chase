package Game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean isGameOver;  
    private Player player;
    private List<Obstacle> obstacleList;

    //Constructor, also initilaze player and obstacles
    public Game(Player player) {
        this.player = player;
        this.obstacleList = new ArrayList<>();
        this.isGameOver = false;
    }

    //Start game
    public void startGame() {
        System.out.println("Game started.");
        spawnObstacles();
    }

    public void endGame() {
        System.out.println("Game over!");
        isGameOver = true;
        GameApp.gameOver();
    }
    

    public void spawnObstacles() {
        obstacleList = new ArrayList<>();
    
        //Add starting platform
        Obstacle startingPlatform = new Obstacle(50, 550, 200, 20);
        obstacleList.add(startingPlatform);
    
        //Add 10 platforms
        for (int i = 0; i < 10; i++) { 
            int x = 300 + i * 150;
            int y = 300 + (int)(Math.random() * 150) - 75; 
            
            Obstacle obstacle = new Obstacle(x, y, 100, 20);
            obstacleList.add(obstacle);
        }
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

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }
    
}
