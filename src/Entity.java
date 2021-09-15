public class Entity {

    private float x;
    private float y;

    public Entity(float initialX, float initialY) {
        this.x = initialX;
        this.y = initialY;
    }

    public Entity() {
        this.x = 0;
        this.y = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addX(float dx) {
        setX(getX()+dx);
    }

    public void addY(float dy) {
        setY(getY()+dy);
    }
}
