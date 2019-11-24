package entity.tool;

import entity.Player;
import entity.Tool;

public class RedKey extends Tool implements Pickable {
    public RedKey(){
        super();
    }

    public RedKey(String [] arr){
        super(arr);
    }

    @Override
    public Boolean pick(Player player) {
        int number = player.getRed_key_number() + 1;
        System.out.println("恭喜你捡到红钥匙一个，您目前的红钥匙数量为为 " + number );
        player.setRed_key_number(number);
        return true;
    }
}
