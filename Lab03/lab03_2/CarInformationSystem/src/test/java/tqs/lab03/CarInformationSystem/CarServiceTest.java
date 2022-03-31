package tqs.lab03.CarInformationSystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
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
        Car punto = new Car((long)3,"Fiat", "Punto");

        List<Car> allCars = Arrays.asList(ibiza,golf,tesla, punto);

        when(carRepository.findById(ibiza.getCarId())).thenReturn(Optional.of(ibiza));
        when(carRepository.findById((long)999)).thenReturn(Optional.empty());
        when(carRepository.findAll()).thenReturn(allCars);

    }

    @Test
    public void whenSearchExistingId_thenCarShouldBeFound(){
        long id =  0;
        Car found = carManagerService.getCarById(id);

        verifyFindByIdIsCalledOnce();

        assertThat(found.getCarId()).isEqualTo(id);
    }

    @Test
    public void whenSearchNonExistingId_thenCarShouldBeFound(){
        Car found = carManagerService.getCarById(999L);
        assertThat(found).isNull();
    }

    @Test
    public void whenGiven4Cars_whenGetAll_thenReturn4Cars(){
        Car ibiza = new Car((long)0, "Seat", "Ibiza");
        Car golf = new Car((long)1,"Volkswagen", "Golf");
        Car tesla = new Car((long)2,"Tesla", "S90D");
        Car punto = new Car((long)3,"Fiat", "Punto");

        List<Car> allCars = carManagerService.getAllCars();

        verifyFindAllIsCalledOnce();

        assertThat(allCars).hasSize(4).extracting(Car::getCarId).contains(ibiza.getCarId(), golf.getCarId(), tesla.getCarId(), punto.getCarId());
    }



    private void verifyFindByIdIsCalledOnce(){
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }
    private void verifyFindAllIsCalledOnce(){
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }


}
