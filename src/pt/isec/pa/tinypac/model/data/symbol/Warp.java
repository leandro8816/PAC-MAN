package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;

/**
 * Representa um Portal no labirinto.
 */
public class Warp implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a portal.
     *
     * @return O caractere que representa a Portal.
     */
    @Override
    public char getSymbol() {
        return 'W';
    }
}
