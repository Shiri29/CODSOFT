import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int attemptsLeft;
    private int maxAttempts = 5;
    private int roundsWon = 0;

    private JTextField guessField;
    private JButton guessButton, playAgainButton;
    private JLabel messageLabel, attemptsLabel, roundsLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(173, 216, 230));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Components
        messageLabel = new JLabel("Guess a number between 1 and 100");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField(10);
        guessButton = new JButton("Submit Guess");
        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        roundsLabel = new JLabel("Rounds won: 0");

        // Add components to mainPanel with layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(messageLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Your guess:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(guessField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(guessButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(playAgainButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(attemptsLabel, gbc);

        gbc.gridy++;
        mainPanel.add(roundsLabel, gbc);

        add(mainPanel);

        // Start first round
        startNewRound();

        // Event Listeners
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewRound();
            }
        });

        setVisible(true);
    }

    private void startNewRound() {
        randomNumber = new Random().nextInt(100) + 1; // 1 to 100
        attemptsLeft = maxAttempts;
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        messageLabel.setText("Guess a number between 1 and 100");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attemptsLeft--;

            if (userGuess == randomNumber) {
                messageLabel.setText("Correct! The number was " + randomNumber);
                roundsWon++;
                roundsLabel.setText("Rounds won: " + roundsWon);
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            } else if (userGuess < randomNumber) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }

            attemptsLabel.setText("Attempts left: " + attemptsLeft);

            if (attemptsLeft <= 0 && userGuess != randomNumber) {
                messageLabel.setText("Out of attempts! The number was " + randomNumber);
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}
