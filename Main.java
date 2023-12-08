package project;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

class Main
{
    public static void main (String[] args)
    {
        //luo ikkunan
        JFrame frame = new JFrame("Shop Interface");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //luo napit
        //normaali lippu
        JButton normalTicketButton = new JButton("Normaali lippu");
        frame.add(normalTicketButton);

        JTextField normalNumberField = new JTextField(3);
        frame.add(normalNumberField);

        JButton normalReduceButton = new JButton("-");
        frame.add(normalReduceButton);

        JButton normalAddButton = new JButton("+");
        frame.add(normalAddButton);


        //lasten lippu
        JButton childTicketButton = new JButton("Lasten lippu");
        frame.add(childTicketButton);

        JTextField childNumberField = new JTextField(3);
        frame.add(childNumberField);

        JButton childReduceButton = new JButton("-");
        frame.add(childReduceButton);

        JButton childAddButton = new JButton("+");
        frame.add(childAddButton);

        JLabel childNameLabel = new JLabel("Lapsen nimi:");
        frame.add(childNameLabel);

        JTextField childNameField = new JTextField(15);
        frame.add(childNameField);

        JCheckBox childNameLockCheckBox = new JCheckBox("Lukitse");
        frame.add(childNameLockCheckBox);

        JLabel childPhoneNumberLabel = new JLabel("Huoltajan puh:");
        frame.add(childPhoneNumberLabel);

        JTextField childPhoneNumberField = new JTextField(15);
        frame.add(childPhoneNumberField);

        JCheckBox childPhoneNumberLockCheckBox = new JCheckBox("Lukitse");
        frame.add(childPhoneNumberLockCheckBox);


        //alennus lippu
        JButton discountTicketButton = new JButton("Alennus lippu");
        frame.add(discountTicketButton);

        JTextField discountNumberField = new JTextField(3);
        frame.add(discountNumberField);

        JButton discountReduceButton = new JButton("-");
        frame.add(discountReduceButton);

        JButton discountAddButton = new JButton("+");
        frame.add(discountAddButton);

        JLabel discountTypeLabel = new JLabel("Alennusryhmä:");
        frame.add(discountTypeLabel);

        String discountTypeList[] = {"Opiskelija", "Varusmies", "Eläkeläinen"};
        final JComboBox<String> discountTypeBox = new JComboBox<String>(discountTypeList);
        frame.add(discountTypeBox);


        //ostoskori
        JLabel checkoutLabel = new JLabel("Ostoskori");
        frame.add(checkoutLabel);

        double total_price = 50.20;
        JLabel totalPriceLabel = new JLabel("Kokonaishinta: " + total_price + "€");
        frame.add(totalPriceLabel);

        JButton resetButton = new JButton("X");
        frame.add(resetButton);

        JButton buyButton = new JButton("Osta");
        frame.add(buyButton);


        //temp
        JLabel label = new JLabel("Text");
        frame.add(label);

        //nappien actionit
        normalTicketButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //temp
                    label.setText("BOOM!");
                }
            }
        );

        //tekee näkyväksi
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        //väliaikanen debug
        Purchase testi = new Purchase(10.01);

        try {
            testi.setPurchase_number();
            testi.updatePurchase_number();
            testi.PrintReceipt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}