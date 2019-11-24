package controller;

import entity.Enemy;
import entity.Player;
import entity.tile.Blank;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Board {

    private Object [][][] board = new Object[5][13][13];

    private HashMap<String,String[]> hashMap = new HashMap<>();

    public Board(String dirName){
        readBoard(dirName);
    }

    private void readBoard(String dirName){
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("data/data.txt"), StandardCharsets.UTF_8);
            BufferedReader br= new BufferedReader(isr);
            String line;
            String[][] data = new String[27][];
            int line_number = 0;
            while ((line = br.readLine())!=null){
                data[line_number] = line.split("-");
                hashMap.put(data[line_number][0], data[line_number]);
                line_number++;
            }
            for(int i=0;i<5;i++){
                isr = new InputStreamReader(new FileInputStream(dirName + "/map/" + i + ".txt"), StandardCharsets.UTF_8);
                br= new BufferedReader(isr);
                for (int j = 0; j < 13; j++) {
                    char [] arr = br.readLine().toCharArray();
                    for(int k = 0; k < 13; k++){
                        Class<?> c = Class.forName(hashMap.get(String.valueOf(arr[k]))[2]);
                        Class[] parameterTypes={String [].class};
                        //根据参数类型获取相应的构造函数
                        Constructor constructor= c.getConstructor(parameterTypes);
                        board[i][j][k] = constructor.newInstance((Object) hashMap.get(String.valueOf(arr[k])));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[][][] getBoard() {
        return board;
    }

    public void setBlank(int floor,int x,int y){
        this.board[floor][x][y] = new Blank(hashMap.get("a"));
    }

    public void find(Player player){
        int floor = player.getFloor();
        HashSet<Character> enemies = new HashSet<>();
        for(int i=0;i<13;i++){
            for(int j=0;j<13;j++){
                if(board[floor][i][j] instanceof Enemy){
                   enemies.add(((Enemy)board[floor][i][j]).getSymbol());
                }
            }
        }
        System.out.println("怪物简称\t\t" + "怪物名称\t\t\t" + "生命值\t" + "攻击力\t" + "防御力\t" + "金钱\t" + "损血\t");
        for (Character ch : enemies) {
            try {
                Class<?> c = Class.forName(hashMap.get(String.valueOf(ch))[2]);
                Class[] parameterTypes={String [].class};
                //根据参数类型获取相应的构造函数
                Constructor constructor= c.getConstructor(parameterTypes);
                Enemy enemy = (Enemy) constructor.newInstance((Object) hashMap.get(String.valueOf(ch)));
                int damage = player.computeAttack(enemy);
                if(damage != Integer.MIN_VALUE){
                    System.out.print(enemy.getAbbreviation() + "\t\t\t");
                    System.out.printf("%-16s%-8s%-8s%-8s%-8s%-8s",enemy.getName(),enemy.getBlood(),enemy.getAtk(),enemy.getDef(),enemy.getMoney(),damage);
                    System.out.println();
                }else{
                    System.out.print(enemy.getAbbreviation() + "\t\t\t");
                    System.out.printf("%-16s%-8s%-8s%-8s%-8s%-8s",enemy.getName(),enemy.getBlood(),enemy.getAtk(),enemy.getDef(),enemy.getMoney(),"无法攻击");
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}