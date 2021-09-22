import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Map {

    private int cols;
    private int rows;
    private int[][] gameMap;

    public Map() {

        loadMap();
    }

    public void createMap() {
        File file = new File("C:\\Users\\simon\\IdeaProjects\\RayCastingEngine\\src\\map\\map.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                String line = "";
                for (int j = 0; j < 20; j++) {
                    if(i == 0 || j == 0 || i == 19 || j == 19)
                        line+="1 ";
                    else
                        line+="0 ";
                }
                sb.append(line+"\n");
            }
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            List<List<Integer>> map = new ArrayList<>();

            Scanner scan = new Scanner(new File("C:\\Users\\simon\\IdeaProjects\\RayCastingEngine\\src\\map\\map.txt"));
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] colum = line.split(" ");

                List<Integer> columArr = new ArrayList<>();

                for (int i = 0; i < colum.length; i++) {
                    columArr.add(Integer.valueOf(colum[i]));
                }
                map.add(columArr);
            }

            gameMap = new int[map.size()][map.get(0).size()];

            for(int i = 0; i<map.size(); i++) {
                List<Integer> row = map.get(i);

                for (int j = 0; j < row.size(); j++) {
                    gameMap[i][j] = row.get(j);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int[][] getGameMap() {
        return gameMap;
    }

    public int getTile(int x, int y) {
        return gameMap[y][x];
    }

    public void printMap() {

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                System.out.print(gameMap[i][j]);
            }
            System.out.println();
        }
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
