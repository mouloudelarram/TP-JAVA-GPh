package tp5b.exo2;


import javax.swing.*;
import javax.swing.text.html.HTML;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

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
        this.drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (startPoint != null && endPoint != null) {
                    g.setColor(elementColor); // Set the color of the graphics to the selected color
                    switch (elementType) {
                        case LIGNE:
                            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                            break;
                        case RECTANGLE:
                            g.drawRect(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y),
                                    Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y));
                            break;
                        case CERCLE:
                            g.drawOval(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y),
                                    Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y));
                            break;
                        case COURBE:
                            g.drawArc(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y),
                            Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y), 0, 180);
                            break;
                    }
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
                startPoint = null;
                endPoint = null;
                drawPanel.repaint();
            }
        });

        this.drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
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
