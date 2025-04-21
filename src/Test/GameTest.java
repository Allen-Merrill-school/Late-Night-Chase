package Test;
import Game.*;

public class GameTest {
    public static void main(String[] args) {
        //Create player object and game object
        Player player = new Player(1, 2);

        Game game = new Game(player);

        //Start the game
        game.startGame();
        System.out.println("Game started");

        //Spawn obstacles
        System.out.println("Testing spawn obstacles");
        game.spawnObstacles();

        //Test collision to make sure game ends
        System.out.println("Testing collision");
        game.updateGameState();

        if (game.isGameOver()) {
            System.out.println("Game Over: Collision detected");
        } else {
            System.out.println("No collision detected");
        }

        //Ensure it outputs correct level
        System.out.println("Current level: " + game.getLevel());
    }
}
