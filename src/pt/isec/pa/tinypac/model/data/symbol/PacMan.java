package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa o personagem Pac-Man no labirinto.
 */
public class PacMan implements IMazeElement  , Serializable {

    private int x;
    private int y;
    private int direction = 0;
    /**
     * Obtém a direção atual do Pac-Man.
     *
     * @return O valor da direção do Pac-Man.
     */
    public int getDirection() {
        return direction;
    }
    /**
     * Define a direção do Pac-Man.
     *
     * @param direction O valor da direção a ser definida.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * Obtém a posição X atual do Pac-Man.
     *
     * @return O valor da posição X do Pac-Man.
     */
    public int getX() {
        return x;
    }
    /**
     * Define a posição X do Pac-Man.
     *
     * @param x O valor da posição X a ser definida.
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Obtém a posição Y atual do Pac-Man.
     *
     * @return O valor da posição Y do Pac-Man.
     */
    public int getY() {
        return y;
    }
    /**
     * Define a posição Y do Pac-Man.
     *
     * @param y O valor da posição Y a ser definida.
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Move o Pac-Man no labirinto de acordo com a direção atual.
     *
     * @param map O mapa do labirinto representado por uma matriz de caracteres.
     */
    public void move(char [][] map) {
        switch (direction){
            case 1 -> goup(map);
            case 2 -> goleft(map);
            case 3 -> godown(map);
            case 4 -> goright(map);
        }
    }
    /**
     * Move o Pac-Man para cima no labirinto.
     *
     * @param map O mapa do labirinto representado por uma matriz de caracteres.
     */

    private void goup(char[][] map) {
        if(map[x - 1][y] == 'x'){
            direction = 0;
        }else{
            setX(x - 1);
        }
    }
    /**
     * Move o Pac-Man para baixo no labirinto.
     *
     * @param map O mapa do labirinto representado por uma matriz de caracteres.
     */
    private void godown(char[][] map) {
        if(map[x + 1][y] == 'x'){
            direction = 0;
        }else{
            setX(x + 1);
        }
    }
    /**
     * Move o Pac-Man para a esquerda no labirinto.
     *
     * @param map O mapa do labirinto representado por uma matriz de caracteres.
     */
    private void goleft(char[][] map) {
        if(map[x][y - 1] == 'x'){
            direction = 0;
        }else{
            setY(y - 1);
        }
    }
    /**
     * Move o Pac-Man para a direita no labirinto.
     *
     * @param map O mapa do labirinto representado por uma matriz de caracteres.
     */
    private void goright(char[][] map) {
        if(map[x][y + 1] == 'x'){
            direction = 0;
        }else{
            setY(y + 1);
        }
    }


    @Override
    public char getSymbol() {
        return 'P';
    }


}
