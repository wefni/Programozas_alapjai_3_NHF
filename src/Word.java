import javax.swing.*;
import java.awt.*;

/**
 *      Egyes szavak, amik megjelennek a játék során a képernyőn
 */
public class Word extends JLabel{
    /**
     * Szál
     */
        private Thread t;
    /**
     *  Szó megállító
     */
    private boolean freeze;

    /**
     *  A játék nehézségi szintje
     */
        private int diff;

    /**
     *  Konstruktor
     * @param word - Az adott szó, amit a képernyőre kívánunk helyezni
     * @param diff - A nehézség, amivel szeretne játszani a játékos
     */

    public Word(String word,int diff){
            freeze = false;
            this.diff = diff;
            super.setText(word);
            super.setSize(new Dimension(150,50));
            super.setForeground(new Color(35, 110, 25));
        }

    /**
     *      Elindítja a szavakat a képernyő egyik felétől a másik feléig
     */
    public void go(){
            int y = getRandomNumber(1,300);
            t = new Thread(() -> {
                try{
                    for (int i = 0; i < 600; i++) {
                        super.setLocation(i,y);
                        Thread.sleep(this.diff);
                        if(i>200 && i<400){
                            super.setForeground(new Color(240, 233, 50));
                        }
                        else if(i>400){
                            super.setForeground(new Color(161, 22, 22));
                        }
                        if(freeze){
                            t.interrupt();
                        }
                    }
                }catch(Exception e){
                    System.err.println(" " + e.getMessage());
                }
            });
            t.start();
        }

    /**
     *  Random szám két határ között
     * @param min - Alső határ
     * @param max - Felső határ
     * @return - Visszaad egy random számot a két határ között
     */

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Amikor egy szó beírásra kerül a játék során és helyes, akkor a láthatósága megszűnik és megáll az adott pozíciójában
     */

    public void destroy(){
            super.setVisible(false);
            freeze = true;
    }

    /**
     * Nehézség-et adja vissza
     * @return nehézség
     */
    public int getDiff(){
        return diff;
    }

    /**
     *  A freeze állapotát adja vissza
     * @return - fagyás
     */
    public boolean getFreeze(){
        return freeze;
    }
}
