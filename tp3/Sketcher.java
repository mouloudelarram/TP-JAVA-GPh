package tp3;

import java.awt.*;

public class Sketcher {
    private SketcherModel model;
    private SketcherFrame window;
    private static Sketcher theApp;

    public static void main(String[] args) {
        theApp = new Sketcher();
        theApp.init();
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
}