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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

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

    @Test 
    void givenCars_whenGetAllCars_thenReturnJsonArrayWithAllCars() throws Exception {
        Car golf = new Car("Volkswagen", "Golf");
        Car ibiza = new Car("Seat", "Ibiza");
        Car s90d = new Car("Tesla", "S90D");

        List<Car> allCars = Arrays.asList(golf, ibiza, s90d);

        when(carManagerService.getAllCars()).thenReturn(allCars);

        mvc.perform(
          get("/cars").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].maker", CoreMatchers.is("Volkswagen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].maker", CoreMatchers.is("Seat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", CoreMatchers.is("Ibiza")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].maker", CoreMatchers.is("Tesla")))
        ;

        verify(carManagerService, times(1)).getAllCars();
    }

    
    
}
