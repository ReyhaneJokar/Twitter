package client.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Response;
import model.exception.*;
import model.request.Authentication.SignUpReq;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController extends Controller implements Initializable {

    @FXML
    private TextField birthDateTextfield;

    @FXML
    private ChoiceBox<String> countryChoicebox;

    private final String[] countries = {"Canada" , "China" , " Italy" , "Iran" , "Russia"};

    private String country;

    @FXML
    private TextField emailTextfield;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField idTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private TextField phoneNumberTextfield;

    @FXML
    private TextField repeatPasswordTextfield;

    @FXML
    private Button signupButton;

    @FXML
    void loginButtonPressed(ActionEvent event) {
        changeView("login", event);
    }

    @FXML
    void signupButtonPressed(ActionEvent event) {
        try{
            InfoVerifier.checkUserIdValidity(idTextfield.getText());
            InfoVerifier.checkNameAndLastname(nameTextfield.getText() , lastNameTextfield.getText());
            InfoVerifier.checkEmailValidity(emailTextfield.getText());
            InfoVerifier.checkPhoneNumberValidity(phoneNumberTextfield.getText());
            InfoVerifier.checkPasswordValidity(passwordTextfield.getText());
            InfoVerifier.checkRepeatedPasswordValidity(passwordTextfield.getText() , repeatPasswordTextfield.getText());
            InfoVerifier.checkBirthDateValidity(birthDateTextfield.getText());
            if (emailTextfield.getText() == null && phoneNumberTextfield.getText() == null){
                throw new InvalidInputFormatException("You must enter at least email or phone number.");
            }
            if (country == null){
                throw new InvalidInputFormatException("You must choose your country.");
            }
        }
        catch(InvalidUsernameException e){
            errorLabel.setText("User id must have at least 5 characters and only English characters and numbers.");
            errorLabel.setVisible(true);
            return;
        }
        catch(InvalidPasswordException e){
            errorLabel.setText("Password must have at least 8 characters containing a number and an upperCase and a loweCase");
            errorLabel.setVisible(true);
            return;
        }
        catch (InvalidRepeatedPasswordException e) {
            errorLabel.setText("Repeated password is wrong.");
            errorLabel.setVisible(true);
            return;
        }
        catch(InvalidEmailFormatException e){
            errorLabel.setText("Email should have a valid domain like : @gmail.com");
            errorLabel.setVisible(true);
            return;
        }
        catch(InvalidPhoneNumberException e){
            errorLabel.setText("Phone number must have exactly 11 characters and start with 09...");
            errorLabel.setVisible(true);
            return;
        } catch (InvalidInputFormatException | InvalidBirthDateException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            return;
        }

        clientThread.setId(idTextfield.getText());
        //sends the related request to the server
        clientThread.send(new SignUpReq(clientThread.getId(), null , nameTextfield.getText(), lastNameTextfield.getText() , emailTextfield.getText() , phoneNumberTextfield.getText(), passwordTextfield.getText(), country, birthDateTextfield.getText()));

        try{
            Response response = clientThread.getReceiver().getResponse();
            if(response.isAccepted()){
                changeView("home", event).getController();
            }
            else{
                errorLabel.setText("something is wrong with registering, try again.");
                errorLabel.setVisible(true);
            }
        }
        catch(ResponseNotFoundException e){
            errorLabel.setVisible(true);
            errorLabel.setText("No response was received from server.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countryChoicebox.getItems().addAll(countries);
        countryChoicebox.setOnAction(this::getCountry);
    }

    public void getCountry(ActionEvent event){
         country = countryChoicebox.getValue();
    }
}

