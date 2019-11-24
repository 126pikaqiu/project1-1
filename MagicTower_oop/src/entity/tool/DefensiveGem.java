package entity.tool;

import entity.Player;
import entity.Tool;

public class DefensiveGem extends Tool implements Pickable{
    public DefensiveGem(){
        super();
    }

    public DefensiveGem(String [] arr){
        super(arr);
    }

    private final int improvement = 2;
    @Override
    public Boolean pick(Player player) {
        int def = player.getDef() + improvement;
        System.out.println("恭喜您捡到一个防御宝石,您目前的防御力为 " + def);
        player.setDef(def);
        return true;
    }
}
