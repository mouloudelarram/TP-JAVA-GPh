package tp5a.exo2;


import javax.swing.*;
import javax.swing.text.html.HTML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





// SketcherFrame class
public class SketcherFrame extends JFrame implements Constants {
    // Members for elementType and elementColor
    public static int elementType = DEFAULT_ELEMENT_TYPE;
    public static Color elementColor = DEFAULT_ELEMENT_COLOR;

    public JTextArea textArea;

        // TypeListener class
    class TypeListener implements ActionListener {
        private int type;
        

        public TypeListener(int type, JTextArea textArea) {
            this.type = type;
            textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update elementType when an option in the menu is activated
            SketcherFrame.elementType = type;
            textArea.append("Type selected: " + type + "\n");
        }
    }

    // ColorListener class
    class ColorListener implements ActionListener {
        private Color color;

        public ColorListener(Color color, JTextArea textArea) {
            this.color = color;
            textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update elementColor when a color option in the menu is activated
            SketcherFrame.elementColor = color;
            textArea.append("Color selected: " + color + "\n");
        }
    }

    public SketcherFrame() {
        super("Sketcher");
        this.textArea = new JTextArea();
        textArea.setEditable(false);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the Elements menu
        JMenu elementsMenu = new JMenu("Elements");

        // Create the Types submenu
        JMenu typesSubMenu = new JMenu("Types");

        // Create radio buttons for Types
        JRadioButtonMenuItem lineItem = new JRadioButtonMenuItem("Line");
        JRadioButtonMenuItem rectangleItem = new JRadioButtonMenuItem("Rectangle");
        JRadioButtonMenuItem circleItem = new JRadioButtonMenuItem("Circle");
        JRadioButtonMenuItem curveItem = new JRadioButtonMenuItem("Curve");

        // Add TypeListeners to radio buttons
        lineItem.addActionListener(new TypeListener(LIGNE, textArea));
        rectangleItem.addActionListener(new TypeListener(RECTANGLE, textArea));
        circleItem.addActionListener(new TypeListener(CERCLE, textArea));
        curveItem.addActionListener(new TypeListener(COURBE, textArea));

        // Add radio buttons to Types submenu
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(lineItem);
        typeGroup.add(rectangleItem);
        typeGroup.add(circleItem);
        typeGroup.add(curveItem);

        // Add radio buttons to Types submenu
        typesSubMenu.add(lineItem);
        typesSubMenu.add(rectangleItem);
        typesSubMenu.add(circleItem);
        typesSubMenu.add(curveItem);

        // Add Types submenu to Elements menu
        elementsMenu.add(typesSubMenu);

        // Create the Colors submenu
        JMenu colorsSubMenu = new JMenu("Colors");

        // Create check boxes for Colors
        JCheckBoxMenuItem blackItem = new JCheckBoxMenuItem("Black", true);
        JCheckBoxMenuItem redItem = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem greenItem = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem blueItem = new JCheckBoxMenuItem("Blue");
        
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(blackItem);
        colorGroup.add(redItem);
        colorGroup.add(greenItem);
        colorGroup.add(blueItem);

        // Add ColorListeners to check boxes
        blackItem.addActionListener(new ColorListener(Color.BLACK, textArea));
        redItem.addActionListener(new ColorListener(Color.RED, textArea));
        greenItem.addActionListener(new ColorListener(Color.GREEN, textArea));
        blueItem.addActionListener(new ColorListener(Color.BLUE, textArea));

        // Add check boxes to Colors submenu
        colorsSubMenu.add(blackItem);
        colorsSubMenu.add(redItem);
        colorsSubMenu.add(greenItem);
        colorsSubMenu.add(blueItem);

        // Add Colors submenu to Elements menu
        elementsMenu.add(colorsSubMenu);

        // Add Elements menu to the menu bar
        menuBar.add(elementsMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> new SketcherFrame());
    }
}
