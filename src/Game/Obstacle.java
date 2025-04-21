package Game;

public class Obstacle extends GameObject {
    private int width;
    private int height;

    //Constructor for Obstacle class
    public Obstacle(int x, int y, int width, int height) {
        //Call on abstract class
        super(x, y); 
        this.width = width;
        this.height = height;
    }

    //Basic collision check
    public boolean collide(Player player) {
        return player.getX() < this.x + width &&
               player.getX() + 1 > this.x &&
               player.getY() < this.y + height &&
               player.getY() + 1 > this.y;
    }

    //Moving obstacles
    @Override
    public void move() {
    }
}
