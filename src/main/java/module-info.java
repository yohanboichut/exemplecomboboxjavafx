module exempletableview {

    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    exports application;
    opens view to javafx.fxml;
    opens modele to javafx.base;
}