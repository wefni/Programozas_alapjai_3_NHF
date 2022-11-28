import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class HoF extends JPanel {

    public HoF(Menu m) {
        hof(m);
    }

    public void hof(Menu m) {

        JTable j;

        String[] cNames = {"Name", "Word per Minute"};

        String[][] asd = read();

        j = new JTable(asd, cNames);
        j.setLocation(50, 50);
        j.setSize(new Dimension(200, 300));
        JScrollPane sp = new JScrollPane(j);
        super.add(sp);
        JButton btn = new JButton("Vissza");
        btn.setSize(new Dimension(100,40));
        super.add(btn);
        btn.addActionListener(e -> {
            super.setVisible(false);
            m.getP().setVisible(true);
        });
    }

    public String[][] read() {
        List<Best> word = new ArrayList<>();
        try {
            File myObj = new File("HoF.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                word.add(new Best(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Collections.sort(word, (o1, o2) -> {
            if (Integer.parseInt(o1.getWpm()) < Integer.parseInt(o2.getWpm())) return 1; else return -1;
        });

        String[][] str = new String[word.size()][2];

        for (int i = 0; i < word.size(); i++) {
            str[i][0] = word.get(i).getName();
            str[i][1] = word.get(i).getWpm();
        }
        return str;
    }
}
