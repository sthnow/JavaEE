package factory.test;

public class FactoryImpl implements Factory {


    public Car produceCar() {
        return new Car();
    }

    public Tv produceTv() {
        return new Tv();
    }
}
