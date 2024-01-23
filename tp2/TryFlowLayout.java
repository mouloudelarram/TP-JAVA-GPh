import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class TryFlowLayout {
    TryFlowLayout() {
        JFrame frame = new JFrame("FlowLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*frame.setLayout(new FlowLayout());

        for (int i=0; i <6; i++) {
            JLabel label = new JLabel("Label " + (i+1));
            label.setFont(new Font("Arial", (i%2) == 0 ? Font.PLAIN : Font.BOLD, 12 + i*2));
            frame.add(label);
        }*/
        
        /* 2. *
        frame.setLayout(new BoxLayout( frame.getContentPane(), BoxLayout.Y_AXIS)); //create vertical box layout

         for (int i=1; i <= 6; i++) {
            JLabel label = new JLabel("Label " + (i+1));
            label.setFont(new Font("Arial", (i%2) == 0 ? Font.PLAIN : Font.BOLD, 12 + i*2));
            frame.add(label);
        }
        */

        /* 3 et 4. */
        frame.setLayout(new GridLayout(3, 4));
        
        for (int i=1; i <= 12; i++) {
            JLabel label = new JLabel("Label " + i, JLabel.CENTER);
            label.setFont(new Font("Arial", (i%2) == 0 ? Font.PLAIN : Font.BOLD, 12));

            // Create different types of borders
            Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
            Border titledBorder = BorderFactory.createTitledBorder("Title Border Label " + i + "");
            Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            
            // Create a compound border
            Border compoundBorder = BorderFactory.createCompoundBorder(bevelBorder, titledBorder);
            compoundBorder = BorderFactory.createCompoundBorder(compoundBorder, emptyBorder);
            
            // Set the compound border to the label
            label.setBorder(compoundBorder);

            frame.add(label);
        }
        
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TryFlowLayout();
    }
    
}
