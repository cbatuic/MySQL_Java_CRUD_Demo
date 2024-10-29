import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private DatabaseModel model;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textField1, textField2;
    
    public MainView() {
        model = new DatabaseModel();
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        setTitle("CRUD Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"Column1", "Column2"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Input fields
        JPanel inputPanel = new JPanel(new FlowLayout());
        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        inputPanel.add(textField1);
        inputPanel.add(textField2);
        add(inputPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addRecord());
        updateButton.addActionListener(e -> updateRecord());
        deleteButton.addActionListener(e -> deleteRecord());

        // Table selection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1) {
                textField1.setText((String) table.getValueAt(table.getSelectedRow(), 0));
                textField2.setText((String) table.getValueAt(table.getSelectedRow(), 1));
            }
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        for (String[] record : model.getAllRecords()) {
            tableModel.addRow(record);
        }
    }

    private void addRecord() {
        model.addRecord(textField1.getText(), textField2.getText());
        loadData();
        clearFields();
    }

    private void updateRecord() {
        model.updateRecord(textField1.getText(), textField2.getText());
        loadData();
        clearFields();
    }

    private void deleteRecord() {
        model.deleteRecord(textField1.getText());
        loadData();
        clearFields();
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView();
            view.setVisible(true);
        });
    }
}
