import org.newdawn.slick.Input;

public class Player extends Entity {

    private float facingAngle;
    private float size;
    private Input input;
    private final float speed = 20;

    private float dx;
    private float da;
    private float dy;

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

        if(input.isKeyDown(Input.KEY_W)) {
            dx = (float)Math.cos(Math.toRadians(facingAngle)) * speed * Game.deltaTime;
            dy = (float)Math.sin(Math.toRadians(facingAngle)) * speed * Game.deltaTime;
        }
        else
        if(input.isKeyDown(Input.KEY_S)) {
            dx = -(float)Math.cos(Math.toRadians(facingAngle)) * speed * Game.deltaTime;
            dy = -(float)Math.sin(Math.toRadians(facingAngle)) * speed * Game.deltaTime;
        }
        else {
            dx = 0;
            dy = 0;
        }

        if(input.isKeyDown(Input.KEY_A)) da = -speed * Game.deltaTime;
        else
        if(input.isKeyDown(Input.KEY_D)) da = speed * Game.deltaTime;
        else
            da = 0;

        addA(da);
        move(dx,dy);
    }

    public void addA(float da) {
        setFacingAngle(getFacingAngle()+da);
    }

    public void move(float dx, float dy) {
        addX(dx);
        addY(dy);
    }
}
