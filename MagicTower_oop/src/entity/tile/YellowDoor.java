package entity.tile;

import entity.Player;
import entity.Tile;

public class YellowDoor extends Tile implements Open {
    public YellowDoor(){
        super();
    }

    public YellowDoor(String [] arr){
        super(arr);
    }
    @Override
    public Boolean open(Player player){
        if(player.getYellow_key_number() > 0){
            int number = player.getYellow_key_number() - 1;
            System.out.println("芝麻开门了,你还剩黄钥匙数量为 " + number);
            player.setYellow_key_number(number);
            return true;
        }else{
            System.out.println("你没有黄钥匙了");
            return false;
        }
    }
}
