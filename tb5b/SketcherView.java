package tb5b;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.EOFException;


public class SketcherView extends JPanel implements Constants {

    private Shape tempElement;
    private Point startPoint;
    private Point endPoint;

    public SketcherView() {
        // Initialize the MouseHandler
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // On mouse press, record the starting point for the element
                try {
                startPoint = e.getPoint();
                } catch(Exception ex) {
                    System.out.println("*****************************************************Erreur : " + ex);
                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // On mouse drag, erase the previous temporary element
                repaint();

                // Record the new end point for the element
                endPoint = e.getPoint();

                // Draw the updated temporary element
                switch (SketcherFrame.elementType) {
                    case LIGNE:
                        tempElement = new Line2D.Double(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
                        break;
                    case RECTANGLE:
                        tempElement = new Rectangle2D.Double(startPoint.getX(), startPoint.getY(),
                                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
                        break;
                    case CERCLE:
                        double radius = startPoint.distance(endPoint);
                        tempElement = new Ellipse2D.Double(startPoint.getX() - radius, startPoint.getY() - radius,
                                2 * radius, 2 * radius);
                        break;
                    case COURBE:
                        // Implement drawing of a curve (customize as needed)
                        // For example, you could use a Path2D.Double and add points on drag
                        break;
                }

                // Draw the updated temporary element
                draw((Graphics2D) getGraphics(), tempElement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // On mouse release, record the final end point for the element
                try {
                    
                
                endPoint = e.getPoint();

                // Draw the final element
                draw((Graphics2D) getGraphics(), tempElement);

                // Add the final element to the model (commented out for now)
                // addToModel(tempElement);

                // Reset temporary element and points
                tempElement = null;
                startPoint = null;
                endPoint = null;
            } catch (Exception ecx) {
                // TODO: handle exception
                System.out.println("*****************************************************Erreur : " );

            }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            // Implement any additional mouse motion listener methods here
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the temporary element using XOR mode
        g2d.setXORMode(getBackground());
        if (tempElement != null) {
            draw(g2d, tempElement);
        }
        g2d.setPaintMode();
    }

    private void draw(Graphics2D g2d, Shape shape) {
        g2d.draw(shape);
    }

    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // On mouse press, record the starting point for the element
            try {
                startPoint = e.getPoint();
            } catch (Exception ex) {
                // TODO: handle exception
                System.out.println("**** Erreur ! ***********");           
            }
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // On mouse drag, erase the previous temporary element
            repaint();
            
            // Record the new end point for the element
            endPoint = e.getPoint();

            // Draw the updated temporary element
            switch (SketcherFrame.elementType) {
                case LIGNE:
                    tempElement = new Line2D.Double(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
                    break;
                case RECTANGLE:
                    tempElement = new Rectangle2D.Double(startPoint.getX(), startPoint.getY(),
                            endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
                    break;
                case CERCLE:
                    double radius = startPoint.distance(endPoint);
                    tempElement = new Ellipse2D.Double(startPoint.getX() - radius, startPoint.getY() - radius,
                            2 * radius, 2 * radius);
                    break;
                case COURBE:
                    // Implement drawing of a curve (customize as needed)
                    // For example, you could use a Path2D.Double and add points on drag
                    break;
            }

            // Draw the updated temporary element
            draw((Graphics2D) getGraphics(), tempElement);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // On mouse release, record the final end point for the element
            try {
                endPoint = e.getPoint();
            
           

            // Draw the final element
            draw((Graphics2D) getGraphics(), tempElement);

            // Add the final element to the model (commented out for now)
            // addToModel(tempElement);

            // Reset temporary element and points
            tempElement = null;
            startPoint = null;
            endPoint = null;
        } catch (Exception ex) {
            // TODO: handle exception
            System.out.println("********** error in mouseReleased ************************");

        }
        }
    }

    // Additional methods for interacting with the model can be added here
    // For example:
    // private void addToModel(Shape element) {
    //    // Add the element to the model
    //    sketcherModel.addElement(element);
    // }
}
