package com.example.registrationdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField firstNameField, lastNameField, emailField, dateOfBirthField, zipCodeField;

    @FXML
    private Button addbtn;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, emailErrorLabel, dateOfBirthErrorLabel, zipCodeErrorLabel;

    public void initialize() {
        setValidationListeners();
        validateFields();
    }

    private void setValidationListeners() {
        addValidationListener(firstNameField, firstNameErrorLabel, "[a-zA-Z]{2,25}");
        addValidationListener(lastNameField, lastNameErrorLabel, "[a-zA-Z]{2,25}");
        addValidationListener(emailField, emailErrorLabel, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        addValidationListener(dateOfBirthField, dateOfBirthErrorLabel, "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}");
        addValidationListener(zipCodeField, zipCodeErrorLabel, "\\d{5}");
    }

    private void addValidationListener(TextField field, Label errorLabel, String pattern) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(pattern)) {
                errorLabel.setText("Invalid input");
                field.getStyleClass().remove("text-field-valid");
                field.getStyleClass().add("text-field-invalid");
                validateFields();
            } else {
                errorLabel.setText("");
                field.getStyleClass().remove("text-field-invalid");
                field.getStyleClass().add("text-field-valid");
                validateFields();
            }
        });
    }

    private void validateFields() {
        boolean isFirstNameValid = firstNameField.getText().matches("[a-zA-Z]{2,25}");
        boolean isLastNameValid = lastNameField.getText().matches("[a-zA-Z]{2,25}");
        boolean isEmailValid = emailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        boolean isDobValid = dateOfBirthField.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$");
        boolean isZipCodeValid = zipCodeField.getText().matches("\\d{5}");

        addbtn.setDisable(!(isFirstNameValid && isLastNameValid && isEmailValid && isDobValid && isZipCodeValid));
    }
}
