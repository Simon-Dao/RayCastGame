public class Ray {

    private float x;
    private float y;
    private float length;
    private int type;

    public Ray(float x, float y, float length, int type) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLength() {
        return length;
    }

    public int getType() {
        return type;
    }
}
