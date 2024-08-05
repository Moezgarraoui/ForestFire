package simulationforestfire.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Value("${grid.height}")
    private int height;

    @Value("${grid.width}")
    private int width;

    @Value("${fire.probability}")
    private double probability;

    @Value("${initial.fire.count}")
    private int initialFireCount;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getProbability() {
        return probability;
    }

    public int getInitialFireCount() {
        return initialFireCount;
    }
}
