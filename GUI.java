import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GUI extends JFrame {
    private JButton JSONFormatButton;
    private JButton resetButton;
    private JButton CSVFormatButton;
    private JButton searchListButton;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel bottomPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JPanel searchPanel;
    private JTextArea text;
    private JScrollPane scroll;


    public GUI() {
        createGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(750, 500);
        setVisible(true);
    }

    private void createGUI() {
        setTitle("Patients");
        createButtonPanel();
        createTextPanel();
        createBottomPanel();
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        bottomPanel.add(textPanel, BorderLayout.CENTER);
        //bottomPanel.add(searchPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.CENTER);
    }

    private void createTextPanel() {
        text = new JTextArea();
        text.setColumns(10);
        text.setText("File is loaded here");
        scroll = new JScrollPane(text);
        textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPanel.add(scroll, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        createLoadButton();
        createSaveButton();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        createJSONFormatButtonButton();
        createResetButtonButton();
        createCSVFormatButtonButton();
        createSearchListButtonButton();
        buttonPanel.add(JSONFormatButton);
        buttonPanel.add(CSVFormatButton);
        buttonPanel.add(searchListButton);
        buttonPanel.add(resetButton);
    }

    private void createLoadButton() {
        loadButton = new JButton("Load File");
        loadButton.addActionListener((ActionEvent e) -> load());
    }

    private void createSaveButton() {
        saveButton = new JButton("Save File");
        saveButton.addActionListener((ActionEvent e) -> save());
    }

    private void createJSONFormatButtonButton() {
        JSONFormatButton = new JButton("JSON Format");
        JSONFormatButton.addActionListener((ActionEvent e) -> JSONFormat());
    }

    private void createResetButtonButton() {
        resetButton = new JButton("Reset");
        resetButton.addActionListener((ActionEvent e) -> reset());
    }

    private void createCSVFormatButtonButton() {
        CSVFormatButton = new JButton("CSVFormat");
        CSVFormatButton.addActionListener((ActionEvent e) -> CSVFormat());
    }

    private void createSearchListButtonButton() {
        searchListButton = new JButton("Search");
        searchListButton.addActionListener((ActionEvent e) -> search());
    }

    private void load() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                text.read(new FileReader(file), null);
            } catch (IOException exp) {
                JOptionPane.showMessageDialog(this, "Unable to load the file", "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void save() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                text.write(new FileWriter(file));
            } catch (IOException exp) {
                JOptionPane.showMessageDialog(this, "Unable to save the file", "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void JSONFormat(){
    }

    private void reset(){
    }

    private void CSVFormat(){
    }

    private void search(){
    }

    public static void main(String[] args)
    {
        new GUI();
    }

}
