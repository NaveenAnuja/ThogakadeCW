package mainpage;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Customer;

import java.util.List;
import java.util.Optional;

public class SearchCustomer {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    private void editText(){
        txtId.setEditable(false);
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtNumber.setEditable(false);
    }

    private void clearText(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
    }

   private void askForAnotherSearch() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Search");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to search another customer ?");

        ButtonType yesButton = new ButtonType("yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton,noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            clearText();
            txtId.setEditable(true);
        }
    }
    @FXML
    void txtIdOnAction(ActionEvent event) {

        String id = txtId.getText();
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        boolean customerFound = false;

        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtNumber.setText(customer.getContactNumber());
                editText();
                customerFound = true;
            }
        }

            if (customerFound) {
                askForAnotherSearch();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No customer found with ID: " + id);
                alert.showAndWait();
                txtId.setText(null);
            }
        }
    }

