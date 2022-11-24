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
        tf.setBorder(BorderFactory.createLineBorder(new Color(136, 137, 138),2));
        tf.setBackground(Color.WHITE);
        tf.setFont(new Font("Arial",Font.BOLD,12));
        tf.setForeground(Color.BLACK);
        tf.setLocation(0,340);
        tf.setVisible(true);

        mW = new JLabel("Missed Words: "+missedWords);
        mW.setSize(new Dimension(150,15));
        mW.setLocation(200,345);
        mW.setForeground(new Color(136, 137, 138));
        mW.setFont(new Font("Arial",Font.BOLD,15));

        WordPerMin = new JLabel("WordsPerMinute: " + wpm);
        WordPerMin.setSize(new Dimension(150,15));
        WordPerMin.setLocation(350,345);
        WordPerMin.setForeground(new Color(0, 0, 0));
        WordPerMin.setFont(new Font("Arial",Font.BOLD,15));
        wpm = 0;

        JLabel csik = new JLabel("");
        csik.setSize(new Dimension(600,20));
        csik.setLocation(0,340);
        csik.setBackground(Color.WHITE);
        csik.setOpaque(true);

        Jatek();

        p.setBackground(new Color(137, 207, 240));


        p.add(tf);
        p.add(mW);
        p.add(WordPerMin);
        p.add(csik);
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
                            mW.setText("Missed Words: " + missedWords);
                        }
                    }

                if(missedWords==5){

                    for (int k = 0; k < list.size(); k++) {
                        list.get(k).setFreeze();
                    }
                    double s = ((double)i/10)/60.00;
                    wpm = (double)(d/5)/ s;

                    WordPerMin.setText("WordsPerMinute: " +(int) wpm);
                    tf.setEnabled(false);
                    t.cancel();
                    t.purge();
                }
                System.out.println(i);
            }
        };
        t.scheduleAtFixedRate(task,0,100);
    }

    public void check(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().equals(tf.getText())){
                list.get(i).destroy();
                d+=list.get(i).getText().length();
                tf.setText("");
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
