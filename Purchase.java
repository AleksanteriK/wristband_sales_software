package project;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;

class Purchase
{
    public ArrayList<Ticket> tickets = new ArrayList<>();
    public String purchase_number_file_name = "ostokset_maara.txt";
    public String receipt_file_name = "kuitti.txt";
    public String daysales_file_name = "paivanmyynnit.txt";
    public File purchase_number_file;
    public File receipt_file;
    public File daysales_file;
    String total_price_without_alv;
    String tax_from_total_price;

    Purchase(double total_price, ArrayList<Ticket> pending_tickets, Totalsales total_sales)
    {
        this.total_price = total_price;

        for(int i = 0; i < pending_tickets.size(); i++)
        {
            tickets.add(pending_tickets.get(i));
        }

        total_sales.addTo_sales(total_price);
        time_of_purchase = LocalTime.now();
    }
    
    //tällä metodilla saadaan siis ostoksen numero (kinda id) päivitettyä
    //tän ostosluokan propertyyn purchase_number, kyseinen löytyy siis tosta
    //tekstitiedostosta josta se lukee sen ja assignaa sen
    String setPurchase_number()
    {
        try
        {
            purchase_number_file = new File(purchase_number_file_name);

            if(!purchase_number_file.exists())
            {

                try
                {
                    purchase_number_file.createNewFile();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    return "error creating the file";
                }
            }

            purchase_number_file_scanner = new Scanner(purchase_number_file);

            if(purchase_number_file_scanner.hasNext())
            {
                String content = purchase_number_file_scanner.next();

                try
                {
                    purchase_number = Integer.parseInt(content);
                } 
                catch(NumberFormatException e)
                {
                    purchase_number_file_scanner.close();
                    return "Error reading number from file";
                }
            } 
            
            else
            {
                purchase_number_file_scanner.close();
                return "file empty";
            }

            purchase_number_file_scanner.close();
        } 
        
        catch(FileNotFoundException e)
        {
            e.printStackTrace(); // or log the exception
            return "file not found";
        }

        return "method was called"; //temp
    }

    int getPurchase_number()
    {
        return purchase_number;
    }

    void clearTotal_price()
    {
        total_price = 0;
    }

    //tällä metodilla taas sitten se uus seuraava ostosnumero päivitetään tiedostoon
    void updatePurchase_number() throws IOException
    {
        next_purchase_number = purchase_number + 1;
        purchase_number_file = new File(purchase_number_file_name);
    
        //tiedoston olemassaolochecki
        if(!purchase_number_file.exists())
        {
            try
            {
                purchase_number_file.createNewFile();
            } 

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    
        //'false' enablee päällekirjotuksen
        writer = new FileWriter(purchase_number_file, false);
        writer.write(Integer.toString(next_purchase_number));
        writer.close();
    }

    void printReceipt() throws IOException
    {   //päivitetään ostopäivämäärä, ja tätä arvoa verrataan sitten
        //ajankohtaiseen päivämäärään kun päivitetään päivän myyntitiedostoa
        buydate = LocalDate.now();
        receipt_file = new File(receipt_file_name);
        total_price_without_alv = simplified.format(total_price * 0.90);
        tax_from_total_price = simplified.format(total_price / 10);

        if(receipt_file.createNewFile())
        {
            System.out.println("File created");
            //'false' enablee päällekirjotuksen
            receipt_writer = new FileWriter(receipt_file_name, false);
        } 
        
        else
        {
            //'false' enablee päällekirjotuksen
            receipt_writer = new FileWriter(receipt_file_name, false);
        }

        try
        {
            buytime = LocalDateTime.now();
            receipt_writer.write("Kuitti_" + purchase_number + " / Receipt_" + purchase_number);
            receipt_writer.write("\r\n");
            receipt_writer.write("Huvipuisto / Amusement Park");
            receipt_writer.write("\r\n");
            receipt_writer.write(buytime.format(dtf));
            receipt_writer.write("\r\n");
            receipt_writer.write("Maksettu / Paid total: " + Double.toString(total_price) + "€");
            receipt_writer.write("\r\n");
            receipt_writer.write("\r\n");

            for(int i = 0; i < tickets.size(); i++)
            {
                receipt_writer.write(tickets.get(i).ticket_toString());
                receipt_writer.write("\r\n");
            }

            receipt_writer.write("\r\n");
            receipt_writer.write("Alv %   ");
            receipt_writer.write("Veroton   ");
            receipt_writer.write("Vero   ");
            receipt_writer.write("Summa   ");
            receipt_writer.write("\r\n");
            receipt_writer.write("10 %   ");
            receipt_writer.write(total_price_without_alv + "€    ");
            receipt_writer.write(tax_from_total_price + "€    ");
            receipt_writer.write(total_price + "€    ");
        } 
        
        finally
        {
            receipt_writer.close();
        }
    }

    void update_Day_sales(ArrayList<Ticket> shoppingcart_tickets) throws IOException {
        daysales_file = new File(daysales_file_name);
        //haetaan tässä kohtaa jo tieto onko päivä vaihtunut, jotta try catch ja tiedosto
        //luku saadaan tehtyy järkevästi
        boolean has_day_changed = hasDayChanged();
        BufferedWriter daysales_writer = null;
    
        try
        {
            daysales_writer = new BufferedWriter(new FileWriter(daysales_file, !has_day_changed));

            if (has_day_changed)
            {
                daysales_writer.write("Päivän Myynnit:");
                daysales_writer.write("\r\n");
                daysales_writer.write(buydate.format(daysales_date_format));
                daysales_writer.write("\r\n");
                daysales_writer.write("\r\n");
                daysales_writer.write("Ostoaika: ");
                daysales_writer.write(time_of_purchase.format(time_format));
                daysales_writer.write("\r\n");
                daysales_writer.write("Ostos_ID: " + Integer.toString(purchase_number));
                daysales_writer.write("\r\n");

                for (int i = 0; i < shoppingcart_tickets.size(); i++) 
                {
                    daysales_writer.write(shoppingcart_tickets.get(i).ticket_toString());
                    daysales_writer.write("\r\n");
                    daysales_writer.write(shoppingcart_tickets.get(i).ticket_Getnumber_and_name());
                    daysales_writer.write("\r\n");
                    daysales_writer.write("\r\n");
                }
            }

            else 
            {
                //Alottaa uudelta riviltä jos päivä ei ole vaihtunu
                daysales_writer.write("\r\n");
                daysales_writer.write("Ostoaika: ");
                daysales_writer.write(time_of_purchase.format(time_format));
                daysales_writer.write("\r\n");
                daysales_writer.write("Ostos_ID: " + Integer.toString(purchase_number));
                daysales_writer.write("\r\n");

                for (int i = 0; i < shoppingcart_tickets.size(); i++) {
                    daysales_writer.write(shoppingcart_tickets.get(i).ticket_toString());
                    daysales_writer.write("\r\n");
                    daysales_writer.write(shoppingcart_tickets.get(i).ticket_Getnumber_and_name());
                    daysales_writer.write("\r\n");
                    daysales_writer.write("\r\n");
                }
            }
    
        } 
        
        finally 
        {
            daysales_writer.close();
        }
    }

    //kutsutaan update_Day_sales metodissa
    boolean hasDayChanged() {
        daysales_file = new File(daysales_file_name);
    
        if (!daysales_file.exists()) 
        {
            return true;
        }
    
        BufferedReader date_reader = null;
    
        try {
            date_reader = new BufferedReader(new FileReader(daysales_file));
            //skippaa ekan rivin, jotta lukee pvm oikeesta kohtaa
            date_reader.readLine();
            String date_string = date_reader.readLine();
    
            if (date_string != null && !date_string.isEmpty()) 
            {
                LocalDate stored_date = LocalDate.parse(date_string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                //vertaa päivämääriä ja palauttaa joko false tai true
                return !stored_date.equals(LocalDate.now());
            }
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        
        finally 
        {
            if (date_reader != null) 
            {
                try {
                    date_reader.close();
                } 
                
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return false; //default
    }

    private LocalTime time_of_purchase;
    private LocalDate buydate;
    private LocalDateTime buytime;
    private DateTimeFormatter time_format = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter daysales_date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private DecimalFormat simplified = new DecimalFormat("#.####");
    private int purchase_number;
    private double total_price;
    private int next_purchase_number;
    private FileWriter writer;
    private FileWriter receipt_writer;
    private Scanner purchase_number_file_scanner;
}