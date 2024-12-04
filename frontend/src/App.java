import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends JPanel implements ActionListener {
    JPanel output;
    JTextField textbox;
    ArrayList<JTextArea> lines = new ArrayList<JTextArea>();

    public App() {
        super(new FlowLayout());

        JPanel content = new JPanel(new BorderLayout());
        content.setOpaque(true);
        content.setBackground(Color.lightGray);
        content.setPreferredSize(new Dimension(850, 685));

        JTextArea title = new JTextArea("Ascii Video Display Client");
        title.setOpaque(false);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setMargin(new Insets(0, 5, 0, 0));

        JPanel adressField = new JPanel();
        adressField.setOpaque(false);

        JTextArea prompt = new JTextArea("Enter Server Address:");
        prompt.setOpaque(false);
        prompt.setFont(new Font("Segoe UI", Font.BOLD, 15));
        prompt.setMargin(new Insets(0, 0, 2, 0));
        adressField.add(prompt);

        textbox = new JTextField(20);
        textbox.setText("127.0.0.1:8080");
        textbox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        adressField.add(textbox);
        textbox.addActionListener(this);

        JPanel north = new JPanel(new BorderLayout());
        north.setOpaque(false);
        north.add(adressField, BorderLayout.EAST);
        north.add(title, BorderLayout.WEST);

        output = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, -3));
        output.setPreferredSize(new Dimension(128, 72));

        HashMap<TextAttribute, Object> font = new HashMap<TextAttribute, Object>();
        font.put(TextAttribute.SIZE, 9);
        font.put(TextAttribute.FAMILY, Font.MONOSPACED);
        font.put(TextAttribute.TRACKING, 0.1);
        font.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

        for (int i = 0; i < 72; i++) {
            JTextArea line = new JTextArea();
            line.setOpaque(false);
            line.setFont(new Font(font));
            lines.add(line);
        }

        updateOutput();
        content.add(north, BorderLayout.NORTH);
        content.add(output, BorderLayout.CENTER);
        add(content);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Group 7B - Mayur Bhat, Jack Birlingmair, Mossy Jimmerson");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JComponent app = new App();
        app.setOpaque(true);
        app.setBackground(Color.darkGray);
        frame.setContentPane(app);

        frame.pack();
        frame.setVisible(true);
    }

    public void updateOutput() {
        output.removeAll();
        for (JTextArea line : lines) {
            output.add(line);
        }
        output.repaint();
    }

    public void setOutput(String input) {
        String[] split = input.split("(?<=\\G.{" + 128 + "})");

        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).setText(split[i]);
        }

        updateOutput();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client client = new Client(textbox.getText(), this);
        client.start();
        textbox.setEnabled(false);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
