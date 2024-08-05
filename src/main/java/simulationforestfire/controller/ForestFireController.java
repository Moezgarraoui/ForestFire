package simulationforestfire.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import simulationforestfire.service.SimulationService;
import simulationforestfire.exception.ApiConnectionException;
import simulationforestfire.exception.InvalidFireCountException;

@RestController
public class ForestFireController {

    @Autowired
    private SimulationService simulationService;

    @GetMapping("/simulate")
    public List<String> simulate() {
        try {
            return simulationService.runSimulation();
        } catch (ApiConnectionException e) {
            throw new ApiConnectionException("Probléme de conn avec API.", e);
        } catch (InvalidFireCountException e) {
            throw new InvalidFireCountException("Fire count invalide.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.", e);
        }
    }
}
