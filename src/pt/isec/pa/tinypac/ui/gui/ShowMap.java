package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class ShowMap {

    public void mapshow(TilePane tilePane, GameManager gameManager){

            /*System.out.println("size x "+ (gameManager.getFsm().getData().getMapLayer().length));
            System.out.println("size y "+ (gameManager.getFsm().getData().getMapLayer()[0].length));*/


           for (int i = 0; i < gameManager.getFsm().getData().getMapLayer().length -1; i++) {
            for (int j = 0; j < gameManager.getFsm().getData().getMapLayer()[i].length ; j++) {
                char mapCell = gameManager.getFsm().getData().getMapLayer()[i][j];
                ImageView imageView = new ImageView();

                switch (mapCell) {
                    case 'x' -> {
                        imageView.setImage(ImageManager.getImage("wall.png"));
                        imageView.setFitHeight(21);
                        imageView.setFitWidth(21);
                    }
                    case 'º' -> {
                        imageView.setImage(ImageManager.getImage("ball.png"));
                        imageView.setFitHeight(8);
                        imageView.setFitWidth(8);
                    }
                    case 'O' -> {
                        imageView.setImage(ImageManager.getImage("power.png"));
                        imageView.setFitHeight(18);
                        imageView.setFitWidth(18);
                    }
                    case 'W' -> {
                        imageView.setImage(ImageManager.getImage("portal.png"));
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'P' -> {
                        switch (gameManager.getFsm().getData().getPac().getDirection()){
                            case 1 ->imageView.setImage(ImageManager.getImage("pacUP.png"));
                            case 2 ->imageView.setImage(ImageManager.getImage("pacLEFT.png"));
                            case 3 ->imageView.setImage(ImageManager.getImage("pacDOWN.png"));
                            case 4 ->imageView.setImage(ImageManager.getImage("pacRIGHT.png"));
                            default -> imageView.setImage(ImageManager.getImage("pacRIGHT.png"));
                        }
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'B' -> {
                        if(gameManager.getFsm().getData().getBlinky().isVulnerable()){
                            imageView.setImage(ImageManager.getImage("run.png"));
                        }else {
                            imageView.setImage(ImageManager.getImage("blinky.png"));
                        }
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'p' -> {
                        if(gameManager.getFsm().getData().getBlinky().isVulnerable()){
                            imageView.setImage(ImageManager.getImage("run.png"));
                        }else {
                            imageView.setImage(ImageManager.getImage("pinky.png"));
                        }
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'I' -> {
                        if(gameManager.getFsm().getData().getBlinky().isVulnerable()){
                            imageView.setImage(ImageManager.getImage("run.png"));
                        }else {
                            imageView.setImage(ImageManager.getImage("inky.png"));
                        }
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'C' -> {
                        if(gameManager.getFsm().getData().getBlinky().isVulnerable()){
                            imageView.setImage(ImageManager.getImage("run.png"));
                        }else {
                            imageView.setImage(ImageManager.getImage("clyde.png"));
                        }
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(18);
                    }
                    case 'F' -> {
                        imageView.setImage(ImageManager.getImage("food.png"));
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(20);
                    }

                    default -> {
                        imageView.setFitHeight(20);
                        imageView.setFitWidth(20);
                    }
                }
                tilePane.getChildren().add(imageView);
            }
        }
    }
}
