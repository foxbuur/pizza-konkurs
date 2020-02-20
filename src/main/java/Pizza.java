import enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {

    private Menu position;
    private Dough dough;
    private List<Topping> additionalToppings;

    private double price;

    private Pizza() {}

    public static final class Builder {
        private Menu position;
        private Dough dough;
        private List<Topping> additionalToppings = new ArrayList<>();

        public Builder fromMenu(Menu position) {
            this.position = position;
            return this;
        }

        public Builder setDough(Dough dough) {
            this.dough = dough;
            return this;
        }

        public Builder setAdditionalToppings(List<Topping> additionalToppings) {
            this.additionalToppings = additionalToppings;
            return this;
        }

        public Builder addTopping(Topping topping) {
            additionalToppings.add(topping);
            return this;
        }

        public Builder removeTopping(Topping topping) {
            additionalToppings.remove(topping);
            return this;
        }

        public double currentPrice() {
            if(dough == null || position == null)
                return 0.0;

            double price = dough.getPrice();
            for(Topping topping : position.getBasicToppings())
                price += topping.getPrice();
            for(Topping topping : additionalToppings)
                price += topping.getPrice();
            return price;
        }

        public Pizza build() {
            if(dough == null || position == null)
                throw new IllegalStateException("Not all required values given!");

            Pizza pizza = new Pizza();
            pizza.position = position;
            pizza.dough = dough;
            pizza.additionalToppings = additionalToppings;
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

    public Menu getPosition() {
        return position;
    }

    public Dough getDough() {
        return dough;
    }

    public List<Topping> getAdditionalToppings() {
        return additionalToppings;
    }

    @Override
    public String toString() {
        return String.format("%s, Ciasto: %s, składniki: %s, dodatkowe składniki: %s, cena: %.2f",
                position,
                dough,
                position.getBasicToppings().stream().map(Topping::getName).collect(Collectors.toList()),
                additionalToppings.stream().map(Topping::getName).collect(Collectors.toList()),
                price);
    }
}
