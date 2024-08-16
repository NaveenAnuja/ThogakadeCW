package mainpage;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXTextField txtNumber;
    @FXML
    private ComboBox<String> cmbTitles;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    private int num = 1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
        txtId.setEditable(false);
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
        cmbTitles.setItems(titles);
    }

    private void clearText(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
    }

    private void generateId(){

        String id="";
        if(num<10){
            id=("C000"+ num++);
            txtId.setText(id);
        }else if(num<100){
            id=("C00"+ num++);
            txtId.setText(id);
        }else if(num<1000){
            id=("C0"+ num++);
            txtId.setText(id);
        }else if(num<10000){
            id="C"+ num++;
            txtId.setText(id);
        }
    }

    private boolean validatePhoneNumber() {

        String number = txtNumber.getText();

        if (number.length() == 10 && number.charAt(0) == '0') {
            for (int i = 0; i < number.length(); i++) {
                char digit = number.charAt(i);

                if (digit < '0' || digit > '9') {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Phone Number, Please try again !");
                    alert.showAndWait();
                    txtNumber.setText(null);

                    return false;
                }
            }
            return true;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Phone Number, Please try again !");
        alert.showAndWait();
        txtNumber.setText(null);

        return false;
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (validatePhoneNumber()) {
            String title = cmbTitles.getValue();

            if (title == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please select a title before adding the customer!");
                alert.showAndWait();
                return;
        }
            if(txtName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nothing Entered");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the customer name !");
                alert.showAndWait();
                return;
            }
            if(txtAddress.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nothing Entered");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the customer address !");
                alert.showAndWait();
                return;
            }

            List<Customer> customerList = DBConnection.getInstance().getConnection();
            customerList.add(new Customer(
                    txtId.getText(),
                    cmbTitles.getValue(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtNumber.getText()
            ));

            clearText();
            generateId();
            txtId.setEditable(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Customer");
            alert.setHeaderText(null);
            alert.setContentText("Customer was Added Successfully !");
            alert.showAndWait();
        }
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearText();
    }
}