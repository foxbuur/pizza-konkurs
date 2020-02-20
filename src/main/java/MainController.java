import enums.Delivery;
import enums.Dough;
import enums.Topping;
import enums.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> deliveryDrop, menuDrop;

    @FXML
    private ToggleGroup doughGroup;

    @FXML
    private RadioButton italianRadio, americanRadio;

    @FXML
    private CheckBox pepperCheck, mushroomsCheck, mozzarellaCheck, artichokeCheck, tomatoSauceCheck,
            hamCheck, oliveOilCheck, garlicCheck, pineappleCheck, salamiCheck,
            sweetcornCheck, baconCheck, garlicSauceCheck, blackOlivesCheck;

    @FXML CheckBox studentCheck;

    @FXML
    private Button submitButton, addPizzaButton;

    @FXML
    private Label priceLabel, orderSummaryLabel, basicToppingsLabel;

    @FXML
    private ListView<Pizza> pizzaListView;

    private RadioButton[] doughRadio;
    private CheckBox[] toppingCheckboxes;

    private Menu position;
    private Dough selectedDough;
    private List<Topping> additionalToppings;
    private Delivery selectedDelivery;
    private boolean discount;
    private List<Pizza> createdPizzas;

    private void refreshLabels() {
        double rawPizzaPrice = Pizza.builder().fromMenu(position).setDough(selectedDough).setAdditionalToppings(additionalToppings).currentPrice();
        double orderPrice = Order.builder().setDelivery(selectedDelivery).setPizzas(createdPizzas).setDiscount(discount).currentPrice();

        orderSummaryLabel.setText(String.format("Łącznie: %.2f", orderPrice));
        priceLabel.setText(String.format("Łącznie: %.2f", rawPizzaPrice));
    }

    private void newPizza() {
        additionalToppings = new ArrayList<>();
        position = null;

        for(CheckBox cb : toppingCheckboxes)
            cb.setSelected(false);
        position = Menu.valueOf(menuDrop.getValue());
        basicToppingsLabel.setText(String.format("Podstawowe składniki: %s", position.getBasicToppings().stream().map(Topping::getName).collect(Collectors.toList())));
        refreshLabels();
    }

    private void newOrder() {
        createdPizzas = new ArrayList<>();
        selectedDelivery = null;

        if(selectedDough!=null)
            doughGroup.getSelectedToggle().setSelected(false);

        selectedDough = null;
        pizzaListView.getItems().clear();
        newPizza();
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        doughRadio = new RadioButton[] {
                italianRadio, americanRadio
        };

        toppingCheckboxes = new CheckBox[] {
                pepperCheck, mushroomsCheck, mozzarellaCheck, artichokeCheck, tomatoSauceCheck,
                hamCheck, oliveOilCheck, garlicCheck, pineappleCheck, salamiCheck,
                sweetcornCheck, baconCheck, garlicSauceCheck, blackOlivesCheck
        };

        menuDrop.getItems().addAll(Arrays.stream(Menu.values()).map(Menu::toString).collect(Collectors.toList()));

        menuDrop.setOnAction(event -> {
            newPizza();
            refreshLabels();
        });

        deliveryDrop.setOnAction(event -> {
            selectedDelivery = Delivery.getByName(deliveryDrop.getValue());
            refreshLabels();
        });

        addPizzaButton.setOnAction(event -> {
            try {
                Pizza pizza = Pizza.builder().fromMenu(position).setAdditionalToppings(additionalToppings).setDough(selectedDough).build();
                createdPizzas.add(pizza);
                pizzaListView.getItems().add(pizza);

                newPizza();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Sprawdź, czy poprawnie wypełniono formularz zamówienia!");
                alert.showAndWait();
            }
        });

        submitButton.setOnAction(event -> {
            try {
                Order order = Order.builder().setPizzas(createdPizzas).setDelivery(selectedDelivery).setDiscount(discount).build();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Udało Ci się zamówić pizzę!");
                alert.setHeaderText(null);
                alert.setContentText(String.format("Zamówienie zostało przyjęte! Do zapłaty: %.2f", order.getPrice()));
                alert.showAndWait();

                newOrder();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Sprawdź, czy poprawnie wypełniono formularz zamówienia!");
                alert.showAndWait();
            }
        });

        studentCheck.setOnAction(event -> {
            discount = studentCheck.isSelected();
            refreshLabels();
        });

        for(RadioButton rb : doughRadio)
            rb.setOnAction(event -> {
                selectedDough = Dough.getByName(rb.getText());
                refreshLabels();
            });

        for(CheckBox cb : toppingCheckboxes)
            cb.setOnAction(event -> {
                if(cb.isSelected())
                    additionalToppings.add(Topping.getByName(cb.getText()));
                else
                    additionalToppings.remove(Topping.getByName(cb.getText()));

                refreshLabels();
            });

        newOrder();
    }

}
