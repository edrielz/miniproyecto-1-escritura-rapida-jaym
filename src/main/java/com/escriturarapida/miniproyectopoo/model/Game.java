package com.escriturarapida.miniproyectopoo.model;

/**
 * Represents the game state and rules for the Fast Typing game.
 */
public class Game {

    private int currentLevel;
    private int completedLevels;
    private int currentTimeLimit;
    private String currentText;
    private boolean gameOver;

    /**
     * Creates a new game with default initial values.
     */
    public Game() {
        reset();
    }

    /**
     * Resets the whole game to its initial state.
     */
    public void reset() {
        this.currentLevel = 1;
        this.completedLevels = 0;
        this.currentTimeLimit = 20;
        this.currentText = "";
        this.gameOver = false;
    }

    /**
     * Sets the target text for the current level.
     *
     * @param currentText text the player must type
     */
    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    /**
     * Processes a successful answer and moves to the next level.
     */
    public void advanceLevel() {
        completedLevels++;
        currentLevel++;

        if (completedLevels % 5 == 0 && currentTimeLimit > 2) {
            currentTimeLimit = Math.max(2, currentTimeLimit - 2);
        }
    }

    /**
     * Marks the game as finished.
     */
    public void endGame() {
        this.gameOver = true;
    }

    /**
     * Returns the current level.
     *
     * @return current level number
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Returns the amount of completed levels.
     *
     * @return completed levels
     */
    public int getCompletedLevels() {
        return completedLevels;
    }

    /**
     * Returns the current time limit in seconds.
     *
     * @return current time limit
     */
    public int getCurrentTimeLimit() {
        return currentTimeLimit;
    }

    /**
     * Returns the current target text.
     *
     * @return current target text
     */
    public String getCurrentText() {
        return currentText;
    }

    /**
     * Indicates whether the game is over.
     *
     * @return true if the game is over; false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }
}