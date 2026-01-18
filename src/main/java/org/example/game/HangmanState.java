package org.example.game;

public enum HangmanState {
    ZERO_MISTAKES("""
            
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """),

    ONE_MISTAKE("""
            
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """),

    TWO_MISTAKE("""
            
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========
            """),

    THREE_MISTAKE("""
            
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========
            """),

    FOUR_MISTAKE("""
            
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========
            """),

    FIVE_MISTAKE("""
            
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========
            """),

    SIX_MISTAKE("""
            
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========
            """);

    private final String state;

    HangmanState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    public static HangmanState fromMistakes(int mistakes) {
        if (mistakes >= values().length) {
            return values()[values().length - 1];
        }
        return values()[mistakes];
    }
}
