import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JFrame {
    private JFrame f = new JFrame();
    private JPanel p = new JPanel();
    private JTextField tfGame;
    private JTextField tfEnd;
    private JLabel mW;
    private JLabel WordPerMin;
    private JLabel end;
    private JLabel Username;
    private JButton kuld;
    private Words szavak;
    private ArrayList<Word> list;

    private double wpm;
    private int missedWords = 0;
    int d = 0;
    private int diff;

    public Game(String nyelv,int diff){
        szavak = new Words(nyelv);
        f.getContentPane();
        this.diff = diff;
        init();
        Jatek();

        f.add(p);
        f.setSize(600,400);
        f.setVisible(true);
        f.setResizable(false);
        f.setTitle("Nyomod");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void init(){

        list = new ArrayList<>();


        p.setLayout(null);

        tfGame = new JTextField();
        setTf(tfGame,0,341,150,20,new Font("Arial",Font.BOLD,12),Color.WHITE,Color.BLACK);
        tfGame.setBorder(BorderFactory.createLineBorder(new Color(136, 137, 138),2));
        tfGame.setVisible(true);

        mW = new JLabel("Missed Words: "+missedWords);
        setLabel(mW,200,345,150,15,new Font("Arial",Font.BOLD,15),Color.BLACK);

        WordPerMin = new JLabel("WordsPerMinute: " + wpm);
        setLabel(WordPerMin,350,345,150,15,new Font("Arial",Font.BOLD,15), Color.BLACK);
        wpm = 0;

        end = new JLabel("");
        setLabel(end,125,100,300,125,null,Color.WHITE);
        end.setVisible(false);
        end.setBorder(BorderFactory.createLineBorder(new Color(136, 137, 138),2));

        Username = new JLabel("Username: ");
        setLabel(Username,150,150,60,30,new Font("Arial",Font.BOLD,10),Color.WHITE);
        Username.setVisible(false);

        tfEnd = new JTextField();
        setTf(tfEnd,215,160,90,15,new Font("Arial",Font.PLAIN,12),Color.WHITE,Color.BLACK);
        tfEnd.setVisible(false);

        kuld = new JButton("Send");
        kuld.setSize(new Dimension(75,25));
        kuld.setFont(new Font("Arial",Font.BOLD,10));
        kuld.setLocation(310,153);
        kuld.setVisible(false);


        JLabel csik = new JLabel("");
        csik.setSize(new Dimension(600,20));
        csik.setLocation(0,342);
        csik.setBackground(Color.WHITE);
        csik.setOpaque(true);

        p.setBackground(new Color(137, 207, 240));

        p.add(Username);
        p.add(tfEnd);
        p.add(kuld);
        p.add(end);
        p.add(tfGame);
        p.add(mW);
        p.add(WordPerMin);
        p.add(csik);
    }

    public void setLabel(JLabel j,int x,int y,int w,int h,Font f,Color c){
        j.setSize(new Dimension(w,h));
        j.setLocation(x,y);
        j.setFont(f);
        j.setBackground(c);
    }

    public void setTf(JTextField j,int x,int y,int w,int h,Font f,Color c,Color c2){
        j.setSize(new Dimension(w,h));
        j.setLocation(x,y);
        j.setFont(f);
        j.setBackground(c);
        j.setForeground(c2);
    }

    public void Jatek(){
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                Word szo;
                if(i%10==0){
                    szo = new Word(szavak.getWords().get(getRandomNumber(1,szavak.getWords().size())),diff);
                    szo.go();
                    list.add(szo);
                    p.add(szo);
                }

                i+=1;

                tfGame.addKeyListener(new KeyListener() {
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
                        System.out.println(list.get(j).getLocation().x%100);
                        if(list.get(j).getLocation().x%100>85 && list.get(j).getLocation().x/100==5){
                            list.get(j).setLocation(100,100);
                            list.get(j).destroy();
                            missedWords+=1;
                            mW.setText("Missed Words: " + missedWords);
                            list.remove(j);
                        }
                    }


                if(missedWords==5){

                    for (int k = 0; k < list.size(); k++) {
                        list.get(k).destroy();
                    }
                    double s = ((double)i/10)/60.00;
                    wpm = (double)(d/5)/ s;

                    WordPerMin.setText("WordsPerMinute: " +(int) wpm);
                    tfGame.setEnabled(false);
                    tfGame.setText("");
                    t.cancel();
                    t.purge();
                    vege();
                }
            }
        };
        t.scheduleAtFixedRate(task,0,100);


    }

    public void vege(){
        end.setOpaque(true);
        end.setVisible(true);

        Username.setOpaque(true);
        Username.setVisible(true);

        tfEnd.setOpaque(true);
        tfEnd.setVisible(true);

        kuld.setOpaque(true);
        kuld.setVisible(true);

        kuld.addActionListener(e -> {
            try {
                FileWriter wr = new FileWriter("HoF.txt",true);
                BufferedWriter bw = new BufferedWriter(wr);
                bw.write(tfEnd.getText() + " " + (int) wpm+"\n");
                bw.close();
                f.dispose();
                Menu menu = new Menu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void check(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().equals(tfGame.getText())){
                list.get(i).destroy();
                d+=list.get(i).getText().length();
                tfGame.setText("");
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
