import javax.swing.*;
import java.awt.*;

public class Menu{
    private JFrame f = new JFrame("TypeRacer");
    private JPanel p = new JPanel();

    private ImageIcon ic = new ImageIcon("magyar.png");
    private int diff = 25;
    private String lan = "magyar";

    public Menu() {
        initAll();
    }

    private void initAll() {
        ImageIcon img = new ImageIcon("kep.png");
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

        /**
         * Magyar
         */
        item = new JMenuItem("Hungarian");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "magyar";
            ic = new ImageIcon("magyar.png");
            flag.setIcon(ic);
        });

        /**
         * Angol
         */
        item = new JMenuItem("English");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "angol";
            ic = new ImageIcon("angol.png");
            flag.setIcon(ic);
        });

        item = new JMenuItem("Spanish");
        lang.add(item);
        item.addActionListener(e -> {
            this.lan = "spanyol";
            ic = new ImageIcon("spanyol.png");
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
        f.setIconImage(img.getImage());
        f.setVisible(true);
        f.setResizable(false);
        p.setBackground(Color.lightGray);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton addButton(String name, JPanel p) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(150, 40));
        p.add(button,BorderLayout.PAGE_END);
        return button;
    }

    public JPanel getP() {
        return p;
    }
}