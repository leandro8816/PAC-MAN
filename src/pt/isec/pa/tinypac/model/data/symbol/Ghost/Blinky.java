package pt.isec.pa.tinypac.model.data.symbol.Ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.symbol.Ball;
import pt.isec.pa.tinypac.model.data.symbol.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * A classe Blinky representa um dos fantasmas do jogo Pac-Man.
 * Ele é um subtipo da classe Ghost.
 */
public class Blinky extends Ghost  {

    private int direcao = 0;
    /**
     * Cria um objeto Blinky com as coordenadas especificadas.
     *
     * @param x A coordenada x inicial do Blinky.
     * @param y A coordenada y inicial do Blinky.
     */
    public Blinky(int x, int y) {
        super(x, y);
    }
    /**
     * Verifica as paredes ao redor do Blinky no mapa fornecido e atualiza a direção do movimento.
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
        Food a = new Food();

            if(getFistmove()){
                setX(getX() - 1);
                setFistmove(false);
                direcao = 1;
                return;
            }

            checkwall(map);
            if(direcao == 1){
                setX(getX() - 1);
                setY(getY());
            }
            if(direcao == 2){
                setX(getX());
                setY(getY() - 1);
            }
            if(direcao == 3){
                setX(getX() + 1);
                setY(getY());
            }
            if(direcao == 4){
                setX(getX());
                setY(getY() + 1);
            }


        }

    @Override
    public char getSymbol() {
        return 'B';
    }

}
