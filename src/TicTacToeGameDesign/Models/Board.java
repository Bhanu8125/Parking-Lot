package TicTacToeGameDesign.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>();
        for(int i=0;i<dimension;i++){
            board.add(new ArrayList<Cell>());
            for(int j=0;j<dimension;j++){
                board.get(i).add(new Cell(i,j, CellState.EMPTY));
            }
        }
    }

    public List<List<Cell>> getBoard() {
        return this.board;
    }

    public Cell getBoardCell(int row, int col){
        return this.board.get(row).get(col);
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void displayBoard(){
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(board.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("|  |");
                }
                else {
                    System.out.print("| " + board.get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println();
        }
    }
}
