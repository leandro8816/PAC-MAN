package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import pt.isec.pa.tinypac.model.fsm.PacManStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacManStates;

public class Start extends PacManStateAdapter {
    public Start(PacManContext context, Data manager) {
        super(context, manager);
        manager.setPaustate(1);
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
        return PacManStates.START;
    }

    @Override
    public void run() {
        System.out.println("start");
        int choice = getManager().ghostout();

        if(getManager().getTimer() == 10){
            getManager().setout();
        }

        switch (choice){
            case 1 -> changeState(PacManStates.POWERED);
            case 2 -> {
                if (getManager().iMorreu()){
                    changeState(PacManStates.GAMEND);
                }else {
                    changeState(PacManStates.STOPED);
                }
            }
            case 3 -> {
                if(getManager().levels()){
                    getManager().setWin(true);
                    changeState(PacManStates.GAMEND);
                }else{
                    changeState(PacManStates.STOPED);
                }
            }
        }
    }
}
