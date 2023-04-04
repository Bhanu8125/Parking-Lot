package TicTacToeGameDesign.Strategy;

import TicTacToeGameDesign.Models.GameStatus;
import TicTacToeGameDesign.Models.Move;

public interface GameWinningStrategy {
    GameStatus winningStrategy(Move move);
    void undoUpdateCount(int row, int col, char symbol);
}
