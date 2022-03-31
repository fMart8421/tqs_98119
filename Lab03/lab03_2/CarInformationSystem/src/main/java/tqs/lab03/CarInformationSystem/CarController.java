package tqs.lab03.CarInformationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CarController {
    
    @Autowired
    private CarManagerService carManagerService;


    public CarController(CarManagerService cms) {
        this.carManagerService = cms;
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return new ResponseEntity<>(carManagerService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }


}
