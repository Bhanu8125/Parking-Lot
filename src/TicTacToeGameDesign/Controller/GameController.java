package TicTacToeGameDesign.Controller;

import TicTacToeGameDesign.Models.Game;
import TicTacToeGameDesign.Models.GameStatus;
import TicTacToeGameDesign.Models.Player;
import TicTacToeGameDesign.Models.PlayerType;

import java.util.List;

public class GameController {

    public Game createGame(List<Player> players, int dimension){
            try {
                return Game.getBuilder()
                        .setDimension(dimension)
                        .setPlayers(players)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Game Cannot be Created");
                return null;
            }
    }

    public int getCurrentMove(Game game){
        return game.getCurrentMove();
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }

    public void undo(Game game){
        game.undo();
    }

    public void makeNextMove(Game game){
        game.makeNextMove();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getGameWinner(Game game) {
        return game.getGameWinner();
    }

    public PlayerType getCurrentPlayerType(Game game)
    {
        return game.getCurrentPlayerType();
    }
}
