package org.example.game;

public class HangmanRenderer {
    private static final String[] PICTURES = {
            """
            
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========
            """,
            """
            
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========
            """
    };

    public void render(int pictureNumber) {
        String picture = PICTURES[pictureNumber];
        System.out.println(picture);
    }
}
