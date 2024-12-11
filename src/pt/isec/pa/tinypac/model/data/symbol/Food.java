package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa uma Fruta no labirinto.
 */
public class Food implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a Fruta.
     *
     * @return O caractere que representa a Fruta.
     */

    @Override
    public char getSymbol() {
        return 'F';
    }
}
