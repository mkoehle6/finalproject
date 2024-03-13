package edu.depaul.csc450.shoppingcart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please complete the form");
				alert.showAndWait();
			} else {
				
				DatabaseQuery dbQuery = new DatabaseQuery();				
				
				String[] iStrings = new String[6];
				iStrings[0] = firstName.getText();
				iStrings[1] = lastName.getText();
				iStrings[2] = userName.getText();
				iStrings[3] = password.getText();
				iStrings[4] = (String) securityQuestions.getValue();
				iStrings[5] = answer.getText();
				
				dbQuery.databaseInsertCreateAccount(iStrings);

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informatio Message");
				alert.setHeaderText(null);
				alert.setContentText("Account registration successful.");
				alert.showAndWait();
				
				firstName.setText("");
				lastName.setText("");
				userName.setText("");
				password.setText("");
				securityQuestions.getSelectionModel().clearSelection();
				answer.setText("");
			}
		}
	}
}
