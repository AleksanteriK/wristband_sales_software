package project;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.GridLayout;

public class Main extends JFrame
{
    public Main()
    {
        setSize(1920, 1080);
        setTitle("Shop Window");

        Shoppingcart shoppingcart = new Shoppingcart();
        shoppingcart.setHasNormalTicket(false);
        shoppingcart.setHasChildTicket(false);
        shoppingcart.setHasDiscountTicket(false);
        Totalsales total_sales = new Totalsales();

        final double NORMAL_PRICE = 26.00;
        final double CHILD_PRICE = 16.00;
        final double DISCOUNT_PRICE = 20.00;

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));
        JPanel buyPanel = new JPanel(new GridLayout(4, 1));
        JPanel normalPanel = new JPanel(new GridLayout(2, 1));
        JButton normalButton = new JButton("Normaali");
        JPanel normalButtonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel emptyNormalPanel = new JPanel();
        JTextField normalNumberField = new JTextField(3);
        JButton normalReduceButton = new JButton("-");
        JButton normalAddButton = new JButton("+");
        JPanel childPanel = new JPanel(new GridLayout(2, 1));
        JButton childButton = new JButton("Lapsi");
        JPanel childButtonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel emptyChildPanel = new JPanel();
        JTextField childNumberField = new JTextField(3);
        JButton childReduceButton = new JButton("-");
        JButton childAddButton = new JButton("+");
        JPanel discountPanel = new JPanel(new GridLayout(2, 1));
        JButton discountButton = new JButton("Alennus");
        JPanel discountButtonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel emptyDiscountPanel = new JPanel();
        JTextField discountNumberField = new JTextField(3);
        JButton discountReduceButton = new JButton("-");
        JButton discountAddButton = new JButton("+");
        JPanel firstPanel = new JPanel(new GridLayout(4, 1));
        JPanel emptyfirstPanel1 = new JPanel();
        JPanel childNamePanel = new JPanel(new GridLayout(4, 1));
        JLabel childNameLabel = new JLabel("Lapsen nimi:");
        JTextField childNameField = new JTextField(15);
        JCheckBox childNameLockCheckBox = new JCheckBox("Lukitse");
        JPanel emptyChildNamePanel = new JPanel();
        JPanel discountTypePanel = new JPanel(new GridLayout(4, 1));
        JLabel discountTypeLabel = new JLabel("Alennusryhmä:");
        String discountTypeList[] = {"-Valitse-", "Opiskelija", "Varusmies", "Eläkeläinen", "Kampanja", "Extra"};
        final JComboBox<String> discountTypeBox = new JComboBox<String>(discountTypeList);
        JPanel emptyDiscountTypePanel1 = new JPanel();
        JPanel emptyDiscountTypePanel2 = new JPanel();
        JPanel emptyfirstPanel2 = new JPanel();
        JPanel secondPanel = new JPanel(new GridLayout(4, 1));
        JPanel emptySecondPanel1 = new JPanel();
        JPanel childPhonePanel = new JPanel(new GridLayout(4, 1));
        JLabel childPhoneLabel = new JLabel("Huoltajan puh:");
        JTextField childPhoneField = new JTextField(15);
        JCheckBox childPhoneLockCheckBox = new JCheckBox("Lukitse");
        JPanel emptyChildPhonePanel = new JPanel();
        JPanel emptySecondPanel2 = new JPanel();
        JPanel emptySecondPanel3 = new JPanel();
        JPanel thirdPanel = new JPanel(new GridLayout(4, 1));
        JPanel emptyThirdPanel1 = new JPanel();
        JPanel emptyThirdPanel2 = new JPanel();
        JPanel emptyThirdPanel3 = new JPanel();
        JPanel cancelPanel = new JPanel();
        JButton cancelButton = new JButton("X");
        JPanel shoppingcartPanel = new JPanel(new GridLayout(3, 1));
        JLabel checkoutLabel = new JLabel("Ostoskori");
        JLabel totalPriceLabel = new JLabel("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");
        JButton buyButton = new JButton("Maksa");

        normalNumberField.setText("1");
        childNumberField.setText("1");
        discountNumberField.setText("1");

        add(mainPanel);

            mainPanel.add(buyPanel);

                buyPanel.add(normalPanel);

                    normalPanel.add(normalButton);

                    normalButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                int numFieldNum = Integer.parseInt(normalNumberField.getText());
                                shoppingcart.setNormalTicketAmount(numFieldNum);

                                if(shoppingcart.getHasNormalTicket() == true)
                                {
                                    for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                    {
                                        if(shoppingcart.pending_tickets.get(i).getType() == "Normaali")
                                        {
                                            shoppingcart.pending_tickets.get(i).addAmount(shoppingcart.getNormalTicketAmount());
                                            shoppingcart.pending_tickets.get(i).addPrice(shoppingcart.getNormalTicketAmount() * NORMAL_PRICE);
                                        }
                                    }
                                }
                                else
                                {
                                    Ticket normalTicket = new Ticket();
                                    normalTicket.setType("Normaali");
                                    normalTicket.setAmount(shoppingcart.getNormalTicketAmount());
                                    normalTicket.setPrice(shoppingcart.getNormalTicketAmount() * NORMAL_PRICE);
                                    shoppingcart.pending_tickets.add(normalTicket);                                    
                                }

                                shoppingcart.setHasNormalTicket(true);
                                shoppingcart.setNormalTicketAmount(1);
                                normalNumberField.setText("1");

                                shoppingcart.count_Total_price();
                                totalPriceLabel.setText("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");

                                checkoutLabel.setText("<html>Ostoskori<br/><br/>");
                                    for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                    {
                                        checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getType() + " ");

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Lapsi")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + "(" + shoppingcart.pending_tickets.get(i).getCustomer_name() + " " + shoppingcart.pending_tickets.get(i).getPhone_number() + ")" + " ");
                                        }

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Alennus")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getDiscountType() +" ");
                                        }

                                        checkoutLabel.setText(checkoutLabel.getText() +  shoppingcart.pending_tickets.get(i).getAmount() + " " + shoppingcart.pending_tickets.get(i).getPrice() + "<br/>");
                                    }
                                    checkoutLabel.setText(checkoutLabel.getText() + "</html>");
                            }
                        }
                    );

                    normalPanel.add(normalButtonsPanel);

                        normalButtonsPanel.add(emptyNormalPanel);

                        normalButtonsPanel.add(normalNumberField);

                        normalButtonsPanel.add(normalReduceButton);

                        normalReduceButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(Integer.parseInt(normalNumberField.getText()) > 1)
                                    {
                                        Integer numFieldString = Integer.parseInt(normalNumberField.getText()) - 1;
                                        normalNumberField.setText(numFieldString.toString());
                                    }
                                }
                            }
                        );

                        normalButtonsPanel.add(normalAddButton);

                        normalAddButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    Integer numFieldString = Integer.parseInt(normalNumberField.getText()) + 1;
                                    normalNumberField.setText(numFieldString.toString());
                                }
                            }
                        );

                buyPanel.add(childPanel);

                    childPanel.add(childButton);

                    childButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                if(childNameField.getText().isEmpty() == false
                                && childPhoneField.getText().isEmpty() == false)
                                {
                                    int numFieldNum = Integer.parseInt(childNumberField.getText());
                                    shoppingcart.setChildTicketAmount(numFieldNum);

                                    if(shoppingcart.getHasChildTicket() == true)
                                    {
                                        shoppingcart.setTicketFound(false);

                                        for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                        {
                                            if(shoppingcart.pending_tickets.get(i).getType() == "Lapsi"
                                            && shoppingcart.pending_tickets.get(i).getCustomer_name() == childNameField.getText()
                                            && shoppingcart.pending_tickets.get(i).getPhone_number() == childPhoneField.getText())
                                            {
                                                shoppingcart.pending_tickets.get(i).addAmount(shoppingcart.getChildTicketAmount());
                                                shoppingcart.pending_tickets.get(i).addPrice(shoppingcart.getChildTicketAmount() * CHILD_PRICE);
                                                shoppingcart.setTicketFound(true);
                                            }
                                        }

                                        if(shoppingcart.getTicketFound() == false)
                                        {
                                            Ticket childTicket = new Ticket();
                                            childTicket.setType("Lapsi");
                                            childTicket.setAmount(shoppingcart.getChildTicketAmount());
                                            childTicket.setPrice(shoppingcart.getChildTicketAmount() * CHILD_PRICE);
                                            childTicket.setCustomer_name(childNameField.getText());
                                            childTicket.setPhone_number(childPhoneField.getText());
                                            shoppingcart.pending_tickets.add(childTicket);
                                        }
                                    }
                                    else
                                    {
                                        Ticket childTicket = new Ticket();
                                        childTicket.setType("Lapsi");
                                        childTicket.setAmount(shoppingcart.getChildTicketAmount());
                                        childTicket.setPrice(shoppingcart.getChildTicketAmount() * CHILD_PRICE);
                                        childTicket.setCustomer_name(childNameField.getText());
                                        childTicket.setPhone_number(childPhoneField.getText());
                                        shoppingcart.pending_tickets.add(childTicket);
                                    }

                                    shoppingcart.setHasChildTicket(true);
                                    shoppingcart.setChildTicketAmount(1);
                                    childNumberField.setText("1");

                                    shoppingcart.count_Total_price();
                                    totalPriceLabel.setText("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");

                                    checkoutLabel.setText("<html>Ostoskori<br/><br/>");
                                    for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                    {
                                        checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getType() + " ");

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Lapsi")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + "(" + shoppingcart.pending_tickets.get(i).getCustomer_name() + " " + shoppingcart.pending_tickets.get(i).getPhone_number() + ")" + " ");
                                        }

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Alennus")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getDiscountType() +" ");
                                        }

                                        checkoutLabel.setText(checkoutLabel.getText() +  shoppingcart.pending_tickets.get(i).getAmount() + " " + shoppingcart.pending_tickets.get(i).getPrice() + "<br/>");
                                    }
                                    checkoutLabel.setText(checkoutLabel.getText() + "</html>");

                                    if(childNameLockCheckBox.isSelected() == false)
                                    {
                                        childNameField.setText("");
                                    }

                                    if(childPhoneLockCheckBox.isSelected() == false)
                                    {
                                        childPhoneField.setText("");
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(mainPanel, "Muista lisätä lapsen nimi ja huoltajan puhelinnumero", "Muistutus",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    );

                    childPanel.add(childButtonsPanel);

                        childButtonsPanel.add(emptyChildPanel);

                        childButtonsPanel.add(childNumberField);

                        childButtonsPanel.add(childReduceButton);

                        childReduceButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(Integer.parseInt(childNumberField.getText()) > 1)
                                    {
                                        Integer numFieldString = Integer.parseInt(childNumberField.getText()) - 1;
                                        childNumberField.setText(numFieldString.toString());
                                    }
                                }
                            }
                        );

                        childButtonsPanel.add(childAddButton);

                        childAddButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    Integer numFieldString = Integer.parseInt(childNumberField.getText()) + 1;
                                    childNumberField.setText(numFieldString.toString());
                                }
                            }
                        );

                buyPanel.add(discountPanel);

                    discountPanel.add(discountButton);

                    discountButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                int numFieldNum = Integer.parseInt(discountNumberField.getText());
                                shoppingcart.setDiscountTicketAmount(numFieldNum);

                                if(shoppingcart.getHasDiscountTicket() == true)
                                {
                                    shoppingcart.setTicketFound(false);

                                    for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                    {
                                        if(shoppingcart.pending_tickets.get(i).getType() == "Alennus"
                                        && shoppingcart.pending_tickets.get(i).getDiscountType() == discountTypeBox.getSelectedItem().toString())
                                        {
                                            shoppingcart.pending_tickets.get(i).addAmount(shoppingcart.getDiscountTicketAmount());
                                            shoppingcart.pending_tickets.get(i).addPrice(shoppingcart.getDiscountTicketAmount() * DISCOUNT_PRICE);
                                            shoppingcart.setTicketFound(true);
                                        }
                                    }

                                    if(shoppingcart.getTicketFound() == false)
                                    {
                                        Ticket discountTicket = new Ticket();
                                        discountTicket.setType("Alennus");
                                        discountTicket.setDiscountType(discountTypeBox.getSelectedItem().toString());
                                        discountTicket.setAmount(shoppingcart.getDiscountTicketAmount());
                                        discountTicket.setPrice(shoppingcart.getDiscountTicketAmount() * DISCOUNT_PRICE);
                                        shoppingcart.pending_tickets.add(discountTicket);
                                    }
                                }
                                else
                                {
                                    Ticket discountTicket = new Ticket();
                                    discountTicket.setType("Alennus");
                                    discountTicket.setDiscountType(discountTypeBox.getSelectedItem().toString());
                                    discountTicket.setAmount(shoppingcart.getDiscountTicketAmount());
                                    discountTicket.setPrice(shoppingcart.getDiscountTicketAmount() * DISCOUNT_PRICE);
                                    shoppingcart.pending_tickets.add(discountTicket);
                                }

                                shoppingcart.setHasDiscountTicket(true);
                                shoppingcart.setDiscountTicketAmount(1);
                                discountNumberField.setText("1");

                                shoppingcart.count_Total_price();
                                totalPriceLabel.setText("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");

                                checkoutLabel.setText("<html>Ostoskori<br/><br/>");
                                    for(int i = 0; i < shoppingcart.pending_tickets.size(); i++)
                                    {
                                        checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getType() + " ");

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Lapsi")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + "(" + shoppingcart.pending_tickets.get(i).getCustomer_name() + " " + shoppingcart.pending_tickets.get(i).getPhone_number() + ")" + " ");
                                        }

                                        if(shoppingcart.pending_tickets.get(i).getType() == "Alennus")
                                        {
                                            checkoutLabel.setText(checkoutLabel.getText() + shoppingcart.pending_tickets.get(i).getDiscountType() +" ");
                                        }

                                        checkoutLabel.setText(checkoutLabel.getText() +  shoppingcart.pending_tickets.get(i).getAmount() + " " + shoppingcart.pending_tickets.get(i).getPrice() + "<br/>");
                                    }
                                    checkoutLabel.setText(checkoutLabel.getText() + "</html>");
                            }
                        }
                    );

                    discountPanel.add(discountButtonsPanel);

                        discountButtonsPanel.add(emptyDiscountPanel);

                        discountButtonsPanel.add(discountNumberField);

                        discountButtonsPanel.add(discountReduceButton);

                        discountReduceButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(Integer.parseInt(discountNumberField.getText()) > 1)
                                    {
                                        Integer numFieldString = Integer.parseInt(discountNumberField.getText()) - 1;
                                        discountNumberField.setText(numFieldString.toString());
                                    }
                                }
                            }
                        );

                        discountButtonsPanel.add(discountAddButton);

                        discountAddButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    Integer numFieldString = Integer.parseInt(discountNumberField.getText()) + 1;
                                    discountNumberField.setText(numFieldString.toString());
                                }
                            }
                        );

            mainPanel.add(firstPanel);

                firstPanel.add(emptyfirstPanel1);

                firstPanel.add(childNamePanel);

                    childNamePanel.add(childNameLabel);

                    childNamePanel.add(childNameField);

                    childNamePanel.add(childNameLockCheckBox);

                    childNamePanel.add(emptyChildNamePanel);

                firstPanel.add(discountTypePanel);

                    discountTypePanel.add(discountTypeLabel);

                    discountTypePanel.add(discountTypeBox);

                    discountTypePanel.add(emptyDiscountTypePanel1);

                    discountTypePanel.add(emptyDiscountTypePanel2);

                firstPanel.add(emptyfirstPanel2);

            mainPanel.add(secondPanel);

                secondPanel.add(emptySecondPanel1);

                secondPanel.add(childPhonePanel);

                    childPhonePanel.add(childPhoneLabel);

                    childPhonePanel.add(childPhoneField);

                    childPhonePanel.add(childPhoneLockCheckBox);

                    childPhonePanel.add(emptyChildPhonePanel);

                secondPanel.add(emptySecondPanel2);

                secondPanel.add(emptySecondPanel3);

            mainPanel.add(thirdPanel);

                thirdPanel.add(emptyThirdPanel1);

                thirdPanel.add(emptyThirdPanel2);

                thirdPanel.add(emptyThirdPanel3);

                thirdPanel.add(cancelPanel);

                    cancelPanel.add(cancelButton);

                    cancelButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                shoppingcart.empty_Shopping_cart();
                                checkoutLabel.setText("Ostoskori");
                                totalPriceLabel.setText("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");
                            }
                        }
                    );

            mainPanel.add(shoppingcartPanel);

                shoppingcartPanel.add(checkoutLabel);
        
                shoppingcartPanel.add(totalPriceLabel);

                shoppingcartPanel.add(buyButton);

                buyButton.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            // aktivoi maksupäätteen (popup window "odottaa maksua" jossa nappi maksa ja peruuta) ja tulostaa kuitin tilauksesta
                            int pay = JOptionPane.showConfirmDialog(mainPanel, "Odottaa maksua.");
                            if(pay == JOptionPane.YES_OPTION)
                            {
                                // tulostaa kuitin
                                Purchase purchase = new Purchase(shoppingcart.get_Total_price(), shoppingcart.pending_tickets, total_sales);

                                try
                                {
                                    purchase.setPurchase_number();
                                    purchase.updatePurchase_number();
                                    purchase.printReceipt();
                                    purchase.update_Day_sales(shoppingcart.pending_tickets);
                                    total_sales.update_Sales_state(purchase);
                                }

                                catch(IOException a)
                                {
                                    a.printStackTrace();
                                }

                                finally 
                                {
                                    purchase.clearTotal_price();
                                    shoppingcart.empty_Shopping_cart();
                                    checkoutLabel.setText("Ostoskori");
                                    totalPriceLabel.setText("Kokonaishinta: " + shoppingcart.get_Total_price() + "€");
                                }
                            }
                        }
                    }
                );
        

        setVisible(true);
    }

    public static void main (String[] args)
    {
        new Main();
    }
}