package tp8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class MovingImageTimer {
    private boolean isAnimationFrozen = false;
    private Timer timer;
    private BufferedImage bgImage, fgImage;
    private JLabel imageLabel;

    public MovingImageTimer() {
        try {
            bgImage = ImageIO.read(new URL("https://mouloudelarram.github.io/profil-mouloud-elarram/images/mouloud%20elarram.png"));
            fgImage = ImageIO.read(new URL("https://mouloudelarram.github.io/profil-mouloud-elarram/images/mouloud%20elarram.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel = new JLabel(new ImageIcon(bgImage));
        imageLabel.setPreferredSize(new Dimension(bgImage.getWidth(), bgImage.getHeight()));
        imageLabel.setLayout(null);

        JLabel fgImageLabel = new JLabel(new ImageIcon(fgImage));
        fgImageLabel.setSize(fgImageLabel.getPreferredSize());
        imageLabel.add(fgImageLabel);

        timer = new Timer(100, e -> {
            fgImageLabel.setLocation((int) (Math.random() * (imageLabel.getWidth() - fgImageLabel.getWidth())), (int) (Math.random() * (imageLabel.getHeight() - fgImageLabel.getHeight())));
        });

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAnimationFrozen) {
                    timer.start();
                } else {
                    timer.stop();
                }
                isAnimationFrozen = !isAnimationFrozen;
            }
        });

        JFrame frame = new JFrame("Moving Image Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(imageLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovingImageTimer::new);
    }
}