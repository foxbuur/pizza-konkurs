package enums;

import java.math.BigDecimal;

public enum Delivery {

    NO("0.00", "Bez dowozu"),
    GRUNWALD("4.00", "Grunwald"),
    STARE_MIASTO("5.00", "Stare miasto"),
    WILDA("5.00", "Wilda"),
    JEZYCE("5.00", "Jeżyce"),
    NOWE_MIASTO("6.00", "Nowe miasto");

    private BigDecimal price;
    private String name;

    Delivery(String price, String name) {
        this.price = new BigDecimal(price);
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static Delivery getByName(String name) {
        for(Delivery delivery: Delivery.values())
            if(delivery.name.equals(name))
                return delivery;
        return null;
    }

}
