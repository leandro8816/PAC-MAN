package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import pt.isec.pa.tinypac.model.fsm.PacManStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

import java.util.Objects;

public class Pause extends PacManStateAdapter {

    public Pause(PacManContext context, Data manager) {
        super(context, manager);
    }

    @Override
    public void pressKey(String up) {
         if(Objects.equals(up, "escape")){
             System.out.println(getManager().getPaustate());
             if(getManager().getPaustate() == 3){
                 changeState(PacManStates.POWERED);
             }
             else
             {
                 changeState(PacManStates.values()[getManager().getPaustate()]);
             }

         }
    }
    @Override
    public boolean stop() {
        changeState(PacManStates.STOPED);
        return false;
    }
    @Override
    public PacManStates getState() {
        return PacManStates.PAUSE;
    }

    @Override
    public void run() {
        System.out.println("pause");
    }
}
