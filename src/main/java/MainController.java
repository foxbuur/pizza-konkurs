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
    private Label priceLabel, orderSummaryLabel;

    @FXML
    private ListView<Pizza> pizzaListView;

    private RadioButton[] doughRadio;
    private CheckBox[] toppingCheckboxes;

    private Dough selectedDough;
    private List<Topping> selectedToppings;
    private Delivery selectedDelivery;
    private boolean discount;
    private List<Pizza> createdPizzas;

    private void refreshLabels() {
        double rawPizzaPrice = Pizza.builder().setDough(selectedDough).setToppings(selectedToppings).currentPrice();
        double orderPrice = Order.builder().setDelivery(selectedDelivery).setPizzas(createdPizzas).setDiscount(discount).currentPrice();

        orderSummaryLabel.setText(String.format("Łącznie: %.2f", orderPrice));
        priceLabel.setText(String.format("Łącznie: %.2f", rawPizzaPrice));
    }

    private void newPizza() {
        selectedToppings = new ArrayList<>();

        for(CheckBox cb : toppingCheckboxes)
            cb.setSelected(false);

        addToppings();
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

    private void addToppings() {
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
            default:
                break;
        }

        for(CheckBox cb : toppingCheckboxes)
            if(cb.isSelected())
                selectedToppings.add(Topping.getByName(cb.getText()));

        refreshLabels();
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

        menuDrop.setOnAction(event -> {
            newPizza();
        });

        deliveryDrop.setOnAction(event -> {
            selectedDelivery = Delivery.getByName(deliveryDrop.getValue());
            refreshLabels();
        });

        addPizzaButton.setOnAction(event -> {
            try {
                Pizza pizza = Pizza.builder().setToppings(selectedToppings).setDough(selectedDough).build();
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
                    selectedToppings.add(Topping.getByName(cb.getText()));
                else
                    selectedToppings.remove(Topping.getByName(cb.getText()));

                refreshLabels();
            });

        newOrder();
    }

}
