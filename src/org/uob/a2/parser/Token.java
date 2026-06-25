package org.uob.a2.parser;

/**
 * Represents a token in the parsing process, consisting of a {@code TokenType} and an optional value.
 * 
 * <p>
 * Tokens are used to represent the smallest units of meaning in the command input,
 * such as keywords, or variables.
 * </p>
 */
public class Token {

    private TokenType tokenType;
    private String value;

    // Constructor that constructs a new Token with the specified type and no associated value
    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
        this.value = null;
    }

    // Constructor that constructs a new Token with the specified type and value
    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    // Method to retrieve the type of this token
    public TokenType getTokenType() {
        return tokenType;
    }

    // Method to retrieve the value of this token
    public String getValue() {
        return value;
    }
}
