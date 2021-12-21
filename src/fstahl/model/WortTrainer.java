package fstahl.model;

import fstahl.model.WortEintrag;
import fstahl.model.WortListe;

import java.util.Random;

/**
 * Diese Klasse ist für das training von wörtern verantwortlich
 * @author Felix
 * @version 08.11.2021
 */
public class WortTrainer {
    private WortListe liste;
    private WortEintrag wortJz;
    private int richtig = 0;
    private int falsch = 0;

    public WortTrainer(){
        this.liste = new WortListe();
        this.wortJz = new WortEintrag("","");
    }
    /**
     * Der Konstruktor der Klasse
     */
    public WortTrainer(WortListe liste, WortEintrag w){
        this.liste = liste;
        this.wortJz = w;
    }

    /**
     * Diese Methode ermöglicht es einen zufälligen Worteintrag aus der liste auszuwählen
     * @return w der zufällige fstahl.model.WortEintrag
     */
    public WortEintrag randomWort(){
        Random rmd = new Random(); //Random obj erstellen
        int rmdIndex = rmd.nextInt(this.liste.anzahlWorte() + 1);   //random index generieren

        WortEintrag w = this.liste.getWortByIndex(rmdIndex);    //Random Wort zurück geben
        this.wortJz = w;    //Wird später benötigt
        return w;
    }

    /**
     * Getter für wortJz
     */
    public WortEintrag getWortJz(){
        return this.wortJz;
    }

    /**
     * prüft ob das übergebene Wort mit dem Wort des jetzigen Worteintrags übereinstimmt
     * @param wort das übergebene Wort
     * @return
     */
    public boolean check(String wort){
        this.falsch += 1;
        if(this.wortJz.getWort().equals(wort)){  //vergleicht die Strings
            this.richtig += 1;
            return true;
        }
        return false;
    }

    /**
     * Diese Methode ist genau gleich wie check jedoch ignoriert sie Groß- und Kleinschreibung
     * @param wort
     * @return
     */
    public boolean checkIgnoreCase(String wort){
        this.falsch += 1;
        if(this.wortJz.getWort().equalsIgnoreCase(wort)){   //vergleicht Strings ohne auf Groß- und Kleinschreibung zu achten
            this.richtig += 1;
            return true;
        }
        return false;
    }

    /**
     * Diese Methode wandeöt einen String in einen WortEintrag um und gibt diesen zurück
     * @param s der zu bearbeitende String
     * @return
     */
    public WortEintrag stringZuWortEintrag(String s){
        WortEintrag w = new WortEintrag("", "");
        if(s.contains(";")){
            int indexSemiKolon = s.indexOf(";");
            String wort = s.substring(0,(indexSemiKolon)); //Der String bis zu dem Semikolon
            String url = s.substring(indexSemiKolon + 1, s.length());   //Der String nach dem Semikolon
            w.setWort(wort);
            w.setWortURL(url);
        }
        return w;
    }

    //Getter und Setter für liste
    public WortListe getListe(){
        return liste;
    }
    public void setRichtig(int r){
        this.richtig = r;
    }
    public void setFalsch(int f){
        this.falsch = f;
    }

    public int getRichtig(){
        return this.richtig;
    }
    public int getFalsch(){
        return this.falsch;
    }

    public void setListe(WortListe l){
        this.liste = l;
    }

    public String getStatistik(){
        return "\nRichtig: " + getRichtig() + "\nAnzahl: " + getFalsch();
    }
}
