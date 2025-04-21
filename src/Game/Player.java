package Game;

public class Player extends GameObject {
    private int speed;
    private int jumpHeight;
    private boolean isJumping;

    //Constructor for Player class
    public Player(int speed, int jumpHeight) {
        super(0, 0);
        this.speed = speed;
        this.jumpHeight = jumpHeight;
        this.isJumping = false;
    }

    //Movement methods
    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void jump() {
        if (!isJumping) {
            y += jumpHeight;
            isJumping = true;
        }
    }

    public boolean isCollidingWithObstacle(Obstacle obstacle) {
        return obstacle.collide(this);
    }

    @Override
    public void move() {
    }
}
