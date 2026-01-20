package org.example.validator;

public class WordTextValidator extends TextValidator<String> {
    private static final int MIN_LETTERS_IN_WORD_FOR_GAME = 5;

    public WordTextValidator(Character.UnicodeBlock allowedAlphabet) {
        super(allowedAlphabet);
    }

    @Override
    public boolean isValid(String word) {
        return word.length() >= MIN_LETTERS_IN_WORD_FOR_GAME
                && word.chars().allMatch(c -> Character.isLetter(c)
                && Character.UnicodeBlock.of(c) == allowedAlphabet);
    }
}
