import javax.swing.*;
import java.awt.*;

/**
 *  MENU
 */
public class Menu{
    /**
     * Frame
     */
    private JFrame f = new JFrame("TypeRacer");
    /**
     * Panel
     */
    private JPanel p = new JPanel();
    /**
     * Alapértelmezett zászló
     */
    private ImageIcon ic = new ImageIcon("kepek/magyar.png");
    /**
     * Alapértelmezett nehézségi szint
     */
    private int diff = 25;
    /**
     * Alapértelmezett nyelv
     */
    private String lan = "magyar";

    /**
     * Konstruktor
     */
    public Menu() {
        initAll();
    }

    /**
     * Létrehozza a megjeleníteni kívánt elemeket
     */
    private void initAll() {
        JButton btn;
        JMenuBar menuBar = new JMenuBar();
        JLabel lab = new JLabel("Diffculty: Easy");
        lab.setFont(new Font("Arial", Font.BOLD,20));
        JLabel flag = new JLabel();
        flag.setIcon(this.ic);
        JMenu diff = new JMenu("Diffculty");
        JMenu lang = new JMenu("Language");
        JMenuItem item;

        item = new JMenuItem("Easy");
        diff.add(item);
        item.addActionListener(e1 -> {
            this.diff = 25;
            lab.setText("Diffculty: Easy");
        });

        item = new JMenuItem("Medium");
        diff.add(item);
        item.addActionListener(e12 -> {
            this.diff = 15;
            lab.setText("Diffculty: Medium");
        });

        item = new JMenuItem("Hard");
        diff.add(item);
        item.addActionListener(e13 -> {
            this.diff = 10;
            lab.setText("Diffculty: Hard");
        });


        item = new JMenuItem("Hungarian");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "magyar";
            ic = new ImageIcon("kepek/magyar.png");
            flag.setIcon(ic);
        });


        item = new JMenuItem("English");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "angol";
            ic = new ImageIcon("kepek/angol.png");
            flag.setIcon(ic);
        });

        item = new JMenuItem("Spanish");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "spanyol";
            ic = new ImageIcon("kepek/spanyol.png");
            flag.setIcon(ic);
        });


        btn = addButton("Play",this.p);
        btn.addActionListener(e -> {
            f.dispose();
            Game g = new Game(this.lan,this.diff);
        });

        btn = addButton("Hall of Fame",this.p);
        btn.addActionListener(e -> {
            this.p.setVisible(false);
            HoF h = new HoF(this);
            h.setVisible(true);
            f.add(h);
        });


        flag.setSize(new Dimension(30,10));

        menuBar.add(diff);
        menuBar.add(lang);
        p.add(flag,BorderLayout.SOUTH);
        p.add(lab,BorderLayout.PAGE_START);
        f.add(this.p);
        f.setJMenuBar(menuBar);
        f.setSize(600, 500);
        f.setVisible(true);
        f.setResizable(false);
        p.setBackground(Color.lightGray);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *  Egy gombhoz rendel stílust
     * @param name - A gomb neve, amit látni szeretnénk
     * @param p - A panel, ahol meg akarjuk jeleníteni
     * @return - Egy designed gomb
     */
    public JButton addButton(String name, JPanel p) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(150, 40));
        p.add(button,BorderLayout.PAGE_END);
        return button;
    }

    /**
     *  Panel
     * @return - Panel
     */
    public JPanel getP() {
        return p;
    }

    /**
     * Visszaadja a nyelvet, amin játszani kíván a játékos
     * @return nyelv
     */
    public String getLan() {
        return lan;
    }
    /**
     * Visszaadja a nehézséget, amin játszani kíván a játékos
     * @return nehézség
     */
    public int getDiff(){
        return diff;
    }
}