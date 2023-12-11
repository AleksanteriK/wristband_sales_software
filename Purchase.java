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
    public ArrayList<Ticket> tickets = new ArrayList<>();
    public String purchase_number_file_name = "ostokset_maara.txt";
    public String receipt_file_name = "kuitti.txt";
    public File purchase_number_file;
    public File receipt_file;

    Purchase (double total_price, ArrayList<Ticket> pending_tickets) {
        this.total_price = total_price;

        for (int i = 0; i < pending_tickets.size(); i++) {
            tickets.add(pending_tickets.get(i));
        }
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
            } 
            
            catch (IOException e) {
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
        } 
        
        else {
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
            receipt_writer.write("Maksettu / Paid total:" + Double.toString(total_price) + "e");
            receipt_writer.write("\r\n");
            receipt_writer.write("\r\n");

            for (int i = 0; i < tickets.size(); i++) {
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
            receipt_writer.write(Double.toString(total_price * 0.90) + "e    ");
            receipt_writer.write(Double.toString(total_price * 0.10) + "e    ");
            receipt_writer.write(Double.toString(total_price) + "e    ");
        } 
        
        finally {
            receipt_writer.close();
        }
    }

    //voidaan myös toteuttaa erikseen puchase olion luonti ilman vakioconstructoria,
    //eli luodaan esim ensin vaan pelkkä purchase olio ilman mitään parametrejä ja yks kerrallaan
    //lisätään properteihin settereitten kautta noi hinnat ja aika jne, tein kuitenki ton constructorin
    //valmiiks 
    private int purchase_number;
    private double total_price;
    private int next_purchase_number;
    private LocalDateTime buytime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private FileWriter writer;
    private FileWriter receipt_writer;
    private Scanner purchase_number_file_scanner;
}