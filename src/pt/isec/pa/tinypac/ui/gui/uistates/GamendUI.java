package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import static pt.isec.pa.tinypac.model.fsm.PacManStates.GAMEND;

public class GamendUI extends BorderPane {
    GameManager gameManager;
    Button  btnExit, btnSave;
    TextField textField;
    ImageView imageView ;
    EventHandler<ActionEvent> btnExitClicked;


    public GamendUI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
            btnExit = new Button("Exit");
            btnExit.setMinWidth(100);
            btnSave = new Button("Save top 5");
            btnSave.setMinWidth(100);

            imageView = new ImageView();
            imageView.setImage(ImageManager.getImage("win.png"));
            imageView.setFitHeight(300);
            imageView.setFitWidth(400);

            textField = new TextField();

            textField.setMaxWidth(200);
            textField.setMaxHeight(30);

            textField.setPromptText("Digite aqui");

            VBox vBox = new VBox(imageView, textField, btnSave);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            this.setCenter(vBox);
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener( e->update());


        btnSave.setOnAction(event -> {
            gameManager.getTop5().add(textField.getText(),gameManager.getFsm().getData().getPoints());
            gameManager.getSave().savetop5(gameManager.getTop5());

            gameManager.getFsm().stop();
            gameManager.getFsm().getData().resete();
            if (btnExitClicked != null) {
                btnExitClicked.handle(event);
            }
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
        if (gameManager.getFsm().getCurrentState() != GAMEND) {
            this.setVisible(false);
            return;
        }

        if(!gameManager.getFsm().getData().isWin()){
            this.getChildren().clear();

            imageView = new ImageView();
            imageView.setImage(ImageManager.getImage("over.png"));
            imageView.setFitHeight(250);
            imageView.setFitWidth(400);

            VBox vBox = new VBox(imageView, btnExit);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            this.setCenter(vBox);
        }

        this.setVisible(true);
    }
}