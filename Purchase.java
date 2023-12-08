package project;
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
    private double total_price;
    private int next_purchase_number;

    Purchase (double total_price) {
        this.total_price = total_price; 
    }
    //tällä metodilla saadaan siis ostoksen numero (kinda id) päivitettyä
    //tän ostosluokan propertyyn purchase_number, kyseinen löytyy siis tosta
    //tekstitiedostosta josta se lukee sen ja assignaa sen
    String setPurchase_number() {
        try {
            purchase_number_file = new File(purchase_number_file_name);

            //nyt myös luo ton tiedoston jos ei oo olemassa, unohtu aiemmin laittaa
            if (!purchase_number_file.exists()) {

                try {
                    purchase_number_file.createNewFile();
                }
                
                catch (IOException e) {
                    e.printStackTrace();
                    return "error creating the file";
                }
            }

            purchase_number_file_scanner = new Scanner(purchase_number_file);

            if (purchase_number_file_scanner.hasNext()) {
                String content = purchase_number_file_scanner.next();

                try {
                    purchase_number = Integer.parseInt(content);
                } 
                
                catch (NumberFormatException e) {
                    purchase_number_file_scanner.close();
                    return "Error reading number from file";
                }
            } 
            
            else {
                purchase_number_file_scanner.close();
                return "file empty";
            }

            purchase_number_file_scanner.close();
        } 
        
        catch (FileNotFoundException e) {
            e.printStackTrace(); // or log the exception
            return "file not found";
        }

        return "method was called"; //temp
    }

    //tällä metodilla taas sitten se uus seuraava ostosnumero päivitetään tiedostoon
    void updatePurchase_number() throws IOException {
        next_purchase_number = purchase_number + 1;
    
        purchase_number_file = new File(purchase_number_file_name);
    
        //tiedoston olemassaolochecki
        if (!purchase_number_file.exists()) {
            try {
                purchase_number_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        //'false' enablee päällekirjotuksen
        writer = new FileWriter(purchase_number_file, false);
        writer.write(Integer.toString(next_purchase_number));
        writer.close();
    }

    void PrintReceipt() throws IOException {
        receipt_file = new File(receipt_file_name);
        if (receipt_file.createNewFile()) {
            System.out.println("File created");
            //'false' enablee päällekirjotuksen
            receipt_writer = new FileWriter(receipt_file_name, false);
        } else {
            //'false' enablee päällekirjotuksen
            receipt_writer = new FileWriter(receipt_file_name, false);
        }

        try {
            buytime = LocalDateTime.now();
            receipt_writer.write("Kuitti / Receipt");
            receipt_writer.write("\r\n");
            receipt_writer.write("Huvipuisto / Amusement Park");
            receipt_writer.write("\r\n");
            receipt_writer.write(buytime.format(dtf));
            receipt_writer.write("\r\n");
            receipt_writer.write("Maksettu / Paid total:" + total_price);
            // kesken viel
        } finally {
            receipt_writer.close();
        }
    }

    //voidaan myös toteuttaa erikseen puchase olion luonti ilman vakioconstructoria,
    //eli luodaan esim ensin vaan pelkkä purchase olio ilman mitään parametrejä ja yks kerrallaan
    //lisätään properteihin settereitten kautta noi hinnat ja aika jne, tein kuitenki ton constructorin
    //valmiiks 

    //public Arraylist<Ticket> tickets;
    public String purchase_number_file_name = "ostokset_maara.txt";
    public String receipt_file_name = "kuitti.txt";
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public LocalDateTime buytime;
    public FileWriter writer;
    public FileWriter receipt_writer;
    public File purchase_number_file;
    public File receipt_file;
    public Scanner purchase_number_file_scanner;
}