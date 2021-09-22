import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    private Game renderPipeline;
    private static final String TITLE = "DOOM Java Edition";
    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;
    public static final int MAP_WIDTH = 20;
    public static final int MAP_HEIGHT = 20;

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void start() throws SlickException {
        renderPipeline = new Game(TITLE);
        new Textures();

        AppGameContainer game = new AppGameContainer(renderPipeline);
        game.setDisplayMode(WINDOW_WIDTH,WINDOW_HEIGHT,false);
        //game.start();

    }
}
