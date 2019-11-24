package entity.tool;

import entity.Player;
import entity.Tool;

public class YellowKey extends Tool implements Pickable {
    public YellowKey(){
        super();
    }

    public YellowKey(String [] arr){
        super(arr);
    }

    @Override
    public Boolean pick(Player player) {
        int number = player.getYellow_key_number() + 1;
        System.out.println("恭喜你捡到黄钥匙一个，您目前的黄钥匙数量为为 " + number );
        player.setYellow_key_number(number);
        return true;
    }
}
