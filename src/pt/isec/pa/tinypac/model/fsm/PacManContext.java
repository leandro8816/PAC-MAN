package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.Data;

import java.io.Serializable;

public class PacManContext implements IGameEngineEvolve , Serializable {
    private IPacManState currentState;
    private Data data;

    public PacManContext() {
        data = new Data();
        this.currentState = PacManStates.STOPED.createState(this,data);
    }

    public Data getData() {
        return data;
    }

    public boolean start() {
        return currentState.start();
    }

    public boolean stop() {
        return currentState.stop();
    }

    protected void changeState(IPacManState newState) {
        this.currentState = newState;
    }
    public PacManStates getCurrentState() {return currentState.getState();}

    protected void setCurrentState(IPacManState state) {this.currentState = state;}

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        currentState.run();
    }

    public void pressKey(String up) {
        currentState.pressKey(up);
    }
}
