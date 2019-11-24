package entity.tile;

import entity.Player;
import entity.Tile;

public class BlueDoor extends Tile implements Open {
    public BlueDoor(){
        super();
    }

    public BlueDoor(String [] arr){
        super(arr);
    }


    @Override
    public Boolean open(Player player){
        if(player.getBlue_key_number() > 0){
            int number = player.getBlue_key_number() - 1;
            System.out.println("芝麻开门了,你还剩蓝钥匙数量为 " + number);
            player.setBlue_key_number(number);
            return true;
        }else{
            System.out.println("你没有蓝钥匙了");
            return false;
        }
    }
}
