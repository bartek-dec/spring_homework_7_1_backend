package dom.dec.carapp.api;

import dom.dec.carapp.domain.Car;
import dom.dec.carapp.dto.CarDto;
import dom.dec.carapp.repository.CarDao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin
@Validated
public class CarApi {
    private CarDao carDao;

    @Autowired
    public CarApi(CarDao carDao) {
        this.carDao = carDao;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(carDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Car>> getAllByYear(@RequestParam(name = "from") @Min(value = 1900, message = "From param cannot be less than 1900") long from,
                                                  @RequestParam(name = "to") @Min(value = 1900, message = "To param cannot be less than 1900") long to) {
        return new ResponseEntity<>(carDao.findAllByYear(from, to), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@Valid @RequestBody CarDto carDto) {
        carDao.saveCar(carDto.getBrand(), carDto.getModel(), carDto.getColor(), carDto.getProductionYear());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@Valid @RequestBody CarDto carDto) {
        carDao.updateCar(carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable long id) {
        carDao.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
