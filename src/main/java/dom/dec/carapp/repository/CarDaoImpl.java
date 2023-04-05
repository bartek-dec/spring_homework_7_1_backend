package dom.dec.carapp.repository;

import dom.dec.carapp.domain.Car;
import dom.dec.carapp.dto.CarDto;
import dom.dec.carapp.exception.InternalServerException;
import dom.dec.carapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(String brand, String model, String color, long productionYear) {
        try {
            String sql = "INSERT INTO cars (brand,model,color,production_year) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, brand, model, color, productionYear);
        } catch (DataAccessException e) {
            throw new InternalServerException("Internal server error");
        }
    }

    @Override
    public List<Car> findAll() {
        try {
            String sql = "SELECT * FROM cars ORDER BY car_id ASC";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

            return maps.stream()
                    .map(element -> new Car(
                            Long.parseLong(String.valueOf(element.get("car_id"))),
                            String.valueOf(element.get("brand")),
                            String.valueOf(element.get("model")),
                            String.valueOf(element.get("color")),
                            Long.parseLong(String.valueOf(element.get("production_year")))
                    )).toList();
        } catch (DataAccessException e) {
            throw new InternalServerException("Internal server error");
        }
    }

    @Override
    public List<Car> findAllByYear(long from, long to) {
        try {
            long min;
            long max;

            if (to > from) {
                min = from;
                max = to;
            } else if (from > to) {
                min = to;
                max = from;
            } else {
                min = from;
                max = from;
            }

            String sql = "SELECT * FROM cars WHERE production_year>=? AND production_year<=? ORDER BY car_id ASC";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, min, max);

            return maps.stream()
                    .map(element -> new Car(
                            Long.parseLong(String.valueOf(element.get("car_id"))),
                            String.valueOf(element.get("brand")),
                            String.valueOf(element.get("model")),
                            String.valueOf(element.get("color")),
                            Long.parseLong(String.valueOf(element.get("production_year")))
                    )).toList();
        } catch (DataAccessException e) {
            throw new InternalServerException("Internal server error");
        }
    }

    @Override
    public void updateCar(CarDto newCar) {
        try {
            String sql = "UPDATE cars SET brand=?, model=?, color=?, production_year=? WHERE car_id=?";
            jdbcTemplate.update(sql, newCar.getBrand(), newCar.getModel(), newCar.getColor(), newCar.getProductionYear(), newCar.getCarId());
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Car", "id", newCar.getCarId());
        }
    }

    @Override
    public void deleteCar(long id) {
        try {
            String sql = "DELETE FROM cars WHERE car_id=?";
            int rows = jdbcTemplate.update(sql, id);

            if (rows < 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Car", "id", id);
        }
    }
}
