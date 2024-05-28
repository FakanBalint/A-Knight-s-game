import Game.*;
import GuiPackage.GUIGameController;
import javafx.stage.Stage;
import javafx.application.Application;

import static javafx.application.Application.launch;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GUIGameController game = new GUIGameController();
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.playGameConsole();
        //GameWithOpponent gameWithOpponent = new GameWithOpponent(AiPlayerType.BestFirst);
        //gameWithOpponent.PlayGameConsole();

    }
}
