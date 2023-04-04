package TicTacToeGameDesign.Models;

import java.util.Scanner;

public class Player {
    private String name;
    private int playerId;

    private char symbol;

    private PlayerType playerType;

    public Player(String name, int playerId, char symbol, PlayerType playerType) {
        this.name = name;
        this.playerId = playerId;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move decideMove(Board board){
        Scanner sc = new Scanner(System.in);
        System.out.println(this.getName() +", Please select your row");
        int row = sc.nextInt();

        System.out.println(this.getName() +", Please select your col");
        int col = sc.nextInt();

        return new Move(this, new Cell(row , col, CellState.FILLED));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
