import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JFrame {
    private JFrame f = new JFrame();
    private JPanel p = new JPanel();
    private JTextField tf;

    private JLabel mW;
    private JLabel WordPerMin;
    private Words szavak;
    private ArrayList<Word> list;

    private double wpm;
    private int missedWords = 0;
    int d = 0;

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

        mW = new JLabel("Missed words: "+missedWords);
        mW.setSize(new Dimension(150,15));
        mW.setLocation(200,345);
        mW.setForeground(Color.WHITE);
        mW.setFont(new Font("Arial",Font.PLAIN,15));

        WordPerMin = new JLabel("WordsPerMinute: " + wpm);
        WordPerMin.setSize(new Dimension(150,15));
        WordPerMin.setLocation(350,345);
        WordPerMin.setForeground(Color.WHITE);
        WordPerMin.setFont(new Font("Arial",Font.PLAIN,15));
        wpm = 0;


        Jatek();

        p.setBackground(Color.BLACK);

        p.add(tf);
        p.add(mW);
        p.add(WordPerMin);
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
                Word szo = new Word("asd");
                if(i%10==0){

                    szo = new Word(szavak.getWords().get(getRandomNumber(1,szavak.getWords().size())));
                    szo.go();
                    list.add(szo);
                    p.add(szo);
                }

                i+=1;
                tf.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {

                    }
                    @Override
                    public void keyPressed(KeyEvent e) {

                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        check();
                    }
                });

                    for (int j = 0; j < list.size(); j++) {
                        if(list.get(j).getLocation().x>590 && list.get(j).getLocation().x<599){
                            missedWords+=1;
                            mW.setText("Missed words: " + missedWords);
                        }
                    }

                if(missedWords==5){
                    for (int k = 0; k < list.size(); k++) {
                        list.get(k).setFreeze();
                    }

                    wpm = ((double)(d/5)/(double) ((i/10))/60);
                    WordPerMin.setText("WordsPerMinute: " + (int) wpm);
                    tf.setEnabled(false);
                    t.cancel();
                    t.purge();
                }
                System.out.println(d);
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
