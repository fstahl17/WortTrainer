package fstahl.controller;
import fstahl.model.WortEintrag;
import fstahl.model.WortListe;
import fstahl.model.WortSpeichern;
import fstahl.model.WortTrainer;
import fstahl.view.WortFrame;
import fstahl.view.WortPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Diese Klasse ruft alle anderen Klassen auf und kümmert sich um den Ablauf
 * @author Felix
 * @version 03.12.2021
 */
public class WortController implements ActionListener, KeyListener {
        private WortPanel panel;
        private WortFrame frame;
        private WortTrainer trainer;
        private WortSpeichern speichern;
        private WortEintrag w;

    /**
     * Der Konstruktor
     * @throws MalformedURLException
     */
    public WortController() throws MalformedURLException {
        this.panel = new WortPanel(this);
        this.frame = new WortFrame(this.panel);
        this.trainer = new WortTrainer();
        this.speichern = new WortSpeichern();

        this.trainer.getListe().wortePlus(new WortEintrag("Hund", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTO8l39ID652idYp_PNfTPKEwNA0hQjc0ZKFg&usqp=CAU"));
        this.trainer.getListe().wortePlus(new WortEintrag("Katze", "https://www.sunshine.it/wp-content/uploads/2019/03/top-10-funny-cat-videos-funny-ca.jpg"));
        this.trainer.getListe().wortePlus(new WortEintrag("Affe", "https://www.postcardfinder.co.uk/ekmps/shops/c8ed37/images/funny-face-monkey-witzigger-affe-ape-comic-german-animal-postcard-131351-p.jpg"));
        this.trainer.getListe().wortePlus(new WortEintrag("Faultier", "https://i.pinimg.com/564x/f0/3a/34/f03a349219e07c262f961a9afefb9a66.jpg"));
        this.trainer.getListe().wortePlus(new WortEintrag("Pferd", "https://media.istockphoto.com/photos/laughing-horse-picture-id1160791767?k=20&m=1160791767&s=170667a&w=0&h=KBZR7FdbOQuoWxGn4K8M9Da2kR54bDJGra6KUgelVco="));

        refresh();
    }

    /**
     * Die main Methode
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        new WortController();
    }

    /**
     * Diese Methode bestimmt welcher Button gedrückt wurde und reagiert anschließend darauf
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("b1")){  //b1 = Zurücksetzen
            this.clear();
            try{
                refresh();
            } catch (MalformedURLException exception){
                exception.printStackTrace();
            }
        } else if(e.getActionCommand().equals("b2")){   //b2 = Wort hinzufügen
            this.trainer.getListe().wortePlus(trainer.stringZuWortEintrag(this.panel.getTextFeld()));
        } else if(e.getActionCommand().equals("b3")){   //b3 = Speichern
            this.speichern.speichern(trainer);
        } else if(e.getActionCommand().equals("b4")){   //b4 = laden
            this.speichern.laden("Worttrainer.txt");
        }
    }

    /**
     * Reagiert auf gedrückte Tasten auf dem Keyboard
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(panel.getTextFeld().equalsIgnoreCase(w.getWort())){
                statusSetzen(this.trainer.getRichtig(),this.trainer.getFalsch(),true);   //Status auf "gewonnen" setten
                try{
                    refresh();  //Neuen WortEintrag erzeugen
                } catch (MalformedURLException exce){
                    exce.printStackTrace();
                }
            }else{
                statusSetzen(this.trainer.getRichtig(),this.trainer.getFalsch(),false);
                try {
                    refresh();
                } catch (MalformedURLException exc){
                    exc.printStackTrace();
                }
            }
        }
    }

    /**
     * Prüft ob eine Taste losgelassen wurde
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Prüft ob etwas eingegben wurde
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Diese Methode aktualisiert die WortEinträge
     * @throws MalformedURLException wegen der URL
     */
    public void refresh() throws MalformedURLException {
        this.w = this.trainer.randomWort();
        this.trainer.getListe().wortePlus(this.w);
        this.panel.setImage(this.w);
    }

    /**
     * Diese Methode setzt den Spielstand zurück
     */
    public void clear(){
        panel.setRichtigFalschLabels("0","0","");
        trainer.setRichtig(0);
        trainer.setFalsch(0);
    }

    /**
     * Diese Methode wird aufgerufen wenn das Wort eingegeben wurde
     * und es werden die attribute angepasst
     *
     * @param rWoerter richtige Woerter
     * @param aWoerter gesamt anzahl Woerter
     */
    public void statusSetzen(int rWoerter, int aWoerter, boolean rf) {
        if (rf) {
            trainer.setRichtig(rWoerter + 1);
            trainer.setFalsch(aWoerter + 1);
            panel.setR(rWoerter + 1);
            panel.setA(aWoerter + 1);
        } else {
            trainer.setFalsch(aWoerter + 1);
            panel.setA(aWoerter + 1);
        }
    }
}
