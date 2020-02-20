import enums.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {

    private Menu position;
    private Dough dough;
    private List<Topping> additionalToppings;

    private BigDecimal price;

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

        public BigDecimal currentPrice() {
            if(dough == null || position == null)
                return new BigDecimal("0.00");

            BigDecimal price = dough.getPrice();
            for(Topping topping : position.getBasicToppings())
                price = price.add(topping.getPrice());
            for(Topping topping : additionalToppings)
                price = price.add(topping.getPrice());
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

    public BigDecimal getPrice() {
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
