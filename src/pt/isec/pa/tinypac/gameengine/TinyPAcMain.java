package pt.isec.pa.tinypac.gameengine;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
//import pt.isec.pa.tinypac.ui.text.UiGame;
import javafx.application.Application;

import java.io.IOException;

public class TinyPAcMain {

    public static GameManager gameManager;
    static {
        gameManager = new GameManager();
    }


    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();

        //GAMEMANGER  <- CONTEX <- DATA

        gameEngine.registerClient(gameManager.getFsm());

        gameEngine.registerClient((g,t) -> gameManager.evolve(t));

        gameEngine.start(150);

        Application.launch(pt.isec.pa.tinypac.ui.gui.MainJFX.class,args);



        //UiGame ui = new UiGame(gameEngine, save.getData(), fsm , save);
        //gameEngine.registerClient(ui);
        //ui.start();
        //gameEngine.waitForTheEnd();
    }
}
