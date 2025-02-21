module com.example.Teak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.Teak to javafx.fxml;
    exports com.example.Teak;
}