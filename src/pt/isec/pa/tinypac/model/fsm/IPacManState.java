package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Maze;

import java.io.Serializable;

public interface IPacManState extends Serializable {
    boolean stop();
    boolean start();
    boolean gamend();
    boolean gopower();
    void pressKey(String up);
    PacManStates getState();
    void run();
}
