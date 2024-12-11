/*
package pt.isec.pa.tinypac.ui.text;

//import com.googlecode.lanterna.TerminalPosition;
//import com.googlecode.lanterna.TerminalSize;
//import com.googlecode.lanterna.graphics.TextGraphics;
//import com.googlecode.lanterna.screen.TerminalScreen;
//import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.Utils.Save;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import java.io.IOException;
//import com.googlecode.lanterna.TextCharacter;
//import com.googlecode.lanterna.TextColor;
//import com.googlecode.lanterna.input.KeyStroke;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import static pt.isec.pa.tinypac.model.fsm.PacManStates.*;

public class UiGame implements IGameEngineEvolve {
    public GameEngine game;
    //Screen screen;
    private boolean quit;

    public PacManContext context;
   // TextGraphics graphics;
    Data manager;
    Save save ;

    public UiGame(GameEngine a , Data manager, PacManContext fsm, Save save) throws IOException {
        this.quit = false;
        game = a;
        this.context = fsm;
        this.manager = manager;
        this.save = save;

DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80, 40));
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();

    }

public void start() throws IOException {
        while (!quit) {
            switch (game.getCurrentState()){
                case READY ->ready();
                case RUNNING ->runing();
                case PAUSED ->pause();
               }
               System.out.println(" ");
        }
    }


    private void runing() {
    }

 private void ready() throws IOException {
        System.out.println("ready");
        int option = chooseOption("TinyPAcMan", "Iniciar Jogo","Top 5", "Quit");
        if (option == 1) {
            if(save.existSave){
                if(chooseOption("Do you want to continue the saved game?", "Yes","No") == 1){
                    switch (manager.getSaveState()){
                        case START -> context.start();
                        case GHOST_STATE -> context.goghost();
                        case POWERED -> context.gopower();
                    }
                    game.start(150);
                    show();
                    return;
                }
            }
                manager.resete();
                manager.stop();
                game.start(150);
                show();

        } else if (option == 2) {
                System.out.println("top 5");
        }else if (option == 3) {
            quit = true;
            System.exit(1);// todo esta mal
        }
    }


 public void pause() throws IOException {
        System.out.println("pausa");

        int option = chooseOption("TinyPAcMan", "Resume","Save", "Quit");

        if (option == 1) {
            game.resume();
        } else if (option == 2) {
            if(chooseOption("Do you want to save the game?", "Yes","No") == 1){
                manager.setSaveState(context.getCurrentState());
                save.save();
                //pause();
            }
        }else if (option == 3) {
            if(chooseOption("Are you sure you want to leave?", "Yes","No")== 1){
                context.stop();
                manager.resete();
                game.stop();
                //ready();
            }
        }
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
    if(context.getCurrentState() == GAMEND){
            try {
                end();
            }catch (IOException ignored) {
            }
        }

        if(context.getCurrentState() != STOPED){ char[][] mapLayer = manager.getMapLayer();
            try {
                KeyStroke key = screen.pollInput();
                show();
                if (key != null) {
                    switch (key.getKeyType()) {
                        case ArrowUp    -> manager.setdup();
                        case ArrowDown  -> manager.setddown();
                        case ArrowLeft  -> manager.setdleft();
                        case ArrowRight -> manager.setdright();
                        case Escape     -> game.pause();
                    }
                }
            } catch (IOException ignored) {
            }
        }else {
            try {
                KeyStroke key = screen.pollInput();
                if (key != null) {
                    switch (key.getKeyType()) {
                        case ArrowUp    -> {context.start(); manager.getPac().setDirection(1);}
                        case ArrowDown  -> {context.start(); manager.getPac().setDirection(3);}
                        case ArrowLeft  -> {context.start(); manager.getPac().setDirection(2);}
                        case ArrowRight -> {context.start(); manager.getPac().setDirection(4);}
                    }
                }
                show();
            } catch (IOException ignored) {
            }
        }

    }

 public void end () throws IOException {
        if(chooseOption("Game End  Points "+manager.getPoints(),  "Quit") == 1)
            game.stop();
        ready();
    }


private void show() throws IOException {
        TextColor textc , basec;

        screen.clear();
        graphics = screen.newTextGraphics();

        char[][] mapLayer = manager.getMapLayer();
        //this.pacmanLayer = manager.getPacLayer();

        for (int x = 0; x < mapLayer.length; x++) {
            for (int y = 0; y < mapLayer[x].length; y++) {

                TerminalPosition pos = new TerminalPosition(y, x);

                graphics.putString(pos, Character.toString(mapLayer[x][y]));

                if (mapLayer[x][y] == 'x') {
                    textc = TextColor.ANSI.CYAN;
                    basec = TextColor.ANSI.CYAN;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(mapLayer[x][y],textc,basec)[0]);
                }

                if (mapLayer[x][y] == 'º') {
                    textc = TextColor.ANSI.YELLOW;
                    basec = TextColor.ANSI.BLACK;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(mapLayer[x][y],textc,basec)[0]);
                }

                if (mapLayer[x][y] == 'O') {
                    textc = TextColor.ANSI.GREEN_BRIGHT;
                    basec = TextColor.ANSI.BLACK;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(mapLayer[x][y],textc,basec)[0]);
                }

                if (x == manager.getPac().getX() && y == manager.getPac().getY()) {
                    textc = TextColor.ANSI.WHITE;
                    basec = TextColor.ANSI.YELLOW_BRIGHT;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(manager.getPac().getSymbol(),textc,basec)[0]);
                }

                if (x == manager.getBlinky().getX() && y == manager.getBlinky().getY()) {
                    if(manager.getBlinky().isVulnerable()){
                        basec = TextColor.ANSI.WHITE;
                    }else {basec = TextColor.ANSI.RED;}
                    textc = TextColor.ANSI.BLACK;

                    screen.setCharacter(y,x, TextCharacter.fromCharacter(manager.getBlinky().getSymbol(),textc,basec)[0]);
                }
                if (x == manager.getPinky().getX() && y == manager.getPinky().getY()) {
                    if(manager.getPinky().isVulnerable()){
                        basec = TextColor.ANSI.WHITE;
                    }else {basec = TextColor.ANSI.MAGENTA_BRIGHT;}
                    textc = TextColor.ANSI.BLACK;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(manager.getPinky().getSymbol(),textc,basec)[0]);
                }
                if (x == manager.getInky().getX() && y == manager.getInky().getY()) {
                    if(manager.getInky().isVulnerable()){
                        basec = TextColor.ANSI.WHITE;
                    }else {basec = TextColor.ANSI.BLUE_BRIGHT;}
                    textc = TextColor.ANSI.BLACK;

                    screen.setCharacter(y,x, TextCharacter.fromCharacter(manager.getInky().getSymbol(),textc,basec)[0]);
                }
                if (x == manager.getClyde().getX() && y == manager.getClyde().getY()) {
                    if(manager.getClyde().isVulnerable()){
                        basec = TextColor.ANSI.WHITE;
                    }else {basec = TextColor.ANSI.YELLOW;}
                    textc = TextColor.ANSI.BLACK;
                    screen.setCharacter(y,x, TextCharacter.fromCharacter(manager.getClyde().getSymbol(),textc,basec)[0]);
                }
            }
        }
//score
        textc = TextColor.ANSI.GREEN;
        basec = TextColor.ANSI.BLACK;

        String numStr = Integer.toString(manager.getPoints());
        for (int i = 0; i < numStr.length(); i++) {
            screen.setCharacter(i+70,12, TextCharacter.fromCharacter(numStr.charAt(i),textc,basec)[0]);
        }

        String points = "Score";

        for (int i = 0; i < points.length(); i++) {
            screen.setCharacter(i+70,11, TextCharacter.fromCharacter(points.charAt(i),textc,basec)[0]);
        }
// tempo

        String temp = "time";

        for (int i = 0; i < temp.length(); i++) {
            screen.setCharacter(i+70,8, TextCharacter.fromCharacter(temp.charAt(i),textc,basec)[0]);
        }

        String numtime = Integer.toString(manager.getTimer());
        for (int i = 0; i < numtime.length(); i++) {
            screen.setCharacter(i+70,9, TextCharacter.fromCharacter(numtime.charAt(i),textc,basec)[0]);
        }

        String lifes = "Hearts";

        for (int i = 0; i < lifes.length(); i++) {
            screen.setCharacter(i+70,5, TextCharacter.fromCharacter(lifes.charAt(i),textc,basec)[0]);
        }

        textc = TextColor.ANSI.RED;

        switch (manager.getLifes()){
            case 1 -> numtime = "<3 ";
            case 2 -> numtime = "<3 <3 ";
            case 3 -> numtime = "<3 <3 <3";
        }

        for (int i = 0; i < numtime.length(); i++) {
            screen.setCharacter(i+70,6, TextCharacter.fromCharacter(numtime.charAt(i),textc,basec)[0]);
        }

        textc = TextColor.ANSI.GREEN;

        temp = "Level";

        for (int i = 0; i < temp.length(); i++) {
            screen.setCharacter(i+70,2, TextCharacter.fromCharacter(temp.charAt(i),textc,basec)[0]);
        }

        numtime = Integer.toString(manager.getLevel());
        for (int i = 0; i < numtime.length(); i++) {
            screen.setCharacter(i+70,3, TextCharacter.fromCharacter(numtime.charAt(i),textc,basec)[0]);
        }

        screen.refresh();
    }


  public int chooseOption(String title, String ... options) throws IOException {
        int option = -1;

        screen.clear();
        graphics = screen.newTextGraphics();

        if (title != null) {
            graphics.putString(new TerminalPosition(30, 15), title);
        }

        graphics.putString(new TerminalPosition(5, 38), " DEIS-ISEC-IPC   LEI   PA   2022/23   Leandro Carvalho nº2021142870");


        for (int i = 0; i < options.length; i++) {
            graphics.putString(new TerminalPosition(28, i + 17), String.format("%3d - %s", i + 1, options[i]));
        }

        screen.refresh();
        do{
            try {
                KeyStroke key = screen.readInput();
                if (key != null && key.getKeyType() == com.googlecode.lanterna.input.KeyType.Character) {
                    char c = key.getCharacter();
                    if (Character.isDigit(c)) {
                        option = Character.getNumericValue(c);
                    }
                }
            } catch (IOException ignored) {
            }
            screen.refresh();
    }while (option < 1 || option > options.length);
        return option;
    }

}
*/
