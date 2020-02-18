import enums.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {

    @Test
    public void shouldBuildMargerittaWithoutDelivery(){
        Pizza margeritta = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.NO)
                .setDiscount(false)
                .build();

        assertEquals(13.50, order.getPrice(),0);
    }

    @Test
    public void shouldBuildMargerittaAndCapricciosaWithoutDelivery(){
        Pizza margeritta = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        Pizza capricciosa = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .addTopping(Topping.HAM)
                .addTopping(Topping.MUSHROOMS)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .addPizza(capricciosa)
                .setDelivery(Delivery.NO)
                .setDiscount(false)
                .build();

        assertEquals(30.30, order.getPrice(),0);
    }

    @Test
    public void shouldBuildMargerittaWithDelivery(){
        Pizza margeritta = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(false)
                .build();

        assertEquals(18.50, order.getPrice(),0);
    }

    @Test
    public void shouldBuildMargerittaWithDeliveryForStudent(){
        Pizza margeritta = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(true)
                .build();

        assertEquals(14.80, order.getPrice(),0);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        Order order = Order.builder()
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(false)
                .build();
    }

}
