package entity.tool;

import entity.Player;
import entity.Tool;

public class AttackShop extends Tool implements Bought {
    public AttackShop(){
        super();
    }

    public AttackShop(String [] arr){
        super(arr);
    }

    private final int price = 20;
    private final int improvement = 3;
    @Override
    public Boolean buy(Player player) {
        if(player.getMoney() >= price){
            int atk = player.getAtk() + improvement;
            int money = player.getMoney() - price;
            player.setMoney(money);
            player.setAtk(atk);
            System.out.println("交易成功，您还剩金币 " + money + " 您目前的攻击力为 " + atk);
            return true;
        }else{
            System.out.println("不好意思，您没有二十块钱");
            return false;
        }
    }
}
