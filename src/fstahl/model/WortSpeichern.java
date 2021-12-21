package fstahl.model;

import java.io.BufferedWriter;
import java.io.*;

/**
 * Diese Klasse speichert und lädt WortTrainer
 * @author Felix
 * @version 24.11.2021
 */
public class WortSpeichern {

    /**
     * Speichert WortTrainer
     * @param trainer
     * @param pfad
     */
    public void speichern(WortTrainer trainer, String pfad){
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(pfad));
            w.write(trainer.getListe().toString());     //Liste schreiben
            w.write(trainer.getStatistik());
            w.close();  //schließen nicht vergessen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichern mit default pfad
     * @param trainer
     */
    public void speichern(WortTrainer trainer){
        speichern(trainer, "Worttrainer.txt");
    }

    /**
     * Diese Methode soll die gespeicherte Worttrainer.txt datei laden
     * @param pfad der Datei Pfad
     * @return den Worttrainer
     */
    public WortTrainer laden(String pfad){
        WortListe liste = new WortListe();
        WortTrainer trainer = new WortTrainer(liste,new WortEintrag("/", "/"));
        int falsch = 0;
        int richtig = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfad));
            String line = reader.readLine();
            for(int i = 0;line != null;i++){
                if(line.contains(";")){
                    WortEintrag w = trainer.stringZuWortEintrag(line);
                    liste.wortePlus(w);
                } else if (line.indexOf("F") == 0){
                    falsch = Integer.parseInt(line.substring(8,line.length() - 1));
                } else if (line.indexOf("R") == 0){
                    richtig = Integer.parseInt(line.substring(9,(line.length()-1)));
                }
                trainer.setFalsch(falsch);
                trainer.setRichtig(richtig);
                trainer.setListe(liste);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainer;
    }
}
