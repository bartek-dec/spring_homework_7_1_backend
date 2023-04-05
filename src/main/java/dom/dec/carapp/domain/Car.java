package dom.dec.carapp.domain;

public class Car {

    private long carId;
    private String brand;
    private String model;
    private String color;
    private long productionYear;

    public Car(long carId, String brand, String model, String color, long productionYear) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
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
