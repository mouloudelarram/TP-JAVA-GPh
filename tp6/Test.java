package tp6;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Test extends JFrame {
    private JList<String> list = new JList<>();
    String[] items = { "item[0]", "item[1]", "item[2]", "item[3]",
    "item[4]", "item[5]", "item[6]", "item[7]",
    "item[8]", "item[9]" };

    public Test() {
        // Initialize the JFrame
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create the list and add it to a JScrollPane
        JList<String> list = new JList<>(new String[] {"Item 1", "Item 2", "Item 3"});
        JScrollPane listPanel = new JScrollPane(list);

        // Add the JScrollPane to the JFrame
        add(listPanel, BorderLayout.SOUTH);
        init();
        // Make the JFrame visible
        setVisible(true);
    }

    public void init () {
        
        ControlPanel controlPanel = new ControlPanel(list);
        this.add(controlPanel, BorderLayout.NORTH);

        // Create a new JPanel
        JPanel listPanel = new JPanel(new BorderLayout());
        this.add(listPanel, BorderLayout.SOUTH);
        // Add the JScrollPane containing the list to the JPanel
        listPanel.add(new JScrollPane(list), BorderLayout.CENTER);

        // Create an empty border
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Create a line border
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Create a compound border
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);

        // Create a titled border
        Border titledBorder = BorderFactory.createTitledBorder(compoundBorder, "Liste des elements", TitledBorder.CENTER, TitledBorder.TOP);

        // Set the border of the JPanel
        listPanel.setBorder(titledBorder);

        // Add the JPanel to this JPanel
        this.add(listPanel, BorderLayout.CENTER);

        populateList();
    }

    public void populateList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String item : items) {
            model.addElement(item);
        }
        list.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test test = new Test();
        });
    }
}

class ControlPanel extends JPanel {
    private JButton remove = new JButton("Supprimer la selection");
    private JButton add = new JButton("Ajouter un item");
    private JList<String> list;
    private JComboBox<String> selectionModeComboBox;
    private JLabel selectedIndicesLabel;
    private JLabel minMaxIndicesLabel;

    public ControlPanel(JList<String> ls) {
        this.list = ls;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.add(remove);
        topPanel.add(add);

        // Initialize the JComboBox
        String[] selectionModes = { "SINGLE_SELECTION", "SINGLE_INTERVAL_SELECTION", "MULTIPLE_INTERVAL_SELECTION" };
        selectionModeComboBox = new JComboBox<>(selectionModes);
        topPanel.add(selectionModeComboBox);
        add(topPanel);

        initializeSelectionMode();

        remove.addActionListener(e -> {
            DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
            int[] selectedIndices = list.getSelectedIndices();
            if (selectedIndices.length > 0) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer la sélection ?");
                if (dialogResult == JOptionPane.YES_OPTION) {
                    for (int i = selectedIndices.length - 1; i >= 0; i--) {
                        model.remove(selectedIndices[i]);
                    }
                }
            }
            updateLabel();
        });
        add.addActionListener(e -> {
            String newItem = JOptionPane.showInputDialog("Entrez le nouvel item a ajouter");
            if (newItem != null && !newItem.isEmpty()) {
                DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
                model.addElement(newItem);
                SwingUtilities.invokeLater(() -> list.ensureIndexIsVisible(model.getSize()-1));
            }
            updateLabel();
        });

        // Add an ItemListener to the JComboBox
        selectionModeComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String) e.getItem();
                switch (selectedMode) {
                    case "SINGLE_SELECTION":
                        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        break;
                    case "SINGLE_INTERVAL_SELECTION":
                        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                        break;
                    case "MULTIPLE_INTERVAL_SELECTION":
                        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        break;
                }
            }
        });

        selectedIndicesLabel = new JLabel();
        minMaxIndicesLabel = new JLabel();
        add(selectedIndicesLabel);
        add(minMaxIndicesLabel);

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateLabel();
            }
        });
    }

    private void initializeSelectionMode() {
        int selectionMode = list.getSelectionMode();
        switch (selectionMode) {
            case ListSelectionModel.SINGLE_SELECTION:
                selectionModeComboBox.setSelectedItem("SINGLE_SELECTION");
                break;
            case ListSelectionModel.SINGLE_INTERVAL_SELECTION:
                selectionModeComboBox.setSelectedItem("SINGLE_INTERVAL_SELECTION");
                break;
            case ListSelectionModel.MULTIPLE_INTERVAL_SELECTION:
                selectionModeComboBox.setSelectedItem("MULTIPLE_INTERVAL_SELECTION");
                break;
        }
    }

    private void updateLabel() {
        int[] selectedIndices = list.getSelectedIndices();
        if (selectedIndices.length > 0) {
            selectedIndicesLabel.setText("Index des items selectionnes : " + Arrays.toString(selectedIndices));
            minMaxIndicesLabel.setText("Index minimum : " + selectedIndices[0] + ", Index maximum : " + selectedIndices[selectedIndices.length - 1]);
        } else {
            selectedIndicesLabel.setText("Aucun item sélectionne");
            minMaxIndicesLabel.setText("");
        }
    }
}