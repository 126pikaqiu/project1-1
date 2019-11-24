package entity.tile;

import entity.Tile;

public class Wall extends Tile implements Move{

    public Wall(){
        super();
    }

    public Wall(String [] arr){
       super(arr);
    }

    @Override
    public Boolean move() {
        System.out.println("喂，你撞墙了！");
        return false;
    }
}
