package model;

import java.math.BigDecimal;

public enum Dough {

    ITALIAN("10.00", "Cienkie ciasto"),
    AMERICAN("12.00", "Grube ciasto");

    private BigDecimal price;
    private String name;

    Dough(String price, String name) {
        this.price = new BigDecimal(price);
        this.name = name;
    }

    public BigDecimal getPrice() {
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
