module com.example.streamapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires tools.jackson.databind;
    requires static lombok;
    requires javafx.base;

    opens com.example.streamapi to javafx.fxml;
    opens com.example.streamapi.controller to javafx.fxml;
    opens com.example.streamapi.model to com.fasterxml.jackson.databind, tools.jackson.databind, javafx.base;
    exports com.example.streamapi;
    exports com.example.streamapi.controller;
}