package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import pt.isec.pa.tinypac.model.fsm.PacManStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

public class Powered extends PacManStateAdapter {


    public Powered(PacManContext context, Data manager) {
        super(context, manager);
        manager.setPaustate(3);
    }

    @Override
    public void pressKey(String up) {
        switch (up) {
            case "up" -> getManager().setdup();
            case "down"->  getManager().setddown();
            case "left" ->  getManager().setdleft();
            case "right" ->  getManager().setdright();
            case "escape" ->  changeState(PacManStates.PAUSE);
            //default -> System.out.println(" " + keyEvent.getCode());
        }
    }

    @Override
    public PacManStates getState() {
        return PacManStates.POWERED;
    }

    @Override
    public void run() {
        System.out.println("powered");
        if(getManager().ghostRun())
            if(getManager().levels()){
                changeState(PacManStates.GAMEND);
            }else{
                changeState(PacManStates.STOPED);
            }


        if(getManager().alldeath())
            changeState(PacManStates.START);

        if(getManager().getPowerTime() == 0 )
            changeState(PacManStates.START);


    }
}
