import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

public class SketcherFrame extends JFrame {
    public SketcherFrame() {
        setTitle("Sketcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the 'Fichier' menu
        JMenu fichierMenu = new JMenu("Fichier");
        fichierMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem ouvrirItem = new JMenuItem("Ouvrir");
        ouvrirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        ouvrirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ouvrir");
            }
        });

        JMenuItem sauverItem = new JMenuItem("Sauver");
        sauverItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem iconeTexteItem = new JMenuItem("Icône et texte");
        JMenuItem iconeSeuleItem = new JMenuItem(new ImageIcon("icon.gif"));
        JMenuItem enregistrerItem = new JMenuItem("Enregistrer");
        enregistrerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

        fichierMenu.add(ouvrirItem);
        fichierMenu.add(sauverItem);
        fichierMenu.add(iconeTexteItem);
        fichierMenu.add(iconeSeuleItem);
        fichierMenu.addSeparator();
        fichierMenu.add(enregistrerItem);

        // Create the 'Elements' menu
        JMenu elementsMenu = new JMenu("Elements");
        elementsMenu.setMnemonic(KeyEvent.VK_E);

        JRadioButtonMenuItem ligneItem = new JRadioButtonMenuItem("Ligne");
        ligneItem.setMnemonic(KeyEvent.VK_L);
        JRadioButtonMenuItem rectangleItem = new JRadioButtonMenuItem("Rectangle");
        rectangleItem.setMnemonic(KeyEvent.VK_R);
        JRadioButtonMenuItem cercleItem = new JRadioButtonMenuItem("Cercle");
        cercleItem.setMnemonic(KeyEvent.VK_C);
        JRadioButtonMenuItem courbeItem = new JRadioButtonMenuItem("Courbe");
        courbeItem.setMnemonic(KeyEvent.VK_B);

        ButtonGroup elementsGroup = new ButtonGroup();
        elementsGroup.add(ligneItem);
        elementsGroup.add(rectangleItem);
        elementsGroup.add(cercleItem);
        elementsGroup.add(courbeItem);

        JMenu couleursMenu = new JMenu("Couleurs");
        couleursMenu.setMnemonic(KeyEvent.VK_C);

        JCheckBoxMenuItem rougeItem = new JCheckBoxMenuItem("Rouge");
        rougeItem.setMnemonic(KeyEvent.VK_R);
        JCheckBoxMenuItem vertItem = new JCheckBoxMenuItem("Vert");
        vertItem.setMnemonic(KeyEvent.VK_V);
        JCheckBoxMenuItem bleuItem = new JCheckBoxMenuItem("Bleu");
        bleuItem.setMnemonic(KeyEvent.VK_B);

        couleursMenu.add(rougeItem);
        couleursMenu.add(vertItem);
        couleursMenu.add(bleuItem);

        elementsMenu.add(ligneItem);
        elementsMenu.add(rectangleItem);
        elementsMenu.add(cercleItem);
        elementsMenu.add(courbeItem);
        elementsMenu.addSeparator();
        elementsMenu.add(couleursMenu);

        // Create the 'Aide' menu
        JMenu aideMenu = new JMenu("Aide");
        aideMenu.setMnemonic(KeyEvent.VK_A);

        // Add menus to the menu bar
        menuBar.add(fichierMenu);
        menuBar.add(elementsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(aideMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Create the scrollable text area
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SketcherFrame frame = new SketcherFrame();
            frame.setVisible(true);
        });
    }
}