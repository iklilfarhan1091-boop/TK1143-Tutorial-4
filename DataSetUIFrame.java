
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

// Interface Measurable
interface Measurable {
    double getMeasure();
    String getName();
}

// Class BankAccount
class BankAccount implements Measurable {
    private static int nextNumber = 1;
    private int accountNumber;
    private double balance;

    public BankAccount(double balance) {
        accountNumber = nextNumber++;
        this.balance = balance;
    }

    public double getMeasure() {
        return balance;
    }

    public String getName() {
        return "Bank Account " + accountNumber;
    }
}

// Class Coin
class Coin implements Measurable {
    private double value;
    private String name;

    public Coin(double value, String name) {
        this.value = value;
        this.name = name;
    }

    public double getMeasure() {
        return value;
    }

    public String getName() {
        return name;
    }
}

// Class Student
class Student implements Measurable {
    private String name;
    private double height;

    public Student(String name, double height) {
        this.name = name;
        this.height = height;
    }

    public double getMeasure() {
        return height;
    }

    public String getName() {
        return name;
    }
}

// Class DataSet
class DataSet {
    private ArrayList<Measurable> items;

    public DataSet() {
        items = new ArrayList<Measurable>();
    }

    public void add(Measurable item) {
        items.add(item);
    }

    public int getCount() {
        return items.size();
    }

    public double getAverage() {
        if (items.size() == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).getMeasure();
        }

        return sum / items.size();
    }

    public Measurable getMax() {
        if (items.size() == 0) {
            return null;
        }

        Measurable max = items.get(0);

        for (int i = 1; i < items.size(); i++) {
            if (items.get(i).getMeasure() > max.getMeasure()) {
                max = items.get(i);
            }
        }

        return max;
    }
}

// Main GUI Frame
public class DataSetUIFrame extends JFrame {

    private JComboBox typeCombo;
    private JTextField nameField;
    private JTextField valueField;
    private JTextArea outputArea;

    private DataSet bankData;
    private DataSet coinData;
    private DataSet studentData;

    public DataSetUIFrame() {

        bankData = new DataSet();
        coinData = new DataSet();
        studentData = new DataSet();

        setTitle("DataSet Management System");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        setContentPane(mainPanel);

        // Application title
        JLabel title = new JLabel(
            "DATASET MANAGEMENT SYSTEM",
            JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(35, 90, 140));

        mainPanel.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(
            new GridLayout(4, 2, 8, 8)
        );

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Add New Record")
        );

        inputPanel.add(new JLabel("Data Type:"));

        typeCombo = new JComboBox(
            new String[] {
                "Bank Account",
                "Coin",
                "Student"
            }
        );

        inputPanel.add(typeCombo);

        inputPanel.add(
            new JLabel("Name (Coin / Student):")
        );

        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(
            new JLabel("Balance / Value / Height:")
        );

        valueField = new JTextField();
        inputPanel.add(valueField);

        JButton addButton = new JButton("Add Data");
        JButton summaryButton =
            new JButton("Calculate Summary");

        inputPanel.add(addButton);
        inputPanel.add(summaryButton);

        // Output area
        outputArea = new JTextArea(12, 50);
        outputArea.setEditable(false);

        outputArea.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane = new JScrollPane(outputArea);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Output")
        );

        JPanel centerPanel = new JPanel(
            new BorderLayout(8, 8)
        );

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(
            new FlowLayout(FlowLayout.RIGHT)
        );

        JButton clearButton = new JButton("Clear All");

        bottomPanel.add(clearButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Event handling: Add Data
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        // Event handling: Calculate Summary
        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSummary();
            }
        });

        // Event handling: Clear All
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bankData = new DataSet();
                coinData = new DataSet();
                studentData = new DataSet();

                nameField.setText("");
                valueField.setText("");
                outputArea.setText("");
            }
        });
    }

    // Method to add records
    private void addRecord() {

        try {
            String type =
                (String) typeCombo.getSelectedItem();

            double value = Double.parseDouble(
                valueField.getText().trim()
            );

            String name = nameField.getText().trim();

            if (value < 0) {
                JOptionPane.showMessageDialog(
                    this, "Value cannot be negative."
                );
                return;
            }

            if (type.equals("Bank Account")) {

                BankAccount account =
                    new BankAccount(value);

                bankData.add(account);

                outputArea.append(
                    "Added: " + account.getName() +
                    " | Balance: " + value + "\n"
                );

            } else if (type.equals("Coin")) {

                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(
                        this, "Please enter coin name."
                    );
                    return;
                }

                Coin coin = new Coin(value, name);
                coinData.add(coin);

                outputArea.append(
                    "Added: " + coin.getName() +
                    " | Value: " + value + "\n"
                );

            } else {

                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(
                        this, "Please enter student name."
                    );
                    return;
                }

                Student student = new Student(name, value);
                studentData.add(student);

                outputArea.append(
                    "Added: " + student.getName() +
                    " | Height: " + value + "\n"
                );
            }

            nameField.setText("");
            valueField.setText("");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid number.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Method to calculate average and maximum
    private void showSummary() {

        DecimalFormat df = new DecimalFormat("0.00");

        outputArea.append(
            "\n========== DATASET SUMMARY ==========\n"
        );

        if (bankData.getCount() > 0) {

            Measurable max = bankData.getMax();

            outputArea.append(
                "Bank Average Balance: " +
                bankData.getAverage() + "\n"
            );

            outputArea.append(
                "Bank Highest Balance: " +
                max.getMeasure() + "\n\n"
            );
        }

        if (coinData.getCount() > 0) {

            Measurable max = coinData.getMax();

            outputArea.append(
                "Average Coin Value: " +
                df.format(coinData.getAverage()) + "\n"
            );

            outputArea.append(
                "Highest Coin Value: " +
                df.format(max.getMeasure()) + "\n"
            );

            outputArea.append(
                "Highest Coin Name: " +
                max.getName() + "\n\n"
            );
        }

        if (studentData.getCount() > 0) {

            Measurable max = studentData.getMax();

            outputArea.append(
                "Average Student Height: " +
                df.format(studentData.getAverage()) + "\n"
            );

            outputArea.append(
                "Tallest Height: " +
                df.format(max.getMeasure()) + "\n"
            );

            outputArea.append(
                "Tallest Student Name: " +
                max.getName() + "\n"
            );
        }

        if (bankData.getCount() == 0 &&
            coinData.getCount() == 0 &&
            studentData.getCount() == 0) {

            outputArea.append(
                "No records available. Please add data first.\n"
            );
        }
    }
}
