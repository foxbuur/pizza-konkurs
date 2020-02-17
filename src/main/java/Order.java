import enums.Delivery;

public class Order {

    private Pizza pizza;
    private Delivery delivery;
    private boolean discount;

    private double price;

    private Order() {}

    public static final class Builder {
        private Pizza pizza;
        private Delivery delivery;
        private boolean discount;

        private double price;

        public Builder pizza(Pizza pizza) {
            this.pizza = pizza;
            this.price += pizza.getPrice();
            return this;
        }

        public Builder delivery(Delivery delivery) {
            this.delivery = delivery;
            this.price += delivery.getPrice();
            return this;
        }

        public Builder discount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public Order build() {
            if(pizza == null)
                throw new IllegalStateException("Not all required values given!");

            Order order = new Order();
            order.pizza = pizza;
            order.delivery = delivery;
            order.discount = discount;
            order.price = discount? 0.8 * price : price;

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
