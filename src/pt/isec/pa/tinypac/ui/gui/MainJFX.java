package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.TinyPAcMain;
import pt.isec.pa.tinypac.model.GameManager;

public class MainJFX extends Application {
    GameManager gameManager;

    @Override
    public void init() throws Exception {
        super.init();
        gameManager = TinyPAcMain.gameManager;
    }

    @Override
    public void start(Stage stage) throws Exception {
        newStageForTesting(stage,"Tiny-PacMan");
    }

    private void newStageForTesting(Stage stage, String title) {
        RootPane root = new RootPane(gameManager);
        Scene scene = new Scene(root,900,600);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.show();
    }
}
