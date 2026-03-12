package com.escriturarapida.miniproyectopoo.controller;

import com.escriturarapida.miniproyectopoo.model.Game;
import com.escriturarapida.miniproyectopoo.model.WordRepository;
import com.escriturarapida.miniproyectopoo.util.Validator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class GameController {

    @FXML
    private Label levelLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label targetTextLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button validateButton;

    @FXML
    private Button restartButton;

    private Game game;
    private WordRepository wordRepository;
    private Timeline timeline;
    private int remainingSeconds;

    @FXML
    public void initialize() {
        game = new Game();
        wordRepository = new WordRepository();
        restartButton.setDisable(true);
        startNewRound();
    }

    @FXML
    public void handleValidate() {
        if (game.isGameOver()) {
            return;
        }

        stopTimer();

        String userInput = inputTextField.getText();
        boolean correct = Validator.isExactMatch(game.getCurrentText(), userInput);

        if (correct) {
            messageLabel.setText("¡Correcto!");
            messageLabel.setStyle("-fx-text-fill: green;");
            game.advanceLevel();
            startNewRound();
        } else {
            messageLabel.setText("¡Incorrecto!");
            messageLabel.setStyle("-fx-text-fill: red;");
            finishGame("¡Te equivocaste!");
        }
    }

    @FXML
    public void handleRestart() {
        stopTimer();
        game.reset();
        inputTextField.setDisable(false);
        validateButton.setDisable(false);
        restartButton.setDisable(true);
        messageLabel.setText("");
        startNewRound();
    }

    private void startNewRound() {
        game.setCurrentText(wordRepository.getRandomText());
        levelLabel.setText("Nivel: " + game.getCurrentLevel());
        targetTextLabel.setText(game.getCurrentText());
        inputTextField.clear();
        inputTextField.requestFocus();

        remainingSeconds = game.getCurrentTimeLimit();
        updateTimerLabel();
        startTimer();
    }

    private void startTimer() {
        stopTimer();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            updateTimerLabel();

            if (remainingSeconds <= 0) {
                stopTimer();
                messageLabel.setText("Tiempo agotado");
                finishGame("Tiempo agotado");
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText("Tiempo: " + remainingSeconds + " s");
    }

    private void finishGame(String reason) {
        game.endGame();
        inputTextField.setDisable(true);
        validateButton.setDisable(true);
        restartButton.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resumen de partida");
        alert.setHeaderText("Juego terminado");
        alert.setContentText(
                "Motivo: " + reason + "\n" +
                        "Niveles completados: " + game.getCompletedLevels() + "\n" +
                        "Nivel alcanzado: " + game.getCurrentLevel() + "\n" +
                        "Tiempo restante: " + remainingSeconds + " s"
        );
        alert.showAndWait();
    }
}