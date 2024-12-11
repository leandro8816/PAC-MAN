package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.ShowMap;

import static pt.isec.pa.tinypac.model.fsm.PacManStates.STOPED;


public class StopUI extends AnchorPane {
    GameManager gameManager;
    TilePane tilePane;
    Button btnEnd;

    public StopUI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        tilePane = new TilePane(Orientation.HORIZONTAL);
        tilePane.setPrefColumns(gameManager.getFsm().getData().getMapLayer()[0].length);
        tilePane.setPrefTileHeight(20);
        tilePane.setPrefTileWidth(20);
        FlowPane auxPane = new FlowPane(tilePane);
        auxPane.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(auxPane,0.0);
        AnchorPane.setBottomAnchor(auxPane,0.0);
        AnchorPane.setLeftAnchor(auxPane,0.0);
        AnchorPane.setRightAnchor(auxPane,0.0);

        btnEnd = new Button("End game");

        AnchorPane.setRightAnchor(btnEnd,10.0);
        AnchorPane.setBottomAnchor(btnEnd,10.0);
        this.getChildren().addAll(auxPane,btnEnd);
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener(e->update());
    }

    private void update() {
        if (gameManager.getFsm().getCurrentState() != STOPED) {
            this.setVisible(false);
            return;
        }

        tilePane.getChildren().clear();

        ShowMap a= new ShowMap();
        a.mapshow(tilePane, gameManager);

        this.setVisible(true);
    }
}