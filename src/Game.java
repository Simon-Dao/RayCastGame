import org.newdawn.slick.*;

public class Game extends BasicGame {

    private Map gameMap;
    private Player player;
    private Enemy[] enemies;
    private int tileSize;
    private final int ENEMY_COUNT = 3;

    //todo temp variable
    private final int scaler = tileSize;

    private final int fov = 200;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameMap = new Map(Main.MAP_WIDTH,Main.MAP_HEIGHT);
        player = new Player(gameContainer.getInput(),500,500);
        enemies = new Enemy[ENEMY_COUNT];
        tileSize = Main.WINDOW_HEIGHT/10;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.clear();
        renderScene(graphics);
        //draw2DMap(graphics);
        //drawPlayer(graphics);

        player.updatePlayerMovement();
    }

    public void renderScene(Graphics g) {
        float[] distances = getRayLengths(g);

        for(int i = 0; i<distances.length; i++) {
            float width = Main.WINDOW_HEIGHT/fov;
            float x = width * i;
            float height = distances[i]/2;
            float y = 500 - ((Main.WINDOW_HEIGHT - height) / 2);

            g.setColor(getShade(distances[i]));
            g.fillRect(x,y, width, Main.WINDOW_HEIGHT - height * 2);
        }
    }

    public Color getShade(float distance) {

        int shade = (int)((1 - (distance/Main.WINDOW_HEIGHT)) * 255);

        return new Color(shade,shade,shade);
    }

    public void draw2DMap(Graphics g) {
        int[][] map = gameMap.getGameMap();

        getRayLengths(g);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {

                switch (map[i][j]) {
                    case WallType.WALL:
                        g.setColor(Color.green);
                        g.fillRect(j*tileSize,i*tileSize,tileSize,tileSize);
                        break;
                }
            }
        }
    }
    public float[] getRayLengths(Graphics g) {
        float[] lengths = new float[fov];

        float increment = .3f;
        float offset = 0;

        for (int i = -fov/2; i < fov/2; i++) {

            lengths[i + fov/2] = getRayLength(g, player.getFacingAngle() + offset);
            offset+=increment;
        }

        return lengths;
}
    public float getRayLength(Graphics g, float angle) {
        float stepSpeed = 1f;
        float x = player.getX();
        float y = player.getY();

        while(!rayCollide(x,y)) {
            float dx = (float)Math.cos(Math.toRadians(angle)) * stepSpeed;
            float dy = (float)Math.sin(Math.toRadians(angle)) * stepSpeed;

            x += dx;
            y += dy;
        }

        float dist = (float)Math.sqrt(Math.pow(player.getX()-x,2) + Math.pow(player.getY()-y, 2));

        g.setColor(Color.red);
        g.drawLine(player.getX(), player.getY(), x, y);

        return dist;
    }
    private boolean rayCollide(float x, float y) {
        int indexX = (int)(x/tileSize);
        int indexY = (int)(y/tileSize);

        return gameMap.getTile(indexX, indexY) != WallType.EMPTY;
    }
    private void drawPlayer(Graphics g) {
        getRayLengths(g);

        g.setColor(Color.white);
        g.fillOval(player.getX()- player.getSize()/2, player.getY()- player.getSize()/2, player.getSize(), player.getSize());
    }
}
