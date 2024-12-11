package pt.isec.pa.tinypac.gameengine;

import pt.isec.pa.tinypac.Utils.ReedFile;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import static pt.isec.pa.tinypac.model.fsm.PacManStates.*;

class TestClient implements IGameEngineEvolve {
    int count = 0;

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
       //System.out.printf("[%d] %d\n",currentTime,++count);

        if(count == 5 ){
            //fantasma andar
        }


        ++count;
        //if (count >= 60) gameEngine.stop();
    }
}
