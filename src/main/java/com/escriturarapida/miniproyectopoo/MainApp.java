package com.escriturarapida.miniproyectopoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the Fast Typing Game application.
 *
 * This class launches the JavaFX application.
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MainApp.class.getResource("game-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 520, 420);
        stage.setTitle("Escritura Rápida");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}