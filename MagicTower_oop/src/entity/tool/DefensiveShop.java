package entity.tool;

import entity.Player;
import entity.Tool;

public class DefensiveShop extends Tool implements Bought {
    public DefensiveShop(){
        super();
    }

    public DefensiveShop(String [] arr){
        super(arr);
    }

    private final int price = 20;
    private final int improvement = 3;
    @Override
    public Boolean buy(Player player) {
        if(player.getMoney() >= price){
            int def = player.getDef() + improvement;
            int money = player.getMoney() - price;
            player.setMoney(money);
            player.setDef(def);
            System.out.println("交易成功，您还剩金币 " + money + " 您目前的防御力为 " + def);
            return true;
        }else{
            System.out.println("不好意思，您没有二十块钱");
            return false;
        }
    }
}
