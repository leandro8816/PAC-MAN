package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import pt.isec.pa.tinypac.model.fsm.PacManStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

public class Stoped extends PacManStateAdapter {

    boolean isInicialize = false;

    public Stoped(PacManContext context, Data manager) {
        super(context, manager);
        manager.setPaustate(0);
    }

    @Override
    public boolean start() {
        changeState(PacManStates.START);
        return false;
    }
    @Override
    public boolean gopower() {
        changeState(PacManStates.POWERED);
        return false;
    }

    @Override
    public void pressKey(String up) {
        changeState(PacManStates.START);
        switch (up) {
            case "up" -> getManager().setdup();
            case "down"->  getManager().setddown();
            case "left" ->  getManager().setdleft();
            case "right" ->  getManager().setdright();
            //default -> System.out.println(" " + keyEvent.getCode());
        }
    }

    @Override
    public PacManStates getState() {
        return PacManStates.STOPED;
    }

    @Override
    public void run() {
        //System.out.println("stop");
        if (!isInicialize){
            getManager().stop();
            isInicialize = true;
        }else {
            getManager().ghostMove();
        }
    }
}
