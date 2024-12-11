package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
//import pt.isec.pa.tinypac.model.fsm.GameBWState;

public class BeginUI extends BorderPane {
    GameManager gameManager;
    Button btnStart, btnExit, btnTop,btnno,btnyes;
    ImageView imageView ;
    VBox vBox;
    EventHandler<ActionEvent> btnStartClicked;

    public BeginUI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnno = new Button("NO");
        btnno.setMinWidth(100);
        btnyes  = new Button("YES");
        btnyes.setMinWidth(100);

        btnStart = new Button("Start");
        btnStart.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        btnTop  = new Button("Top5");
        btnTop.setMinWidth(100);

        imageView = new ImageView();
        imageView.setImage(ImageManager.getImage("begin.png"));
        imageView.setFitHeight(150);
        imageView.setFitWidth(400);

        btnStart.setStyle(
                "-fx-background-color: Yellow; " +
                        "-fx-border-color: red; " +
                        "-fx-border-width: 2px; " +
                        "-fx-text-fill: red;"
        );


        vBox = new VBox(imageView, btnStart, btnTop, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener(e->update());

        btnStart.setOnAction( event -> {

            if(gameManager.existSave()){
                this.getChildren().clear();
                Label label = new Label("Pretende continuar o jogo");
                vBox.getChildren().add(label);
                vBox = new VBox(imageView, label, btnno,btnyes);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(10);
                this.setCenter(vBox);
            }else {
                if (btnStartClicked != null) {
                    btnStartClicked.handle(event);
                }
            }
        });
        btnExit.setOnAction( event -> {
            Platform.exit();
        });

        btnTop.setOnAction( event -> {
            if (gameManager.existSavetop()) {
                this.getChildren().clear();
                vBox = new VBox(btnStart);
                Label label = new Label("Name      Points");
                vBox.getChildren().add(label);
                for (int i = 0; i < gameManager.getTop5().getNames().size(); i++) {
                    label = new Label(gameManager.getTop5().getNames().get(i)+ "   " + gameManager.getTop5().getPoints().get(i));
                    vBox.getChildren().add(label);
                }

                vBox.getChildren().add(btnExit);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(10);
                this.setCenter(vBox);
            }
        });

        btnyes.setOnAction( event -> {
            if (btnStartClicked != null) {
                btnStartClicked.handle(event);
            }
            vBox = new VBox(imageView, btnStart, btnTop, btnExit);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            this.setCenter(vBox);
        });

        btnno.setOnAction( event -> {
            gameManager.newfsm(true);
            if (btnStartClicked != null) {
                btnStartClicked.handle(event);
            }
            vBox = new VBox(imageView, btnStart, btnTop, btnExit);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            this.setCenter(vBox);
        });
    }

    public void btnStartClicked(EventHandler<ActionEvent> handler) {
        btnStartClicked = handler;
    }

    private void update() {}
}
