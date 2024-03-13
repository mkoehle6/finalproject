package edu.depaul.csc450.shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ResourceBundle;

//import javax.management.openmbean.OpenMBeanAttributeInfoSupport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class MainPageController extends Application {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label mainSignIn;

    @FXML
    private ImageView myCart;

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField searchTextField;


    @Override
    public void start(Stage primaryStage)  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainPageController.class.getResource("MainPage.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            Scene scene = new Scene(anchorPane, 600, 400);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("ShoppingCartDesign.css")).toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();

        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    
    
    public void switchSignInPageScene(Event event)  {
    	
    	Stage stage = null;
    	
        try {
			FXMLLoader fxmlLoader = new FXMLLoader(SignInPageController.class.getResource("SignInPage.fxml"));
			AnchorPane anchorPane = fxmlLoader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();      
			Scene scene = new Scene(anchorPane, 600, 400);
			stage.setScene(scene);
	        stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
    }
}
