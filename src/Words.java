import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *          Beolvasott szavak a nyelvnek megfelelően
 */
public class Words{
    /**
     * Beolvasott szavak listája
     */
    private ArrayList<String> words = new ArrayList<>();

    /**
     * Konstruktor
     * @param l - A beolvasni kívánt szavak nyelve
     */
    public Words(String l){
        addWords(l);
    }

    /**
     *  Beolvasott szavak listája
     * @return Visszaadja a beolvasott szavak listáját
     */

    public ArrayList<String> getWords(){
        return words;
    }

    /**
     *  Hozzáadja a listához a szavakat
     * @param nyelv - megadja, hogy melyik nyelv szavait olvassa be a program
     */
    public void addWords(String nyelv){
        try{
            File myObj = new File("txt/"+nyelv+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                words.add(myReader.nextLine());
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
