package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    @Override
    public UseInformation getUseInformation() {
        return useInformation;
    }

    @Override
    public void setUseInformation(UseInformation useInformation) {
        this.useInformation = useInformation;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String use(GameObject target, GameState gameState) {
        // Check if the equipment has been used and if the target is valid
        if (!useInformation.isUsed() && target.getId().equals(useInformation.getTarget())) {
            // Reveal the hidden item if the result matches an item ID
            for (Item item : gameState.getCurrentRoom().getItems()) {
                if (item.getId().equals(useInformation.getResult())) {
                    item.setHidden(false);
                }
            }
            // Mark the equipment as used
            useInformation.setUsed(true);
            return useInformation.getMessage();
        }
        return "You have already used " + getName();
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "useInformation=" + useInformation +
                ", id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", hidden=" + getHidden() +
                '}';
    }
}
