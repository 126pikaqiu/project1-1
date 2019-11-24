package entity;

public abstract class Entity {
    private char symbol;
    private String name;
    private String abbreviation;
    private String color;
    private String clazzName;

    public Entity(){}

    public Entity(String arr[]) {
        symbol = arr [0].charAt(0);
        name = arr[1];
        clazzName = arr[2];
        abbreviation = arr[3];
        color = arr[4];
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Override
    public String toString() {
        return this.abbreviation;
    }

}
