import Game.*;
public class main {
    public static void main(String[] args) {
        //Game game = new Game();
        GameWithOpponent gameWithOpponent = new GameWithOpponent(AiPlayerType.BestFirst);
        gameWithOpponent.PlayGame();
    }
}
