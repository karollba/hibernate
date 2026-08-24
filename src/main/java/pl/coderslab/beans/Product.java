package pl.coderslab.beans;

public class Product {
    private String name;
    private double price;
    private Long id;

    public Product(String name, double price, Long id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }

    public String nameToString() {
        return name;
    }
}

