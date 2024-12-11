package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa uma parede no labirinto.
 */
public class Wall implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a parede.
     *
     * @return O caractere que representa a parede.
     */
    @Override
    public char getSymbol() {
        return 'x';
    }
}
