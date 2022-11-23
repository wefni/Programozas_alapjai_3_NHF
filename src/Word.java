import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Word extends JLabel{

        private JLabel word;
        Thread t;

        public Word(String word){
            super.setText(word);
            super.setSize(new Dimension(150,50));
            super.setForeground(new Color(77, 163, 55));
        }

        public void go(){
            int y = getRandomNumber(1,300);
            t = new Thread(() -> {
                try{
                    for (int i = 0; i < 600; i++) {
                        Word.super.setLocation(i,y);
                        Thread.sleep(25);
                        if(i>200 && i<400){
                            super.setForeground(new Color(240, 233, 50));
                        }
                        else if(i>400){
                            super.setForeground(new Color(217, 37, 37));
                        }
                    }
                }catch(Exception e){
                    System.err.println(" " + e.getMessage());
                }
            });
            t.start();
        }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void destroy(){
            super.setVisible(false);
    }

}
