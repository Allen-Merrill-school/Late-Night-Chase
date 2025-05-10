package Game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean isGameOver;  
    private Player player;
    private List<Obstacle> obstacleList;
    private double sceneWidth;
    private double sceneHeight;


    //Constructor, also initilaze player and obstacles
    public Game(Player player) {
        this.player = player;
        this.obstacleList = new ArrayList<>();
        this.isGameOver = false;
    }

    public void setSceneWidth(double width) {
        this.sceneWidth = width;
    }
    
    public void setSceneHeight(double height) {
        this.sceneWidth = height;
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

    public void winGame() {
        System.out.println("You reached the final platform. Congrats!");
        isGameOver = true;
        GameApp.winGame();
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
        if (isGameOver) return;
    
        //Check for win first
        if (player.getX() + player.getWidth() >= sceneWidth - 10) {
            isGameOver = true;
            GameApp.winGame();
            return;
        }
    
        //Then check if player fell off the bottom
        if (player.getY() > sceneHeight) {
            isGameOver = true;
            GameApp.gameOver();
            return;
        }
    
        //Check obstacle collisions
        for (Obstacle o : obstacleList) {
            if (player.isCollidingWithObstacle(o)) {
                isGameOver = true;
                GameApp.gameOver();
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
