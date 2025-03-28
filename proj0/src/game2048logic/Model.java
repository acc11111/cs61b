package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import javax.swing.*;
import java.util.Formatter;


/** The state of a game of 2048.
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board. */
    private final Board board;
    /** Current score. */
    private int score;

    /* Coordinate System: column x, row y of the board (where x = 0,
     * y = 0 is the lower-left corner of the board) will correspond
     * to board.tile(x, y).  Be careful!
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (x, y) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** Return the current Tile at (x, y), where 0 <= x < size(),
     *  0 <= y < size(). Returns null if there is no tile there.
     *  Used for testing. */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board. */
    public int size() {
        return board.size();
    }

    /** Return the current score. */
    public int score() {
        return score;
    }


    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        board.clear();
    }


    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board. */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the board is empty.
     *  Empty spaces are stored as null.
     * */

    public boolean emptySpaceExists() {
        for(int i = 0;i< board.size();i++){
            for(int j = 0;j< board.size();j++){
                if(board.tile(i,j)==null){
                    return true;
                }
            }
        }
        // TODO: Task 1. Fill in this function.
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public boolean maxTileExists() {
        for(int i = 0;i< board.size();i++){
            for(int j = 0;j< board.size();j++){
                if(board.tile(i,j) != null && board.tile(i,j).value()==MAX_PIECE){
                    return true;
                }
            }
        }
        // TODO: Task 2. Fill in this function.
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public boolean atLeastOneMoveExists() {
        if(emptySpaceExists()){
            return true;
        }else{
            // 循环遍历每一个元素，查看四周有没有可以合并的元素/相同的值
            for(int x = 0;x< board.size();x++){
                for(int y = 0;y< board.size();y++){
                    // 使用熔断机制避免访问越界的位置，这里需要避免右侧边界的访问下标最多是3，因此不取等
                    if((0 <= x - 1 && x - 1 < 4)&&(board.tile(x,y).value()==board.tile(x-1,y).value())){
                        //检查左侧
                        return true;
                    }
                    if((0 <= x + 1 && x + 1 < 4)&&(board.tile(x,y).value()==board.tile(x+1,y).value())){
                        //检查右侧
                        return true;
                    }
                    if((0 <= y + 1 && y + 1 < 4)&&(board.tile(x,y).value()==board.tile(x,y+1).value())){
                        //检查上侧
                        return true;
                    }
                    if((0 <= y - 1 && y - 1 < 4)&&(board.tile(x,y).value()==board.tile(x,y-1).value())){
                        //检查下侧
                        return true;
                    }
                }
            }
        }
        // TODO: Task 3. Fill in this function.
        return false;
    }

    /**
     * Moves the tile at position (x, y) as far up as possible.
     *
     * Rules for Tilt:
     * 1. If two Tiles are adjacent in the direction of motion (ignoring empty space)
     *    and have the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile currTile = board.tile(x, y);
        int myValue = currTile.value();
        int targetY = y;
        // 依然是使用熔断机制限制访问的位置，以后先将下标放在前面判断，可以使用熔断避免访问
        while(targetY + 1 < 4 && board.tile(x,targetY +1) == null){
            targetY += 1;
        }
        //目前来到了未合并的最前端
        if(targetY + 1 < 4 ){
            Tile nextTile = board.tile(x,targetY +1);
            if(nextTile != null ){
                //检查是否为空指针，避免空指针错误
                if(!currTile.wasMerged()&& (!nextTile.wasMerged())){
                //检查是否合并过
                    if(currTile.value()==board.tile(x,targetY+1).value()){
                        //如果没有被合并过
                        score += currTile.value()*2;
                        targetY += 1;
                        //...
                    }
                }
            }
        }


        board.move(x,targetY,currTile);



    // TODO: Tasks 5, 6, and 10. Fill in this function.
}

    /** Handles the movements of the tilt in column x of board B
     * by moving every tile in the column as far up as possible.
     * The viewing perspective has already been set,
     * so we are tilting the tiles in this column up.
     * */
    public void tiltColumn(int x) {
        for(int y = board.size()-1;y >= 0 ;y--){
            //注意这里需要从移动方向的头那里开始看，不然会导致后面合并，我们想要的是优先头部合并
            if(board.tile(x,y)!=null){
                //还是注意检查null
                moveTileUpAsFarAsPossible(x,y);
            }
        }
        // TODO: Task 7. Fill in this function.
    }

    public void tilt(Side side) {
        board.setViewingPerspective(side);
        for(int x = 0;x<board.size();x++){
            tiltColumn(x);
        }
        board.setViewingPerspective(Side.NORTH);
        // TODO: Tasks 8 and 9. Fill in this function.
    }

    /** Tilts every column of the board toward SIDE.
     */
    public void tiltWrapper(Side side) {
        board.resetMerged();
        tilt(side);
    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
