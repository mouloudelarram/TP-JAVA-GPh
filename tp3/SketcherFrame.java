package tp3;

import java.awt.*;
import javax.swing.*;

public class SketcherFrame extends JFrame implements Constants {
    private String title;
    private Color elementColor = DEFAULT_ELEMENT_COLOR;
    private int elementType = DEFAULT_ELEMENT_TYPE;

    public SketcherFrame(String titre) {
        setTitle(titre);
        title = titre;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}