package simulationforestfire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simulationforestfire.exception.InvalidFireCountException;
import simulationforestfire.model.Grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class FireSimulatorService implements SimulationService {

    @Autowired
    private ConfigurationService configurationService;

    private Grid grid;
    private double probability;
    private Random random = new Random();

    @Override
    public List<String> runSimulation() {
        int height = configurationService.getHeight();
        int width = configurationService.getWidth();
        probability = configurationService.getProbability();
        int initialFireCount = configurationService.getInitialFireCount();

        if (initialFireCount <= 0) {
            throw new InvalidFireCountException("The initial fire count must be greater than zero.");
        }

        grid = new Grid(height, width);

        Set<String> firePositions = new HashSet<>();
        while (firePositions.size() < initialFireCount) {
            int x = random.nextInt(height);
            int y = random.nextInt(width);
            firePositions.add(x + "," + y);
        }

        for (String position : firePositions) {
            String[] coords = position.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            grid.setCellState(x, y, 'F');
        }

        boolean fireExists = true;
        List<String> simulationOutput = new ArrayList<>();
        while (fireExists) {
            simulationOutput.add(grid.printGrid());
            step();
            fireExists = isFireStillBurning();
        }

        simulationOutput.add(grid.printGrid());
        return simulationOutput;
    }

    private void step() {
        int height = grid.getHeight();
        int width = grid.getWidth();
        char[][] newGrid = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newGrid[i][j] = grid.getCellState(i, j);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid.getCellState(i, j) == 'F') {
                    newGrid[i][j] = 'A';
                    spreadFire(i, j, newGrid);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid.setCellState(i, j, newGrid[i][j]);
            }
        }
    }

    private void spreadFire(int x, int y, char[][] newGrid) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < grid.getHeight() && ny >= 0 && ny < grid.getWidth()) {
                if (grid.getCellState(nx, ny) == 'T' && random.nextDouble() < probability) {
                    newGrid[nx][ny] = 'F';
                }
            }
        }
    }

    private boolean isFireStillBurning() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.getCellState(i, j) == 'F') {
                    return true;
                }
            }
        }
        return false;
    }
}
