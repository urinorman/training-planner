package norman.uri.myapplication;

public class Workout {

    private String name;
    private String description;
    private int difficulty;
    private int length;
    private boolean needEquipment;

    // Constructor with all fields
    public Workout(String name, String description, int difficulty, int length, boolean needEquipment) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.length = length;
        this.needEquipment = needEquipment;
    }

    // Constructor with minimal fields (name only)
    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Default constructor
    public Workout() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNeedEquipment() {
        return needEquipment;
    }

    public void setNeedEquipment(boolean needEquipment) {
        this.needEquipment = needEquipment;
    }
}
