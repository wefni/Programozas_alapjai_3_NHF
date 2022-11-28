import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCase {
    @Test
    public void testGetName()
    {
        String sor = "david 2";
        Player p = new Player(sor);
        String eredmeny = p.getName();
        assertEquals(eredmeny,"david");
    }
    @Test
    public void testGetWpm(){
        String sor = "david 2";
        Player p = new Player(sor);
        String eredmeny = p.getWpm();
        assertEquals(eredmeny,"2");
    }

    @Test
    public void testRead(){
        Words w = new Words("angol");
        int c = w.getWords().size();
        assertEquals(c,1000);
    }

    @Test
    public void testGetDiff(){
        Word w = new Word("en",25);
        assertEquals(w.getDiff(),25);
    }

    @Test
    public void testHoF(){
        Menu m = new Menu();
        HoF h = new HoF(m);
        String[][] str = h.read();
        assertEquals(str.length,5);
    }
    @Test
    public void testRandWord(){
        Word w = new Word("en",25);
        assertEquals(w.getRandomNumber(30,500),500,500);
    }
    @Test
    public void testWordDestr(){
        Word w = new Word("en",25);
        w.destroy();
        assertEquals(w.getFreeze(),true);
    }
    @Test
    public void testMenu1(){
        Menu m = new Menu();
        JPanel p = new JPanel();
        JButton t  = m.addButton("hajra foci",p);
        assertEquals(t.getText(),"hajra foci");
    }
    @Test
    public void testMenu2(){
        Menu m = new Menu();
        assertEquals(m.getLan(),"magyar");
    }
    @Test
    public void testMenu3(){
        Menu m = new Menu();
        assertEquals(m.getDiff(),25);
    }
}
