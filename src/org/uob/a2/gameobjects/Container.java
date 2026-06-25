package org.uob.a2.gameobjects;

import java.util.List;

public class Container extends Feature {
    private List<Item> items;
    private List<Equipment> equipment;
    
    public Container(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden);
    }

    public List<Item> getItems() { 
        return items; 
    } 
    
    public void setItems(List<Item> items) { 
        this.items = items; 
    }

    public List<Equipment> getEquipment() { 
        return equipment; 
    } 
    
    public void setEquipment(List<Equipment> equipment) { 
        this.equipment = equipment; 
    }

    public String openContainer() {
        // Itererates between items and equipment declared in that container and appends its names along with description
        StringBuilder message = new StringBuilder("Inside the chest you see:\n"); 
        for (Item item : items) { 
            item.setHidden(false); 
            message.append("Item - ").append(item.getName()).append(": ").append(item.getDescription()).append("\n"); 
        } 
        for (Equipment equip : equipment) { 
            equip.setHidden(false); 
            message.append("Equipment - ").append(equip.getName()).append(": ").append(equip.getDescription()).append("\n"); 
        } 
        return message.toString(); 
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "Container{" +
                "items=" + items +
                ", equipment=" + equipment +
                ", id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", hidden=" + isHidden() +
                '}';
    }
}
