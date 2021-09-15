import org.newdawn.slick.Input;

public class Player extends Entity {

    private float facingAngle;
    private float size;
    private Input input;

    public float getFacingAngle() {
        return facingAngle;
    }

    public void setFacingAngle(float facingAngle) {
        this.facingAngle = facingAngle;
    }


    public Player(Input input, float x, float y) {
        super(x,y);
        this.facingAngle = 180;
        this.size = 50;
        this.input = input;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void updatePlayerMovement() {
        this.facingAngle-=.01f;
    }

    public void move() {
        setX( getX() + (float)Math.cos(Math.toRadians(facingAngle)) * .01f);
        setY( getY() + (float)Math.sin(Math.toRadians(facingAngle)) * .01f);
    }
}
