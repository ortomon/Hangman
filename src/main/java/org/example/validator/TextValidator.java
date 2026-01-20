package org.example.validator;

public abstract class TextValidator<T> {
    protected final Character.UnicodeBlock allowedAlphabet;

    protected TextValidator(Character.UnicodeBlock allowedAlphabet) {
        this.allowedAlphabet = allowedAlphabet;
    }

    public abstract boolean isValid(T value);
}
