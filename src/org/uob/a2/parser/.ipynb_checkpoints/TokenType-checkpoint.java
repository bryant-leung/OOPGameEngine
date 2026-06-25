package org.uob.a2.parser;

/**
 * Represents the types of tokens that can be identified and processed by the parser.
 * 
 * <p>
 * Each {@code TokenType} corresponds to a specific category of input, such as a command,
 * variable, or special symbol. These token types are used during the tokenisation and parsing process.
 * </p>
 */

// NESTED CLASS: Enum.EnumDesc<E extends Enum <E>>
public enum TokenType  {

    // Enum constants representing various commands and token types
    USE, // Represents the "use" command
    GET, // Represents the "get" command
    DROP, // Represents the "drop" command
    LOOK, // Represents the "look" command
    STATUS, // Represents the "status" command
    HELP, // Represents the "help" command
    QUIT, // Represents the "quit" command
    ERROR, // Represents an error token, typically for invalid input
    VAR, // Represents a variable or unclassified word
    MOVE, // Represents the "move" command
    PREPOSITION, // Represents a preposition such as "on", "with", or "using"
    EOL, // Represents the end of a line or a command
    COMBINE, // Added new Combine enum (Not used)
    KEYWORD; // Represents a generic keyword token

    /*
    // Returns an array containing the constants of all the enum constants in the
    // order they are declared
    public static TokenType[] values() {
        
    }


    // Returns the enum constant of this class with the specified name
    // The string must match exactly an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted)
    // name is the name of the enum constant to be returned
    // It returns the enum constant with the specified name
    // Throws IllegalArgumentException if this enum class has no constant with the specified name
    // Throws NullPointerException if the argument is null
    public static TokenType valueOf(String name) {
        

        
      
    }
        */
}
    

