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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame
{
    public Main()
    {
        setSize(1920, 1080);
        setTitle("Shop Window");

        Ticket normalTicket = null;
        int normalTicketAmmount = 1;
        final double NORMAL_PRICE = 26.00;

        JPanel mainPanel = new JPanel(new GridLayout(1, 5));
        add(mainPanel);

            JPanel buyPanel = new JPanel(new GridLayout(4, 1));
            mainPanel.add(buyPanel);

                JPanel normalPanel = new JPanel(new GridLayout(2, 1));
                buyPanel.add(normalPanel);

                    JButton normalButton = new JButton("Normaali");
                    normalPanel.add(normalButton);

                    normalButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                if(normalTicket == null)
                                {
                                    Ticket normalTicket = new Ticket();
                                    normalTicket.setAmount(normalTicketAmmount);
                                    normalTicket.setType("Normaali");
                                    normalTicket.setPrice(normalTicketAmmount * NORMAL_PRICE);
                                }
                                else
                                {
                                    int tempNormalTicketAmmount += normalTicketAmmount;
                                    normalTicket.setAmount(tempNormalTicketAmmount);
                                    normalTicket.setType("Normaali");
                                    normalTicket.setPrice(normalTicketAmmount * NORMAL_PRICE);
                                }
                            }
                        }
                    );

                    JPanel normalButtonsPanel = new JPanel(new GridLayout(1, 4));
                    normalPanel.add(normalButtonsPanel);

                        JPanel emptyNormalPanel = new JPanel();
                        normalButtonsPanel.add(emptyNormalPanel);

                        JTextField normalNumberField = new JTextField(3);
                        normalButtonsPanel.add(normalNumberField);

                        normalNumberField.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    int numFieldNum = Integer.parseInt(normalNumberField.getText());
                                    normalTicketAmmount = numFieldNum;
                                }
                            }
                        );

                        JButton normalReduceButton = new JButton("-");
                        normalButtonsPanel.add(normalReduceButton);

                        normalReduceButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(normalTicketAmmount > 1)
                                    {
                                        normalTicketAmmount -= 1;
                                    }
                                }
                            }
                        );

                        JButton normalAddButton = new JButton("+");
                        normalButtonsPanel.add(normalAddButton);

                        normalAddButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(normalTicketAmmount > 1)
                                    {
                                        normalTicketAmmount += 1;
                                    }
                                }
                            }
                        );

                JPanel childPanel = new JPanel(new GridLayout(2, 1));
                buyPanel.add(childPanel);

                    JButton childButton = new JButton("Lapsi");
                    childPanel.add(childButton);

                    childButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                // lisää lasten lippu ostoskoriin
                            }
                        }
                    );

                    JPanel childButtonsPanel = new JPanel(new GridLayout(1, 4));
                    childPanel.add(childButtonsPanel);

                        JPanel emptyChildPanel = new JPanel();
                        childButtonsPanel.add(emptyChildPanel);

                        JTextField childNumberField = new JTextField(3);
                        childButtonsPanel.add(childNumberField);

                        JButton childReduceButton = new JButton("-");
                        childButtonsPanel.add(childReduceButton);

                        childReduceButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    // -1 lasten lippu
                                }
                            }
                        );

                        JButton childAddButton = new JButton("+");
                        childButtonsPanel.add(childAddButton);

                        childAddButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    // +1 lasten lippu
                                }
                            }
                        );

                JPanel discountPanel = new JPanel(new GridLayout(2, 1));
                buyPanel.add(discountPanel);

                    JButton discountButton = new JButton("Alennus");
                    discountPanel.add(discountButton);

                    discountButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                // lisää alennus lippu ostoskoriin
                            }
                        }
                    );

                    JPanel discountButtonsPanel = new JPanel(new GridLayout(1, 4));
                    discountPanel.add(discountButtonsPanel);

                        JPanel emptyDiscountPanel = new JPanel();
                        discountButtonsPanel.add(emptyDiscountPanel);

                        JTextField discountNumberField = new JTextField(3);
                        discountButtonsPanel.add(discountNumberField);

                        JButton discountReduceButton = new JButton("-");
                        discountButtonsPanel.add(discountReduceButton);

                        discountButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    // -1 alennus lippu
                                }
                            }
                        );

                        JButton discountAddButton = new JButton("+");
                        discountButtonsPanel.add(discountAddButton);

                        discountButton.addActionListener(
                            new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    // +1 alennus lippu
                                }
                            }
                        );

            JPanel firstPanel = new JPanel(new GridLayout(4, 1));
            mainPanel.add(firstPanel);

                JPanel emptyfirstPanel1 = new JPanel();
                firstPanel.add(emptyfirstPanel1);

                JPanel childNamePanel = new JPanel(new GridLayout(4, 1));
                firstPanel.add(childNamePanel);

                    JLabel childNameLabel = new JLabel("Lapsen nimi:");
                    childNamePanel.add(childNameLabel);

                    JTextField childNameField = new JTextField(15);
                    childNamePanel.add(childNameField);

                    JCheckBox childNameLockCheckBox = new JCheckBox("Lukitse");
                    childNamePanel.add(childNameLockCheckBox);

                    JPanel emptyChildNamePanel = new JPanel();
                    childNamePanel.add(emptyChildNamePanel);

                JPanel discountTypePanel = new JPanel(new GridLayout(4, 1));
                firstPanel.add(discountTypePanel);

                    JLabel discountTypeLabel = new JLabel("Alennusryhmä:");
                    discountTypePanel.add(discountTypeLabel);

                    String discountTypeList[] = {"Opiskelija", "Varusmies", "Eläkeläinen", "Kampanja", "Extra"};
                    final JComboBox<String> discountTypeBox = new JComboBox<String>(discountTypeList);
                    discountTypePanel.add(discountTypeBox);

                    JPanel emptyDiscountTypePanel1 = new JPanel();
                    discountTypePanel.add(emptyDiscountTypePanel1);

                    JPanel emptyDiscountTypePanel2 = new JPanel();
                    discountTypePanel.add(emptyDiscountTypePanel2);

                JPanel emptyfirstPanel2 = new JPanel();
                firstPanel.add(emptyfirstPanel2);

            JPanel secondPanel = new JPanel(new GridLayout(4, 1));
            mainPanel.add(secondPanel);

                JPanel emptySecondPanel1 = new JPanel();
                secondPanel.add(emptySecondPanel1);

                JPanel childPhonePanel = new JPanel(new GridLayout(4, 1));
                secondPanel.add(childPhonePanel);

                    JLabel childPhoneLabel = new JLabel("Huoltajan puh:");
                    childPhonePanel.add(childPhoneLabel);

                    JTextField childPhoneField = new JTextField(15);
                    childPhonePanel.add(childPhoneField);

                    JCheckBox childPhoneLockCheckBox = new JCheckBox("Lukitse");
                    childPhonePanel.add(childPhoneLockCheckBox);

                    JPanel emptyChildPhonePanel = new JPanel();
                    childPhonePanel.add(emptyChildPhonePanel);

                JPanel emptySecondPanel2 = new JPanel();
                secondPanel.add(emptySecondPanel2);

                JPanel emptySecondPanel3 = new JPanel();
                secondPanel.add(emptySecondPanel3);

            JPanel thirdPanel = new JPanel(new GridLayout(4, 1));
            mainPanel.add(thirdPanel);

                JPanel emptyThirdPanel1 = new JPanel();
                thirdPanel.add(emptyThirdPanel1);

                JPanel emptyThirdPanel2 = new JPanel();
                thirdPanel.add(emptyThirdPanel2);

                JPanel emptyThirdPanel3 = new JPanel();
                thirdPanel.add(emptyThirdPanel3);

                JPanel cancelPanel = new JPanel();
                thirdPanel.add(cancelPanel);

                    JButton cancelButton = new JButton("X");
                    cancelPanel.add(cancelButton);

                    cancelButton.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                // poista koko tilaus ostoskorista
                            }
                        }
                    );

            JPanel shoppingcartPanel = new JPanel();
            mainPanel.add(shoppingcartPanel);

                JLabel checkoutLabel = new JLabel("Ostoskori");
                shoppingcartPanel.add(checkoutLabel);
        
                double total_price = 50.20;
                JLabel totalPriceLabel = new JLabel("Kokonaishinta: " + total_price + "€");
                shoppingcartPanel.add(totalPriceLabel);

                JButton buyButton = new JButton("Maksa");
                shoppingcartPanel.add(buyButton);

                buyButton.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            // aktivoi maksupäätteen (popup window "odottaa maksua" jossa nappi maksa ja peruuta) ja tulostaa kuitin tilauksesta
                        }
                    }
                );

        

        setVisible(true);
    }

    public static void main (String[] args)
    {
        /* ---- Declarations ---- */
        Shoppingcart shoppingcart = new Shoppingcart();
        Totalsales total_sales = new Totalsales();

        new Main();

        //väliaikanen debug
        //kokeilen jos noitten settereitten kautta lois tän tiketin
        //esim aina kun sit on valittu se myytävä lippu
        //niin se voi sit olla modulaarisempaa ehkä asettaa sille
        //settereitten kautta properteihin arvoja kun et kaikki
        //kerralla yhteen constructoriin
        //lähinnä välttäen sen ongelman esim et kun jos luo
        //sen lasten lipun johon tarvitaan myös nimi ja puhnro
        //niin sitä ei tarvii aina assignata sit myös normaaleille
        //lipuille. Toki senki vois myös säätää erikseen et
        //constructorilla on vaan tietyt parametrit
        //ja sit on setterit erikseen puhnrolle ja nimelle

        //ymmärsin näin kun luin muutaman päivän jälkeen uudestaan
        //sitä UI suunnitelmaa, niin tää amount tulee todennäkösesti
        //sitten siitä sen normaali, lapsi/alennus lipun napin kanssa
        //olevasta siitä määrästä, eli nääkin lippuoliot periaattees
        //voi sisältää useemman saman lipun kerralla.
        //mua ei ainakaan haittaa tää tyyli
        int amount = 3; //eli montako normaali tai lapsi tai alennus lippua
        String type = "normaali"; //tää tulee siitä napista varmaa kans (actionlistener)
        double price = 20;
        Ticket dummyticket = new Ticket(); //ei constructoria
        //tän tapahtuman jälkeen varmaan sit menee siihen ostoskorin arraylistiin nää
        dummyticket.setAmount(amount);
        dummyticket.setType(type);
        dummyticket.setPrice(price);
        shoppingcart.pending_tickets.add(dummyticket);
        //tän kutsun Count_total_price() voi joko lisätä esim Shoppingcartin omaan
        //printtimetodiin joka printtaa sen ostoskorin UI:hin esim
        shoppingcart.count_Total_price();
        //sit toi total_price tohon purchasen constructoriin tulee siitä ostoskorin
        //kokonaishinta muuttujasta
        Purchase testi = new Purchase(shoppingcart.total_price, shoppingcart.pending_tickets, total_sales);

        try {
            testi.setPurchase_number();
            testi.updatePurchase_number();
            testi.printReceipt();
            total_sales.update_Sales_state();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }

        /*Mul tuli myös sellanen idea et jos jaksetaan / halutaan
         * niin tehäänkö sellanen ominaisuus et näitten lippujen
         * hintoja pystyy käyttäjä muuttaan jollain "super"
         * oikeuksilla?
         */
    }
}