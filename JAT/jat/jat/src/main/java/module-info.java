module com.sturdy_softwares {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sturdy_softwares to javafx.fxml;
    exports com.sturdy_softwares;
}
