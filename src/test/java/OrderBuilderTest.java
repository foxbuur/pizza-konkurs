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
                .pizza(margeritta)
                .delivery(Delivery.NO)
                .discount(false)
                .build();

        assertEquals(13.50, order.getPrice(),0);
    }

    @Test
    public void shouldBuildMargerittaWithDelivery(){
        Pizza margeritta = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        Order order = Order.builder()
                .pizza(margeritta)
                .delivery(Delivery.JEZYCE)
                .discount(false)
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
                .pizza(margeritta)
                .delivery(Delivery.JEZYCE)
                .discount(true)
                .build();

        assertEquals(14.80, order.getPrice(),0);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        Order order = Order.builder()
                .delivery(Delivery.JEZYCE)
                .discount(false)
                .build();
    }

}
