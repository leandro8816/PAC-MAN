package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa o spawner no labirinto.
 */
public class Spawn implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a spawner.
     *
     * @return O caractere que representa a spawner.
     */
    @Override
    public char getSymbol() {
        return 'M';
    }
}
