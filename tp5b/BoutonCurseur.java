package tp5a;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BoutonCurseur extends JFrame {
    public BoutonCurseur() {
        getContentPane().add(new MonBouton ("Essai de Curseur"), BorderLayout.CENTER);
        getContentPane().add(new JButton ("Un bouton JButton normal"), BorderLayout.NORTH);
        getContentPane().add(new MonBouton ("Un autre MonBouton"), BorderLayout.SOUTH);
    }

    public static void main(String args[]) {
        BoutonCurseur window = new BoutonCurseur();
        window.setTitle("Bouton + Curseur");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 200, 300, 200);
        window.setVisible(true);
    }
}

class MonBouton extends JButton {
    public MonBouton(String titre) {
        super(titre);

        this.addMouseListener(new MouseHandler());
    }
}

class MouseHandler extends MouseAdapter {
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    public void mouseEntered(MouseEvent e) {
        ((JButton) e.getSource()).setCursor(handCursor);
        ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).setTitle("Title changed " + Math.random()*100);
    }

    public void mouseExited(MouseEvent e) {
        ((JButton) e.getSource()).setCursor(defaultCursor);
    }
} 