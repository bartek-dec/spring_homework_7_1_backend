package dom.dec.carapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CarDto {

    @Min(value = 1, message = "Id cannot be less than 1")
    private long carId;

    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, message = "Brand cannot be less than 2 characters")
    private String brand;

    @NotNull(message = "Model cannot be null")
    @Size(min = 1, message = "Model cannot be less than 1 character")
    private String model;

    @NotNull(message = "Color cannot be null")
    private String color;

    @Min(value = 1900, message = "Production year cannot be less than 1900")
    private long productionYear;

    public CarDto(long carId, String brand, String model, String color, long productionYear) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public CarDto() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(long productionYear) {
        this.productionYear = productionYear;
    }
}
