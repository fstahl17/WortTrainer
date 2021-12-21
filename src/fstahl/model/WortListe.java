package fstahl.model;

import fstahl.model.WortEintrag;

/**
 * Diese Klasse beinhaltet alle WortEintraege und management diese
 * @author Felix
 * @version 08.11.2021
 */
public class WortListe {
    private WortEintrag[] liste;

    /**
     * Erster Konstruktor für Wortliste
     */
    public WortListe(){
        this.liste = new WortEintrag[0];
    }

    /**
     * Zweiter Konstuktor
     * @param laenge
     */
    public WortListe(int laenge){
        if(laenge < 0){
            throw new IllegalArgumentException("Länge darf nicht kleiner 0 sein!");
        } else {
            this.liste = new WortEintrag[laenge];
        }
    }

    /**
     * Diese Methode fügt WortEintraege hinzu
     * @param w Der hinzuzufügende fstahl.model.WortEintrag
     */
    public void wortePlus(WortEintrag w){
        if(w.getWortURL() == null || w.getWort() == null){
            throw new IllegalArgumentException("Ungültiger fstahl.model.WortEintrag");
        } else {
            WortEintrag[] neuListe = new WortEintrag[1];
            neuListe = new WortEintrag[this.liste.length + 1];

            //Abfrage auf freien Platz
            for(int i = 0; i < this.liste.length; i++){
                if(this.liste[i] != null){
                    neuListe[i] = this.liste[i];
                }
            }

            neuListe[neuListe.length-1] = w;
            this.liste = neuListe;
        }
    }
    /**
     * Das Wort zu einem gewissen Index wird zurück gegeben
     * @param index der Index
     */
    public WortEintrag getWortByIndex(int index){
        try{
            if(index >= 0) {
                return this.liste[index];
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.toString());
        }
        return new WortEintrag("#FEHLER", "#FEHLER");
    }

    /**
     * Diese Methode löscht ein Wort aus der Liste
     * @param wort das zu löschende Wort
     */
    public boolean wortMinus(String wort){

        for(int i = 0;i < this.liste.length; i++){
            if(this.liste[i].getWort().equals(wort)){   //Prüft ob das Wort gleich ist
                this.liste[i] = null; // Löscht das Wort aus der Liste ich bin gay
                return true;
            }
        }
        return false;
    }

    /**
     * toString Methode wird so überschrieben dass fstahl.model.WortListe untereinander
     * ausgegeben wird
     */
    @Override
    public String toString(){
        String ergebnis = "";

        for(int i = 0; i < this.liste.length;i++){
            if(this.liste[i] != null){ //Falls der Platz leer ist . ich bin gay
                ergebnis += this.liste[i].toString() + "\n";    //Ergebnis in die Variable speichern
            }
        }
        return ergebnis;
    }

    /**
     * Diese Methode ermittelt die Länge meiner Liste
     * @return die Anzahl der WortEinträge
     */
    public int anzahlWorte(){
        return this.liste.length;
    }
}
