module edu.depaul.csc450.shoppingcart {
    requires transitive javafx.graphics;
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
	requires javafx.base;
	requires transitive java.sql;

    opens edu.depaul.csc450.shoppingcart to javafx.fxml;
    exports edu.depaul.csc450.shoppingcart;
}