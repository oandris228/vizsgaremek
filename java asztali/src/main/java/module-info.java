module com.example.Teak {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires unirest.java;

    opens com.example.Teak to javafx.fxml;
    exports com.example.Teak;
}

