package Simulationforestfiretest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import simulationforestfire.SimulationForestFireApplication;
import simulationforestfire.service.SimulationService;
import simulationforestfire.exception.ApiConnectionException;
import simulationforestfire.exception.InvalidFireCountException;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = SimulationForestFireApplication.class)
@AutoConfigureMockMvc
public class ForestFireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimulationService simulationService;

    @Test
    public void testSimulate() throws Exception {
       
        when(simulationService.runSimulation()).thenReturn(Arrays.asList("Simulation Step 1", "Simulation Step 2"));

        
        mockMvc.perform(get("/simulate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Simulation Step 1"))
                .andExpect(jsonPath("$[1]").value("Simulation Step 2"));
    }

    @Test
    public void testSimulateApiConnectionException() throws Exception {
        
        when(simulationService.runSimulation()).thenThrow(new ApiConnectionException("Failed to connect to the API."));

         
        mockMvc.perform(get("/simulate"))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    public void testSimulateInvalidFireCountException() throws Exception {
        
        when(simulationService.runSimulation()).thenThrow(new InvalidFireCountException("Invalid initial fire count."));

     
        mockMvc.perform(get("/simulate"))
                .andExpect(status().isBadRequest());
    }
}

