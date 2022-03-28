package tqs.lab03.CarInformationSystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.CoreMatchers;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarManagerService carManagerService;


    @Test
    void whenPostToCar_thenCreateCar() throws Exception {
       Car golf = new Car("Volkswagen", "Golf");
       
       when( carManagerService.save(Mockito.any())).thenReturn(golf);

       mvc.perform(
           post("/car").contentType(MediaType.APPLICATION_JSON).content(golf.toJson()))
           .andExpect(MockMvcResultMatchers.status().isCreated())
           .andExpect(MockMvcResultMatchers.jsonPath("$.maker", CoreMatchers.is("Volkswagen")))
           .andExpect(MockMvcResultMatchers.jsonPath("$.model", CoreMatchers.is("Golf")));


        verify(carManagerService, times(1)).save(Mockito.any());
    }

    
}
