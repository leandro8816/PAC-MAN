package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.ShowMap;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import static pt.isec.pa.tinypac.model.fsm.PacManStates.POWERED;
import static pt.isec.pa.tinypac.model.fsm.PacManStates.STOPED;
//import pt.isec.pa.tinypac.model.fsm.BetResult;
//import pt.isec.pa.tinypac.model.fsm.GameBWState;
//import pt.isec.pa.tinypac.ui.gui.util.ToastMessage;

public class PoweredUI extends AnchorPane {
    GameManager gameManager;
    TilePane tilePane;
    Button btnEnd;

    public PoweredUI(GameManager gameManager) {
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

        ShowMap a= new ShowMap();
        a.mapshow(tilePane, gameManager);

    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener( e->update());
      /*  for(int i=0;i<btns.length;i++) {
            btns[i].setOnAction(t);
        }*/
       /* btnEnd.setOnAction(event -> {
            gameManager.runnns();
        });*/
    }


    private void update() {
        if (gameManager.getFsm().getCurrentState() != POWERED) {
            this.setVisible(false);
            return;
        }

        tilePane.getChildren().clear();
        ShowMap a= new ShowMap();
        a.mapshow(tilePane, gameManager);
        this.setVisible(true);
    }
}