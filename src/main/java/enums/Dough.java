package enums;

public enum Dough {

    ITALIAN(10.00, "Cienkie ciasto"),
    AMERICAN(12.00, "Grube ciasto");

    private double price;
    private String name;

    Dough(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static Dough getByName(String name) {
        for(Dough dough: Dough.values())
            if(dough.name.equals(name))
                return dough;
        return null;
    }

}
