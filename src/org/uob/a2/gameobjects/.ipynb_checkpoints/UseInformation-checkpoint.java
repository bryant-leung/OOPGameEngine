package org.uob.a2.gameobjects;

/**
 * Represents information about how an object can be used in the game.
 * 
 * <p>
 * This class stores details about the usage of an object, such as whether it has
 * already been used, the type of action it performs, the target of the action,
 * the result of the action, and any associated message.
 * </p>
 */
public class UseInformation {
    private boolean isUsed; // Show whether the object is used
    private String action;  // The action performed
    private String target;  // The target of the action
    private String result;  // Result of the action
    private String message; // Associated message with the usage

    // Constructor
    public UseInformation(boolean isUsed, String action, String target, String result, String message) {
        this.isUsed = isUsed;
        this.action = action;
        this.target = target;
        this.result = result;
        this.message = message;
    }

    // Retrieves the type of action associated with this usage
    public String getAction() {
        return action;
    }

    // Retrieves the message associated with this usage
    public String getMessage() {
        return message;
    }

    // Retrieves the key of the resulting game object
    public String getResult() {
        return result;
    }

    // Retrieves the key of the game object being targeted
    public String getTarget() {
        return target;
    }

    // Checks if the object has already been used, return true if used otherwise false
    public boolean isUsed() {
        return isUsed;
    }

    // Sets the type of action associated with this usage
    public void setAction(String action) {
        this.action = action;
    }

    // Sets the message associated with this usage
    public void setMessage(String message) {
        this.message = message;
    }

    // Sets the target of said equipment
    public void setTarget(String target) {
        this.target = target;
    }

    // Sets result of said equipment
    public void setResult(String result) {
        this.result = result;
    }

    // Sets whether an object has been used
    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * Returns a string representation of the usage information, including all attributes.
     *
     * @return a string describing the usage information
     */
    @Override
    public String toString() {
        return "UseInformation{" +
                "isUsed=" + isUsed +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
