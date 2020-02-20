import enums.Delivery;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Pizza> pizzas;
    private Delivery delivery;
    private boolean discount;

    private BigDecimal price;

    private Order() {}

    public static final class Builder {
        private List<Pizza> pizzas = new ArrayList<>();
        private Delivery delivery;
        private boolean discount;

        public Builder addPizza(Pizza pizza) {
            pizzas.add(pizza);
            return this;
        }

        public Builder setPizzas(List<Pizza> pizzas) {
            this.pizzas = pizzas;
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

        public BigDecimal currentPrice() {
            BigDecimal price = delivery!=null? delivery.getPrice() : BigDecimal.ZERO;
            if(!(pizzas == null || pizzas.isEmpty()))
                for(Pizza pizza : pizzas)
                    price = price.add(pizza.getPrice());
            price = price.multiply(discount? new BigDecimal("0.80") : BigDecimal.ONE);
            return price.setScale(2, RoundingMode.HALF_EVEN);
        }

        public Order build() {
            if(pizzas == null || pizzas.isEmpty())
                throw new IllegalStateException("Not all required values given!");

            Order order = new Order();
            order.pizzas = pizzas;
            order.delivery = delivery;
            order.discount = discount;
            order.price = currentPrice();

            return order;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public boolean getDiscount() {
        return discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
