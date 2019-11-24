package entity;

public abstract class Enemy extends Entity{
    private int blood;
    private int atk;
    private int def;
    private int money;

    public Enemy(String [] arr){
        super(arr);
        blood = Integer.parseInt(arr[5]);
        atk = Integer.parseInt(arr[6]);
        def = Integer.parseInt(arr[7]);
        money = Integer.parseInt(arr[8]);
    }

    public Enemy(){}

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}
