package TicTacToeGameDesign.Strategy;

import TicTacToeGameDesign.Models.*;

public class EasyBotPlayingStrategy implements  BotPlayingStrategy{


    @Override
    public Move decideMove(Player player, Board board) {
        int dim = board.getBoard().size();
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(player, new Cell(i, j , CellState.FILLED));
                }
            }
        }
        return null;
    }
}
