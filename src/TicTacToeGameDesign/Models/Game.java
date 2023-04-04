package TicTacToeGameDesign.Models;

import TicTacToeGameDesign.Exception.GameException;
import TicTacToeGameDesign.Strategy.EfficientGameWinningStrategy;
import TicTacToeGameDesign.Strategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private  List<Move> moves;
    private int nextPlayerIndex;
    private GameStatus gameStatus;
    private int dimension;
    private GameWinningStrategy gameWinningStrategy;

    private PlayerType currentPlayerType;

    private int currentMove;

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    private Player gameWinner;

    public void displayBoard(){
        this.board.displayBoard();
    }

    public Player getGameWinner() {
        return this.gameWinner;
    }

    public void setGameWinner(Player gameWinner) {
        this.gameWinner = gameWinner;
    }

    public static  GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void undo(){
        int size = moves.size();
        Move move = moves.get(size-1);
        Cell lastPlayedCell = move.getCell();

        int row = lastPlayedCell.getRow();
        int col = lastPlayedCell.getCol();
        //Player player = move.getPlayer();
        //System.out.println(player +"   " + row +"...." + col );
        char symbol = move.getPlayer().getSymbol();

        Cell boardCell = board.getBoardCell(row, col);
        boardCell.setCellState(CellState.EMPTY);
        gameWinningStrategy.undoUpdateCount(row, col, symbol);

        this.gameStatus = GameStatus.IN_PROGRESS;
        this.nextPlayerIndex--;
        this.currentMove--;
    }

    public void makeNextMove(){
        Player player = players.get(nextPlayerIndex);
        currentPlayerType = player.getPlayerType();
        System.out.println(player.getName() +" 's move");
        Move playedMove = player.decideMove(board);

        int row = playedMove.getCell().getRow();
        int col = playedMove.getCell().getCol();

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(player);
        moves.add(playedMove);

        //System.out.print(player +"   " + row +"   " + col +"   " + player.getName() +"  "+ player.getSymbol() + player.getPlayerId());

        gameStatus = gameWinningStrategy.winningStrategy(playedMove);
        if(gameStatus.equals(GameStatus.ENDED)){
            this.gameWinner = player;
        }
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();
        currentMove++;
    }

    public PlayerType getCurrentPlayerType(){
        return this.currentPlayerType;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public static class GameBuilder{

        private int dimension;
        private List<Player> players;

        private GameBuilder(){

        }

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() throws GameException {
            try{
                valid();
            }
            catch (GameException gameException){
                throw gameException;
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setDimension(this.dimension);
            game.setBoard(new Board(this.dimension));
            game.setPlayers(players);
            game.setMoves(new ArrayList<Move>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new EfficientGameWinningStrategy(this.dimension));

            return game;
        }

        public void valid() throws  GameException{
            if(dimension < 3) throw new GameException("Dimension should be greater than or equal to be 3");

            if(this.players.size() != dimension-1) throw new GameException("Game should have dimension-1 players");

            int botCount=0, humanCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
                else {
                    humanCount++;
                }
            }
            if(botCount > 1) throw new GameException("Game Should have only one Bot");

            if(players.size()>=2){
                if(humanCount < dimension-2) throw new GameException("Game should have at least dimension-2 Human Players");
            }
        }
    }
}
