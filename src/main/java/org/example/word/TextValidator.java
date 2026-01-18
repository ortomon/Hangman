package org.example.word;

public class TextValidator {
    private static final int MIN_LETTERS_IN_WORD_FOR_GAME = 5;
    private final Character.UnicodeBlock allowedAlphabet;

    public TextValidator(Character.UnicodeBlock allowedAlphabet) {
        this.allowedAlphabet = allowedAlphabet;
    }

    public boolean isSingleLetterValid(String text) {
        text = toCanonicalForm(text);
        return text.length() == 1
                && Character.isLetter(text.charAt(0))
                && Character.UnicodeBlock.of(text.charAt(0)) == allowedAlphabet;
    }

    public boolean isWordValid(String word) {
        word = toCanonicalForm(word);
        return word.length() >= MIN_LETTERS_IN_WORD_FOR_GAME
                && word.chars().allMatch(c -> Character.isLetter(c)
                && Character.UnicodeBlock.of(c) == allowedAlphabet);
    }

    public String toCanonicalForm(String text) {
        return text.trim().toLowerCase();
    }
}
