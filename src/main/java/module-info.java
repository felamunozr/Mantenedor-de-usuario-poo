module com.munoz.felipe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.munoz.felipe to javafx.fxml;
    exports com.munoz.felipe;
    exports com.munoz.felipe.controller;
    opens com.munoz.felipe.controller to javafx.fxml;
    opens com.munoz.felipe.dto to javafx.base;
}