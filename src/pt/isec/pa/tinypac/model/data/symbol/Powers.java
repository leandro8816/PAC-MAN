package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa uma Fruta de Poder no labirinto.
 */
public class Powers implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a Fruta de poder.
     *
     * @return O caractere que representa a Frutan de poder.
     */
    @Override
    public char getSymbol() {
        return 'O';
    }
}
