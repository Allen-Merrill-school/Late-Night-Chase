package Game;

public abstract class GameObject {
    protected int x, y;
    protected int width, height;

    //Constructor that initializes x and y
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;//Updated from last week
        this.height = height;
    }

    //Abstract method for subclasses
    public abstract void move();

    //Getters/Setters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Updated to add with and height
    public int getWidth() { 
        return width; 
    }

    public int getHeight() {
         return height; 
    }
} 
