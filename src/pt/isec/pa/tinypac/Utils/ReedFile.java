package pt.isec.pa.tinypac.Utils;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.symbol.*;

import java.io.*;
import java.util.Scanner;

public class ReedFile {

    public ReedFile() {

    }

    public void ler (Maze map)  {
        try {
            File arquivo = new File("C:\\Users\\leand\\IdeaProjects\\TP_PA\\files\\Level01.txt");
            Scanner scanner = new Scanner(arquivo);
            int x = 0;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                for (int y = 0; y < linha.length(); y++) {
                    Wall wall = new Wall();
                    Ball ball = new Ball();
                    Food food = new Food();
                    Portal portal = new Portal();
                    Powers powers = new Powers();
                    Spawn spawn= new Spawn();
                    WallCave wallcave = new WallCave();
                    Warp warp = new Warp();

                    if(linha.charAt(y)== 'x'){
                        map.set(x, y, wall);
                    }if(linha.charAt(y)== 'o'){
                        map.set(x, y, ball);
                    }if(linha.charAt(y)== 'O'){
                        map.set(x, y, powers);
                    }if(linha.charAt(y)== 'y'){
                        map.set(x, y, wallcave);
                    }if(linha.charAt(y)== 'Y'){
                        map.set(x, y, portal);
                    }if(linha.charAt(y)== 'M'){
                        map.set(x, y, spawn);
                    }if(linha.charAt(y)== 'F'){
                        map.set(x, y, food);
                    }if(linha.charAt(y)== 'W'){
                        map.set(x, y, warp);
                    }

                }
                x++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }
    }

    public Maze seeSize(String filepath){
        try {
            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int altura = 0;
            int largura = 0;

            String linha = bufferedReader.readLine();
            while (linha != null) {
                altura++;
                if (linha.length() > largura) {
                    largura = linha.length();
                }
                linha = bufferedReader.readLine();
            }

            return new Maze(altura, largura);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public   Maze readFile(String filepath) {
        int x = 0;
        Maze poss = null;
        FileReader fr = null;
        try {
            File file = new File(filepath);
            if (!file.exists())
                return null;

            fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);

          /*  int numRows = sc.nextInt();
            int numCols = sc.nextInt();*/
            poss = seeSize(filepath);/*= new Maze(numRows, numCols);*/

            sc.useDelimiter("\\n");

            while (sc.hasNext()) {
                String a = sc.next();
                for (int i = 0; i < a.length(); i++) {

                    IMazeElement O = switch (a.charAt(i)) {
                        case 'x' -> new Wall();
                        case 'W' -> new Warp();
                        case 'o' -> new Ball();
                        case 'F' -> new Food();
                        case 'M' -> new Spawn();
                        case 'O' -> new Powers();
                        case 'y' -> new WallCave();
                        case 'Y' -> new Portal();
                        default -> null;
                    };

                    poss.set(x, i, O);
                }
                x++;
            }

        } catch (IOException e) {

        }
        return poss;
    }

}
