package Test;


import Game.Player;
import Game.Obstacle;

public class ObstacleTest {

    public static void main(String[] args) {
        //Run both collision tests
        testCollisionWithPlayer();
        testNoCollision();
    }

    //Create player and test for collision 
    public static void testCollisionWithPlayer() {
        Player player = new Player(5, 10); 
        player.moveRight(); 
        Obstacle obstacle = new Obstacle(5, 0, 2, 2); 

        if (obstacle.collide(player)) {
            System.out.println("testCollisionWithPlayer passed");
        } else {
            System.out.println("testCollisionWithPlayer failed");
        }
    }

    //Create player and test for no collision 
    public static void testNoCollision() {
        Player player = new Player(5, 10); 
        player.moveRight(); 
        Obstacle obstacle = new Obstacle(10, 0, 2, 2); 

        if (!obstacle.collide(player)) {
            System.out.println("testNoCollision passed");
        } else {
            System.out.println("testNoCollision failed");
        }
    }
}
