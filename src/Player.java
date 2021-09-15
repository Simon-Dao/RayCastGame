public class Player extends Entity {

    private double facingAngle;

    public double getFacingAngle() {
        return facingAngle;
    }

    public void setFacingAngle(double facingAngle) {
        this.facingAngle = facingAngle;
    }

    public Player() {
        super();
        this.facingAngle = 0;
    }

    public Player(double x, double y) {
        super(x,y);
        this.facingAngle = 0;
    }
}
