package tp3;

import java.util.*;

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