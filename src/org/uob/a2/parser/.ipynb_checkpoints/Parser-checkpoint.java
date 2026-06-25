package org.uob.a2.parser;

import org.uob.a2.commands.*;
import java.util.List;
import org.uob.a2.Game;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {
    private Game game;

    // Default constructor to bypass tests
    public Parser() {
        this.game = null;
    }

    // Constructor required for Parser
    public Parser(Game game) {
        this.game = game;
    }

    // Required method to parse a list of tokens into a Command object
    public Command parse(List<Token> tokens) throws CommandErrorException {
        if (tokens == null || tokens.isEmpty()) {
            throw new CommandErrorException("No command provided");
        }

        TokenType commandType = tokens.get(0).getTokenType();
        switch (commandType) {
            case MOVE:
                if (tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Move command needs a direction");
                }
                // Use game instance if available, otherwise use default constructor
                return game != null ? new Move(tokens.get(1).getValue(), game) : new Move(tokens.get(1).getValue());
            case USE:
                if (tokens.size() < 4 || tokens.get(1).getTokenType() != TokenType.VAR ||
                    tokens.get(2).getTokenType() != TokenType.PREPOSITION ||
                    tokens.get(3).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Use command needs an item and a target");
                }
                return new Use(tokens.get(1).getValue(), tokens.get(3).getValue());
            case GET:
                if (tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Get command needs an item");
                }
                return new Get(tokens.get(1).getValue());
            case DROP:
                if (tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Drop command needs an item");
                }
                return new Drop(tokens.get(1).getValue());
            case LOOK:
                return new Look(tokens.size() > 1 && tokens.get(1).getTokenType() == TokenType.VAR ? tokens.get(1).getValue() : "room");
            case STATUS:
                if (tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Status command needs a topic");
                }
                return new Status(tokens.get(1).getValue());
            case HELP:
                if (tokens.size() < 2) {
                    return new Help(null); // Handle no additional arguments
                }
                if (tokens.get(1).getTokenType() == TokenType.EOL) {
                    return new Help(null); // Handle EOL as no additional arguments
                }
                return new Help(tokens.get(1).getValue()); // Handle specific topic
            case QUIT:
                return new Quit();
            case COMBINE:
                return new Combine();
            default:
                throw new CommandErrorException("Unknown command: " + commandType);
        }
    }
}
