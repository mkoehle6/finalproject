package edu.depaul.csc450.shoppingcart;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainPageController extends Application {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addToCartBtn;
    
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
    private TableView<Product> prodTableView;

    @FXML
    private TableColumn<Product, Integer> productIDCol;
    
    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, String> productTypeCol;
     
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TableColumn<Product, String> prodDescCol;
    
    @FXML
    private TableColumn<Product, String> prodImgCol;
    
    @FXML
    private TableColumn<Product, Integer> stockRemainCol;
    
    
    private TableSelectionModel<Product> selectionModel;
    private ObservableList<Product> selectedProducts;
	private static Logger logger = (Logger) Logger.getLogger(MainPageController.class.getName());  
	private final String path = "src/main/resources/edu/depaul/csc450/shoppingcart/images/productimages/";
	
	
	@FXML
	public void initialize() {
						
		logger.info("Initialize Method");				
		
		productIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("nameString"));
		productTypeCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productTypeString"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		prodDescCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		stockRemainCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantityInStock"));
		prodImgCol.setCellValueFactory(new PropertyValueFactory<Product, String>("imageName"));
		
		ProductTable pTable = new ProductTable();
		prodTableView.setItems(pTable.getProductsList());
		selectionModel = prodTableView.getSelectionModel();
    	selectionModel.setSelectionMode(SelectionMode.SINGLE);
    	
    	selectionModel.selectFirst();
    	selectedProducts = selectionModel.getSelectedItems();   
    	Image image = displayProdImg(selectedProducts.get(0).getImageName());
    	prodImage.setImage(image);
    	selectedProducts.addListener(new ListChangeListener<Product>() {
    			    public void onChanged(
    			      Change<? extends Product> change) {
    			    	Image image = displayProdImg(selectedProducts.get(0).getImageName());
    			    	prodImage.setImage(image);
    			        System.out.println(
    			          "Selection changed: " + change.getList());
    			      }
    	}); 
	}


	private Image displayProdImg(String imgString) {
		InputStream inputStream = null;
		Image image;
		
		try {
			
			StringBuilder sBuilder = new StringBuilder().append(path).append(imgString);
			
			System.out.println("path " + sBuilder);
			inputStream = new FileInputStream(sBuilder.toString());
			
			System.out.print(inputStream.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		image = new Image(inputStream);
		return image;
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
			String msgString = e.getMessage();
			logger.severe(msgString);
		} catch (IOException e) {
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
			e.printStackTrace();
		}
    }
    
  
    public void addToCart() {
    	
    	//check for cart
    	// check for customer
    	ArrayList<Product> myList = new ArrayList<Product>();
    	System.out.println("here " +selectedProducts.toString());
    	
    	 
    }
    
    public void displayProduct() {
    	
    }
}
