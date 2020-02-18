import enums.Delivery;
import enums.Dough;
import enums.Topping;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> deliveryDrop, menuDrop;

    @FXML
    private RadioButton italianRadio, americanRadio;

    @FXML
    private CheckBox pepperCheck, mushroomsCheck, mozzarellaCheck, artichokeCheck, tomatoSauceCheck,
            hamCheck, oliveOilCheck, garlicCheck, pineappleCheck, salamiCheck,
            sweetcornCheck, baconCheck, garlicSauceCheck, blackOlivesCheck;

    @FXML CheckBox studentCheck;

    @FXML
    private Button submitButton;

    @FXML
    private Label priceLabel;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Pizza.Builder rawPizza = Pizza.builder();

        RadioButton[] doughs = {
                italianRadio, americanRadio
        };

        CheckBox[] toppings = {
                pepperCheck, mushroomsCheck, mozzarellaCheck, artichokeCheck, tomatoSauceCheck,
                hamCheck, oliveOilCheck, garlicCheck, pineappleCheck, salamiCheck,
                sweetcornCheck, baconCheck, garlicSauceCheck, blackOlivesCheck
        };

        for(CheckBox cb : toppings)
            if(cb.isSelected())
                rawPizza.addTopping(Topping.getByName(cb.getText()));

        priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice()));

        menuDrop.setOnAction(event -> {
            rawPizza.setToppings(new ArrayList<>());
            for(CheckBox cb : toppings)
                cb.setSelected(false);

            mozzarellaCheck.setSelected(true);
            tomatoSauceCheck.setSelected(true);

            switch(menuDrop.getValue()) {
                case "MARGERITTA":
                    break;
                case "MARINARA":
                    garlicCheck.setSelected(true);
                    break;
                case "NAPOLETANA":
                    blackOlivesCheck.setSelected(true);
                    break;
                case "HAWAJSKA":
                    pineappleCheck.setSelected(true);
                    break;
                case "FUNGHI":
                    mushroomsCheck.setSelected(true);
                    break;
                case "QUATRO STAGIONI":
                    hamCheck.setSelected(true);
                    artichokeCheck.setSelected(true);
                    pepperCheck.setSelected(true);
                    break;
                case "CAPRICCIOSA":
                    hamCheck.setSelected(true);
                    mushroomsCheck.setSelected(true);
                    break;
                case "DINAMITE":
                    salamiCheck.setSelected(true);
                    break;
            }

            for(CheckBox cb : toppings)
                if(cb.isSelected())
                    rawPizza.addTopping(Topping.getByName(cb.getText()));

            priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice()));
        });

        for(RadioButton rb : doughs)
            rb.setOnAction(event -> {
                rawPizza.setDough(Dough.getByName(rb.getText()));
                priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice()));
            });

        for(CheckBox cb : toppings)
            cb.setOnAction(event -> {
                if(cb.isSelected())
                    rawPizza.addTopping(Topping.getByName(cb.getText()));
                else
                    rawPizza.removeTopping(Topping.getByName(cb.getText()));

                priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice()));
            });

        deliveryDrop.setOnAction(event -> priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice())));

        submitButton.setOnAction(event -> {
            try {
                Pizza pizza = rawPizza.build();
                Order order = Order.builder()
                        .pizza(pizza)
                        .delivery(Delivery.getByName(deliveryDrop.getValue()))
                        .discount(studentCheck.isSelected())
                        .build();

                priceLabel.setText(String.format("Pizza: %.2f + Dostawa: %.2f", rawPizza.currentPrice(), Delivery.getByName(deliveryDrop.getValue()).getPrice()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Udało Ci się zamówić pizzę!");
                alert.setHeaderText(null);
                alert.setContentText(String.format("Zamówienie zostało przyjęte! Do zapłaty: %.2f", order.getPrice()));
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Sprawdź, czy poprawnie wypełniono formularz zamówienia!");
                alert.showAndWait();
            }
        });
    }

}
