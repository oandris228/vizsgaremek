module com.example.carproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.carproject to javafx.fxml;
    exports com.example.carproject;
}