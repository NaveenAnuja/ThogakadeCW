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


public class DeleteCustomerForm {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    private String id ;

    List<Customer> customerList = DBConnection.getInstance().getConnection();
    Customer customerToDelete=null;

    private void clearText(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
    }

    private void editText(){
        txtId.setEditable(false);
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtNumber.setEditable(false);
    }

    private void askForSureToDelete() {

        id = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the customer with ID: " + id + "?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            customerList.remove(customerToDelete);

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("DELETE");
            alert1.setHeaderText(null);
            alert1.setContentText("Customer was Deleted Successfully !");
            alert1.showAndWait();
            askForAnotherDelete();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("NOT DELETE");
            alert1.setHeaderText(null);
            alert1.setContentText("Customer was Not Deleted ");
            alert1.showAndWait();

            askForAnotherDelete();
        }
    }

    private void askForAnotherDelete() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete another customer ?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton,noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == yesButton){
            clearText();
            txtId.setEditable(true);
        } else {
            clearText();
            txtId.setEditable(true);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        askForSureToDelete();
    }

    public void txtIdOnAction() {

        id = txtId.getText();
        boolean isDelete = false;

        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtNumber.setText(customer.getContactNumber());
                customerToDelete = customer;
                editText();
                isDelete = true;
            }
        }
            if(!isDelete){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No customer found with ID: " + id);
                alert.showAndWait();
                txtId.setText(null);
        }
    }
}
