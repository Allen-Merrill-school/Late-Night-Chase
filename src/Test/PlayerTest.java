package Test;


import Game.Player;
import Game.Obstacle;

public class PlayerTest {

    public static void main(String[] args) {
        testMoveLeft();
        testMoveRight();
        testJump();
        testCollisionWithObstacle();
    }

    //Test if the player can move around
    public static void testMoveLeft() {
        Player player = new Player(5, 10); 
        player.moveLeft(); 
        if (player.x == 5) {
            System.out.println("testMoveLeft works");
        } else {
            System.out.println("testMoveLeft doesn't work");
        }
    }

    public static void testMoveRight() {
        Player player = new Player(5, 10);
        player.moveRight(); 
        if (player.x == 5) {
            System.out.println("testMoveRight works");
        } else {
            System.out.println("testMoveRight doesn't work");
        }
    }

    public static void testJump() {
        Player player = new Player(5, 10);
        player.jump();
        if (player.y == 10) {
            System.out.println("testJump passed");
        } else {
            System.out.println("testJump failed");
        }
    }

    public static void testCollisionWithObstacle() {
        Player player = new Player(5, 10);
        player.moveRight(); 
        Obstacle obstacle = new Obstacle(5, 0, 2, 2); 
        if (player.isCollidingWithObstacle(obstacle)) {
            System.out.println("testCollisionWithObstacle passed");
        } else {
            System.out.println("testCollisionWithObstacle failed");
        }
    }
}
