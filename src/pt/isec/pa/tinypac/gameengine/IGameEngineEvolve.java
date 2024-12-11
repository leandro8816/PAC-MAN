package pt.isec.pa.tinypac.gameengine;

import java.io.IOException;

public interface IGameEngineEvolve {
    void evolve(IGameEngine gameEngine, long currentTime) throws IOException;

}
