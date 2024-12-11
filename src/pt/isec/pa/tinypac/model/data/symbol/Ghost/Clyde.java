package pt.isec.pa.tinypac.model.data.symbol.Ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.symbol.Food;

import java.util.ArrayList;
import java.util.Random;
/**
 * A classe Clyde representa um dos fantasmas do jogo Pac-Man.
 * Ele é um subtipo da classe Ghost.
 */
public class Clyde extends Ghost  {
    private int direcao = 0;
    private Boolean iseepac = false;
    /**
     * Cria um objeto Clyde com as coordenadas especificadas.
     *
     * @param x A coordenada x inicial do Clyde.
     * @param y A coordenada y inicial do Clyde.
     */
    public Clyde(int x, int y) {
        super(x, y);
    }

    /*public void iSeePacman(int x, int y , char [][] map){
        if(x == getX()){

            for (int i = y; i < getY(); i++) {
                if(map[getX()][i] == 'x'){
                    iseepac = false;
                    return;
                }
            }
            direcao =
        }
        if(y == getY()){
            for (int i = x; i < getX(); i++) {
                if(map[i][getY()] == 'x'){
                    iseepac = false;
                    return;
                }
            }
            direcao =
        }
        iseepac = true;
    }*/
    /**
     * Verifica se o Clyde vê o Pac-Man no mapa fornecido e atualiza a direção do movimento.
     *
     * @param x    A coordenada x do Pac-Man.
     * @param y    A coordenada y do Pac-Man.
     * @param map  O mapa do jogo representado como uma matriz de caracteres.
     */
    public void iSeePacman(int x, int y, char[][] map) {
        int xi = getX();
        int yi = getY();

        if (x == xi) { // horizontal
            if (yi < y) { // Pacman está à direita
                for (int j = yi + 1; j < y; j++) {
                    if (map[x][j] == 'x') {
                        iseepac = false;
                        return;
                    }
                }
                direcao = 4;
                iseepac = true;
            } else { // Pacman está à esquerda
                for (int j = y + 1; j < yi; j++) {
                    if (map[x][j] == 'x') {
                        iseepac = false;
                        return;
                    }
                }
                direcao = 2;
                iseepac = true;
            }
        } else if (y == yi) { // vertical
            if (xi < x) { // Pacman está abaixo
                for (int i = xi + 1; i < x; i++) {
                    if (map[i][y] == 'x') {
                        iseepac = false;
                        return;
                    }
                }
                direcao = 3;
                iseepac = true;
            } else { // Pacman está acima
                for (int i = x + 1; i < xi; i++) {
                    if (map[i][y] == 'x') {
                        iseepac = false;
                        return;
                    }
                }
                direcao = 1;
                iseepac = true;
            }
        } else {
            iseepac = false;
        }
    }
    /**
     * Verifica as paredes ao redor do Clyde no mapa fornecido e atualiza a direção do movimento.
     *
     * @param maps O mapa do jogo representado como uma matriz de caracteres.
     */
    private void checkwall(char [][] maps){
        ArrayList<Integer> deca = new ArrayList<>();
        Random random = new Random();
        int randomNumber;

        //   1
        //2     4
        //   3

        if(direcao == 1){
            if(maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y'){
                deca.add(1);
            }
            if(maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y'){
                deca.add(4);
            }
            if(maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y'){
                deca.add(2);
            }
            if(deca.isEmpty()){
                direcao = 3;
            }
            if(deca.size() == 1){
                direcao = deca.get(0);
            } else {
                do{
                    randomNumber = random.nextInt(4) + 1;
                }while (!deca.contains(randomNumber));

                direcao = randomNumber;
            }
        }

        if(direcao == 2){
            if(maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y'){
                deca.add(1);
            }
            if(maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y'){
                deca.add(3);
            }
            if(maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y'){
                deca.add(2);
            }
            if(deca.isEmpty()){
                direcao = 4;
            }
            if(deca.size() == 1){
                direcao = deca.get(0);
            } else {
                do{
                    randomNumber = random.nextInt(4) + 1;
                }while (!deca.contains(randomNumber));
                direcao = randomNumber;
            }
        }

        if(direcao == 3){
            if(maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y'){
                deca.add(3);
            }
            if(maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y'){
                deca.add(4);
            }
            if(maps[getX()][getY() - 1] != 'x' && maps[getX()][getY() - 1] != 'Y'){
                deca.add(2);
            }
            if(deca.isEmpty()){
                direcao = 1;
            }
            if(deca.size() == 1){
                direcao = deca.get(0);
            } else {
                do{
                    randomNumber = random.nextInt(4) + 1;
                }while (!deca.contains(randomNumber));
                direcao = randomNumber;
            }
        }


        if(direcao == 4){
            if(maps[getX() - 1][getY()] != 'x' && maps[getX() - 1][getY()] != 'Y'){
                deca.add(1);
            }
            if(maps[getX()][getY() + 1] != 'x' && maps[getX()][getY() + 1] != 'Y'){
                deca.add(4);
            }
            if(maps[getX() + 1][getY()] != 'x' && maps[getX() + 1][getY()] != 'Y'){
                deca.add(3);
            }
            if(deca.isEmpty()){
                direcao = 2;
            }
            if(deca.size() == 1){
                direcao = deca.get(0);
            } else {
                do{
                    randomNumber = random.nextInt(4) + 1;
                }while (!deca.contains(randomNumber));
                direcao = randomNumber;
            }
        }
    }

    @Override
    protected void mov(char [][] map) {
        if(getFistmove()){
            setX(getX() - 1);
            setFistmove(false);
            direcao = 1;
            return;
        }
        char [][] maps = map;

        if(!iseepac){
            checkwall(maps);
        }

        if (direcao == 1) {
            setX(getX() - 1);
            setY(getY());
        }
        if (direcao == 2) {
            setX(getX());
            setY(getY() - 1);
        }
        if (direcao == 3) {
            setX(getX() + 1);
            setY(getY());
        }
        if (direcao == 4) {
            setX(getX());
            setY(getY() + 1);
        }
    }

    @Override
    public char getSymbol() {
        return 'C';
    }
}
