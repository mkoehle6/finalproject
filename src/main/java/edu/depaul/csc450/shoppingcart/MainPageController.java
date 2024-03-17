package edu.depaul.csc450.shoppingcart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private ImageView userImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView prodImage;

    @FXML
    private TableView<Product> prodTable;                                                                         

	private static Logger logger = (Logger) Logger.getLogger(MainPageController.class.getName());  

	@Override
	public void init() {
	}
	
    @Override
    public void start(Stage primaryStage) {
    	
    	
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainPageController.class.getResource("MainPage.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            Scene scene = new Scene(anchorPane, 600, 400);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("ShoppingCartDesign.css")).toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();   
        	prodTable = populateProductTable();

            
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    public static void main(String[] args) { 
    	logger.setLevel(Level.INFO);
		logger.addHandler(new ConsoleHandler());
    	
    	 try {
			FileHandler fileHandler = new FileHandler("/ShoppingCart/logs/logger.log");
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			String msgString = e.getMessage();
			logger.severe(msgString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String msgString = e.getMessage();
			logger.severe(msgString);
		}
    	 
        launch(args);
        
    }

    
    public void switchSignInPageScene(Event event)  {
    	logger.info(event.toString());
    	Stage stage = null;
    	
        try {
			FXMLLoader fxmlLoader = new FXMLLoader(SignInPageController.class.getResource("SignInPage.fxml"));
			AnchorPane anchorPane = fxmlLoader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();      
			Scene scene = new Scene(anchorPane, 600, 400);
			stage.setScene(scene);
	        stage.show();
		} catch (Exception e) {
			String msgString = e.getMessage();
			logger.severe(msgString);
		}
    }
    
    public void addToCart() {
    	
    }
    
    public TableView<Product> populateProductTable() {
    	TableView<Product> tableView;
     	ProductTable productTable = new ProductTable();
    	ObservableList<Product> list = FXCollections.observableArrayList();
    	ArrayList<Product> products = productTable.getProducts();
  //  	for(int i=0;i<products.size();i++) {
  //  		list.add(products.get(i));
  //  	}
    	Product p1 =new Product(5,"Car","Auto","12,000",3);
    	list.add(p1);
		tableView = new TableView<Product>(list);	
		//tableView.getColumns().add(productTable.getProdIDColumn());
//					tableData.getProdIDColumn(), tableData.getProdTypeColumn()
//					, tableData.getProdCostColumn());

		return tableView;
    }
    
  
}
