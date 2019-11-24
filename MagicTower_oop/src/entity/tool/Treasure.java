package entity.tool;

import entity.Player;
import entity.Tool;

public class Treasure extends Tool implements Pickable {
    public Treasure(){
        super();
    }

    public Treasure(String [] arr){
        super(arr);
    }

    @Override
    public Boolean pick(Player player) {
        player.setHasTreasure(Boolean.TRUE);
        return true;
    }
}
