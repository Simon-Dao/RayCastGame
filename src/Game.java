import org.newdawn.slick.*;

import javax.swing.*;

public class Game extends BasicGame {

    private Map gameMap;
    private Player player;
    private Enemy[] enemies;
    private final int tileSize = 10;
    private final int ENEMY_COUNT = 3;

    //todo temp variable
    private final int scaler = 5;

    private final int fov = 100;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameMap = new Map(10,10);
        player = new Player(50,50);
        enemies = new Enemy[ENEMY_COUNT];
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.clear();

        drawMap(graphics);
        castRays(graphics);

        //graphics.setColor(Color.blue);
        //graphics.fillRect(30,30,50,50);
    }

    public void drawMap(Graphics g) {

        int[][] map = gameMap.getGameMap();

        for(int y = 0; y < map.length; y++) {
            for(int x = 0 ; x < map[0].length; x++) {
                switch (gameMap.getTile(x,y)) {
                    case WallType.WALL:

                        g.setColor(Color.white);
                        g.fillRect(x*tileSize*scaler-1, y*tileSize*scaler-1, tileSize*scaler-1,tileSize*scaler-1);

                        break;
                }
            }
        }
    }

    public void castRays(Graphics g) {
        double angle = player.getFacingAngle();
        for (int i = 0; i < 1; i++) {

            double[] data = castRay(angle);
            g.setColor(Color.blue);
            g.fillOval((float)data[0]*50, (float)data[1]*50, 20, 20);
        }
    }

    public double[] castRay(double angle) {
        double x = player.getX();
        double y = player.getY();
        double distance = 0;

        while(distance > 300 && rayCollide(x,y)) {
            double dx = Math.cos(Math.toRadians(angle));
            double dy = Math.sin(Math.toRadians(angle));

            distance += Math.sqrt((dx*dx) + (dy*dy));

            x+=dx;
            y+=dy;
        }

        return new double[]{x,y,distance};
    }

    public boolean rayCollide(double x, double y) {

        int indexX = (int)x / 10;
        int indexY = (int)y / 10;

        return gameMap.getTile(indexX,indexY) == WallType.WALL;
    }
}
