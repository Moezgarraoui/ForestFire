package simulationforestfire.model;


public class Grid {
    private char[][] grid;
    private int height;
    private int width;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new char[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = 'T'; // T for tree
            }
        }
    }

    public void setCellState(int x, int y, char state) {
        grid[x][y] = state;
    }

    public char getCellState(int x, int y) {
        return grid[x][y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String printGrid() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
