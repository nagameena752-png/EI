
import java.util.*;


interface Observer {
    void update(float temperature);
}

class PhoneDisplay implements Observer {
    public void update(float temperature) {
        System.out.println("Phone Display updated: Temp = " + temperature + "°C");
    }
}

class TVDisplay implements Observer {
    public void update(float temperature) {
        System.out.println("TV Display updated: Temp = " + temperature + "°C");
    }
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class UPIPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}

class ShoppingCart {
    private PaymentStrategy strategy;
    public ShoppingCart(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    public void checkout(int amount) {
        strategy.pay(amount);
    }
}


class Logger {
    private static Logger instance;
    private Logger() {}
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    public void log(String msg) {
        System.out.println("Log: " + msg);
    }
}


interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) return new Circle();
        else if (type.equalsIgnoreCase("square")) return new Square();
        return null;
    }
}


interface TypeCCharger {
    void chargeWithTypeC();
}

class OldMicroUSBPhone {
    public void chargeWithMicroUSB() {
        System.out.println("Charging phone with MicroUSB port.");
    }
}

class MicroUSBToTypeCAdapter implements TypeCCharger {
    private OldMicroUSBPhone oldPhone;
    public MicroUSBToTypeCAdapter(OldMicroUSBPhone oldPhone) {
        this.oldPhone = oldPhone;
    }
    public void chargeWithTypeC() {
        oldPhone.chargeWithMicroUSB();
    }
}


interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
    public double getCost() {
        return 20.0;
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    public double getCost() {
        return coffee.getCost() + 5.0;
    }
}

class SugarDecorator implements Coffee {
    private Coffee coffee;
    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    public double getCost() {
        return coffee.getCost() + 2.0;
    }
}


public class DesignPatternsDemo {
    public static void main(String[] args) {
        System.out.println("===== Behavioral: Observer Pattern =====");
        WeatherStation ws = new WeatherStation();
        ws.addObserver(new PhoneDisplay());
        ws.addObserver(new TVDisplay());
        ws.setTemperature(28.5f);

        System.out.println("\n===== Behavioral: Strategy Pattern =====");
        ShoppingCart cart1 = new ShoppingCart(new CreditCardPayment());
        cart1.checkout(500);
        ShoppingCart cart2 = new ShoppingCart(new UPIPayment());
        cart2.checkout(300);

        System.out.println("\n===== Creational: Singleton Pattern =====");
        Logger logger = Logger.getInstance();
        logger.log("System started.");
        logger.log("System running smoothly.");

        System.out.println("\n===== Creational: Factory Pattern =====");
        Shape s1 = ShapeFactory.getShape("circle");
        s1.draw();
        Shape s2 = ShapeFactory.getShape("square");
        s2.draw();

        System.out.println("\n===== Structural: Adapter Pattern =====");
        OldMicroUSBPhone oldPhone = new OldMicroUSBPhone();
        TypeCCharger adapter = new MicroUSBToTypeCAdapter(oldPhone);
        adapter.chargeWithTypeC();

        System.out.println("\n===== Structural: Decorator Pattern =====");
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " => Cost: " + coffee.getCost());
    }
}
