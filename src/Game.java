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
        gameMap = new Map();
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
        Ray[] rays = getRays(g);

        for(int i = 0; i<rays.length; i++) {

            //get the color for the wall
            Color color = getTextureColor(rays[i]);

            float width = Main.WINDOW_WIDTH/fov;
            float x = width * i;
            float height = (Main.WINDOW_HEIGHT * tileSize) / rays[i].getLength();

            g.fillRect(x,  (Main.WINDOW_HEIGHT/2) - (height/2) , width, height);
        }
    }

    public Color getTextureColor(Ray ray) {

        int tileX = (int)(ray.getX() % tileSize);
        int tileY = (int)(ray.getY() % tileSize);

        int index =
    }

    public void draw2DMap(Graphics g) {
        int[][] map = gameMap.getGameMap();

        getRays(g);

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

    public Ray[] getRays(Graphics g) {
        Ray[] rays = new Ray[fov];

        float increment = .3f;
        float offset = 0;

        for (int i = -fov/2; i < fov/2; i++) {

            rays[i + fov/2] = getRayData(g, player.getFacingAngle() + offset);
            offset+=increment;
        }

        return rays;
}
    public Ray getRayData(Graphics g, float angle) {
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
        return new Ray(x,y,dist, getTileHit(x,y));
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
        getRays(g);

        g.setColor(Color.white);
        g.fillOval(player.getX()- player.getSize()/2, player.getY()- player.getSize()/2, player.getSize(), player.getSize());
    }
}
