package com.escriturarapida.miniproyectopoo.util;

/**
 * Utility class that provides validation methods for the Fast Typing Game.
 *
 * This class contains helper methods used to validate the text entered
 * by the player during the game. It ensures that the player's input matches
 * the expected target text.
 *
 * The class is declared as final and has a private constructor
 * because it is designed as a utility class and should not be instantiated.
 *
 * All validation methods are static and can be accessed directly
 * without creating an object.
 *
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */
public final class Validator {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * This ensures the class is used only through its static methods.
     */
    private Validator() {
    }

    /**
     * Checks whether two texts match exactly.
     *
     * This method compares the expected target text with the text
     * entered by the user. The comparison is case-sensitive and requires
     * both strings to be exactly equal.
     *
     * If either parameter is null, the method returns false
     * to prevent NullPointerException.
     *
     * @param expected the expected target text
     * @param actual the text entered by the user
     * @return true if both texts are exactly equal;
     *         false otherwise
     */
    public static boolean isExactMatch(String expected, String actual) {
        if (expected == null || actual == null) {
            return false;
        }
        return expected.equals(actual);
    }
}