package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.Utils.Position;
import pt.isec.pa.tinypac.Utils.ReedFile;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.symbol.Ghost.*;
import pt.isec.pa.tinypac.model.data.symbol.PacMan;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A classe Data representa os dados do jogo Pac-Man e é utilizada para armazenar e
 * manipular informações relevantes durante o jogo.
 */
public class Data implements  Serializable {
    private int paustate;
    private static final long serialVersionID = 100L;
    private int points = 0;
    private int pointsTotal = 0;
    private int lifes = 3;
    private int ball = 0;
    private int powerTime = 0;
    private int timer = 0;
    private int totalBall = 0;
    private int level = 1;
    private int powerBall = 0;
    private boolean win = false;
    PacMan pac;
    Maze map_layer;
    Blinky blinky ;
    Clyde clyde ;
    Pinky pinky ;
    Inky inky ;
    ArrayList<Position> posibiliTP = new ArrayList<>();
    /**
     * Construtor padrão da classe Data.
     */
    public Data() {
    }
    /**
     * Verifica se o jogador venceu o jogo.
     *
     * @return true se o jogador venceu, false caso contrário.
     */
    public boolean isWin() {
        return win;
    }
    /**
     * Define se o jogador venceu o jogo.
     *
     * @param win true para indicar que o jogador venceu, false caso contrário.
     */
    public void setWin(boolean win) {
        this.win = win;
    }
    /**
     * Obtém o estado de pausa do jogo.
     *
     * @return o estado de pausa (paustate).
     */
    public int getPaustate() {
        return paustate;
    }
    /**
     * Define o estado de pausa do jogo.
     *
     * @param paustate o estado de pausa a ser definido.
     */
    public void setPaustate(int paustate) {
        this.paustate = paustate;
    }
    /**
     * Obtém o objeto PacMan.
     *
     * @return o objeto PacMan.
     */
    public PacMan getPac() {
        return pac;
    }

    /**
     * Obtém o objeto Blinky.
     *
     * @return o objeto Blinky.
     */
    public Blinky getBlinky() {
        return blinky;
    }
    /**
     * Obtém o objeto Clyde.
     *
     * @return o objeto Clyde.
     */
    public Clyde getClyde() {
        return clyde;
    }
    /**
     * Obtém o objeto Pinky.
     *
     * @return o objeto Pinky.
     */
    public Pinky getPinky() {
        return pinky;
    }
    /**
     * Obtém o objeto Inky.
     *
     * @return o objeto Inky.
     */
    public Inky  getInky() {
        return inky;
    }
    /**
     * Obtém a camada de mapa (map_layer) contendo os símbolos do jogo.
     *
     * @return a camada de mapa com os símbolos do jogo.
     */
    public char [][] getMapLayer() {
        char[][] finalmap = map_layer.getMaze();

        finalmap[pac.getX()][pac.getY()] = pac.getSymbol();

        finalmap[pinky.getX()][pinky.getY()] = pinky.getSymbol();
        finalmap[blinky.getX()][blinky.getY()] = blinky.getSymbol();
        finalmap[clyde.getX()][clyde.getY()] = clyde.getSymbol();
        finalmap[inky.getX()][inky.getY()] = inky.getSymbol();

        return finalmap;
    }
    /**
     * Obtém a pontuação atual do jogador.
     *
     * @return a pontuação atual do jogador.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Obtém o número de vidas restantes do jogador.
     *
     * @return o número de vidas restantes do jogador.
     */
    public int getLifes() {
        return lifes;
    }
    /**
     * Atualiza a pontuação do jogador com base no tipo de bola que ele comeu.
     */
    public void pwrpoits(){
        switch (powerBall){
            case 1 -> points += 50;
            case 2 -> points += 100;
            case 3 -> points += 150;
            case 4 -> points += 200;
            default -> {powerBall = 1;pwrpoits();}
        }
    }
    /**
     * Move os fantasmas.
     */
    private void allMove() {
        pac.move(map_layer.getMaze());

        clyde.iSeePacman(pac.getX(), pac.getY(), map_layer.getMaze());
        clyde.move(map_layer.getMaze());
        blinky.move(map_layer.getMaze());
        pinky.move(map_layer.getMaze());
        inky.move(map_layer.getMaze());
    }
    /**
     * Move os fantasmas.
     */
    public void ghostMove() {
        clyde.iSeePacman(pac.getX(), pac.getY(), map_layer.getMaze());
        clyde.move(map_layer.getMaze());
        blinky.move(map_layer.getMaze());
        pinky.move(map_layer.getMaze());
        inky.move(map_layer.getMaze());
    }
    /**
     * Obtém o tempo restante da habilidade de power ball.
     *
     * @return o tempo restante da habilidade de power ball.
     */
    public int getPowerTime() {
        return powerTime;
    }

    private boolean eat(){
        if(map_layer == null){System.out.println(0);}


        char a[][] = map_layer.getMaze();
        if(a[pac.getX()][pac.getY()]== 'º'){
            map_layer.set(pac.getX(),pac.getY(),null);
            points++;
            ball++;
        }

        if(a[pac.getX()][pac.getY()]== 'F'){
            map_layer.set(pac.getX(),pac.getY(),null);
            points += 25;
        }

        if(a[pac.getX()][pac.getY()]== 'O'){
            map_layer.set(pac.getX(),pac.getY(),null);
            powerBall++;
            pwrpoits();
            Ghost[] ghosts = {blinky, clyde, pinky, inky};
            for (Ghost g : ghosts) {
                g.setVulnerable(true);
            }
            powerTime = 500;
            return true;
        }

        return false;
    }
    /**
     * Obtém o valor do temporizador do jogo.
     *
     * @return o valor do temporizador do jogo.
     */
    public int getTimer() {
        return timer;
    }
    /**
     * Define que os fantasmas devem sair da casa inicial.
     */
    public void setout(){
        getBlinky().setOut(true);
        getPinky().setOut(true);
        getInky().setOut(true);
        getClyde().setOut(true);
    }
    /**
     * Teletransporta o PacMan para a posição oposta do teletransporte.
     */
    public void teleport(){
        if(pac.getX() == posibiliTP.get(1).getX() && pac.getY() == posibiliTP.get(1).getY()){
            pac.setX(posibiliTP.get(0).getX());
            pac.setY(posibiliTP.get(0).getY());
            return;
        }
            if(pac.getX() == posibiliTP.get(0).getX() && pac.getY() == posibiliTP.get(0).getY()){
                pac.setX(posibiliTP.get(1).getX());
                pac.setY(posibiliTP.get(1).getY());
                return;
            }

    }
    /**
     * Verifica se todos os fantasmas estão vulneráveis.
     *
     * @return true se todos os fantasmas estão vulneráveis, false caso contrário.
     */
    public boolean alldeath() {
        return !blinky.isVulnerable() && !pinky.isVulnerable() && !inky.isVulnerable() && !clyde.isVulnerable();
    }
    /**
     * Define a direção do PacMan para cima.
     */
    private String select() {
        String levelNum = String.format("%02d", level);
        return "files/Level" + levelNum + ".txt";
    }
    /**
     * Define a direção do PacMan para baixo.
     */
    public void setdup(){
        if (getMapLayer()[pac.getX() - 1][pac.getY()] != 'x' && getMapLayer()[pac.getX() + 1][pac.getY()] != 'Y') {
            pac.setDirection(1);
        }
    }/**
     * Define a direção do PacMan para a esquerda.
     */
    public void setddown(){
        if (getMapLayer()[pac.getX() + 1][pac.getY()] != 'x' && getMapLayer()[pac.getX() + 1][pac.getY()] != 'Y') {
            pac.setDirection(3);
        }
    } /**
     * Define a direção do PacMan para a direita.
     */
    public void setdleft(){
        if (getMapLayer()[pac.getX()][pac.getY() - 1] != 'x' && getMapLayer()[pac.getX()][pac.getY() - 1] != 'Y') {
            pac.setDirection(2);
        }
    }/**
     * Para o movimento do PacMan.
     */
    public void setdright(){
        if (getMapLayer()[pac.getX()][pac.getY() + 1] != 'x' && getMapLayer()[pac.getX()][pac.getY() + 1] != 'Y') {
            pac.setDirection(4);
        }
    }
    /**
     * Para inicializar o nivel.
     */
    public void stop()  {
        int n = 0;
        Random rand = new Random();
        ReedFile a= new ReedFile();

        map_layer = a.readFile(select());

        char [][] mapLayer = map_layer.getMaze();
        ArrayList<Position> validPositions = new ArrayList<>();

        blinky = new Blinky(0,0);
        clyde = new Clyde(0,0);
        pinky = new Pinky(0 ,0);
        inky = new Inky(0,0);
        pac = new PacMan();

        posibiliTP.clear();

        for (int x = 0; x < mapLayer.length; x++) {
            for (int y = 0; y < mapLayer[x].length; y++) {
                if(mapLayer[x][y] == 'M') {
                    //pacman_layer.set(x,y,pac);
                    pac.setX(x);
                    pac.setY(y);
                }
                if(mapLayer[x][y] == 'y'){
                    validPositions.add(new Position(x,y));
                }
                if(mapLayer[x][y] == 'º'){
                    n++;
                }
                if(mapLayer[x][y] == 'W'){
                    posibiliTP.add(new Position(x, y));
                }
            }
        }
        totalBall = n;

        ball = 0;
        powerBall = 0;

        Ghost[] ghosts = {blinky, clyde, pinky, inky};
        for (Ghost g : ghosts) {
            int pos = rand.nextInt(validPositions.size());
            Position arr = validPositions.get(pos);
            g.setX(arr.getX());
            g.setY(arr.getY());
            validPositions.remove(pos);
        }
    }

   /* public void gopac() {
        timer++;

        eat();
        allMove();
        teleport();
    }*/

//    public void setTimer(int timer) {
//        this.timer = timer;
//    }
    /**
     * Movimenta o pacman e os fantasmas e faz verificacoes
     */
    public int ghostout() {
        timer++;
        if(eat()){
            return 1;//powered
        }

        int oldx;
        int oldy;
        oldx = pac.getX();
        oldy = pac.getY();
        allMove();
        teleport();

        Ghost[] ghosts = {blinky, clyde, pinky, inky};
        for (Ghost g : ghosts) {
            if(oldx == g.getX() && oldy == g.getY() && pac.getX() == g.getoldcord().getX() && pac.getY() == g.getoldcord().getY()){
                return 2;//catched
            }
        }
        if(pac.getX() == blinky.getX() &&  pac.getY() == blinky.getY() || pac.getX() == pinky.getX() &&  pac.getY() == pinky.getY() || pac.getX() == inky.getX() &&  pac.getY() == inky.getY() || pac.getX() == clyde.getX() &&  pac.getY() == clyde.getY()){
            return 2;//catched
        }

        if(ball == totalBall && powerBall == 4){
            return 3;//nextlevel
        }
        return 0;
    }
    /**
     * Verifica s e morreu
     */
    public boolean iMorreu() {
        lifes--;
        if(lifes <= 0){
            return true;
        }else {
            this.timer = 0;
            this.points = 0;
            return false;
        }
    }
    /**
     * Movimenta o pacman e os fantasmas e faz verificacoes
     */
    public boolean ghostRun() {
        timer++;
        powerTime--;
        eat();

        int oldx;
        int oldy;
        oldx = pac.getX();
        oldy = pac.getY();

        allMove();
        teleport();

        Ghost[] ghosts = {blinky, clyde, pinky, inky};
        for (Ghost g : ghosts) {
            if(oldx == g.getX() && oldy == g.getY() && pac.getX() == g.getOldxcord() && pac.getY() == g.getOldycord()){
                g.morreu();
                g.setVulnerable(false);

            }
            if(pac.getX() == g.getX() && pac.getY() == g.getY()){
                g.morreu();
                g.setVulnerable(false);

            }
        }

        return ball == totalBall && powerBall == 4;//nextlevel
    }
    /**
     * Verifica os levels
     */
    public boolean levels(){
        if(level == 2){
            pointsTotal += points;
            return true;
        }else {
            pointsTotal += points;
            level ++;
            return false;
        }
    }
    /**
     * Para resetar as variaveis de jogo.
     */
    public void resete(){
        timer = 0;
        powerBall = 0;
        level = 1;
        totalBall = 0;
        powerTime = 0;
        ball = 0;
        lifes = 3;
        points = 0;
        pointsTotal = 0;
    }

}
