import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Textures {

    private int cols;
    private int rows;
    private static final int RED = 0;
    private static final int BLUE = 1;
    private static final int LIGHT_BLUE = 2;
    private static final int GREEN = 3;
    private static final int LIME_GREEN = 4;
    private static final int ORANGE = 5;
    private static final int TAN = 6;
    private static final int PURPLE = 7;
    private static final int BLACK = 8;
    private static final int WHITE = 9;

    //hashmap for all the textures
    private HashMap<Integer,int[][]> textures = new HashMap<>();

    public Textures() {

        cols = 3;
        rows = 3;

        initTextures();
    }

    //TODO read textures from files but im too lazy to implement that for now
    public void initTextures() {

        int[][] testTexture = {
                {1,0,1},
                {1,0,1},
                {1,0,1}
        };

        textures.put(1, testTexture);
    }

    public Color mapTexture(int textureCode) {

        Color result = new Color(255,255,255);

        switch (textureCode) {
            case RED:
                result = Color.red;
                break;
            case BLUE:
                result = Color.blue;
                break;
            case GREEN:
                result = Color.green;
                break;
            case LIME_GREEN:
                result = new Color(69,229,33);
                break;
            case ORANGE:
                result = Color.orange;
                break;
            case TAN:
                result = new Color(210,180,140);
                break;
            case PURPLE:
                result = new Color(162,40,255);
                break;
            case BLACK:
                result = Color.black;
                break;
            case WHITE:
                result = Color.white;
                break;
        }

        return result;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
