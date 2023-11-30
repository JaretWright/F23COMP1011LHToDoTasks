module com.example.f23comp1011lhtodotasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;

    opens com.example.f23comp1011lhtodotasks to javafx.fxml, com.google.gson;
    exports com.example.f23comp1011lhtodotasks;
}