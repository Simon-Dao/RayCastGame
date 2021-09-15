import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    private Game renderPipeline;
    private static final String TITLE = "DOOM Java Edition";

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void start() throws SlickException {
        renderPipeline = new Game(TITLE);

        AppGameContainer game = new AppGameContainer(renderPipeline);
        game.setDisplayMode(500,500,false);
        game.start();
    }
}
