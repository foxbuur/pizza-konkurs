package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Menu {
    MARGERITTA(new Topping[] {}),
    MARINARA(new Topping[] {Topping.GARLIC}),
    NAPOLETANA(new Topping[]{Topping.BLACK_OLIVES}),
    HAWAJSKA(new Topping[]{Topping.PINEAPPLE}),
    FUNGHI(new Topping[]{Topping.MUSHROOMS}),
    QUATRO_STAGIONI(new Topping[]{Topping.HAM, Topping.ARTICHOKE, Topping.PEPPER}),
    CAPRICCIOSA(new Topping[]{Topping.HAM, Topping.MUSHROOMS}),
    DINAMITE(new Topping[]{Topping.SALAMI});

    private List<Topping> basicToppings;

    Menu(Topping[] toppings) {
        basicToppings = new ArrayList<>();
        basicToppings.add(Topping.TOMATO_SAUCE);
        basicToppings.add(Topping.MOZZARELLA);
        basicToppings.addAll(Arrays.asList(toppings));
    }

    public List<Topping> getBasicToppings() {
        return basicToppings;
    }

}
