package entity.tool;

import entity.Player;
import entity.Tool;

public class AttackGem extends Tool implements Pickable {
    public AttackGem(){
        super();
    }

    public AttackGem(String [] arr){
        super(arr);
    }

    private final int improvement = 2;
    @Override
    public Boolean pick(Player player) {
        int atk = player.getAtk() + improvement;
        System.out.println("恭喜您捡到一个攻击宝石,您目前的攻击力为 " + atk);
        player.setAtk(atk);
        return true;
    }
}
