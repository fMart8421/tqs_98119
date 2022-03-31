package tqs.lab03.CarInformationSystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock(lenient=true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp(){
        Car ibiza = new Car((long)0, "Seat", "Ibiza");
        Car golf = new Car((long)1,"Volkswagen", "Golf");
        Car tesla = new Car((long)2,"Tesla", "S90D");

        List<Car> allCars = Arrays.asList(ibiza,golf,tesla);

        when(carRepository.findById(ibiza.getCarId())).thenReturn(Optional.of(ibiza));
        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findById((long)1)).thenReturn(Optional.empty());

    }

    @Test
    public void whenSearchExistingId_thenReturnCarWithId(){
        long id =  0;
        Car found = carManagerService.getCarById(id);

        assertThat(found.getCarId()).isEqualTo(id);
    }




}
