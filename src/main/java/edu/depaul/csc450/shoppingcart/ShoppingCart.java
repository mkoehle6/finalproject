package edu.depaul.csc450.shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Integer;
import java.util.Objects;


public class ShoppingCart extends Application {

    @Override
    public void start(Stage primaryStage)  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShoppingCart.class.getResource("ShoppingCart.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            Scene scene = new Scene(anchorPane, 600, 400);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("ShoppingCartDesign.css")).toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
