import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame {
    double usDollar = 1.161;
    double nigerianNaira = 476.57;
    double brazilianReal = 5.47;
    double canadianDollar = 1.71;
    double kenyanShilling = 132.53;
    double indonesianRupiah = 19554.94;
    double indianRupee = 96.21;
    double philippinePeso = 71.17;
    double pakistaniRupee = 162.74;

    String[] currencyUnits = {
        "Units",
        "US Dollar ($)",
        "Nigerian Naira (₦)",
        "Brazilian Real (R$)",
        "Canadian Dollar (C$)",
        "Kenyan Shilling (KSh)",
        "Indonesian Rupiah (Rp)",
        "Indian Rupee (Rs.)",
        "Philippine Peso (₱)",
        "Pakistani Rupee (₨)"
    };

    private JPanel jPanel1;
    private JLabel jLabel1;
    private JComboBox<String> firstCountry;
    private JComboBox<String> secondCountry;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField t1;
    private JTextField t2;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel secondCurrencyUnit;
    private JLabel firstCurrencyUnit;

    public CurrencyConverter() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        firstCountry = new JComboBox<>();
        secondCountry = new JComboBox<>();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        t1 = new JTextField();
        t2 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        secondCurrencyUnit = new JLabel();
        firstCurrencyUnit = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 5));
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 24));
        jLabel1.setText("Currency Converter");

        firstCountry.setFont(new Font("Tahoma", Font.BOLD, 18));
        firstCountry.setModel(new DefaultComboBoxModel<>(currencyUnits));
        firstCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                firstCountryActionPerformed(evt);
            }
        });

        secondCountry.setFont(new Font("Tahoma", Font.BOLD, 18));
        secondCountry.setModel(new DefaultComboBoxModel<>(currencyUnits));
        secondCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                secondCountryActionPerformed(evt);
            }
        });

        jLabel2.setText("Amount:");
        jLabel3.setText("Converted:");

        jButton1.setText("Convert");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                convertCurrency(evt);
            }
        });

        // Layout setup
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(firstCountry)
                .addComponent(secondCountry)
                .addComponent(t1)
                .addComponent(t2)
                .addComponent(jButton1))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addComponent(firstCountry)
            .addComponent(secondCountry)
            .addComponent(jLabel2)
            .addComponent(t1)
            .addComponent(jButton1)
            .addComponent(jLabel3)
            .addComponent(t2)
        );

        add(jPanel1);
        pack();
        setVisible(true);
    }

    private void firstCountryActionPerformed(ActionEvent evt) {
        // Update the first currency label based on the selection
        firstCurrencyUnit.setText((String) firstCountry.getSelectedItem());
    }

    private void secondCountryActionPerformed(ActionEvent evt) {
        // Update the second currency label based on the selection
        secondCurrencyUnit.setText((String) secondCountry.getSelectedItem());
    }

    private void convertCurrency(ActionEvent evt) {
        try {
            double amount = Double.parseDouble(t1.getText());
            double conversionRate = getConversionRate((String) firstCountry.getSelectedItem(), (String) secondCountry.getSelectedItem());
            double convertedAmount = amount * conversionRate;
            t2.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        double fromRate = getCurrencyRate(fromCurrency);
        double toRate = getCurrencyRate(toCurrency);
        return toRate / fromRate;
    }

    private double getCurrencyRate(String currency) {
        switch (currency) {
            case "US Dollar ($)":
                return usDollar;
            case "Nigerian Naira (₦)":
                return nigerianNaira;
            case "Brazilian Real (R$)":
                return brazilianReal;
            case "Canadian Dollar (C$)":
                return canadianDollar;
            case "Kenyan Shilling (KSh)":
                return kenyanShilling;
            case "Indonesian Rupiah (Rp)":
                return indonesianRupiah;
            case "Indian Rupee (₹)":
                return indianRupee;
            case "Philippine Peso (₱)":
                return philippinePeso;
            case "Pakistani Rupee (₨)":
                return pakistaniRupee;
            default:
                return 1; // Default to 1 for unknown currencies
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
 