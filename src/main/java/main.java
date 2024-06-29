import Game.*;
import GuiPackage.GUIGameController;
import javafx.stage.Stage;
import javafx.application.Application;


public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GUIGameController game = new GUIGameController();
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.playGameConsole();

    }
}
