import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Word extends JLabel{
        private Thread t;
        private boolean freeze;
        public Word(String word){
            freeze = false;
            super.setText(word);
            super.setSize(new Dimension(150,50));
            super.setForeground(new Color(35, 110, 25));
        }

        public void go(){
            int y = getRandomNumber(1,300);
            t = new Thread(() -> {
                try{
                    for (int i = 0; i < 600; i++) {
                        super.setLocation(i,y);
                        Thread.sleep(10);
                        if(i>200 && i<400){
                            super.setForeground(new Color(240, 233, 50));
                        }
                        else if(i>400){
                            super.setForeground(new Color(161, 22, 22));
                        }

                        if(freeze){
                            break;
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
            freeze = true;
    }

    public void setFreeze(){
            freeze = true;
    }
}
