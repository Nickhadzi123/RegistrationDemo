module com.example.registrationdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.registrationdemo to javafx.fxml;
    exports com.example.registrationdemo;
}