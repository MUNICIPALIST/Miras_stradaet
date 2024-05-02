module com.example.game_endterm_final {
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
    requires java.sql;

    opens com.example.game_endterm_final to javafx.fxml, javafx.graphics;
    exports com.example.game_endterm_final;
    exports com.example.game_endterm_final.server_path;
    opens com.example.game_endterm_final.server_path to javafx.fxml, javafx.graphics;
}