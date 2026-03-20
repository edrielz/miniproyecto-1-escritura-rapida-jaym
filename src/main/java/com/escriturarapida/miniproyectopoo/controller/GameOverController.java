package com.escriturarapida.miniproyectopoo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Controller for the Game Over user interface.
 * <p>
 * This class is responsible for the operation of the endgame stage.
 * <p>
 * Main responsibilities include:
 *     Updating UI elements such as labels and buttons
 *     Handling game restart and gameover states
 * <p>
 * This controller displays the results obtained by the player
 *
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */

public class GameOverController {

    /**
     * Button to return to main menu.
     */
    @FXML public Button menuButton;

    /**
     * Button to restart the game from the statistics window.
     */
    @FXML public Button restartButton;
    /**
     * Label that shows if you have won or lost the game
     */
    @FXML private Label headerLabel;
    /**
     * Label showing the reason why the game ended in case of winning or losing
     */
    @FXML private Label reasonLabel;
    /**
     * Label showing completed levels
     */
    @FXML private Label levelsLabel;
    /**
     * Label showing the maximum level reached
     */
    @FXML private Label levelLabel;
    /**
     * Label to display the player's remaining time
     */
    @FXML private Label timeLabel;

    private Stage gameStage;

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void setData(String reason, int completedLevels, int currentLevel, int time) {

        if (reason.contains("Felicidades")) {
            headerLabel.setText("🎉 ¡Ganaste!");
            headerLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else {
            headerLabel.setText("❌ Juego terminado");
            headerLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }

        reasonLabel.setText(reason);
        levelsLabel.setText("🏆 Niveles completados: " + completedLevels);
        levelLabel.setText("📊 Nivel alcanzado: " + currentLevel);
        timeLabel.setText("⏱ Tiempo restante: " + time + " s");
    }

    private Runnable onRestart;

    public void setOnRestart(Runnable onRestart) {
        this.onRestart = onRestart;
    }
    /**
     * This method close the stage
     */
    @FXML
    private void handleClose(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method executes the logic to restart the game from the Game Over-view
     */

    @FXML
    private void handleBackToMenu(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/escriturarapida/miniproyectopoo/main-menu-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Menú Principal");
            stage.setScene(new Scene(root));
            stage.show();

            if (gameStage != null) {
                gameStage.close();
            }

            handleClose(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRestart(javafx.event.ActionEvent event) {

        if (onRestart != null) {
            onRestart.run();
        }

        handleClose(event);
    }
}