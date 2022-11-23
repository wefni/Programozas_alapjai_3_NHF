import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JFrame {
    private JFrame f = new JFrame();
    private JPanel p = new JPanel();
    private JTextField tf;

    private JLabel mW;
    private Words szavak;
    private ArrayList<Word> list;

    private int missedWords = 0;

    public Game(String nyelv){

        tf = new JTextField();
        list = new ArrayList<>();
        szavak = new Words(nyelv);
        f.getContentPane();
        p.setLayout(null);

        tf.setSize(new Dimension(150,20));
        tf.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        tf.setBackground(Color.BLACK);
        tf.setForeground(Color.WHITE);
        tf.setLocation(0,340);
        tf.setVisible(true);

        mW = new JLabel(""+missedWords);
        mW.setSize(new Dimension(50,15));
        mW.setLocation(250,0);
        mW.setForeground(Color.WHITE);

        //Jatek();

        p.setBackground(Color.BLACK);

        p.add(tf);
        p.add(mW);
        f.add(p);

        f.setSize(600,400);
        f.setVisible(true);
        f.setResizable(false);
        f.setTitle("Nyomod");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Jatek(){
        Timer t = new Timer();

        TimerTask task = new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                Word szo = new Word("");
                if(i%10==0){
                    szo = new Word(szavak.getWords().get(getRandomNumber(1,szavak.getWords().size())));
                    szo.go();
                    list.add(szo);
                    p.add(szo);
                }
                i+=1;
                tf.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        check();
                    }
                });

                if(szo.getLocation().x>600){
                    missedWords +=1;
                }

            }
        };
        t.scheduleAtFixedRate(task,0,100);
    }

    public void check(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().equals(tf.getText())){
                list.get(i).destroy();
                tf.setText("");
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
