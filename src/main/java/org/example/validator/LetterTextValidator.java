package org.example.validator;

public class LetterTextValidator extends TextValidator<Character> {
    public LetterTextValidator(Character.UnicodeBlock allowedAlphabet) {
        super(allowedAlphabet);
    }

    @Override
    public boolean isValid(Character symbol) {
        return Character.isLetter(symbol)
                && Character.UnicodeBlock.of(symbol) == allowedAlphabet;    }
}
