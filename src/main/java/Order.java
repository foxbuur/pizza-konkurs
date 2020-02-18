import enums.Delivery;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Pizza> pizzas;
    private Delivery delivery;
    private boolean discount;

    private double price;

    private Order() {}

    public static final class Builder {
        private List<Pizza> pizzas = new ArrayList<>();
        private Delivery delivery;
        private boolean discount;

        public Builder addPizza(Pizza pizza) {
            pizzas.add(pizza);
            return this;
        }

        public Builder removePizza(Pizza pizza){
            pizzas.remove(pizza);
            return this;
        }

        public Builder removePizza(int index) {
            pizzas.remove(index);
            return this;
        }

        public Builder setDelivery(Delivery delivery) {
            this.delivery = delivery;
            return this;
        }

        public Builder setDiscount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public double currentPrice() {
            double price = delivery!=null? delivery.getPrice() : 0;
            for(Pizza pizza : pizzas)
                price += pizza.getPrice();
            return price;
        }

        public Order build() {
            if(pizzas == null || pizzas.isEmpty())
                throw new IllegalStateException("Not all required values given!");

            Order order = new Order();
            order.pizzas = pizzas;
            order.delivery = delivery;
            order.discount = discount;
            order.price = currentPrice() * (discount? 0.8 : 1.0);

            return order;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public double getPrice() {
        return price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

}
