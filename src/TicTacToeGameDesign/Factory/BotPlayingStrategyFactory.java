package TicTacToeGameDesign.Factory;

import TicTacToeGameDesign.Models.BotDifficultyLevel;
import TicTacToeGameDesign.Strategy.BotPlayingStrategy;
import TicTacToeGameDesign.Strategy.EasyBotPlayingStrategy;
import TicTacToeGameDesign.Strategy.HardBotPlayingStrategy;
import TicTacToeGameDesign.Strategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyInstance(BotDifficultyLevel botDifficultyLevel){
        return switch(botDifficultyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
