package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;

import static pt.isec.pa.tinypac.model.fsm.PacManStates.*;


public class PauseUI extends BorderPane {
    GameManager gameManager;
    Button btnResume,btnSave,btnExit;
    EventHandler<ActionEvent> btnExitClicked;

    public PauseUI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnResume = new Button("Resume");
        btnResume.setMinWidth(200);
        btnSave  = new Button("Save");
        btnSave.setMinWidth(200);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(200);

        VBox vBox = new VBox(btnResume,btnSave,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener( e->update());

        btnResume.setOnAction(event -> {
             gameManager.pressKey("escape");
        });
        btnSave.setOnAction(event -> {
            gameManager.save();
        });
        btnExit.setOnAction(event -> {
            gameManager.getFsm().stop();
            gameManager.getFsm().getData().resete();

            if (btnExitClicked != null) {
                btnExitClicked.handle(event);
            }
        });
    }
    public void setBtnExitClicked(EventHandler<ActionEvent> handler) {
        btnExitClicked = handler;
    }

    private void update() {
        if (gameManager.getFsm().getCurrentState() != PAUSE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
