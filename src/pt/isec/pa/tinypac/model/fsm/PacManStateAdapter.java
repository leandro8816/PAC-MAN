package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.Data;
public abstract class PacManStateAdapter implements IPacManState {

    private final PacManContext context;

    private final Data manager;

    public PacManStateAdapter(PacManContext context, Data manager) {
        this.context = context;
        this.manager = manager;
    }

    public Data getManager() {
        return manager;
    }

    @Override
    public boolean stop() {
        return false;
    }
    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean gamend() {
        return false;
    }

    @Override
    public boolean gopower() {
        return false;
    }

    @Override
    public PacManStates getState() {
        return null;
    }


    protected void changeState(PacManStates state) {
        context.setCurrentState(state.createState(context, manager));
    }



}
