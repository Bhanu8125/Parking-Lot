package TicTacToeGameDesign.Strategy;

import TicTacToeGameDesign.Models.Board;
import TicTacToeGameDesign.Models.Move;
import TicTacToeGameDesign.Models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board);

}
