package pt.isec.pa.tinypac.model.data.symbol.Ghost;

import pt.isec.pa.tinypac.Utils.Position;
import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Classe abstrata que representa um fantasma no jogo TinyPac.
 * Os fantasmas têm a capacidade de se mover pelo labirinto e podem ser vulneráveis ou não.
 * Implementa a interface IMazeElement e é serializável.
 */
public abstract class Ghost implements IMazeElement , Serializable {

    private int x;
    private int y;
    private int oldxcord;
    private int oldycord;
    boolean vulnerable = false;
    private Boolean fistmove = true;
    boolean out = false;
    boolean isout = false;
    ArrayList<Position> poss = new ArrayList<>();
    private int direction = 0;

    /**
     * Construtor da classe Ghost.
     *
     * @param x A coordenada x inicial do fantasma.
     * @param y A coordenada y inicial do fantasma.
     */
    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtém o primeiro movimento do fantasma.
     *
     * @return Retorna verdadeiro se for o primeiro movimento, falso caso contrário.
     */
    public Boolean getFistmove() {
        return fistmove;
    }

    /**
     * Define se o fantasma morreu.
     * Reinicia a posição do fantasma e limpa as posições anteriores.
     * Define a vulnerabilidade do fantasma como falso.
     */
    public void morreu() {
        setX(poss.get(0).getX());
        setY(poss.get(0).getY());
        poss.clear();
        vulnerable = false;
    }

    /**
     * Obtém a posição anterior do fantasma.
     *
     * @return A posição anterior do fantasma.
     */
    public Position getoldcord(){
        return poss.get(poss.size() - 1);
    }
    /**
     * Define se é o primeiro movimento do fantasma.
     *
     * @param fistmove Valor booleano que indica se é o primeiro movimento.
     */
    public void setFistmove(Boolean fistmove) {
        this.fistmove = fistmove;
    }
    /**
     * Move o fantasma no labirinto.
     *
     * @param map O mapa do labirinto.
     */
    public void move(char [][] map) {
        if(!isout) {
            if(out){
                if (direction == 0){
                    verx(map);
                    isout = true;
                    return;
                }
                getOut(map);
            }else {
                randon(map);
            }
        }else {
            if (vulnerable) {
                runAway();
            } else {
                poss.add(new Position(x,y));
                mov(map);
            }
        }
    }
    /**
     * Verifica se o fantasma está no portal de saída e define a direção de saída.
     *
     * @param map O mapa do labirinto.
     */
    private void verx(char[][] map){
        if (map[x - 1][y] == 'Y') {
            direction = 1;
        }
        if (map[x][y - 1] == 'Y') {
            direction = 2;
        }
        if (map[x + 1][y] == 'Y') {
            direction = 3;
        }
        if (map[x][y + 1] == 'Y') {
            direction = 4;
        }
        makeMove();
    }
    /**
     * Move o fantasma aleatoriamente pelo labirinto.
     *
     * @param map O mapa do labirinto.
     */
    private void randon(char[][] map) {
        Random random = new Random();
        boolean mover = false;
        do {
            direction = random.nextInt(4 +1);
            switch (direction) {
                case 1 -> {
                    if (map[x - 1][y] != 'x' && map[x - 1][y] != 'Y') {
                        mover = true;
                    }
                }
                case 2 -> {
                    if (map[x][y - 1] != 'x' && map[x][y - 1] != 'Y') {
                        mover = true;
                    }
                }
                case 3 -> {
                    if (map[x + 1][y] != 'x' && map[x + 1][y] != 'Y') {
                        mover = true;
                    }
                }
                case 4 -> {
                    if (map[x][y + 1] != 'x' && map[x][y + 1] != 'Y') {
                        mover = true;
                    }
                }
            }
        } while (!mover);

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
    /**
     * Define a vulnerabilidade do fantasma.
     *
     * @param vulnerable Valor booleano que indica se o fantasma está vulnerável.
     */
    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }
    /**
     * Verifica se o fantasma está vulnerável.
     *
     * @return Retorna verdadeiro se o fantasma está vulnerável, falso caso contrário.
     */
    public boolean isVulnerable() {
        return vulnerable;
    }
    /**
     * Obtém a direção de saída do fantasma do portal.
     *
     * @param map O mapa do labirinto.
     */
    private void getOut(char[][] map) {
        int px = 0, py = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'Y'){
                    px = i;
                    py = j;
                }
            }
        }
        /*if(map[px-1][py] == 'y'){px -= 1;}//todo sair do buraco

        if(map[px][py-1] == 'y'){py -= 1;}*/

        if(map[px+1][py] == 'y'){px += 1;}

        //if(map[px][py+1] == 'y'){py += 1;}


        do {
            direction = getDirection(map,x,y, px, py);
        }while (direction == -1);


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
    /**
     * Realiza o movimento do fantasma.
     */
    private void makeMove(){
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
    /**
     * Obtém a direção para um destino específico no labirinto.
     *
     * @param grid O mapa do labirinto.
     * @param xi   A posição atual do fantasma no eixo x.
     * @param yi   A posição atual do fantasma no eixo y.
     * @param x    A posição de destino no eixo x.
     * @param y    A posição de destino no eixo y.
     * @return Retorna a direção para alcançar o destino ou -1 se não for possível.
     */
    public static int getDirection(char[][] grid, int xi, int yi, int x, int y) {
        if (xi == x && yi == y) {
            return 0;
        }
        if (xi > x) {
            if (grid[xi-1][yi] == 'y') {
                return 1; // mover para cima
            } else if (grid[xi-1][yi] == 'x') {
                return -1; // há uma barreira acima
            }
        } else if (xi < x) {
            if (grid[xi+1][yi] == 'y') {
                return 3; // mover para baixo
            } else if (grid[xi+1][yi] == 'x') {
                return -1; // há uma barreira abaixo
            }
        } else {
            if (yi > y) {
                if (grid[xi][yi-1] == 'y') {
                    return 2; // mover para esquerda
                } else if (grid[xi][yi-1] == 'x') {
                    return -1; // há uma barreira à esquerda
                }
            } else if (yi < y) {
                if (grid[xi][yi+1] == 'y') {
                    return 4; // mover para direita
                } else if (grid[xi][yi+1] == 'x') {
                    return -1; // há uma barreira à direita
                }
            } else {
                return 0; // (xi, yi) e (x, y) são iguais
            }
        }
        return -1;
    }
    /**
     * Define a posição do fantasma no eixo x.
     *
     * @param x A posição no eixo x.
     */

    public void setX(int x) {
        this.x = x;
    }
    /**
     * Define a posição do fantasma no eixo y.
     *
     * @param y A posição no eixo y.
     */

    public void setY(int y) {
        this.y = y;
    }
    /**
     * Obtém a posição do fantasma no eixo x.
     *
     * @return A posição do fantasma no eixo x.
     */

    public int getX() {
        return x;
    }
    /**
     * Obtém a posição do fantasma no eixo y.
     *
     * @return A posição do fantasma no eixo y.
     */
    public int getY() {
        return y;
    }

    /**
     * define se o fantasma está fora do labirinto.
     */

    public void setOut(boolean out) {
        this.out = out;
    }
    /**
     * Move o fantasma quando está vulnerável.
     */
    private void runAway() {
        if (poss.size() == 0){
            setVulnerable(false);
            setFistmove(true);
            return;
        }
            setOldxcord(x);
            setOldycord(y);
            setX(poss.get(poss.size()-1).getX());
            setY(poss.get(poss.size()-1).getY());
            poss.remove(poss.size()-1);
    }

    protected abstract void mov(char [][] map);
    /**
     * obtém a coordenada x da posição anterior do fantasma.
     */
    public int getOldxcord() {
        return oldxcord;
    }
    /**
     * define a coordenada x da posição anterior do fantasma.
     */
    public void setOldxcord(int oldxcord) {
        this.oldxcord = oldxcord;
    }
    /**
     * obtém a coordenada x da posição anterior do fantasma.
     */
    public int getOldycord() {
        return oldycord;
    }
    /**
     * define a coordenada x da posição anterior do fantasma.
     */
    public void setOldycord(int oldycord) {
        this.oldycord = oldycord;
    }

    /**
     * Retorna o símbolo do fantasma.
     *
     * @return O símbolo do fantasma.
     */
    @Override
    public char getSymbol() {
        return 'B';
    }
}
