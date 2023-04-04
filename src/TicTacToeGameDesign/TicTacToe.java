package TicTacToeGameDesign;

import TicTacToeGameDesign.Controller.GameController;
import TicTacToeGameDesign.Factory.BotPlayingDifficultyLevelEnumFactory;
import TicTacToeGameDesign.Factory.BotPlayingStrategyFactory;
import TicTacToeGameDesign.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter the Dimension :");
        int dimension = sc.nextInt();
        int playerSize = dimension - 1;
        List<Player> players = new ArrayList<Player>();
        String  botDifficulty = null;

        sc.nextLine();
        System.out.println("Do you need Bot Player Y/N:");
        String isBotReq = sc.nextLine();

        if (isBotReq.equalsIgnoreCase("Y")) {
            playerSize = dimension - 2;
            System.out.println("Select Bot Difficulty Level : Easy, Medium, Hard");
            botDifficulty = sc.nextLine();
        }

        for (int i = 0; i < playerSize; i++) {
            System.out.println("Please Enter Player's Name");
            String name = sc.nextLine();

            System.out.println("Please select Player's Symbol");
            char symbol = sc.nextLine().charAt(0);

            players.add(new Player(name, i, symbol, PlayerType.HUMAN));
        }

        if (isBotReq.equalsIgnoreCase("Y")) {
            System.out.println("Please Enter Bot Name :");
            String botName = sc.nextLine();

            System.out.println("Please Enter Bot Symbol :");
            String botSymbol = sc.nextLine();

            BotDifficultyLevel botDifficultyLevel= BotPlayingDifficultyLevelEnumFactory.getBotPlayingDifficultyLevelEnumFactory(botDifficulty);

            players.add(new Bot(botName, playerSize, botSymbol.charAt(0), BotPlayingStrategyFactory.getBotPlayingStrategyInstance(botDifficultyLevel), botDifficultyLevel));
        }

        GameController gameController = new GameController();
        Game game = gameController.createGame(players, dimension);
        String isUndo = "N";
        boolean undoDone = false;
        while (game != null && gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the Current Board");
            gameController.displayBoard(game);

            if (gameController.getCurrentMove(game) != 0 && gameController.getCurrentPlayerType(game).equals(PlayerType.HUMAN) && !undoDone) {

                System.out.println("Do you want to undo Y/N");
                isUndo = sc.nextLine();
            }
            if (isUndo.equalsIgnoreCase("Y")) {
                gameController.undo(game);
                isUndo = "N";
               undoDone = true;
            } else {
                gameController.makeNextMove(game);
               // isUndo = "N";
               undoDone = false;
            }
        }
        gameController.displayBoard(game);
        if(gameController.getGameStatus(game).equals(GameStatus.DRAW)){
            System.out.println("Game is drawn");
        }
        else{
            System.out.println(gameController.getGameWinner(game).getName() +
                    " with symbol " + gameController.getGameWinner(game).getSymbol() +" is the Winner");
        }
    }

}
