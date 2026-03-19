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
import javafx.application.Platform;

/**
 * Controller for the Fast Typing Game user interface.
 *
 * This class is responsible for managing the interaction between the
 * JavaFX graphical user interface and the game logic.
 *
 * Main responsibilities include:
 *     Initializing the game and UI components
 *     Handling user input validation
 *     Managing the round timer
 *     Updating UI elements such as labels and buttons
 *     Handling game restart and gameover states
 *
 * The controller uses a Timeline to implement a countdown timer
 * for each round of the game.
 *
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */
public class GameController {

    /**
     * Label that displays the current level.
     */
    @FXML
    private Label levelLabel;

    /**
     * Label that displays the remaining time for the current round.
     */
    @FXML
    private Label timerLabel;

    /**
     * Label that displays the target text the player must type.
     */
    @FXML
    private Label targetTextLabel;

    /**
     * Label used to display feedback messages to the player
     * (e.g., correct, incorrect, or time expired).
     */
    @FXML
    private Label messageLabel;

    /**
     * Text field where the player types the target text.
     */
    @FXML
    private TextField inputTextField;

    /**
     * Button used to validate the player's input.
     */
    @FXML
    private Button validateButton;

    /**
     * Button used to restart the game after it ends.
     */
    @FXML
    private Button restartButton;

    /**
     * Game logic instance that manages levels, time limits, and game state.
     */
    private Game game;

    /**
     * Repository that provides random words or texts for each round.
     */
    private WordRepository wordRepository;

    /**
     * Timeline used as a countdown timer for each round.
     */
    private Timeline timeline;

    /**
     * Remaining seconds for the current round.
     */
    private int remainingSeconds;

    /**
     * Maximum number of levels available in the game.
     */
    private static final int MAX_LEVEL = 45;

    /**
     * Initializes the controller after the FXML file has been loaded.
     *
     * This method creates the game instance, loads the word repository,
     * disables the restart button, and starts the first round.
     */
    @FXML
    public void initialize() {
        game = new Game();
        wordRepository = new WordRepository();
        restartButton.setDisable(true);
        startNewRound();
    }

    /**
     * Handles the validation button action.
     *
     * This method compares the text entered by the player with the
     * target text. If the text matches exactly, the player advances
     * to the next level. Otherwise, the game ends.
     *
     * If the maximum level is reached, the game is completed successfully.
     */
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

            if (game.getCompletedLevels() >= MAX_LEVEL) {
                finishGame("Felicidades, Completaste los 45 niveles!");
                return;
            }
            startNewRound();
        } else {
            messageLabel.setText("¡Incorrecto!");
            messageLabel.setStyle("-fx-text-fill: red;");
            finishGame("Te equivocaste!");
        }
    }

    /**
     * Handles the restart button action.
     *
     * This method resets the game state, enables user input again,
     * disables the restart button, and starts a new game from level one.
     */
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

    /**
     * Starts a new round of the game.
     *
     * This method selects a random text from the repository,
     * updates the level label, clears the input field,
     * and starts the countdown timer.
     */
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

    /**
     * Starts the countdown timer for the current round.
     *
     * The timer decreases the remaining seconds every second.
     * If the time reaches zero, the game ends automatically.
     */
    private void startTimer() {
        stopTimer();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            updateTimerLabel();

            if (remainingSeconds <= 0) {
                stopTimer();
                messageLabel.setText("Tiempo Agotado");
                messageLabel.setStyle("-fx-text-fill: red;");
                Platform.runLater(() ->
                        finishGame("Se te acabo el tiempo!"));
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Stops the current timer if it is running.
     */
    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Updates the timer label in the user interface.
     * The text color changes depending on the remaining time.
     */
    private void updateTimerLabel() {
        timerLabel.setText("Tiempo: " + remainingSeconds + " s");

        if (remainingSeconds <= 5) {
            timerLabel.setStyle("-fx-text-fill: red;");
        }
        else if (remainingSeconds <= 10) {
            timerLabel.setStyle("-fx-text-fill: orange;");
        }
        else {
            timerLabel.setStyle("-fx-text-fill: green;");
        }
    }

    /**
     * Ends the current game and displays a summary dialog.
     *
     * The dialog shows the reason why the game ended,
     * the number of completed levels, the level reached,
     * and the remaining time.
     *
     * @param reason the reason why the game ended
     */
    private void finishGame(String reason) {
        game.endGame();
        inputTextField.setDisable(true);
        validateButton.setDisable(true);
        restartButton.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resumen del Juego");
        alert.setHeaderText("Juego Terminado");
        alert.setContentText(
                "Reason: " + reason + "\n" +
                        "Niveles Completados: " + game.getCompletedLevels() + "\n" +
                        "Nivel Alcanzado: " + game.getCurrentLevel() + "\n" +
                        "Tiempo Restante: " + remainingSeconds + " s"
        );
        alert.showAndWait();
    }
}