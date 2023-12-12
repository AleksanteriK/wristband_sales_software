package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//tällä luokalla on vaan se tarkotus että tulostaa sen kokonaismyynnin
class Totalsales
{
    public double total_sales_amount;
    public String sales_file_name = "kokonaismyynnit.txt";
    public File sales_file = new File(sales_file_name);

    void addTo_sales(double new_sale)
    {
        total_sales_amount += new_sale;
    }

    //tällä haetaan tiedostosta edellinen kokonaismyyntimäärä ennenku sinne
    //tulostetaan uus, KUTSTUTAAN update_Sales_state():ssa
    void getPrevious_sales_amount()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(sales_file)))
        {
            //Skippaa ekat kaks riviä
            br.readLine();
            br.readLine();
            String line = br.readLine();

            if(line != null && !line.isEmpty())
            {
                try
                {   //ignoraa muut merkit
                    String numericPart = line.replaceAll("[^\\d.]", "");
                    total_sales_amount = total_sales_amount + Double.parseDouble(numericPart.trim());
                } 

                catch(NumberFormatException e)
                {
                    System.err.println("Error parsing sales amount. The file contains an invalid value on the third line: " + line);
                }
            } 

            else
            {
                System.out.println("No previous sales amount found in the file.");
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    void update_Sales_state() throws IOException
    {
        getPrevious_sales_amount();

        if(sales_file.createNewFile())
        {
            System.out.println("File created");
            //'false' enablee päällekirjotuksen
            sales_writer = new FileWriter(sales_file_name);
        }
         
        else
        {
            sales_writer = new FileWriter(sales_file_name);
        }

        try
        {
            LocalDateTime buytime = LocalDateTime.now();
            sales_writer.write("Kokonaismyyntitilanne: ");
            sales_writer.write("\r\n");
            sales_writer.write(buytime.format(dtf));
            sales_writer.write("\r\n");
            sales_writer.write(Double.toString(total_sales_amount));
            sales_writer.write("€");
            //kesken
        } 
        
        finally
        {
            sales_writer.close();
        }
    }

    private FileWriter sales_writer;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
}