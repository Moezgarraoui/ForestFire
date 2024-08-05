package simulationforestfire.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import simulationforestfire.exception.InvalidFireCountException;
import simulationforestfire.service.ConfigurationService;
import simulationforestfire.service.FireSimulatorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FireSimulatorServiceTest {

    @Mock
    private ConfigurationService configurationService;

    @InjectMocks
    private FireSimulatorService fireSimulatorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunSimulation() {
        
        when(configurationService.getHeight()).thenReturn(10);
        when(configurationService.getWidth()).thenReturn(10);
        when(configurationService.getProbability()).thenReturn(0.8);
        when(configurationService.getInitialFireCount()).thenReturn(3);
 
        List<String> result = fireSimulatorService.runSimulation();

        
        assertNotNull(result);
    }

    @Test
    public void testRunSimulationWithInvalidFireCount() {
         
        when(configurationService.getHeight()).thenReturn(10);
        when(configurationService.getWidth()).thenReturn(10);
        when(configurationService.getProbability()).thenReturn(0.8);
        when(configurationService.getInitialFireCount()).thenReturn(0);

        
        assertThrows(InvalidFireCountException.class, () -> {
            fireSimulatorService.runSimulation();
        });
    }
}
