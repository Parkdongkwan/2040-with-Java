module com.example.demo {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}