package tqs.lab03.CarInformationSystem;

public class Car {
   
    private Long carId;
    private String maker;
    private String model;

    public Car(){}

    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public String getMaker(){
        return this.maker;
    }
    public void setMaker(String maker){
        this.maker = maker;
    }
    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public Long getCarId(){
        return this.carId;
    }
    public void setCarId(Long carId){
        this.carId = carId;
    }
    




}
