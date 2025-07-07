import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {

    private JComboBox<String> baseCurrencyBox, targetCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    private Map<String, Double> exchangeRates;
    private Map<String, String> currencySymbols;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(460, 340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Complete exchange rates for all pairs (approximate values)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD_USD", 1.0);
        exchangeRates.put("USD_INR", 82.0);
        exchangeRates.put("USD_EUR", 0.91);
        exchangeRates.put("USD_GBP", 0.80);
        exchangeRates.put("USD_JPY", 145.0);
        exchangeRates.put("USD_AUD", 1.47);
        exchangeRates.put("USD_CAD", 1.36);

        exchangeRates.put("INR_USD", 0.012);
        exchangeRates.put("INR_INR", 1.0);
        exchangeRates.put("INR_EUR", 0.011);
        exchangeRates.put("INR_GBP", 0.0098);
        exchangeRates.put("INR_JPY", 1.77);
        exchangeRates.put("INR_AUD", 0.018);
        exchangeRates.put("INR_CAD", 0.017);

        exchangeRates.put("EUR_USD", 1.10);
        exchangeRates.put("EUR_INR", 90.0);
        exchangeRates.put("EUR_EUR", 1.0);
        exchangeRates.put("EUR_GBP", 0.88);
        exchangeRates.put("EUR_JPY", 159.0);
        exchangeRates.put("EUR_AUD", 1.62);
        exchangeRates.put("EUR_CAD", 1.50);

        exchangeRates.put("GBP_USD", 1.25);
        exchangeRates.put("GBP_INR", 102.0);
        exchangeRates.put("GBP_EUR", 1.13);
        exchangeRates.put("GBP_GBP", 1.0);
        exchangeRates.put("GBP_JPY", 180.0);
        exchangeRates.put("GBP_AUD", 1.83);
        exchangeRates.put("GBP_CAD", 1.70);

        exchangeRates.put("JPY_USD", 0.0069);
        exchangeRates.put("JPY_INR", 0.56);
        exchangeRates.put("JPY_EUR", 0.0063);
        exchangeRates.put("JPY_GBP", 0.0056);
        exchangeRates.put("JPY_JPY", 1.0);
        exchangeRates.put("JPY_AUD", 0.010);
        exchangeRates.put("JPY_CAD", 0.0095);

        exchangeRates.put("AUD_USD", 0.68);
        exchangeRates.put("AUD_INR", 56.0);
        exchangeRates.put("AUD_EUR", 0.62);
        exchangeRates.put("AUD_GBP", 0.55);
        exchangeRates.put("AUD_JPY", 98.0);
        exchangeRates.put("AUD_AUD", 1.0);
        exchangeRates.put("AUD_CAD", 0.93);

        exchangeRates.put("CAD_USD", 0.74);
        exchangeRates.put("CAD_INR", 60.0);
        exchangeRates.put("CAD_EUR", 0.67);
        exchangeRates.put("CAD_GBP", 0.59);
        exchangeRates.put("CAD_JPY", 105.0);
        exchangeRates.put("CAD_AUD", 1.08);
        exchangeRates.put("CAD_CAD", 1.0);

        // Currency symbols map
        currencySymbols = new HashMap<>();
        currencySymbols.put("USD", "$");
        currencySymbols.put("INR", "₹");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("GBP", "£");
        currencySymbols.put("JPY", "¥");
        currencySymbols.put("AUD", "A$");
        currencySymbols.put("CAD", "C$");

        String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD"};

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font resultFont = new Font("Arial", Font.BOLD, 16);

        JLabel amountLabel = new JLabel(" Enter Amount:");
        amountLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);

        amountField = new JTextField(15);
        gbc.gridx = 1;
        add(amountField, gbc);

        JLabel baseLabel = new JLabel("From Currency:");
        baseLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(baseLabel, gbc);

        baseCurrencyBox = new JComboBox<>(currencies);
        baseCurrencyBox.setFont(labelFont);
        gbc.gridx = 1;
        add(baseCurrencyBox, gbc);

        JLabel targetLabel = new JLabel("To Currency:");
        targetLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(targetLabel, gbc);

        targetCurrencyBox = new JComboBox<>(currencies);
        targetCurrencyBox.setFont(labelFont);
        gbc.gridx = 1;
        add(targetCurrencyBox, gbc);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(convertButton, gbc);

        resultLabel = new JLabel("Converted amount will appear here.");
        resultLabel.setFont(resultFont);
        resultLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(resultLabel, gbc);

        convertButton.addActionListener(e -> convertCurrency());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            String base = (String) baseCurrencyBox.getSelectedItem();
            String target = (String) targetCurrencyBox.getSelectedItem();

            if (base.equals(target)) {
                resultLabel.setText("Base and target currencies cannot be the same.");
                return;
            }

            String key = base + "_" + target;
            Double rate = exchangeRates.get(key);

            if (rate == null) {
                resultLabel.setText("Exchange rate not available for selected currencies.");
                return;
            }

            double converted = amount * rate;

            String baseSymbol = currencySymbols.getOrDefault(base, "");
            String targetSymbol = currencySymbols.getOrDefault(target, "");

            // Show result with only currency symbols
            resultLabel.setText(String.format("%s%.2f = %s%.2f",
                    baseSymbol, amount,
                    targetSymbol, converted));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid numeric amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
