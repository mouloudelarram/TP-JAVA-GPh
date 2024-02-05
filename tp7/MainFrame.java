package tp7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextArea textArea;
    private JEditorPane editorPane;
    private JTextPane textPane;
    private JLabel messageLabel;

    public MainFrame() {
        super("Swing Text Editors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Text fields panel
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));
        textFieldsPanel.setBorder(BorderFactory.createTitledBorder("Champs Textes"));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        JLabel textLabel = new JLabel("JTextField: ");
        textField = new JTextField(20);
        JLabel passwordLabel = new JLabel("JPasswordField: ");
        passwordField = new JPasswordField(20);
        fieldsPanel.add(textLabel);
        fieldsPanel.add(textField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        // Initialize message label and add it to the panel
        messageLabel = new JLabel("Tapez du texte et un return dans un champs");
        textFieldsPanel.add(fieldsPanel);
        textFieldsPanel.add(messageLabel);

        // Add action listeners
        textField.addActionListener(this);
        passwordField.addActionListener(this);

        // Text area panel
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        textAreaPanel.setBorder(BorderFactory.createTitledBorder("Texte Pur"));
        textArea = new JTextArea();
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaPanel.add(textAreaScrollPane, BorderLayout.CENTER);

        // Add panels to left panel
        leftPanel.add(textFieldsPanel);
        leftPanel.add(textAreaPanel);

        // Right panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane();
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        JScrollPane editorPaneScrollPane = new JScrollPane(editorPane);
        textPane = new JTextPane();
        JScrollPane textPaneScrollPane = new JScrollPane(textPane);
        splitPane.setLeftComponent(editorPaneScrollPane);
        splitPane.setRightComponent(textPaneScrollPane);
        rightPanel.add(splitPane, BorderLayout.CENTER);

        // Add panels to content pane
        Box box = Box.createHorizontalBox();
        box.add(leftPanel);
        box.add(rightPanel);
        getContentPane().add(box);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            messageLabel.setText("Vous avez tape dans JTextField: " + textField.getText());
        } else if (e.getSource() == passwordField) {
            messageLabel.setText("Vous avez tape dans JPasswordField: " + new String(passwordField.getPassword()));
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}