import org.newdawn.slick.*;

public class Game extends BasicGame {

    private Map gameMap;
    private Player player;
    private Enemy[] enemies;
    private int tileSize;
    private final int ENEMY_COUNT = 3;
    private long time = 0;
    public static float deltaTime;

    //todo temp variable
    private final int scaler = tileSize;

    private final int fov = 100;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameMap = new Map(Main.MAP_WIDTH,Main.MAP_HEIGHT);
        player = new Player(gameContainer.getInput(),250,250);
        enemies = new Enemy[ENEMY_COUNT];
        tileSize = Main.WINDOW_HEIGHT/Main.MAP_WIDTH;
        time = gameContainer.getTime();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        deltaTime = (float)Math.abs(gameContainer.getTime() - time)/(float)1000;

        time = gameContainer.getTime();

        graphics.clear();
        renderScene(graphics);
        //draw2DMap(graphics);
        //drawPlayer(graphics);

        player.updatePlayerMovement();
    }

    public void renderScene(Graphics g) {
        float[][] data = getRayLengths(g);

        for(int i = 0; i<data.length; i++) {

            g.setColor(getShade(data[i]));
            float width = Main.WINDOW_WIDTH/fov;
            float x = width * i;
            float height = (Main.WINDOW_HEIGHT * tileSize) / data[i][0];

            g.fillRect(x,  (Main.WINDOW_HEIGHT/2) - (height/2) , width, height);
        }
    }

    public Color getShade(float[] data) {
        float distance = data[0];
        float tileType = data[1];

        int r = 255;
        int g = 255;
        int b = 255;

        switch ((int)tileType) {
            case WallType.WALL:
                int shade = (int)((1 - (distance/Main.WINDOW_HEIGHT)) * 255) / 2;
                r = shade; g = shade; b = shade;
                break;
            case WallType.GREEN_WALL:
                r = 0;
                b = 0;
                g = (int)((1 - (distance/Main.WINDOW_HEIGHT)) * 255) / 2;
                break;
        }



        return new Color(r,g,b);
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
                    case WallType.GREEN_WALL:
                        g.setColor(Color.green);
                        g.fillRect(j * tileSize, i*tileSize,tileSize,tileSize);
                }
            }
        }
    }

    public float[][] getRayLengths(Graphics g) {
        float[][] lengths = new float[fov][2];

        float increment = .3f;
        float offset = 0;

        for (int i = -fov/2; i < fov/2; i++) {

            float[] data = getRayData(g, player.getFacingAngle() + offset);

            lengths[i + fov/2][0] = data[0];
            lengths[i + fov/2][1] = data[1];
            offset+=increment;
        }

        return lengths;
}
    public float[] getRayData(Graphics g, float angle) {
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

        dist *= (float)Math.cos(Math.toRadians((player.getFacingAngle()) - angle));
        return new float[] {dist, getTileHit(x,y)};
    }
    private boolean rayCollide(float x, float y) {
        int indexX = (int)(x/tileSize);
        int indexY = (int)(y/tileSize);

        return gameMap.getTile(indexX, indexY) != WallType.EMPTY;
    }

    private int getTileHit(float x, float y) {
        int indexX = (int)(x/tileSize);
        int indexY = (int)(y/tileSize);

        return gameMap.getTile(indexX, indexY);
    }

    private void drawPlayer(Graphics g) {
        getRayLengths(g);

        g.setColor(Color.white);
        g.fillOval(player.getX()- player.getSize()/2, player.getY()- player.getSize()/2, player.getSize(), player.getSize());
    }
}
