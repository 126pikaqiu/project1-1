package entity;

import java.util.HashMap;

public class Player  {
    private int blood;
    private int atk;
    private int def;
    private int money;
    private int red_key_number;
    private int blue_key_number;
    private int yellow_key_number;
    private int floor;
    private int x;
    private int y;
    private Boolean hasTreasure = false;
    private String abbreviation = "勇";
    private String color;

    public Player(){}

    public Player(int blood, int atk, int def, int money, int red_key_number, int blue_key_number, int yellow_key_number, Boolean hasTreasure, int floor, int x, int y) {
        this.blood = blood;
        this.atk = atk;
        this.def = def;
        this.money = money;
        this.red_key_number = red_key_number;
        this.blue_key_number = blue_key_number;
        this.yellow_key_number = yellow_key_number;
        this.hasTreasure = hasTreasure;
        this.floor = floor;
        this.x = x;
        this.y = y;
    }

    public Player(int [] arr) {
        this.blood = arr[0];
        this.atk = arr[1];
        this.def = arr[2];
        this.money = arr[3];
        this.red_key_number = arr[4];
        this.blue_key_number = arr[5];
        this.yellow_key_number = arr[6];
        this.floor = arr[7];
        this.x = arr[8];
        this.y = arr[9];
    }

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

    public int getRed_key_number() {
        return red_key_number;
    }

    public void setRed_key_number(int red_key_number) {
        this.red_key_number = red_key_number;
    }

    public int getBlue_key_number() {
        return blue_key_number;
    }

    public void setBlue_key_number(int blue_key_number) {
        this.blue_key_number = blue_key_number;
    }

    public int getYellow_key_number() {
        return yellow_key_number;
    }

    public void setYellow_key_number(int yellow_key_number) {
        this.yellow_key_number = yellow_key_number;
    }

    public void setHasTreasure(Boolean hasTreasure) {
        this.hasTreasure = hasTreasure;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    //打印玩家状态
    public void printStatus(){
        System.out.println("玩家目前血量为" + this.blood + "，攻击力为" + this.atk + "，防御力为" + this.def + "，金钱数为" + this.money
                + "，黄钥匙数为" + this.yellow_key_number + "，蓝钥匙数为" + this.blue_key_number + "，红钥匙数为" + this.red_key_number + "\n");
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getHasTreasure() {
        return hasTreasure;
    }

    //攻击敌人
    public Boolean attack(Enemy enemy,int x,int y){
        int enemy_blood = enemy.getBlood();
        System.out.println("Attack " + enemy.getName());
        System.out.println(enemy.getName()+ " Blood: " + enemy.getBlood()+ " Atk: " + enemy.getAtk()
                + " Def: " +enemy.getDef() + " Money: " + enemy.getMoney());
        int res = computeAttack(enemy);
        if (res== Integer.MIN_VALUE){
            System.out.println("您的攻击力过低，无法攻击敌人");
        }else if(this.blood <= res){
            System.out.println("您的血量过低，无法攻击敌人");
        }else{
            int damage = this.atk - enemy.getDef();
            int hurt = enemy.getAtk() - this.def;
            while (enemy_blood > 0){
                enemy_blood = Math.max(enemy_blood - damage,0);
                System.out.println("你对敌人造成了" + damage + "伤害， 敌人还剩" + enemy_blood +" 滴血");
                if(enemy_blood> 0){
                    this.blood -= hurt;
                    System.out.println("敌人对你造成了" + hurt + "伤害， 你还剩" + this.blood +" 滴血");
                }
            }
            this.money += enemy.getMoney();
            this.x = x;
            this.y = y;
            System.out.println("恭喜您，您消灭了 " + enemy.getName());
            return true;
        }
        return false;
    }

    //得到攻击敌人收到的伤害 Integer.MIN_VALUE表示无法破防
    public int computeAttack(Enemy enemy){
        int damage = this.atk - enemy.getDef();
        int hurt = enemy.getAtk() - this.def;
        if(damage <= 0){
            return Integer.MIN_VALUE;
        }else if(hurt <= 0){
            return 0;
        }else{
            int damage_times = (int)Math.ceil(enemy.getBlood()/ (double) damage);
            int hurt_times = damage_times - 1;
            return hurt * hurt_times;
        }
    }


}
