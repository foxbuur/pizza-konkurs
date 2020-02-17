package enums;

public enum Topping {

    PEPPER(0.80, "papryka"),
    GARLIC(0.90, "czosnek"),
    MUSHROOMS(1.10, "pieczarki"),
    PINEAPPLE(1.40, "ananas"),
    MOZZARELLA(1.50, "mozzerella"),
    SALAMI(1.60, "salami"),
    ARTICHOKE(1.70, "karczochy"),
    SWEETCORN(1.90, "kukurydza"),
    TOMATO_SAUCE(2.00, "sos pomidorowy"),
    BACON(2.10, "bekon"),
    HAM(2.20, "szynka"),
    GARLIC_SAUCE(2.50, "sos czosnkowy"),
    OLIVE_OIL(3.00, "oliwa z oliwek"),
    BLACK_OLIVES(3.20, "czarne oliwki");

    private double price;
    private String name;

    Topping(double price, String name) {
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
