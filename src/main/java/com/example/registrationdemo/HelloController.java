package com.example.registrationdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
@FXML
private TextField firstNameField;
@FXML
private TextField lastNameField;
@FXML
private TextField emailField;
@FXML
private TextField dateOfBirthField;
@FXML
private TextField zipCodeField;
@FXML
private Button addbtn;
@FXML
private Label errorLabel;


    public void initialize() {
        validateFields();


            firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z]{2,25}")) {
                    errorLabel.setText("Invalid First Name");
                    firstNameField.requestFocus();
                } else {
                    errorLabel.setText("");
                    validateFields();
                }
            });

            lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z]{2,25}")) {
                    errorLabel.setText("Invalid Last Name");
                    lastNameField.requestFocus();
                } else {
                    errorLabel.setText("");
                    validateFields();
                }
            });

            emailField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z0-9._%+-]+@farmingdale\\.com")) {
                    errorLabel.setText("Invalid email, format must be @farmingdale.com");
                    emailField.requestFocus();
                } else {
                    errorLabel.setText("");
                    validateFields();
                }
            });

            dateOfBirthField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$")) {
                    errorLabel.setText("Invalid DOB, must be ##/##/####");
                    dateOfBirthField.requestFocus();
                } else {
                    errorLabel.setText("");
                    validateFields();
                }
            });

            zipCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d{5}")) {
                    errorLabel.setText("Invalid zip code, must be 5 digits");
                    zipCodeField.requestFocus();
                } else {
                    errorLabel.setText("");
                    validateFields();
                }
            });
        }

        private void validateFields() {
            boolean isFirstNameValid = firstNameField.getText().matches("[a-zA-Z]{2,25}");
            boolean isLastNameValid = lastNameField.getText().matches("[a-zA-Z]{2,25}");
            boolean isEmailValid = emailField.getText().matches("[a-zA-Z0-9._%+-]+@farmingdale\\.com");
            boolean isDobValid = dateOfBirthField.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$");
            boolean isZipCodeValid = zipCodeField.getText().matches("\\d{5}");

            boolean allFieldsValid = isFirstNameValid && isLastNameValid && isEmailValid && isDobValid && isZipCodeValid;


                addbtn.setDisable(!allFieldsValid);


        }
    }