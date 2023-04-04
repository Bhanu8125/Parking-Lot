package TicTacToeGameDesign.Factory;

import TicTacToeGameDesign.Models.BotDifficultyLevel;

public class BotPlayingDifficultyLevelEnumFactory {
    public static BotDifficultyLevel getBotPlayingDifficultyLevelEnumFactory(String botPlayingDifficultyLevel){
        return switch(botPlayingDifficultyLevel.toLowerCase()){
            case "easy"->  BotDifficultyLevel.EASY;
            case "medium" -> BotDifficultyLevel.MEDIUM;
            case "large" -> BotDifficultyLevel.HARD;
            default -> null;
        };
    }
}
