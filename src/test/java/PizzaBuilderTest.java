import model.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaBuilderTest {

    @Test
    public void shouldBuildMargeritta(){
        Pizza pizza = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .setDough(Dough.ITALIAN)
                .build();

        assertEquals(new BigDecimal("13.50"), pizza.getPrice());
    }

    @Test
    public void shouldBuildCapricciosaWithExtraPepperAndBacon(){
        Pizza pizza = Pizza.builder()
                .fromMenu(Menu.CAPRICCIOSA)
                .setDough(Dough.ITALIAN)
                .addTopping(Topping.PEPPER)
                .addTopping(Topping.BACON)
                .build();

        assertEquals(new BigDecimal("19.70"), pizza.getPrice());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        //It should throw exception - pizza cannot be crated without a dough.
        Pizza pizza = Pizza.builder()
                .fromMenu(Menu.MARGERITTA)
                .build();
    }

}
