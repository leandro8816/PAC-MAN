package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa uma bola no labirinto.
 */
public class Ball implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a bola.
     *
     * @return O caractere que representa a bola.
     */
    @Override
    public char getSymbol() {
        return 'º';
    }
}
