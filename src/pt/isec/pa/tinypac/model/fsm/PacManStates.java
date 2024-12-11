package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.Data;
import pt.isec.pa.tinypac.model.fsm.states.*;

public enum PacManStates {

    STOPED, START, POWERED, PAUSE, GAMEND;

    public IPacManState createState(PacManContext context, Data manager) {
        return switch (this) {
            case STOPED -> new Stoped(context, manager);
            case START -> new Start(context, manager);
            case POWERED->new Powered(context, manager);
            case PAUSE->new Pause(context, manager);
            case GAMEND->new Gamend(context, manager);
            // default -> null;
        };
    }
}
