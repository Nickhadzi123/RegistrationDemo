package com.example.registrationdemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField firstNameField, lastNameField, emailField, dateOfBirthField, zipCodeField;
    @FXML
    private Button addbtn;
    @FXML
    private Button cancelbtn;
    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, emailErrorLabel, dateOfBirthErrorLabel, zipCodeErrorLabel;

    /* Sets up listener to check validity of input while the user is typing it into the text field
    if input is invalid it will display a message above the text field and turn the outline red.
     */
    /**
     * Adds a validation listener to the provided text field and error label using the given pattern.
     *
     * @param field       The text field to add the validation listener to.
     * @param errorLabel  The label used to display error messages.
     * @param pattern     The regular expression pattern for validation.
     *
     */
    private void addValidationListener(TextField field, Label errorLabel, String pattern) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(pattern)) {
                errorLabel.setText("Invalid input");
                field.getStyleClass().remove("text-field-valid");
                field.getStyleClass().add("text-field-invalid");
                validateFields();
            }
            else {
                errorLabel.setText("");
                field.getStyleClass().remove("text-field-invalid");
                field.getStyleClass().add("text-field-valid");
                validateFields();
            }
        });
    }

    /*
    Sets the listeners to look for certain regular expression patterns, each is different
    and adheres to assignment directions
     */
    private void setValidationListeners() {
        addValidationListener(firstNameField, firstNameErrorLabel, "[a-zA-Z]{2,25}");
        addValidationListener(lastNameField, lastNameErrorLabel, "[a-zA-Z]{2,25}");
        addValidationListener(emailField, emailErrorLabel, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        addValidationListener(dateOfBirthField, dateOfBirthErrorLabel, "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}");
        addValidationListener(zipCodeField, zipCodeErrorLabel, "\\d{5}");
    }
/*
Method used to fully validate fields, checks that the pattern set by the listener
 matches the input in the respective text field. If all fields are valid the add button is enabled.
 */
    private void validateFields() {
        boolean isFirstNameValid = firstNameField.getText().matches("[a-zA-Z]{2,25}");
        boolean isLastNameValid = lastNameField.getText().matches("[a-zA-Z]{2,25}");
        boolean isEmailValid = emailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        boolean isDobValid = dateOfBirthField.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$");
        boolean isZipCodeValid = zipCodeField.getText().matches("\\d{5}");

        addbtn.setDisable(!(isFirstNameValid && isLastNameValid && isEmailValid && isDobValid && isZipCodeValid));
    }

    /*
    Method used to help with transition to next scene,
    if all fields contain valid text then the action of clicking the add button changes the UI.
     */
    private boolean isFormValid() {
        return firstNameField.getStyleClass().contains("text-field-valid") &&
                lastNameField.getStyleClass().contains("text-field-valid") &&
                emailField.getStyleClass().contains("text-field-valid") &&
                dateOfBirthField.getStyleClass().contains("text-field-valid") &&
                zipCodeField.getStyleClass().contains("text-field-valid");
    }

    @FXML
    private void initialize() {
        setValidationListeners();
        validateFields();

    addbtn.setOnAction(event -> {
        if (isFormValid()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("nextView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) addbtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        }
    });
        cancelbtn.setOnAction(event -> {
            Stage stage = (Stage) cancelbtn.getScene().getWindow();
            stage.close();
        });
    }
}

