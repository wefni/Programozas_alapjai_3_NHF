import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words{

    private ArrayList<String> words = new ArrayList<>();

    public Words(String l){
        addWords(l);
    }

    public ArrayList<String> getWords(){
        return words;
    }

    public void addWords(String nyelv){
        try{
            File myObj = new File(nyelv+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                words.add(myReader.nextLine());
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Sikeres!");
    }
}
