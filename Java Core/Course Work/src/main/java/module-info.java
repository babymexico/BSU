module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.xml;
    requires exp4j;


    opens com.example.coursework to javafx.fxml;
    exports com.example.coursework;
}