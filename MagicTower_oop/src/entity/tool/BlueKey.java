package entity.tool;

import entity.Player;
import entity.Tool;

public class BlueKey extends Tool implements Pickable{
    public BlueKey(){
        super();
    }

    public BlueKey(String [] arr){
        super(arr);
    }

    @Override
    public Boolean pick(Player player) {
        int number = player.getBlue_key_number() + 1;
        System.out.println("恭喜你捡到蓝钥匙一个，您目前的蓝钥匙数量为为 " + number );
        player.setBlue_key_number(number);
        return true;
    }
}
