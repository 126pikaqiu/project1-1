package controller;


import entity.Enemy;
import entity.Entity;
import entity.Player;
import entity.Tile;
import entity.tile.Climb;
import entity.tile.Move;
import entity.tile.Open;
import entity.tool.Bought;
import entity.tool.Pickable;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.BLACK;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.MAGENTA;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class GameController {

    private static GameController instance = new GameController();
    private Board board;
    private Player player;
    private HashMap<String, Ansi.Color> colorMap;

    private GameController(){

    }

    public static GameController getInstance(){
        return instance;
    }

    public void init(){

        initPlayer();
        initBoard();
        printWelcome();
        printHelp();
        printMap();
        player.printStatus();
    }

    public void play(){
        while(true) {
            System.out.println("请输入指令");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            Boolean res = false;
            switch (input){
                case "help":
                    printHelp();
                    break;
                case "w":
                    res = move(-1,0);
                    break;
                case "a":
                    res = move(0,-1);
                    break;
                case "s":
                    res = move(1,0);
                    break;
                case "d":
                    res = move(0,1);
                    break;
                case "f":
                    board.find(player);
                    break;
                case "restart":
                    init();
                    break;
                case "undo":
                    System.out.println("悔棋功能暂未完成，请重新输入");
                    break;
                case "redo":
                    System.out.println("取消悔棋功能暂未完成，请重新输入");
                    break;
                case "save":
                    save();
                    break;
                case "load":
                    load();
                    printMap();
                    player.printStatus();
                    break;
                case "exit":
                    System.exit(0);
                case "map":
                    printMap();
                    break;
                case "status":
                    player.printStatus();
                    break;
                default:
                    System.out.println("输入指令有误请重新输入");
            }
            if(res){
                if(judgeWhetherWin()){
                    System.out.println("恭喜你取得了宝物,游戏获胜");
                }else{
                    printMap();
                    player.printStatus();
                }
            }
        }
    }

    //打印欢迎信息
    public static void printWelcome(){
        System.out.println("欢迎来到魔塔小游戏，在这个小游戏中，身为勇士的你必须闯过一层又一层的魔塔,最后到达塔顶,救回美丽的公主." +
                "魔塔里到处都充满着凶残的怪物，我们必须杀死他们。\n");
    }

    //打印帮助
    public static void printHelp(){
        System.out.println("操作说明：");
        System.out.println("w:" + "向上移动 " + "a:" + "向左移动 " + "s:" + "向下移动 " + "d:" + "向右移动 " + "f:" + "调查怪物信息 " +
                "help:" + "输出帮助 " + "restart:" + "重新开始 " + "exit:" + "推出游戏 " + "undo:" + "撤销一步 " + "redo:" + "取消撤销\n");
        System.out.println("物品说明：");
        System.out.println("钥匙：指定颜色的钥匙开指定颜色的门,没有指定颜色的钥匙则无法进行开门操作");
        System.out.println("血瓶：恢复生命值,小血瓶+50,中血瓶+100,大血瓶+250");
        System.out.println("攻击宝石：拾取加2点攻击");
        System.out.println("防御宝石：拾取加2点防御" + "\n");
        System.out.println("属性说明: ");
        System.out.println("生命值：角色生存的基本，为零时死亡");
        System.out.println("攻击力：对敌人造成伤害的能力");
        System.out.println("防御力：抵挡敌人攻击的能力");
        System.out.println("金币：可以在商人处购买攻击或防御,20金币换3点攻击或者3点防御\n");
        System.out.println("地图说明: ");
        System.out.println(printLen("上：向上的楼梯",15)+printLen("下：向下的楼梯",15));
        System.out.println(printLen("钥：钥匙",15)+printLen("门：需要勇士用对应钥匙开门",15));
        System.out.println(printLen("攻：攻击宝石，加二攻击力",15)+printLen("防：防御宝石，加二防御力",15));
        System.out.println(printLen("小：小血瓶，加五十生命",15)+printLen("中：中血瓶，加一百生命",15));
        System.out.println(printLen("大：大血瓶，加二百五十生命",15)+printLen("墙：墙壁，无法经过",15));
        System.out.println("");
        System.out.println("战斗说明：");
        System.out.println("魔塔中的战斗采取我打你一下，你打我一下的回合制战斗模式，直到一方死亡。"+"每次攻击造成的伤害为：攻击方的攻击力-防御方的防御力.");
        System.out.println("当主角的攻击力小于等于怪物的防御力，"+"或者预计损失超过当前生命值时，将判定主角无法击败怪物，此时主角无法向怪物发起战斗.\n");

    }

    //在字符串后面添加空格，控制字符串长度
    private static String printLen(String str, int len){
        int i=str.length();
        for(;i<len;i++){
            str += "　";
        }
        return str;
    }

    //打印地图
    private void printMap(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("您当前所在的楼层为: " + (player.getFloor() + 1));
            String str ="" ;
            for (int i=0;i<13;i++){
                for(int j=0;j<13;j++){
                    if(i== player.getX() && j== player.getY()){
                        str += "@|red 勇|@";
                    }else{
                        Entity entity = ((Entity)board.getBoard()[player.getFloor()][i][j]);
                        str += "@|" + entity.getColor() + " " + entity.getAbbreviation() +  "|@";
                    }
                }
                str += "\n";
            }
            System.out.println(ansi().eraseScreen().render(str.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //初始化玩家信息，包括钥匙数量，血量，攻击力，防御力，金钱，x y坐标
    public void initPlayer(){
       createPlayer("data/player.txt");
    }

    //初始化棋盘信息
    private void initBoard(){
        board = new Board("data");
    }

    //判断是否获胜
    public Boolean judgeWhetherWin(){
        return player.getHasTreasure();
    }

    //移动单步
    public Boolean move(int move_x,int move_y){
            int position_x = player.getX() + move_x;
            int position_y = player.getY() + move_y;
            Object obj = board.getBoard()[player.getFloor()][position_x][position_y];
            Boolean res = false;
            if(obj instanceof Enemy){
                res = player.attack((Enemy) obj,position_x,position_y);
                if(res){
                    board.setBlank(player.getFloor(),position_x,position_y);
                    player.setX(position_x);
                    player.setY(position_y);
                }
            }else if(obj instanceof Pickable){
                res =((Pickable) obj).pick(player);
                board.setBlank(player.getFloor(),position_x,position_y);
                player.setX(position_x);
                player.setY(position_y);
            }else if(obj instanceof Bought){
                res =((Bought) obj).buy(player);
            }else if(obj instanceof Open){
                res = ((Open) obj).open(player);
                if(res){
                    board.setBlank(player.getFloor(),position_x,position_y);
                    player.setX(position_x);
                    player.setY(position_y);
                }
            }else if(obj instanceof Climb){
                res = ((Climb) obj).climb(player,board);
            }else if(obj instanceof Move){
                res = ((Move)obj).move();
                if(res){
                    player.setX(position_x);
                    player.setY(position_y);
                }
            }
            return res;
    }

    //存档
    private void save(){
        try {
            FileOutputStream out = new FileOutputStream(new File("save/player.txt"), false);
            StringBuffer sb = new StringBuffer();
            sb.append("blood=" + player.getBlood());
            sb.append("\natk=" + player.getAtk());
            sb.append("\ndef=" + player.getDef());
            sb.append("\nmoney=" + player.getMoney());
            sb.append("\nred_key_number=" + player.getRed_key_number());
            sb.append("\nblue_key_number=" + player.getBlue_key_number());
            sb.append("\nyellow_key_number=" + player.getYellow_key_number());
            sb.append("\nfloor=" + player.getFloor());
            sb.append("\nx=" + player.getX());
            sb.append("\ny=" + player.getY());
            out.write(sb.toString().getBytes("utf-8"));

            for(int i=0;i<5;i++){
                out = new FileOutputStream(new File("save/map/" + i + ".txt"),false);
                 sb = new StringBuffer();
                for(int j=0;j<13;j++){
                    for(int k=0;k<13;k++){
                        sb.append(((Entity)board.getBoard()[i][j][k]).getSymbol());
                    }
                    sb.append("\n");
                }
                out.write(sb.toString().getBytes("utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("存档成功");
    }

    //读档
    private void load(){
        createPlayer("save/player.txt");
        board = new Board("save");
        System.out.println("读档成功");
    }

    //创建玩家
    public void createPlayer(String fileName){
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
            BufferedReader br= new BufferedReader(isr);
            String line = null;
            int ints[] = new int[10];
            for(int i=0;i<10;i++){
                line = br.readLine();
                String strings[] = line.split("=");
                ints[i] = Integer.parseInt(strings[1]);
            }
            this.player = new Player(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
