package pt.isec.pa.tinypac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Classe Top5 representa um ranking com os cinco melhores nomes e pontuações.
 * Implementa a interface Serializable para permitir a serialização dos objetos.
 */
public class Top5 implements Serializable {

    private ArrayList<String> names;
    private ArrayList<Integer> points;

    /**
     * Construtor da classe Top5.
     * Inicializa as listas de nomes e pontos vazias.
     */
    public Top5() {
        names = new ArrayList<>();
        points = new ArrayList<>();
    }
    /**
     * Adiciona um nome e uma pontuação ao ranking.
     * Se o ranking ainda não estiver completo (menos de 5 elementos),
     * adiciona o nome e a pontuação diretamente e ordena o ranking.
     * Caso contrário, verifica se a pontuação atual é maior do que a menor pontuação
     * no top 5. Se for, substitui o nome e a pontuação correspondentes e reordena o ranking.
     *
     * @param name   O nome a ser adicionado.
     * @param points A pontuação a ser adicionada.
     */
    public void add(String name, int points) {
        if (names.size() < 5) {
            names.add(name);
            this.points.add(points);
            sortTop5();
        } else {
            // Verifica se a pontuação atual é maior do que a menor pontuação no top 5
            int minIndex = getMinIndex();
            if (points > this.points.get(minIndex)) {
                names.set(minIndex, name);
                this.points.set(minIndex, points);
                sortTop5();
            }
        }
    }
    /**
     * Ordena o ranking dos cinco melhores.
     * Cria uma lista de entradas de pontuação (nome e pontos) e as ordena
     * com base nos pontos em ordem decrescente.
     * Em seguida, atualiza as listas de nomes e pontos com os valores ordenados.
     */
    private void sortTop5() {
        List<ScoreEntry> entries = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            entries.add(new ScoreEntry(names.get(i), points.get(i)));
        }
        entries.sort(Comparator.comparingInt(ScoreEntry::getPoints).reversed());

        for (int i = 0; i < names.size(); i++) {
            names.set(i, entries.get(i).getName());
            points.set(i, entries.get(i).getPoints());
        }
    }

    private static class ScoreEntry {
        private String name;
        private int points;

        public ScoreEntry(String name, int points) {
            this.name = name;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getPoints() {
            return points;
        }
    }


    private int getMinIndex() {
        int minIndex = 0;
        int minPoints = points.get(0);
        for (int i = 1; i < names.size(); i++) {
            if (points.get(i) < minPoints) {
                minIndex = i;
                minPoints = points.get(i);
            }
        }
        return minIndex;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }
}
