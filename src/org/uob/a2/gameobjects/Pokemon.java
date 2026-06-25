package org.uob.a2.gameobjects;

// Extra class to declare Pokemon objects and its parameters
public class Pokemon {
    private String name;
    private String type;
    private double catchRate;
    private double spawnRate; 

    public Pokemon(String name, String type, double catchRate, double spawnRate) {
        this.name = name;
        this.type = type;
        this.catchRate = catchRate;
        this.spawnRate = spawnRate; 
    }

    // Additional getters for those declared parameters
    public String getName() 
    { 
        return name; 
    }
    
    public String getType() { 
        return type; 
    }
    public double getCatchRate() { 
        return catchRate; 
    }
    public double getSpawnRate() { 
        return spawnRate; 
    }  

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
