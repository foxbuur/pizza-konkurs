package enums;

import java.math.BigDecimal;

public enum Topping {

    PEPPER("0.80", "papryka"),
    GARLIC("0.90", "czosnek"),
    MUSHROOMS("1.10", "pieczarki"),
    PINEAPPLE("1.40", "ananas"),
    MOZZARELLA("1.50", "mozzarella"),
    SALAMI("1.60", "salami"),
    ARTICHOKE("1.70", "karczochy"),
    SWEETCORN("1.90", "kukurydza"),
    TOMATO_SAUCE("2.00", "sos pomidorowy"),
    BACON("2.10", "bekon"),
    HAM("2.20", "szynka"),
    GARLIC_SAUCE("2.50", "sos czosnkowy"),
    OLIVE_OIL("3.00", "oliwa z oliwek"),
    BLACK_OLIVES("3.20", "czarne oliwki");

    private BigDecimal price;
    private String name;

    Topping(String price, String name) {
        this.price = new BigDecimal(price);
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static Topping getByName(String name) {
        for(Topping topping: Topping.values())
            if(topping.name.equals(name))
                return topping;
        return null;
    }

}
