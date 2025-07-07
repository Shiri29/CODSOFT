import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    JLabel[] subjectLabels;
    JTextField[] subjectFields;

    JTextField totalField, averageField, gradeField;
    JButton calculateButton;

    public StudentGradeCalculator() {
        setTitle("Students Grade Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color lightBlue = new Color(173, 216, 230);

        Dimension inputFieldDim = new Dimension(150, 25); 
        Dimension resultFieldDim = new Dimension(80, 25); 

        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(lightBlue);

        subjectLabels = new JLabel[5];
        subjectFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Marks in Subject " + (i + 1) + ":");
            subjectLabels[i].setBorder(new EmptyBorder(0, 10, 0, 0));
            subjectFields[i] = new JTextField();
            subjectFields[i].setPreferredSize(inputFieldDim);
            subjectFields[i].setMinimumSize(inputFieldDim);
            subjectFields[i].setMaximumSize(inputFieldDim);
            inputPanel.add(subjectLabels[i]);
            inputPanel.add(subjectFields[i]);
        }

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(lightBlue);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);

        
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBackground(lightBlue);
        resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        GridBagConstraints gbc = new GridBagConstraints();

    
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;

        JLabel totalLabel = new JLabel("Total Marks:");
        gbc.gridy = 0;
        resultPanel.add(totalLabel, gbc);

        JLabel averageLabel = new JLabel("Average Percentage:");
        gbc.gridy = 1;
        resultPanel.add(averageLabel, gbc);

        JLabel gradeLabel = new JLabel("Grade:");
        gbc.gridy = 2;
        resultPanel.add(gradeLabel, gbc);

        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setPreferredSize(resultFieldDim);
        totalField.setMinimumSize(resultFieldDim);
        totalField.setMaximumSize(resultFieldDim);
        gbc.gridy = 0;
        resultPanel.add(totalField, gbc);

        averageField = new JTextField();
        averageField.setEditable(false);
        averageField.setPreferredSize(resultFieldDim);
        averageField.setMinimumSize(resultFieldDim);
        averageField.setMaximumSize(resultFieldDim);
        gbc.gridy = 1;
        resultPanel.add(averageField, gbc);

        gradeField = new JTextField();
        gradeField.setEditable(false);
        gradeField.setPreferredSize(resultFieldDim);
        gradeField.setMinimumSize(resultFieldDim);
        gradeField.setMaximumSize(resultFieldDim);
        gbc.gridy = 2;
        resultPanel.add(gradeField, gbc);

        setLayout(new BorderLayout(5, 5));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(lightBlue);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int total = 0;
            for (int i = 0; i < 5; i++) {
                int marks = Integer.parseInt(subjectFields[i].getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks should be between 0 and 100.");
                    return;
                }
                total += marks;
            }
            double average = total / 5.0;
            String grade;
            if (average >= 90) grade = "A";
            else if (average >= 80) grade = "B";
            else if (average >= 70) grade = "C";
            else if (average >= 60) grade = "D";
            else grade = "F";

            totalField.setText(String.valueOf(total));
            averageField.setText(String.format("%.2f", average) + "%");
            gradeField.setText(grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all subjects.");
        }
    }

    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}
