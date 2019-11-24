package entity.tile;

import entity.Player;
import entity.Tile;

public class RedDoor extends Tile implements Open{
    public RedDoor(){
        super();
    }

    public RedDoor(String [] arr){
        super(arr);
    }
    @Override
    public Boolean open(Player player){
        if(player.getRed_key_number() > 0){
            int number = player.getRed_key_number() - 1;
            System.out.println("芝麻开门了,你还剩红钥匙数量为 " + number);
            player.setRed_key_number(number);
            return true;
        }else{
            System.out.println("你没有红钥匙了");
            return false;
        }
    }
}
