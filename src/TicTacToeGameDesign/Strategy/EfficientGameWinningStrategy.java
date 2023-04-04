package TicTacToeGameDesign.Strategy;

import TicTacToeGameDesign.Models.GameStatus;
import TicTacToeGameDesign.Models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EfficientGameWinningStrategy implements  GameWinningStrategy{

    private List<HashMap<Character, Integer>>  rowSymbolCount;

    private List<HashMap<Character, Integer>> colSymbolCount;

    private HashMap<Character, Integer> topLeftDiagonal;

    private HashMap<Character, Integer> topRightDiagonal;

    private GameStatus gameStatus;

    private int dimension;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;
    }

    private int cellCount;

    public EfficientGameWinningStrategy(int dimension) {
        this.dimension = dimension;
        rowSymbolCount = new ArrayList<HashMap<Character,Integer>>();
        colSymbolCount = new ArrayList<HashMap<Character, Integer>>();
        topLeftDiagonal = new HashMap<Character, Integer>();
        topRightDiagonal = new HashMap<Character, Integer>();
        for(int i=0;i<dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    public boolean checkLeftDiagonal(int row, int col){
        return row ==  col;
    }

    public boolean checkRightDiagonal(int row, int col){
        return row+col == dimension-1;
    }

    @Override
    public GameStatus winningStrategy(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        updateCount(row,col,symbol);
        if(checkWinner(row, col, symbol)){
            setGameStatus(GameStatus.ENDED);
            return GameStatus.ENDED;
        }

        if(checkDraw()){
            setGameStatus(GameStatus.DRAW);
            return GameStatus.DRAW;
        }

        return GameStatus.IN_PROGRESS;
    }



    public void updateCount(int row, int col, char symbol){

        this.cellCount++;
        rowSymbolCount.get(row).put(symbol,rowSymbolCount.get(row).getOrDefault(symbol,0)+1);
        colSymbolCount.get(col).put(symbol,colSymbolCount.get(col).getOrDefault(symbol,0)+1);

        if(checkLeftDiagonal(row,col)){
            topLeftDiagonal.put(symbol,topLeftDiagonal.getOrDefault(symbol,0)+1);
        }

        if(checkRightDiagonal(row, col)){
            topRightDiagonal.put(symbol, topRightDiagonal.getOrDefault(symbol,0)+1);
        }
    }

    public boolean checkWinner(int row, int col, char symbol){

        if(rowSymbolCount.get(row).get(symbol) == dimension) { return true; }
        if(colSymbolCount.get(col).get(symbol) == dimension) { return true; }

        if(checkLeftDiagonal(row, col)){
            if(topLeftDiagonal.get(symbol) == dimension){
                return true;
            }
        }
        if(checkRightDiagonal(row, col)){
            if(topRightDiagonal.get(symbol) == dimension){
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw(){
        if(cellCount == dimension*dimension) return true;
        return false;
    }
    @Override
    public void undoUpdateCount(int row, int col, char symbol){

        this.cellCount--;
        rowSymbolCount.get(row).put(symbol,rowSymbolCount.get(row).get(symbol)-1);
        colSymbolCount.get(col).put(symbol,colSymbolCount.get(col).get(symbol)-1);

        if(checkLeftDiagonal(row,col)){
            topLeftDiagonal.put(symbol,topLeftDiagonal.get(symbol)-1);
        }

        if(checkRightDiagonal(row, col)){
            topRightDiagonal.put(symbol, topRightDiagonal.get(symbol)-1);
        }
    }
}
