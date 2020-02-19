import enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {

    private Dough dough;
    private List<Topping> toppings;

    private double price;

    private Pizza() {}

    public static final class Builder {
        private Dough dough;
        private List<Topping> toppings = new ArrayList<>();

        public Builder setDough(Dough dough) {
            this.dough = dough;
            return this;
        }

        public Builder addTopping(Topping topping) {
            toppings.add(topping);
            return this;
        }

        public Builder removeTopping(Topping topping) {
            toppings.remove(topping);
            return this;
        }

        public Builder setToppings(List<Topping> toppings) {
            this.toppings = toppings;
            return this;
        }

        public double currentPrice() {
            double price = dough!=null? dough.getPrice() : 0;
            for(Topping topping : toppings)
                price += topping.getPrice();
            return price;
        }

        public Pizza build() {
            if(dough == null)
                throw new IllegalStateException("Not all required values given!");

            Pizza pizza = new Pizza();
            pizza.dough = dough;
            pizza.toppings = toppings;
            pizza.price = currentPrice();

            return pizza;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public double getPrice() {
        return price;
    }

    public Dough getDough() {
        return dough;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return String.format("Ciasto: %s, dodatki: %s, cena: %.2f", dough, toppings.stream().map(Topping::getName).collect(Collectors.toList()), price);
    }
}
