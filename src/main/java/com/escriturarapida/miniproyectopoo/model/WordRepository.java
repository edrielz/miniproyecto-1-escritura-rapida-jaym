package com.escriturarapida.miniproyectopoo.model;

import java.util.List;
import java.util.Random;

/**
 * Provides random words or phrases for the game.
 */
public class WordRepository {

    private final List<String> texts;
    private final Random random;

    /**
     * Creates a repository with predefined words and phrases.
     */
    public WordRepository() {
        this.texts = List.of(
                "Hola",
                "Programacion orientada a eventos",
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
                "La puntuacion importa.",
                "PascalCase",
                "camelCase",
                "Abrahan",
                "Ayuda",
                "Sueño",
                "Adios",
                "Que sueño",
                "Poco a poco",
                "Vive la vida",
                "Seis siete"
        );
        this.random = new Random();
    }

    /**
     * Returns a random text from the repository.
     *
     * @return random word or phrase
     */
    public String getRandomText() {
        int index = random.nextInt(texts.size());
        return texts.get(index);
    }
}