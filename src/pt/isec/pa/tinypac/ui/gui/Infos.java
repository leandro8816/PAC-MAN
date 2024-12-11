package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class Infos extends HBox {
    GameManager gameManager;
    HBox hbInfos;
    Label lbLifes, lbPoints, lbTime;

    public Infos(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        setBackground(new Background(new BackgroundFill(Color.GRAY.brighter(),CornerRadii.EMPTY, Insets.EMPTY)));
        setPadding(new Insets(10));

        Label lbOut = new Label("Pac-Man");
        lbOut.setAlignment(Pos.CENTER);
        lbOut.setMinWidth(100);

        lbLifes = new Label("Lifes");
        lbLifes.setAlignment(Pos.CENTER);
        lbLifes.setMinWidth(100);

        lbPoints = new Label("Score  " + gameManager.getFsm().getData().getPoints());
        lbPoints.setAlignment(Pos.CENTER);
        lbPoints.setMinWidth(100);

        lbTime = new Label("Time  " + gameManager.getFsm().getData().getTimer());
        lbTime.setAlignment(Pos.CENTER);
        lbTime.setMinWidth(100);

        this.setSpacing(10);

        hbInfos = new HBox();
        hbInfos.setAlignment(Pos.CENTER);

        for (int i = 0; i < gameManager.getFsm().getData().getLifes(); i++) {
            ImageView imageView = new ImageView(ImageManager.getImage("heart.png"));
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            hbInfos.getChildren().add(imageView);
        }
        this.getChildren().addAll(lbOut,lbLifes, hbInfos, lbTime,lbPoints);

    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener(e->update());
    }

    private void update() {

        this.setVisible(true);

        hbInfos.getChildren().clear();

        lbPoints.setText("Points: " + gameManager.getFsm().getData().getPoints());
        lbTime.setText("Timer: " + gameManager.getFsm().getData().getTimer());

        for (int i = 0; i < gameManager.getFsm().getData().getLifes(); i++) {
            ImageView imageView = new ImageView(ImageManager.getImage("heart.png"));
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            hbInfos.getChildren().add(imageView);
        }
    }
}
