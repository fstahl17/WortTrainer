package fstahl.view;

import fstahl.controller.WortController;
import fstahl.model.WortEintrag;
import fstahl.model.WortTrainer;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Diese Klasse ist für das Design in dem Frame zuständig
 * @author Felix
 * @version 01.12.2021
 */
public class WortPanel extends JPanel {

    private JLabel j1, j2, j3, j4, j5, jImage;
    private JTextField t;
    private JButton b1, b2, b3, b4;
    private int r, a;

    /**
     * Der Konstruktor
     * @param controller
     * @throws MalformedURLException
     */
    public WortPanel(WortController controller) throws MalformedURLException {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        JPanel pOben = new JPanel();
        GridLayout gridOben = new GridLayout(2, 1);
        pOben.setLayout(gridOben);

        JPanel pUnten = new JPanel();
        GridLayout gridUnten = new GridLayout(2, 4);
        pUnten.setLayout(gridUnten);

        //Buttons und Labels und Textfield zuweisen
        this.j1 = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        this.t = new JTextField(15);
        this.j2 = new JLabel("Richtige Wörter:");
        this.j3 = new JLabel("Anzahl Wörter:");
        this.j4 = new JLabel("0"); //Richtige Wörter Punktestand
        this.j5 = new JLabel("0"); //Anzahl worte punktestand
        this.b1 = new JButton("Zurücksetzen");
        this.b2 = new JButton("Wort hinzufügen");
        this.b3 = new JButton("Speichern");
        this.b4 = new JButton("Laden");
        this.jImage = new JLabel();
        this.jImage.setHorizontalAlignment(SwingConstants.CENTER);

        //Labels und Buttons zum Panel hinzufügen
        pOben.add(j1);
        pOben.add(t);
        pUnten.add(j2);
        pUnten.add(j4);
        pUnten.add(j3);
        pUnten.add(j5);
        pUnten.add(b1);
        pUnten.add(b2);
        pUnten.add(b3);
        pUnten.add(b4);

        this.add(pOben, BorderLayout.PAGE_START);
        this.add(pUnten, BorderLayout.PAGE_END);
        this.add(jImage, BorderLayout.CENTER);

        j4.setOpaque(true);
        j5.setOpaque(true);

        //Ereignissteurerung für die Buttons
        b1.addActionListener(controller);
        b1.setActionCommand("b1");

        b2.addActionListener(controller);
        b2.setActionCommand("b2");

        b3.addActionListener(controller);
        b3.setActionCommand("b3");

        b4.addActionListener(controller);
        b4.setActionCommand("b4");

        t.addKeyListener(controller);

    }

    /**
     * Diese Methode setzt das Image auf den aktuellen WortEintrag
     * @param w der WortEintrag
     */
    public void setImage(WortEintrag w) throws MalformedURLException {
        if(w.checkURL(w.getWortURL())){
            ImageIcon icon = new ImageIcon(new URL(w.getWortURL()));
            Image image = icon.getImage();
            image = image.getScaledInstance(250,250, java.awt.Image.SCALE_SMOOTH);
            this.jImage.setIcon(new ImageIcon(image));
        }
    }

    /**
     * Gibt das Wort im Textfeld zurück
     */
    public String getTextFeld() {
        return t.getText();
    }

    //Getter und Setter für richtig und anzahlPunkte
    public void setR(int zahl){
        this.r = zahl;
        this.j4.setText("" + this.getR());
    }
    public void setA(int zahl){
        this.a = zahl;
        this.j5.setText("" + this.getA());
    }
    public int getR(){
        return this.r;
    }
    public int getA(){
        return this.a;
    }

    //Setter
    public void setRichtigFalschLabels(String j4, String j5, String t){
        this.j4.setText(j4);
        this.j5.setText(j5);
        this.t.setText(t);
    }
}