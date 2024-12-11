package pt.isec.pa.tinypac.model.data.symbol;

import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.io.Serializable;
/**
 * Representa um Portal no labirinto.
 */
public class Portal implements IMazeElement , Serializable {
    /**
     * Obtém o símbolo que representa a Portal.
     *
     * @return O caractere que representa a Potal.
     */
    @Override
    public char getSymbol() {
        return 'Y';
    }
}
