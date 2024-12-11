package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import pt.isec.pa.tinypac.model.fsm.PacManStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

public class Gamend extends PacManStateAdapter {


    public Gamend(PacManContext context, Data manager) {
        super(context, manager);
        manager.setPaustate(4);
    }

    @Override
    public boolean stop() {
        changeState(PacManStates.STOPED);
        return false;
    }

    @Override
    public void pressKey(String up) {

    }

    @Override
    public PacManStates getState() {
        return PacManStates.GAMEND;
    }

    @Override
    public void run() {
        //System.out.println("gamend");
    }
}
