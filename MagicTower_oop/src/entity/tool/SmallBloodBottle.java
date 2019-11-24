package entity.tool;

import entity.Player;
import entity.Tool;

public class SmallBloodBottle extends Tool implements Pickable{
    public SmallBloodBottle(){
        super();
    }

    public SmallBloodBottle(String [] arr){
        super(arr);
    }

    private final int improvement = 50;
    @Override
    public Boolean pick(Player player) {
        int blood = player.getBlood() + improvement;
        System.out.println("恭喜你捡到小血瓶一个，您目前的生命值为 " + blood );
        player.setBlood(blood);
        return true;
    }

}
