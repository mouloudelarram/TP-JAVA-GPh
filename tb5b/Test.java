package tb5b;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Test extends JFrame {

    private List<Rectangle> rectangles;

    public Test() {
        rectangles = new ArrayList<>();

        setTitle("Dessin de rectangles avec la souris");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                rectangles.add(new Rectangle(x, y, 50, 30)); // Changez les dimensions selon vos besoins
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Rectangle rectangle : rectangles) {
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test example = new Test();
            example.setVisible(true);
        });
    }
}
