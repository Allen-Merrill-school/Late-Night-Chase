package Game;

import java.util.List;

//Basically redid the whole file to make jumping and gravity work
public class Player extends GameObject {
    private int speed;
    private double velocityY;
    private static final double GRAVITY = 0.4;
    private static final double JUMP_STRENGTH = -18;
    private static final int GROUND_LEVEL = 500;
    private static final double TERMINAL_VELOCITY = 8;
    private boolean isJumping;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = 5;
        this.velocityY = 0;
        this.isJumping = false;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void jump() {
        if (!isJumping && (isOnGround() || isOnObstacle())) {
            velocityY = JUMP_STRENGTH;
            isJumping = true;
        }
    }

    //Check for collosion
    public boolean isCollidingWithObstacle(Obstacle obstacle) {
        return this.x < obstacle.getX() + obstacle.getWidth() &&
               this.x + this.width > obstacle.getX() &&
               this.y < obstacle.getY() + obstacle.getHeight() &&
               this.y + this.height > obstacle.getY();
    }
    
    private boolean isOnGround() {
        return y >= GROUND_LEVEL;
    }

    //Check to stand on obstacle
    private boolean isOnObstacle() {
    List<Obstacle> obstacles = GameApp.getObstacles();
    if (obstacles == null) {
        return false;
    }
    for (Obstacle obstacle : obstacles) {
        if (isLandingOnObstacle(obstacle)) {
            return true;
        }
    }
    return false;
}

    //Used stackoverflow examples for reference to get gravity to work.
    @Override
    public void move() {
        //Apply gravity
        velocityY += GRAVITY;

        if (velocityY > TERMINAL_VELOCITY) {
            velocityY = TERMINAL_VELOCITY;
        }

        y += velocityY;

        boolean landed = false;

        //Check if player lands on obstacle
        for (Obstacle obstacle : GameApp.getObstacles()) {
            if (isLandingOnObstacle(obstacle)) {
                y = obstacle.getY() - height;
                velocityY = 0;
                landed = true;
                isJumping = false;
                break;
            }
        }

        if (!landed && y >= GROUND_LEVEL) {
            y = GROUND_LEVEL;
            velocityY = 0;
            isJumping = false;
        }

        //Fall too far and game over
        if (y > GROUND_LEVEL + 100) {
            GameApp.gameOver();
        }
    }

    private boolean isLandingOnObstacle(Obstacle obstacle) {
        boolean withinX = (x + width > obstacle.getX()) && (x < obstacle.getX() + obstacle.getWidth());
        boolean touchingTop = (y + height >= obstacle.getY()) && (y + height - velocityY <= obstacle.getY());
        return withinX && touchingTop;
    }
}
