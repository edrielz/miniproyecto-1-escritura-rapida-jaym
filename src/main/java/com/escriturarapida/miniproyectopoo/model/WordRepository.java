package com.escriturarapida.miniproyectopoo.model;

import java.util.List;
import java.util.Random;

/**
 * Repository that provides words and phrases used in the Fast Typing Game.
 *
 * This class stores a predefined collection of words and short phrases
 * that can be selected randomly during the game. These texts represent
 * the target strings that the player must type correctly in each round.
 *
 * The repository is intentionally simple and keeps all texts in memory.
 * A Random generator is used to select a random entry from the list
 * whenever the controller requests a new target text.
 *
 * This class acts as a data provider and does not contain game logic.
 * Its only responsibility is to supply random texts for each round.
 *
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */
public class WordRepository {

    /**
     * List containing all available words and phrases for the game.
     */
    private final List<String> texts;

    /**
     * Random number generator used to select texts randomly.
     */
    private final Random random;

    /**
     * Creates a new repository containing predefined words and phrases.
     *
     * The texts include simple words, short phrases, and programming-related
     * terms to increase variety and difficulty during the game.
     */
    public WordRepository() {
        this.texts = List.of(
                "Hola",
                "POE",
                "JavaFX",
                "Escritura rapida",
                "Univalle",
                "Intellij",
                "Miniproyecto",
                "FXML",
                "Scene Builder",
                "Cancela",
                "Linux",
                "Windows",
                "Intuitivo",
                "Puntuacion.",
                "PascalCase",
                "camelCase",
                "Abrahan",
                "Ayuda",
                "Sueño",
                "Poco a poco",
                "Vive la vida",
                "Seis siete",
                "código",
                "programa",
                "software",
                "algoritmo",
                "lógica",
                "variable",
                "constante",
                "dato",
                "tipo",
                "valor",
                "función",
                "método",
                "clase",
                "objeto",
                "módulo",
                "paquete",
                "bucle",
                "ciclo",
                "condición",
                "retorno",
                "Python",
                "Java",
                "compilación",
                "XML",
                "docker",
                "jar",
                "pull",
                "push",
                "commit",
                "GitHub"
        );
        this.random = new Random();
    }

    /**
     * Returns a random word or phrase from the repository.
     *
     * This method selects a random index from the list of texts
     * and returns the corresponding entry. The result is used as
     * the target text that the player must type during a game round.
     *
     * @return a randomly selected word or phrase
     */
    public String getRandomText() {
        int index = random.nextInt(texts.size());
        return texts.get(index);
    }
}