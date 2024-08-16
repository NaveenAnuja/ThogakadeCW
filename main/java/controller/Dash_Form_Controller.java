package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Dash_Form_Controller {

    public void btnAddOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.show();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view2/add_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.show();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view2/update_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.show();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view2/search_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.show();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view2/delete_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.show();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view2/view_Customer_Form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
