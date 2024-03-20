package edu.depaul.csc450.shoppingcart;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SignInPageController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane accountSignInPage;

	@FXML
	private AnchorPane createAccountPage;

	@FXML
	private AnchorPane needAccountPage;

	@FXML
	private Button haveAccountBtn;

	@FXML
	private Button createAccountBtn;

	@FXML
	private Button loginBtn;

	@FXML
	private Button signUpBtn;

	@FXML
	private Hyperlink passwordReset;
	
	@FXML
	private Hyperlink retHome;

	@FXML
	private TextField answer;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField signInUsername;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField signInPassword;

	@FXML
	private ComboBox<?> securityQuestions;

	private Alert alert;
	private static Logger logger = (Logger) Logger.getLogger(SignInPageController.class.getName());  

	
	public void switchForm(ActionEvent event) {

		TranslateTransition pageSlider = new TranslateTransition();

		if (event.getSource() == createAccountBtn) {
			pageSlider.setNode(needAccountPage);
			pageSlider.setToX(300);
			pageSlider.setDuration(Duration.millis(200));
			accountSignInPage.setVisible(false);
			pageSlider.play();
			populateComboBox();
			pageSlider.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					createAccountPage.setVisible(true);
					createAccountBtn.setVisible(false);
					haveAccountBtn.setVisible(true);
				}
			});
		}

		else if (event.getSource() == haveAccountBtn) {
			pageSlider.setNode(needAccountPage);
			pageSlider.setToX(0);
			pageSlider.setDuration(Duration.millis(200));
			pageSlider.play();

			pageSlider.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					createAccountPage.setVisible(false);
					accountSignInPage.setVisible(true);
					createAccountBtn.setVisible(true);
					haveAccountBtn.setVisible(false);
				}
			});
		}
	}
	

	public void populateComboBox() {

		String[] questions = { "What is your favorite color?", "What is your pet's name?", "Where were you born?" };

		List<String> list = new ArrayList<String>();
		for (String data : questions) {
			list.add(data);
		}

		ObservableList observableList = FXCollections.observableArrayList(list);
		securityQuestions.setItems(observableList);

	}

	public void registerNewUser(ActionEvent event) {

		if (event.getSource() == signUpBtn) {
			if (userName.getText().isEmpty() || password.getText().isEmpty() || firstName.getText().isEmpty()
					|| lastName.getText().isEmpty() || answer.getText().isEmpty()) {
				displayErrorMsg("Please complete the form");

			} else {

				boolean flag = true;

				while (flag) {
					DatabaseQuery dbQuery = new DatabaseQuery();

					String[] iStrings = new String[6];
					iStrings[0] = firstName.getText();
					iStrings[1] = lastName.getText();
					iStrings[2] = userName.getText();
					iStrings[3] = password.getText();
					iStrings[4] = (String) securityQuestions.getValue();
					iStrings[5] = answer.getText();

					try {
						dbQuery.databaseInsertCreateAccount(iStrings);

						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Message");
						alert.setHeaderText(null);
						alert.setContentText("Account registration successful.");
						alert.showAndWait();

						clearRegistrationFields();
						flag = false;

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						displayErrorMsg("Account registration failed.");
						clearRegistrationFields();						
					}
				}
			}
		}
	}

	public void validateLogin(ActionEvent event) {
		Customer customer = null;
		
		if (event.getSource() == loginBtn) {
			if (signInUsername.getText().isEmpty() || signInPassword.getText().isEmpty())
		 {
				displayErrorMsg("Please complete the form");

			} else {
				String[] iStrings = new String[2];
				iStrings[0] = signInUsername.getText();
				iStrings[1] = signInPassword.getText();
				
				DatabaseQuery dbQuery = new DatabaseQuery();
				signInUsername.setText("");
				signInPassword.setText("");

				customer = dbQuery.databaseQuery(iStrings);
			
				if(dbQuery.databaseQuery(iStrings) != null) {
					System.out.println("Cust: "+ customer.getFirstName());
					//return to main page, display cust name
			}
				else {
					displayErrorMsg("Account not found!");
				}
			}
		}
	}
	
    public void switchMainScene(Event event)  {
    	Stage stage = null;
    	
        try {
			FXMLLoader fxmlLoader = new FXMLLoader(MainPageController.class.getResource("MainPage.fxml"));
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
	
	private void displayErrorMsg(String msg) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private void clearRegistrationFields() {
		firstName.setText("");
		lastName.setText("");
		userName.setText("");
		password.setText("");
		securityQuestions.getSelectionModel().clearSelection();
		answer.setText("");
	}
}