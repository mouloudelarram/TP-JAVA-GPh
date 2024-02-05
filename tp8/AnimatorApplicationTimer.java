package tp8;

import javax.swing.*;
import java.awt.event.*;

public class AnimatorApplicationTimer extends JFrame implements ActionListener {
    private JLabel label;
    private Timer timer;
    private int numAnimations;
    private boolean isAnimating;
    private String title;

    public AnimatorApplicationTimer(int aps, String title) {
        this.title = title;
        this.numAnimations = 0;
        this.isAnimating = true;
        this.label = new JLabel("Number of animations: " + numAnimations);
        this.timer = new Timer(1000 / aps, this);

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
        this.numAnimations++;
        this.label.setText("Number of animations: " + numAnimations);
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
        if (args.length < 2) {
            System.out.println("Usage: java AnimatorApplicationTimer <animations per second> <window title>");
            System.exit(1);
        }
    
        int aps = Integer.parseInt(args[0]);
        String title = args[1];
    
        new AnimatorApplicationTimer(aps, title);
    }
}
