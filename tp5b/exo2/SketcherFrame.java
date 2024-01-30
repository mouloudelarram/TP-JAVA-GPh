package tp5b.exo2;


import javax.swing.*;
import javax.swing.text.html.HTML;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



// SketcherFrame class
public class SketcherFrame extends JFrame implements Constants {
    // Members for elementType and elementColor
    public static int elementType = DEFAULT_ELEMENT_TYPE;
    public static Color elementColor = DEFAULT_ELEMENT_COLOR;
    private Point startPoint = null;
    private Point endPoint = null;
    public JPanel drawPanel;

        // TypeListener class
    class TypeListener implements ActionListener {
        private int type;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update elementType when an option in the menu is activated
            SketcherFrame.elementType = type;
            System.out.println("Type selected: " + type);
        }
    }
    class DrawableElement {
        int type;
        Color color;
        Point start;
        Point end;
    
        DrawableElement(int type, Color color, Point start, Point end) {
            this.type = type;
            this.color = color;
            this.start = start;
            this.end = end;
        }
    }
    
    List<DrawableElement> elements = new ArrayList<>();

    // ColorListener class
    class ColorListener implements ActionListener {
        private Color color;

        public ColorListener(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update elementColor when a color option in the menu is activated
            SketcherFrame.elementColor = color;
            System.out.println("Color selected: " + color);
        }
    }

    public SketcherFrame() {
        super("Sketcher");

         // Créer un nouveau JPanel avec un FlowLayout pour contenir les boutons
         JPanel buttonPanel = new JPanel(new FlowLayout());

         // Créer le bouton "Quitter"
            JButton quitButton = new JButton("Quitter");
         quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher une boîte de dialogue de confirmation avant de quitter
                int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Quitter l'application
                }
            }
        });
 
         // Créer le bouton "Changer le couleur de la fenêtre"
        JButton changeColorButton = new JButton("Changer le couleur de Frame");
        Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.ORANGE}; // Ajoutez plus de couleurs si vous le souhaitez
        AtomicInteger colorIndex = new AtomicInteger(0); // Utilisez AtomicInteger pour le changement de thread-safe

        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utiliser JColorChooser pour choisir la couleur
                Color newColor = JColorChooser.showDialog(null, "Choisissez une couleur", drawPanel.getBackground());
                if (newColor != null) {
                    drawPanel.setBackground(newColor); // Changer la couleur de fond de la fenêtre
                }
            }
        });
 
         // Ajouter les boutons au JPanel
         buttonPanel.add(quitButton);
         buttonPanel.add(changeColorButton);
 
         // Ajouter le JPanel à la fenêtre en position SOUTH
         add(buttonPanel, BorderLayout.SOUTH);

        this.drawPanel = new JPanel() {

            private void drawElement(Graphics g, DrawableElement element) {
                g.setColor(element.color);
                switch (element.type) {
                    case LIGNE:
                        g.drawLine(element.start.x, element.start.y, element.end.x, element.end.y);
                        break;
                    case RECTANGLE:
                        g.drawRect(Math.min(element.start.x, element.end.x), Math.min(element.start.y, element.end.y),
                                Math.abs(element.start.x - element.end.x), Math.abs(element.start.y - element.end.y));
                        break;
                    case CERCLE:
                        g.drawOval(Math.min(element.start.x, element.end.x), Math.min(element.start.y, element.end.y),
                                Math.abs(element.start.x - element.end.x), Math.abs(element.start.y - element.end.y));
                        break;
                    case COURBE:
                        g.drawArc(Math.min(element.start.x, element.end.x), Math.min(element.start.y, element.end.y),
                                Math.abs(element.start.x - element.end.x), Math.abs(element.start.y - element.end.y), 0, 180);
                        break;
                }
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (DrawableElement element : elements) {
                    drawElement(g, element);
                }
                if (startPoint != null && endPoint != null) {
                    drawElement(g, new DrawableElement(elementType, elementColor, startPoint, endPoint));
                }
            }
        };

        this.drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                elements.add(new DrawableElement(elementType, elementColor, startPoint, endPoint));
                startPoint = null;
                endPoint = null;
                drawPanel.repaint();
            }
        });

        this.drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                drawPanel.repaint();
            }
        });
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

        lineItem.addActionListener(e -> {
            System.out.println("Type selected: LIGNE");
            elementType = LIGNE;
            drawPanel.repaint();
        });
        rectangleItem.addActionListener(e -> {
            System.out.println("Type selected: RECTANGLE");
            elementType = RECTANGLE;
            drawPanel.repaint();
        });
        circleItem.addActionListener(e -> {
            System.out.println("Type selected: CERCLE");
            elementType = CERCLE;
            drawPanel.repaint();
        });
        curveItem.addActionListener(e -> {
            System.out.println("Type selected: COURBE");
            elementType = COURBE;
            drawPanel.repaint();
        });

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
        // Par celles-ci
        blackItem.addActionListener(e -> {
            System.out.println("Color selected: BLACK");
            elementColor = Color.BLACK;
            drawPanel.repaint();
        });
        redItem.addActionListener(e -> {
            System.out.println("Color selected: RED");
            elementColor = Color.RED;
            drawPanel.repaint();
        });
        greenItem.addActionListener(e -> {
            System.out.println("Color selected: GREEN");
            elementColor = Color.GREEN;
            drawPanel.repaint();
        });
        blueItem.addActionListener(e -> {
            System.out.println("Color selected: BLUE");
            elementColor = Color.BLUE;
            drawPanel.repaint();
        });

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

        add(drawPanel, BorderLayout.CENTER);

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
