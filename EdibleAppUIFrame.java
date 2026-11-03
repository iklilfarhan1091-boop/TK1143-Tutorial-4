
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Interface Edible
interface Edible {
    String howToEat();
}

// Class Animal
class Animal implements Edible {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String howToEat() {
        return name + ". " + name +
               " needs to be cooked first before eating.";
    }
}

// Class Fruit
class Fruit implements Edible {
    protected String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String howToEat() {
        if (this instanceof Apple) {
            return name + ". " + name +
                   " can make apple pie.";
        }
        else if (this instanceof Orange) {
            return name + ". " + name +
                   " can make orange butter cake.";
        }

        return name;
    }
}

// Class Apple
class Apple extends Fruit {
    public Apple(String name) {
        super(name);
    }
}

// Class Orange
class Orange extends Fruit {
    public Orange(String name) {
        super(name);
    }
}

// Class Person
class Person {
    private String name;
    private Edible eat;

    public Person(String name) {
        this.name = name;
    }

    public String eating(Edible eat) {
        return name + " is eating " + eat.howToEat();
    }
}

// Main GUI Frame
public class EdibleAppUIFrame extends JFrame {

    private JComboBox foodCombo;
    private JComboBox personCombo;
    private JTextArea outputArea;

    public EdibleAppUIFrame() {

        setTitle("Edible Application System");
        setSize(650, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel =
            new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        setContentPane(mainPanel);

        // Application title
        JLabel title = new JLabel(
            "EDIBLE APPLICATION SYSTEM",
            JLabel.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 22)
        );

        title.setForeground(new Color(35, 90, 140));

        mainPanel.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel =
            new JPanel(new GridLayout(3, 2, 10, 15));

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Food Selection")
        );

        inputPanel.add(new JLabel("Select Person:"));

        personCombo = new JComboBox(
            new String[] {"Ali", "Sarah"}
        );

        inputPanel.add(personCombo);

        inputPanel.add(new JLabel("Select Food:"));

        foodCombo = new JComboBox(
            new String[] {
                "Chicken",
                "Red Apple",
                "Navel Orange"
            }
        );

        inputPanel.add(foodCombo);

        JButton showButton =
            new JButton("Show Result");

        JButton clearButton =
            new JButton("Clear");

        inputPanel.add(showButton);
        inputPanel.add(clearButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea(8, 40);

        outputArea.setEditable(false);

        outputArea.setFont(
            new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane =
            new JScrollPane(outputArea);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Result")
        );

        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Event handling: Show Result
        showButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showResult();
                }
            }
        );

        // Event handling: Clear
        clearButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    outputArea.setText("");
                }
            }
        );
    }

    // Method to display result
    private void showResult() {

        String personName =
            (String) personCombo.getSelectedItem();

        String foodName =
            (String) foodCombo.getSelectedItem();

        Person person = new Person(personName);

        Edible food;

        if (foodName.equals("Chicken")) {
            food = new Animal("Chicken");
        }
        else if (foodName.equals("Red Apple")) {
            food = new Apple("Red Apple");
        }
        else {
            food = new Orange("Navel Orange");
        }

        outputArea.append(person.eating(food) + "\n");
    }

}

// Comments in Malay: Program menggunakan interface,
// inheritance dan event handling dalam Java Swing.
