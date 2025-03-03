module com.lgh.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.lgh.demo to javafx.fxml;
    exports com.lgh.demo;
}
