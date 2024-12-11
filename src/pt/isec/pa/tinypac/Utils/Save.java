package pt.isec.pa.tinypac.Utils;

import pt.isec.pa.tinypac.model.Top5;
import pt.isec.pa.tinypac.model.fsm.PacManContext;

import java.io.*;

public class Save {
    private static final String SAVE_FILE = "files/save.dat";
    private static final String SAVE_TOP = "files/top5.dat";

    public boolean existSave = true;
    public boolean existSavetop = true;

    PacManContext data;
    Top5 top5;

    public Save() {
        this.data = load(SAVE_FILE);
        if(this.data == null){
            existSave = false;
            this.data = new PacManContext();
        }

        this.top5 = loadtop5(SAVE_TOP);

        if(this.top5 == null){
            existSavetop = false;
            this.top5 = new Top5();
        }
    }

    public PacManContext getData() {
        return data;
    }

    public void setData(PacManContext data) {
        this.data = data;
    }

    public boolean save(PacManContext dat) {//As variaveis estaticas nao sao gravadas! (ficam com o valor default)
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(SAVE_FILE))){
            oos.writeObject(dat);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro a escrever para o fich " + SAVE_FILE);
            return false;
        }
        return true;
    }

    public PacManContext load(String fileName){
        File file = new File(fileName);
        if(!file.exists())
            return null;
        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(SAVE_FILE))){
//            Object obj = ois.readObject();
            if(ois.readObject() instanceof PacManContext obj){
                return obj;
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erro ao ler do ficheiro "+SAVE_FILE);
            return null;
        }
        return null;
    }

    public boolean savetop5(Top5 dat) {//As variaveis estaticas nao sao gravadas! (ficam com o valor default)
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(SAVE_TOP))){
            oos.writeObject(dat);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro a escrever para o fich " + SAVE_TOP);
            return false;
        }
        return true;
    }

    public Top5 loadtop5(String fileName){
        File file = new File(fileName);
        if(!file.exists())
            return null;
        try(ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(SAVE_TOP))){
            //Object obj = ois.readObject();
            if(ois.readObject() instanceof Top5 obj){
                return obj;
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erro ao ler do ficheiro "+SAVE_TOP);
            return null;
        }
        return null;
    }

    public Top5 getTop5() {
        return top5;
    }

    public void setTop5(Top5 top5) {
        this.top5 = top5;
    }

    public boolean isExistSave() {
        return existSave;
    }

    public void setExistSave(boolean existSave) {
        this.existSave = existSave;
    }

    public boolean isExistSavetop() {
        return existSavetop;
    }
}
