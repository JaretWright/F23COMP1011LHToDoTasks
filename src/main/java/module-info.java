module com.example.f23comp1011lhtodotasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.f23comp1011lhtodotasks to javafx.fxml;
    exports com.example.f23comp1011lhtodotasks;
}