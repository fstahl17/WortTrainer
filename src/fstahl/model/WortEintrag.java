package fstahl.model;

import com.sun.jndi.toolkit.url.UrlUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Diese Klasse kümmert sich um den Worteintrag
 * @author Felix
 * @version 08.11.2021
 */
public class WortEintrag {
    private String wort;
    private String wortURL;

    /**
     * Der Konstruktor der Klasse fstahl.model.WortEintrag
     * @param wort, wortURL
     */
    public WortEintrag(String wort, String wortURL){
        this.wort = wort;
        this.wortURL = wortURL;
    }

    /**
     * Diese Methode schaut ob eine URL gültig ist oder nicht
     */
    public boolean checkURL(String url) throws MalformedURLException {
        try{
            new URL(url);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //Setter und Getter Methoden:
    public void setWort(String wort){
        this.wort = wort;
    }
    public void setWortURL(String wortURL){
        this.wortURL = wortURL;
    }
    public String getWort(){
        return this.wort;
    }
    public String getWortURL(){
        return this.wortURL;
    }

    /**
     * Diese Methode gibt die Attribute zurück
     * @return
     */
    @Override
    public String toString(){
        return this.getWort() + ", " + this.getWortURL() + ";";
    }
}
