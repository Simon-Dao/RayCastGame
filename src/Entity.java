public class Entity {

    private double x;
    private double y;

    public Entity(double initialX, double initialY) {
        this.x = initialX;
        this.y = initialY;
    }

    public Entity() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
