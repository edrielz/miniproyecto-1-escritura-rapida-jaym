package com.escriturarapida.miniproyectopoo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MainMenuController {

    @FXML
    private void handleStart(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/escriturarapida/miniproyectopoo/game-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Escritura Rapida");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}