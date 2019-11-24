package entity.tile;

import controller.Board;
import entity.Player;
import entity.Tile;

import java.util.concurrent.ThreadPoolExecutor;

public class Downstairs extends Tile implements Climb {
    public Downstairs(){
        super();
    }

    public Downstairs(String [] arr){
        super(arr);
    }

    @Override
    public Boolean climb(Player player, Board board) {
        int current_floor = player.getFloor();
        int target_floor = current_floor - 1;
        int target_x = 0;
        int target_y = 0;
        for(int i=0;i<13;i++){
            for(int j=0;j<13;j++){
                if(board.getBoard()[target_floor][i][j] instanceof Upstairs){
                    target_x = i;
                    target_y = j;
                }
            }
        }
        player.setFloor(target_floor);
        player.setX(target_x);
        player.setY(target_y);
        return true;
    }
}
