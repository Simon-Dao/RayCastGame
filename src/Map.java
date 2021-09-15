import java.util.Arrays;

public class Map {

    private int cols;
    private int rows;
    private int[][] gameMap;

    public Map(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        this.gameMap = initMap();
    }

    public int[][] initMap() {
        int[][] gameMap = new int[rows][cols];

        Arrays.fill(gameMap[0], 1);
        Arrays.fill(gameMap[gameMap.length-1],0);

        for(int i = 0; i < gameMap.length; i++) {
            gameMap[i][0] = 1;
            gameMap[i][gameMap[i].length - 1] = 1;
        }

        return gameMap;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    public int getTile(int x, int y) {
        return gameMap[y][x];
    }

    public void printMap() {
        for(int[] row : gameMap)
            for(int col : row)
                System.out.print(col);
            System.out.println();
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
