module com.sturdysoftwares {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.sturdysoftwares to javafx.fxml;
    exports com.sturdysoftwares;
}