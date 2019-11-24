package entity.tile;

import entity.Tile;

public class Blank extends Tile implements Move{
    public Blank(){
        super();
    }

    public Blank(String [] arr){
        super(arr);
    }

    @Override
    public Boolean move() {
        return true;
    }
}
