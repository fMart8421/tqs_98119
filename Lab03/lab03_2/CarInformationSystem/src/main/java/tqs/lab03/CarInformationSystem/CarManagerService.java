package tqs.lab03.CarInformationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {


    @Autowired
    CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){return carRepository.findAll();}

    public Car getCarById(Long id){return carRepository.findById(id).orElse(null);}

    
}
