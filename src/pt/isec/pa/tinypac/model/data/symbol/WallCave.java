package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;

/**
 * Representa uma parede da cave no labirinto.
 */
public class WallCave implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a parede da cave.
     *
     * @return O caractere que representa a parede da cave.
     */
    @Override
    public char getSymbol() {
        return 'y';
    }
}
