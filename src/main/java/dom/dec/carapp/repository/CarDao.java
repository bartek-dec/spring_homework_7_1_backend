package dom.dec.carapp.repository;

import dom.dec.carapp.domain.Car;
import dom.dec.carapp.dto.CarDto;

import java.util.List;

public interface CarDao {

    void saveCar(String brand, String model, String color, long productionYear);

    List<Car> findAll();

    List<Car> findAllByYear(long from, long to);

    void updateCar(CarDto newCar);

    void deleteCar(long id);
}
