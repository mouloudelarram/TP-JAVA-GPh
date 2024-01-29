package tb5b;

// fichier Constants.java
import java.awt.Color;

// fichier SketcherFrame.java (sans la barre de menu)
import java.awt.*;
import javax.swing.*;
// fichier SketcherModel.java
import java.util.*;
import java.awt.Color;
import javax.swing.JFrame;
// fichier Sketcher.java
import java.awt.*;
// fichier Element.java
import java.awt.*;
import java.awt.geom.*;

public class SketcherFrame extends JFrame implements Constants {
    public String title;
    public Color elementColor = DEFAULT_ELEMENT_COLOR;
    public static int elementType = DEFAULT_ELEMENT_TYPE;
    public SketcherModel model;
    public SketcherFrame window;

    public SketcherFrame(String titre) {
        setTitle(titre);
        title = titre;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public SketcherFrame getWindow() {
        return window;
    }

    public SketcherModel getModel() {
        return model;
    }

    public void init() {
        window = new SketcherFrame("Dessin");
        Toolkit leKit = window.getToolkit();
        Dimension wndSize = leKit.getScreenSize();
        window.setBounds(wndSize.width / 4, wndSize.height / 4,
                wndSize.width / 2, wndSize.height / 2);
        model = new SketcherModel();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SketcherFrame frame = new SketcherFrame("My Title");
        frame.init();
    }
}

class SketcherModel {
    protected LinkedList ListeElements = new LinkedList();

    public boolean remove(Element element) {
        boolean removed = ListeElements.remove(element);
        return removed;
    }

    public void add(Element element) {
        ListeElements.add(element);
    }

}
