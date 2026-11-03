
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Interface RadioTuner
interface RadioTuner {
    void setStation(String station);
    void setFrequency(double frequency);
}

// Class MobileDevice
class MobileDevice implements RadioTuner {

    private String manufacturer;
    private String model;
    private String station;
    private double frequency;

    public MobileDevice(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getStation() {
        return station;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getDetails() {
        return "Manufacturer: " + manufacturer +
               "\nModel: " + model;
    }

    public void printDetails() {
        System.out.println(getDetails());
    }
}

// Class SmartPhone
class SmartPhone extends MobileDevice {

    private String type;

    public SmartPhone(String manufacturer, String model, String type) {
        super(manufacturer, model);
        this.type = type;
    }

    public String getDetails() {
        return "SmartPhone Detail:\n" +
               super.getDetails() +
               "\nType: " + type;
    }

    public void printDetails() {
        System.out.println(getDetails());
    }
}

// Class BasicPhone
class BasicPhone extends MobileDevice {

    public BasicPhone(String manufacturer, String model) {
        super(manufacturer, model);
    }

    public String getDetails() {
        return "Basic Phone Detail:\n" +
               super.getDetails();
    }

    public void printDetails() {
        System.out.println(getDetails());
    }
}

// Main GUI Frame
public class MobileAppUIFrame extends JFrame {

    private JComboBox deviceCombo;

    private JTextField manufacturerField;
    private JTextField modelField;
    private JTextField typeField;
    private JTextField stationField;
    private JTextField frequencyField;

    private JTextArea outputArea;

    public MobileAppUIFrame() {

        setTitle("Mobile Device Management System");
        setSize(700, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        setContentPane(mainPanel);

        // Application title
        JLabel title = new JLabel(
            "MOBILE DEVICE MANAGEMENT SYSTEM",
            JLabel.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 21)
        );

        title.setForeground(new Color(35, 90, 140));

        mainPanel.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(
            new GridLayout(6, 2, 8, 10)
        );

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Mobile Device Information")
        );

        inputPanel.add(new JLabel("Device Type:"));

        deviceCombo = new JComboBox(
            new String[] {"SmartPhone", "BasicPhone"}
        );

        inputPanel.add(deviceCombo);

        inputPanel.add(new JLabel("Manufacturer:"));

        manufacturerField = new JTextField();
        inputPanel.add(manufacturerField);

        inputPanel.add(new JLabel("Model:"));

        modelField = new JTextField();
        inputPanel.add(modelField);

        inputPanel.add(new JLabel("Type (SmartPhone):"));

        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Radio Station:"));

        stationField = new JTextField();
        inputPanel.add(stationField);

        inputPanel.add(new JLabel("Frequency:"));

        frequencyField = new JTextField();
        inputPanel.add(frequencyField);

        typeField.setEnabled(true);

        // Button panel
        JPanel buttonPanel = new JPanel(
            new GridLayout(1, 2, 10, 10)
        );

        JButton displayButton =
            new JButton("Display Details");

        JButton clearButton =
            new JButton("Clear");

        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);

        // Output area
        outputArea = new JTextArea(10, 50);

        outputArea.setEditable(false);

        outputArea.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane = new JScrollPane(outputArea);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Mobile Device Details")
        );

        JPanel centerPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Event handling: Device selection
        deviceCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selected =
                    (String) deviceCombo.getSelectedItem();

                if (selected.equals("SmartPhone")) {
                    typeField.setEnabled(true);
                }
                else {
                    typeField.setText("");
                    typeField.setEnabled(false);
                }
            }
        });

        // Event handling: Display Details
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDetails();
            }
        });

        // Event handling: Clear
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                manufacturerField.setText("");
                modelField.setText("");
                typeField.setText("");
                stationField.setText("");
                frequencyField.setText("");
                outputArea.setText("");

                deviceCombo.setSelectedIndex(0);
                typeField.setEnabled(true);
            }
        });
    }

    // Method to display mobile device details
    private void displayDetails() {

        try {

            String device =
                (String) deviceCombo.getSelectedItem();

            String manufacturer =
                manufacturerField.getText().trim();

            String model =
                modelField.getText().trim();

            String type =
                typeField.getText().trim();

            String station =
                stationField.getText().trim();

            if (manufacturer.length() == 0 ||
                model.length() == 0 ||
                station.length() == 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields."
                );

                return;
            }

            double frequency = Double.parseDouble(
                frequencyField.getText().trim()
            );

            if (frequency < 0) {
                JOptionPane.showMessageDialog(
                    this, "Frequency cannot be negative."
                );
                return;
            }

            MobileDevice mobile;

            if (device.equals("SmartPhone")) {

                if (type.length() == 0) {
                    JOptionPane.showMessageDialog(
                        this, "Please enter smartphone type."
                    );
                    return;
                }

                mobile = new SmartPhone(
                    manufacturer, model, type
                );

            }
            else {

                mobile = new BasicPhone(
                    manufacturer, model
                );
            }

            // Set radio information
            mobile.setStation(station);
            mobile.setFrequency(frequency);

            // Display result
            outputArea.setText(
                mobile.getDetails() +
                "\nStation:" + mobile.getStation() +
                "\nFrequency:" + mobile.getFrequency()
            );

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid frequency.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
