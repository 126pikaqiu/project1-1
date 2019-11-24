package entity.tool;

import entity.Player;
import entity.Tool;

public class BigBloodBottle extends Tool implements Pickable{

    public BigBloodBottle(){
        super();
    }

    public BigBloodBottle(String [] arr){
        super(arr);
    }

    private final int improvement = 250;
    @Override
    public Boolean pick(Player player) {
        int blood = player.getBlood() + improvement;
        System.out.println("恭喜你捡到大血瓶一个，您目前的生命值为 " + blood );
        player.setBlood(blood);
        return true;
    }
}
