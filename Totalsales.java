package project;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//tällä luokalla on vaan se tarkotus että tulostaa sen kokonaismyynnin
class Totalsales {
    public double total_sales_amount;
    public File sales_file;
    public String sales_file_name = "kokonaismyynnit.txt";

    void addTo_sales (double new_sale) {
        total_sales_amount += new_sale;
    }

    void update_Sales_state() throws IOException {
        sales_file = new File(sales_file_name);

        if (sales_file.createNewFile()) {
            System.out.println("File created");
            //'false' enablee päällekirjotuksen
            sales_writer = new FileWriter(sales_file_name, false);
        } 
        
        else {
            sales_writer = new FileWriter(sales_file_name, false);
        }

        try {
            LocalDateTime buytime = LocalDateTime.now();
            sales_writer.write("Kokonaismyyntitilanne: ");
            sales_writer.write("\r\n");
            sales_writer.write(buytime.format(dtf));
            sales_writer.write("\r\n");
            sales_writer.write(Double.toString(total_sales_amount));
            //kesken
        } 
        
        finally {
            sales_writer.close();
        }
    }

    private FileWriter sales_writer;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
}