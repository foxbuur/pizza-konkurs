import enums.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaBuilderTest {

    @Test
    public void shouldBuildMargeritta(){
        Pizza pizza = Pizza.builder()
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();

        assertEquals(13.50,pizza.getPrice(),0);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        Pizza pizza = Pizza.builder()
                .addTopping(Topping.TOMATO_SAUCE)
                .addTopping(Topping.MOZZARELLA)
                .build();
    }

}
