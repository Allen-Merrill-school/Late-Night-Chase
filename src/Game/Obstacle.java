package Game;

public class Obstacle extends GameObject {
    //Construcotr
    public Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height);//Changed for jumping
    }

    //Basic collision check
    public boolean collide(Player player) {
        return player.getX() < this.x + this.width &&
               player.getX() + player.getWidth() > this.x &&
               player.getY() < this.y + this.height &&
               player.getY() + player.getHeight() > this.y;
    }

    @Override
    public void move() {
    }
}
