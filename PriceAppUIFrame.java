
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Interface Price
interface Price {
    void getPrice(Customer customer);
}

// Class Customer
class Customer {

    private String name;
    private double salary;

    public Customer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

// Class Product
class Product implements Price {

    private String type;
    private Customer buy;
    private String result;

    public Product(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "The product is ";
    }

    public void getPrice(Customer customer) {

        buy = customer;

        if (buy.getSalary() > 4000) {

            result = "The customer's name is " +
                buy.getName() + " with salary RM" +
                buy.getSalary() + " can buy this product.\n" +
                "Your salary is higher than the price's product.";

        } else {

            result = "The customer's name is " +
                buy.getName() + " with salary RM" +
                buy.getSalary() + " cannot buy this product.\n" +
                "The product price is more than your salary.";
        }
    }

    public String getResult() {
        return toString() + "\n" + result;
    }
}

// Class SmartPhone
class SmartPhone extends Product {

    private String nameSP;
    private double priceSP;

    public SmartPhone(String type, String nameSP, double priceSP) {
        super(type);
        this.nameSP = nameSP;
        this.priceSP = priceSP;
    }

    public String toString() {
        return super.toString() + nameSP + ".";
    }
}

// Class Laptop
class Laptop extends Product {

    private String nameL;
    private double priceL;

    public Laptop(String type, String nameL, double priceL) {
        super(type);
        this.nameL = nameL;
        this.priceL = priceL;
    }

    public String toString() {
        return super.toString() + nameL + ".";
    }
}

// Class Car
class Car implements Price {

    private String brand;
    private double priceCar;
    private Customer buy;
    private String result;

    public Car(String brand, double priceCar) {
        this.brand = brand;
        this.priceCar = priceCar;
    }

    public String getBrand() {
        return brand;
    }

    public String toString() {
        return "The car brand is " + brand +
            " with price RM" + priceCar + ".";
    }

    public void getPrice(Customer customer) {

        buy = customer;

        if (buy.getSalary() > 8000) {

            result = "The customer's name is " +
                buy.getName() + " with salary RM" +
                buy.getSalary() +
                " can buy this car. Your salary is higher than the price's car.";

        } else {

            result = "The customer's name is " +
                buy.getName() + " with salary RM" +
                buy.getSalary() +
                " cannot buy this car. The car price is more than your salary.";
        }
    }

    public String getResult() {
        return toString() + "\n" + result;
    }
}

// Main GUI Frame
public class PriceAppUIFrame extends JFrame {

    private JComboBox itemCombo;

    private JLabel itemNameLabel;

    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField customerNameField;
    private JTextField salaryField;

    private JTextArea outputArea;

    public PriceAppUIFrame() {

        setTitle("Price Management System");
        setSize(700, 570);

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
            "PRODUCT AND CAR PRICE SYSTEM",
            JLabel.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 22)
        );

        title.setForeground(new Color(35, 90, 140));

        mainPanel.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(
            new GridLayout(5, 2, 8, 12)
        );

        inputPanel.setBorder(
            BorderFactory.createTitledBorder(
                "Product and Customer Information"
            )
        );

        // Item type
        inputPanel.add(new JLabel("Item Type:"));

        itemCombo = new JComboBox(
            new String[] {
                "SmartPhone",
                "Laptop",
                "Car"
            }
        );

        inputPanel.add(itemCombo);

        // Product name or car brand
        itemNameLabel = new JLabel("Product Name:");

        inputPanel.add(itemNameLabel);

        itemNameField = new JTextField();

        inputPanel.add(itemNameField);

        // Price
        inputPanel.add(new JLabel("Price (RM):"));

        priceField = new JTextField();

        inputPanel.add(priceField);

        // Customer name
        inputPanel.add(new JLabel("Customer Name:"));

        customerNameField = new JTextField();

        inputPanel.add(customerNameField);

        // Customer salary
        inputPanel.add(new JLabel("Customer Salary (RM):"));

        salaryField = new JTextField();

        inputPanel.add(salaryField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(
            new GridLayout(1, 2, 10, 10)
        );

        JButton checkButton =
            new JButton("Check Price");

        JButton clearButton =
            new JButton("Clear");

        buttonPanel.add(checkButton);
        buttonPanel.add(clearButton);

        // Output area
        outputArea = new JTextArea(9, 50);

        outputArea.setEditable(false);

        outputArea.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane =
            new JScrollPane(outputArea);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Result")
        );

        JPanel bottomPanel = new JPanel(
            new BorderLayout(8, 8)
        );

        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Event handling: Item selection
        itemCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selected =
                    (String) itemCombo.getSelectedItem();

                if (selected.equals("Car")) {
                    itemNameLabel.setText("Car Brand:");
                } else {
                    itemNameLabel.setText("Product Name:");
                }
            }
        });

        // Event handling: Check Price
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkPrice();
            }
        });

        // Event handling: Clear
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                itemCombo.setSelectedIndex(0);

                itemNameField.setText("");
                priceField.setText("");
                customerNameField.setText("");
                salaryField.setText("");

                outputArea.setText("");
                itemNameLabel.setText("Product Name:");
            }
        });
    }

    // Method to check product and car eligibility
    private void checkPrice() {

        try {

            String itemType =
                (String) itemCombo.getSelectedItem();

            String itemName =
                itemNameField.getText().trim();

            String customerName =
                customerNameField.getText().trim();

            if (itemName.length() == 0 ||
                customerName.length() == 0 ||
                priceField.getText().trim().length() == 0 ||
                salaryField.getText().trim().length() == 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
                );

                return;
            }

            double itemPrice = Double.parseDouble(
                priceField.getText().trim()
            );

            double salary = Double.parseDouble(
                salaryField.getText().trim()
            );

            if (itemPrice < 0 || salary < 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Price and salary cannot be negative."
                );

                return;
            }

            Customer customer =
                new Customer(customerName, salary);

            // Check Car
            if (itemType.equals("Car")) {

                Car car = new Car(itemName, itemPrice);

                car.getPrice(customer);

                outputArea.setText(car.getResult());

            }

            // Check Product
            else {

                Product product;

                if (itemType.equals("SmartPhone")) {

                    product = new SmartPhone(
                        itemType, itemName, itemPrice
                    );

                } else {

                    product = new Laptop(
                        itemType, itemName, itemPrice
                    );
                }

                product.getPrice(customer);

                outputArea.setText(product.getResult());
            }

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter valid numbers for price and salary.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
