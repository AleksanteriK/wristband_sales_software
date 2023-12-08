package wristband_sales_software;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class Purchase {
    private int purchase_number;
    private float total_price;
    private int next_purchase_number;
    //aattelin et toi vois olla se mist löytyy se ostosten määrä
    private String path_to_sales_amount = "ostokset_maara.txt";
    private String receipt_file_name = "kuitti.txt";

    Purchase (int purchase_number, float total_price) {
        this.purchase_number = purchase_number;
        this.total_price = total_price; 
    }

    String setPurchase_number () {
        try {
            sales_file = new File(path_to_sales_amount);
            sales_file_scanner = new Scanner(sales_file);

            if (sales_file_scanner.hasNext()) {
                String content = sales_file_scanner.next();

                try {
                    purchase_number = Integer.parseInt(content);
                }

                catch (NumberFormatException e) {
                    //tähän joku error tulostus UI:hin esim
                    sales_file_scanner.close();
                    return "Error reading number from file";
                }
            }

            else {
                //tähän kans jos case että tiedosto tyhjä
                sales_file_scanner.close();
                return "file empty";
            }

            sales_file_scanner.close();
        }

        catch (FileNotFoundException e) {
            //tähän kans error
            sales_file_scanner.close();
            return "file not found";
        }
    }

    void setNewAmountOfSales() {
        //laittaa uuden ostosnumeron, tän vois kutsua joko
        //ton setPurchasenumber sisällä tai jälkeen heti 
        next_purchase_number = purchase_number + 1;
        try {
            receipt_file = new File(receipt_file_name);
        
        try {
            writer.write(Integer.toString(next_purchase_number));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void PrintReceipt() {
        try {
            writer = new FileWriter(receipt_file_name, true);
            buytime = LocalDateTime.now();
            writer.write("Kuitti / Receipt");
            writer.write("\r\n");
            writer.write("Huvipuisto / Amusement Park");
            writer.write(buytime.format(dtf));
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //voidaan myös toteuttaa erikseen puchase olion luonti ilman vakioconstructoria,
    //eli luodaan esim ensin vaan pelkkä purchase olio ilman mitään parametrejä ja yks kerrallaan
    //lisätään properteihin settereitten kautta noi hinnat ja aika jne, tein kuitenki ton constructorin
    //valmiiks 

    public Arraylist<Ticket> tickets;
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public LocalDateTime buytime;
    public FileWriter writer;
    public File sales_file;
    public File receipt_file;
    public Scanner sales_file_scanner;
}