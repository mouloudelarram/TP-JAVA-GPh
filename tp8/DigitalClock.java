package tp8;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame implements ActionListener {
    private JLabel label;
    private Timer timer;
    private boolean isAnimating;
    private String title;

    public DigitalClock(String title) {
        this.title = title;
        this.isAnimating = true;
        this.label = new JLabel();
        this.timer = new Timer(1000, this);

        this.add(label);
        this.setTitle(title);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowIconified(WindowEvent e) {
                stopAnimation();
            }

            public void windowDeiconified(WindowEvent e) {
                startAnimation();
            }
        });

        this.label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isAnimating) {
                    stopAnimation();
                } else {
                    startAnimation();
                }
            }
        });

        this.timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        this.label.setText(formatter.format(date));
    }

    public void stopAnimation() {
        this.timer.stop();
        this.isAnimating = false;
    }

    public void startAnimation() {
        this.timer.start();
        this.isAnimating = true;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DigitalClock <window title>");
            System.exit(1);
        }

        String title = args[0];
        new DigitalClock(title);
    }
}