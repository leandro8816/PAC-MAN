package pt.isec.pa.tinypac.model.data.symbol.Ghost;

import java.util.ArrayList;
import java.util.Random;
/**
 * Represents the Inky ghost character in the game.
 * Extends the Ghost class.
 */
public class Inky extends Ghost {
    private int corner = 4;
    private int direction = 1;

    //2     1//
    //       //
    //3     4//
    /**
     * Cria um objeto Clyde com as coordenadas especificadas.
     *
     * @param x A coordenada x inicial do Inky.
     * @param y A coordenada y inicial do Inky.
     */
    public Inky(int x, int y) {
        super(x, y);
    }
    /**
     * Verifica as paredes ao redor do Inky no mapa fornecido e atualiza a direção do movimento.
     *
     * @param maps O mapa do jogo representado como uma matriz de caracteres.
     */
    private void checkwall(char [][] maps) {
        ArrayList<Integer> deca = new ArrayList<>();
        Random random = new Random();
        int randomNumber;

        //   1
        //2     4
        //   3


        if(corner == 1){
            //1 ,4
            if (direction == 1) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 3;
                }

                if(deca.contains(1) || deca.contains(4)){
                    if(deca.contains(1) && deca.contains(4)){
                        if(deca.contains(2)){
                            deca.removeIf(n -> n == 2);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 2) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 4;
                }

                if(deca.contains(1) || deca.contains(4)){
                    if(deca.contains(1) && deca.contains(4)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 3) {
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 1;
                }

                if(deca.contains(1) || deca.contains(4)){
                    if(deca.contains(1) && deca.contains(4)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 4) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }

                if (deca.isEmpty()) {
                    direction = 2;
                }

                if(deca.contains(1) || deca.contains(4)){
                    if(deca.contains(1) && deca.contains(4)){
                        if(deca.contains(3)){
                            deca.removeIf(n -> n == 3);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }
        }
        if(corner == 2){
            //1 2
            if (direction == 1) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 3;
                }

                if(deca.contains(1) || deca.contains(2)){
                    if(deca.contains(4)){
                        deca.removeIf(n -> n == 4);
                    }
                    if(deca.contains(1) && deca.contains(2)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 2) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 4;
                }

                if(deca.contains(1) || deca.contains(2)){
                    if(deca.contains(1) && deca.contains(2)){
                        if(deca.contains(3)){
                            deca.removeIf(n -> n == 3);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 3) {
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 1;
                }

                if(deca.contains(1) || deca.contains(2)){
                    if(deca.contains(1) && deca.contains(2)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 4) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }

                if (deca.isEmpty()) {
                    direction = 2;
                }

                if(deca.contains(1) || deca.contains(2)){
                    if(deca.contains(1) && deca.contains(2)){
                        if(deca.contains(4)){
                            deca.remove(4);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(1)){
                            direction = 1;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }
        }
        if(corner == 3){
            // 2 3
            if (direction == 1) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 3;
                }

                if(deca.contains(3) || deca.contains(2)){
                    if(deca.contains(3) && deca.contains(2)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 2) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 4;
                }

                if(deca.contains(3) || deca.contains(2)){
                    if(deca.contains(3) && deca.contains(2)){
                        if(deca.contains(1)){
                            deca.removeIf(n -> n == 1);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 3) {
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 1;
                }

                if(deca.contains(3) || deca.contains(2)){
                    if(deca.contains(3) && deca.contains(2)){
                        if(deca.contains(4)){
                            deca.removeIf(n -> n == 4);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 4) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }

                if (deca.isEmpty()) {
                    direction = 2;
                }

                if(deca.contains(3) || deca.contains(2)){
                    if(deca.contains(3) && deca.contains(2)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(2)){
                            direction = 2;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }
        }
        if(corner == 4){
            // 3 4
            if (direction == 1) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 3;
                }

                if(deca.contains(3) || deca.contains(4)){
                    if(deca.contains(3) && deca.contains(4)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 2) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 4;
                }

                if(deca.contains(3) || deca.contains(4)){
                    if(deca.contains(3) && deca.contains(4)){
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 3) {
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y') {
                    deca.add(2);
                }

                if (deca.isEmpty()) {
                    direction = 1;
                }

                if(deca.contains(3) || deca.contains(4)){
                    if(deca.contains(3) && deca.contains(4)){
                        if(deca.contains(2)){
                            deca.removeIf(n -> n == 2);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }

            if (direction == 4) {
                if (maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y') {
                    deca.add(1);
                }
                if (maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y') {
                    deca.add(4);
                }
                if (maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y') {
                    deca.add(3);
                }

                if (deca.isEmpty()) {
                    direction = 2;
                }

                if(deca.contains(3) || deca.contains(4)){
                    if(deca.contains(3) && deca.contains(4)){
                        if(deca.contains(1)){
                            deca.removeIf(n -> n == 1);
                        }
                        randomNumber = random.nextInt(deca.size());
                        direction = deca.get(randomNumber);
                    }else {
                        if(deca.contains(3)){
                            direction = 3;
                        }
                        if(deca.contains(4)){
                            direction = 4;
                        }
                    }
                }else {
                    direction = findRandomEmptyAdjacentDirection(maps, getX(), getY());
                }
            }
        }
    }
    /**
     * Encontra uma direção adjacente vazia aleatória com base no mapa e nas coordenadas fornecidas.
     *
     * @param mapa O mapa 2D representado por uma matriz de caracteres.
     * @param x    A coordenada x da posição atual.
     * @param y    A coordenada y da posição atual.
     * @return Um inteiro representando a direção adjacente vazia aleatória (1-4).
     */
    public static int findRandomEmptyAdjacentDirection(char[][] mapa, int x, int y) {
        ArrayList<Integer> directions = new ArrayList<>();

        if (mapa[x - 1][y] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(1);
        }
        if (mapa[x][y - 1] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(2);
        }
        if (mapa[x + 1][y] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(3);
        }
        if (mapa[x][y + 1] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(4);
        }
        Random rand = new Random();
        int index = rand.nextInt(directions.size());
        return directions.get(index);
    }
    /**
     * Encontra a direção para se mover com base no mapa e nas coordenadas fornecidas.
     *
     * @param mapa O mapa 2D representado por uma matriz de caracteres.
     * @param x    A coordenada x da posição atual.
     * @param y    A coordenada y da posição atual.
     */
    public void finddir (char[][] mapa, int x, int y) {
        ArrayList<Integer> directions = new ArrayList<>();
        //4
        if (getX() == 29 && getY() == 27) {
            corner = 3;
        }
        //1
        if (getX() == 1 && getY() == 27) {
            corner = 2;
        }
        //2
        if (getX() == 1 && getY() == 1) {
            corner = 4;
        }
        //3
        if (getX() == 29 && getY() == 1) {
            corner = 1;
        }

        if (mapa[x - 1][y] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(1);
        }
        if (mapa[x][y - 1] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(2);
        }
        if (mapa[x + 1][y] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(3);
        }
        if (mapa[x][y + 1] != 'x' && mapa[x - 1][y] != 'Y') {
            directions.add(4);
        }

        if (direction == 1){
            if (directions.size() == 2){
                if(directions.contains(1) && directions.contains(3)){
                    return;
                }
            }
        }
        if (direction == 2){
            if (directions.size() == 2){
                if(directions.contains(2) && directions.contains(4)){
                    return;
                }
            }
        }
        if (direction == 3){
            if (directions.size() == 2){
                if(directions.contains(3) && directions.contains(1)){
                    return;
                }
            }
        }
        if (direction == 4){
            if (directions.size() == 2){
                if(directions.contains(2) && directions.contains(4)){
                    return;
                }
            }
        }
        checkwall(mapa);
    }
    @Override
    protected void mov(char [][] map) {
        if(getFistmove()){
            setX(getX() - 1);
            setFistmove(false);
            direction = 1;
            return;
        }

        finddir(map,getX(),getY());

        if(direction == 1){

            setX(getX() - 1);
            setY(getY());
        }
        if(direction == 2){
            setX(getX());
            setY(getY() - 1);
        }
        if(direction == 3){
            setX(getX() + 1);
            setY(getY());
        }
        if(direction == 4){
           setX(getX());
            setY(getY() + 1);
        }
    }

    @Override
    public char getSymbol() {
        return 'I';
    }
}
