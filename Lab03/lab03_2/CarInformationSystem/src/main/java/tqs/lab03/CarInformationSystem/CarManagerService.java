package tqs.lab03.CarInformationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {


    @Autowired
    CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }
    
}
