package com.escriturarapida.miniproyectopoo.util;

/**
 * Utility class for validating typed text.
 */
public final class Validator {

    /**
     * Private constructor to prevent instantiation.
     */
    private Validator() {
    }

    /**
     * Compares the expected text with the user's input using exact matching.
     *
     * @param expected expected target text
     * @param actual   user input
     * @return true if both texts are exactly equal
     */
    public static boolean isExactMatch(String expected, String actual) {
        if (expected == null || actual == null) {
            return false;
        }
        return expected.equals(actual);
    }
}