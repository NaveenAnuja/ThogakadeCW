package mainpage;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {

    @FXML
    public TextField txtName;
    @FXML
    public TextField txtId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNumber;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableView<Customer> tblTable;

        @FXML
        void btnUpdateOnAction(ActionEvent event) {

            Customer selectedCustomer = tblTable.getSelectionModel().getSelectedItem();

            if (selectedCustomer != null) {

                selectedCustomer.setId(txtId.getText());
                selectedCustomer.setName(txtName.getText());
                selectedCustomer.setAddress(txtAddress.getText());
                selectedCustomer.setContactNumber(txtNumber.getText());

                tblTable.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPDATE");
                alert.setHeaderText(null);
                alert.setContentText("Customer was Updated Successfully !");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NOT UPDATE");
                alert.setHeaderText(null);
                alert.setContentText("No Customer Selected for Update ?");
                alert.showAndWait();
            }
        }

    private void loadTable(){
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(obj->{
            customerObservableList.add(obj);
        });

        tblTable.setItems(customerObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));

        tblTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                setTexttoValue(newValue);
            }
        }));

        loadTable();
    }

    private void setTexttoValue(Customer newValue){
        txtId.setText(newValue.getId());
        txtId.setEditable(false);
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtNumber.setText(newValue.getContactNumber());
    }

    public void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }
}
