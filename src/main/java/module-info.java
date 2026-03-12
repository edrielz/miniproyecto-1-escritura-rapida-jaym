module com.escriturarapida.miniproyectopoo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.escriturarapida.miniproyectopoo.controller to javafx.fxml;
    exports com.escriturarapida.miniproyectopoo;
}