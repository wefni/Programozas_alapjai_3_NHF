/**
 *      Játékosok, akik játszottak
 */

public class Player {
    /**
     * Felhasználónév
     */
    private String name;

    /**
     *  Word per Minute szám
     */
    private String wpm;

    /**
     * Konstruktor
     * @param sor - A HoF.txt sora
     */
    public Player(String sor){
        String[] s = sor.split(" ");
        name = s[0];
        wpm = s[1];
    }

    /**
     * WPM
     * @return - Visszaadja a játékos wpm mutatóját
     */
    public String getWpm() {
        return wpm;
    }

    /**
     *  Felhasználónév
     * @return - Visszaadja a játékos nevét
     */
    public String getName() {
        return name;
    }
}
