package enums;

public enum Delivery {

    NO(0.00, "brak"),
    GRUNWALD(4.00, "Grunwald"),
    STARE_MIASTO(5.00, "Stare miasto"),
    WILDA(5.00, "Wilda"),
    JEZYCE(5.00, "Jeżyce"),
    NOWE_MIASTO(6.00, "Nowe miasto");

    private double price;
    private String name;

    Delivery(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}
