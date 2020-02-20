import enums.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {

    @Test
    public void shouldBuildMargerittaWithoutDelivery(){
        Pizza margeritta = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .setDough(Dough.ITALIAN)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.NO)
                .setDiscount(false)
                .build();

        assertEquals(new BigDecimal("13.50"), order.getPrice());
    }

    @Test
    public void shouldBuildMargerittaWithDelivery(){
        Pizza margeritta = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .setDough(Dough.ITALIAN)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(false)
                .build();

        assertEquals(new BigDecimal("18.50"), order.getPrice());
    }

    @Test
    public void shouldBuildMargerittaWithDeliveryForStudent(){
        Pizza margeritta = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .setDough(Dough.ITALIAN)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(true)
                .build();

        assertEquals(new BigDecimal("14.80"), order.getPrice());
    }

    @Test
    public void shouldBuildMargerittaAndCapricciosaWithoutDelivery(){
        Pizza margeritta = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .setDough(Dough.ITALIAN)
                .build();

        Pizza capricciosa = Pizza.builder()
                .fromMenu(Menu.CAPRICCIOSA)
                .setDough(Dough.ITALIAN)
                .build();

        Order order = Order.builder()
                .addPizza(margeritta)
                .addPizza(capricciosa)
                .setDelivery(Delivery.NO)
                .setDiscount(false)
                .build();

        assertEquals(new BigDecimal("30.30"), order.getPrice());
    }

    @Test
    public void shouldBuildQuatroStagioniWithDeliveryForStudent(){
        Pizza quatroStagioni = Pizza.builder()
                .fromMenu(Menu.QUATRO_STAGIONI)
                .setDough(Dough.ITALIAN)
                .build();

        Order order = Order.builder()
                .addPizza(quatroStagioni)
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(true)
                .build();

        assertEquals(new BigDecimal("18.56"), order.getPrice());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        Order order = Order.builder()
                .setDelivery(Delivery.JEZYCE)
                .setDiscount(false)
                .build();
    }

}
