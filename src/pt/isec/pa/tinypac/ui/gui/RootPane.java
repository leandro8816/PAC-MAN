package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;
import pt.isec.pa.tinypac.ui.gui.uistates.*;

import static pt.isec.pa.tinypac.model.fsm.PacManStates.*;

public class RootPane extends BorderPane {
    GameManager gameManager;
    StackPane stackPane;
    BeginUI beginUI;
    GamendUI gamendUI;
    PauseUI pauseUI;
    Infos infos;

    public RootPane(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");

        beginUI = new BeginUI(gameManager);

        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
         stackPane = new StackPane(
                new StopUI(gameManager),
                new StartsUI(gameManager),
                new PoweredUI(gameManager),
                pauseUI = new PauseUI(gameManager),
                gamendUI = new GamendUI(gameManager)
        ); // mencionar a possibilidade de apenas ir criando quando muda de estado
        stackPane.setBackground(
                new Background(backgroundFill)
        );

        this.setCenter(beginUI);

        infos = new Infos(gameManager);
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener(e->update());

        beginUI.btnStartClicked(actionEvent -> {
            this.setCenter(stackPane);
        });

        gamendUI.setBtnExitClicked(actionEvent -> {
            this.getChildren().clear();
            this.setCenter(beginUI);
        });

        pauseUI.setBtnExitClicked(actionEvent -> {
            this.getChildren().clear();
            this.setCenter(beginUI);
        });



        setOnKeyPressed(KeyEvent -> {
            System.out.println(KeyEvent.getCode());
            switch (KeyEvent.getCode()) {
                case W,UP -> gameManager.pressKey("up");
                case S,DOWN ->  gameManager.pressKey("down");
                case A,LEFT ->  gameManager.pressKey("left");
                case D,RIGHT ->  gameManager.pressKey("right");
                case ESCAPE ->  gameManager.pressKey("escape");
                default -> System.out.println(" " + KeyEvent.getCode());
            }
        });
    }

    private void update() {
        if(this.getCenter() == beginUI || gameManager.getFsm().getCurrentState() == PAUSE || gameManager.getFsm().getCurrentState() == GAMEND){
            this.setTop(null);
        }else {
            this.setTop(infos);
        }
    }
}
