package factory.test;

public class Test {
    public static void main(String[] args) {
        Factory factory = new FactoryImpl();
        factory.produceCar();
        factory.produceTv();
    }
}
