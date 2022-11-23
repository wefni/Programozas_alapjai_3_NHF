import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JFrame f = new JFrame("TypeRacer");
    private JPanel p = new JPanel();


    public Menu(){
        initAll();
    }

    private void initAll(){
        ImageIcon img = new ImageIcon("kep.png");


        JButton bt = addButton("Katt!",p);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Words szavak = new Words("angol");

            }
        });


        p.add(bt);
        f.add(p);
        f.setSize(600,700);
        f.setIconImage(img.getImage());
        f.setVisible(true);
        f.setResizable(false);
        p.setBackground(Color.lightGray);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton addButton(String name, JPanel p){
        JButton button = new JButton(name);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300,40));
        p.add(button);
        return button;
    }
}

 class ExitButton implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
