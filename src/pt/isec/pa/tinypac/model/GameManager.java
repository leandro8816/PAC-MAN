package pt.isec.pa.tinypac.model;

import javafx.application.Platform;
import pt.isec.pa.tinypac.Utils.Save;
import pt.isec.pa.tinypac.model.fsm.PacManContext;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A classe GameManager é responsável por gerenciar o jogo Pac-Man.
 * Ela controla o salvamento do jogo, o ranking dos melhores jogadores e o fluxo de controle do jogo.
 */
public class GameManager  {
    Save save;
    Top5 top5;
    private PacManContext fsm;
    PropertyChangeSupport pcs;
    /**
     * Construtor da classe GameManager.
     * Inicializa o objeto Save, o objeto Top5, o contexto do jogo Pac-Man (PacManContext)
     * e o suporte para notificação de mudanças de propriedade.
     */
    public GameManager() {
        save = new Save();

        fsm = save.getData();

        top5 = save.getTop5();

        pcs = new PropertyChangeSupport(this);
    }
    /**
     * Obtém o ranking dos cinco melhores jogadores.
     *
     * @return O objeto Top5 contendo os nomes e pontuações dos melhores jogadores.
     */
    public Top5 getTop5() {
        return top5;
    }
    /**
     * Obtém o contexto do jogo Pac-Man.
     *
     * @return O objeto PacManContext representando o contexto atual do jogo Pac-Man.
     */
    public PacManContext getFsm() {
        return fsm;
    }
    /**
     * Adiciona um ouvinte de mudanças de propriedade.
     *
     * @param listener O ouvinte a ser adicionado.
     */
    public void addPropertyChangeListener( PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    /**
     * Evolui o jogo para o próximo estado.
     *
     * @param t O tempo de evolução do jogo.
     */
    public void evolve(long t) {
        Platform.runLater(() -> {
            pcs.firePropertyChange(null, null, null);
        });
    }
    /**
     * Pressiona uma tecla no jogo Pac-Man.
     *
     * @param up A tecla a ser pressionada.
     */
    public void pressKey(String up) {
        fsm.pressKey(up);
    }
    /**
     * Salva o estado atual do jogo.
     */
    public void save() {
        save.save(fsm);
    }
    /**
     * Verifica se existe um salvamento do jogo.
     *
     * @return true se existir um salvamento, false caso contrário.
     */
    public boolean existSave() {
        return save.isExistSave();
    }
    /**
     * Verifica se existe um salvamento do ranking dos melhores jogadores.
     *
     * @return true se existir um salvamento do ranking, false caso contrário.
     */
    public boolean existSavetop() {
        return save.isExistSavetop();
    }
    /**
     * Obtém o objeto Save que gerencia o salvamento do jogo.
     *
     * @return O objeto Save responsável pelo salvamento do jogo.
     */
    public Save getSave() {
        return save;
    }
    /**
     * Cria um novo contexto do jogo Pac-Man.
     *
     * reiniciar o jogo, false caso contrário.
     */
    public void newfsm(boolean b) {
        fsm.getData().resete();
        fsm.stop();
    }


}
