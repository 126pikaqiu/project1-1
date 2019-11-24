package entity.tool;

import entity.Player;
import entity.Tool;

public class MiddleBloodBottle extends Tool implements Pickable{

    public MiddleBloodBottle(){
        super();
    }

    public MiddleBloodBottle(String [] arr){
        super(arr);
    }

    private final int improvement = 100;
    @Override
    public Boolean pick(Player player) {
        int blood = player.getBlood() + improvement;
        System.out.println("恭喜你捡到中血瓶一个，您目前的生命值为 " + blood );
        player.setBlood(blood);
        return true;
    }
}
