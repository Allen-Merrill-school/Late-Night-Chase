package Game;

public abstract class GameObject {
    public int x;
    public int y;

    //Constructor that initializes x and y
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Abstract method for subclasses
    public abstract void move();

    //Getters/Setters for x and y
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPosition() {
        return "(" + x + ", " + y + ")";
    }
}
