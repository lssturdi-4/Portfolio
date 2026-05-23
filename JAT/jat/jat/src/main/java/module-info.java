module com.sturdy_softwares {
    requires javafx.controls;
    requires javafx.fxml;
    requires tools.jackson.databind;
    requires tools.jackson.core;
    requires java.logging;
    requires java.desktop;

    opens com.sturdy_softwares to javafx.fxml;
    exports com.sturdy_softwares;
}
