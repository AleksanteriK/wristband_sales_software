package wristband_sales_software;
import java.util.ArrayList;

class Purchase {
    private int purchase_number;
    private float buytime;
    float total_price;

    purchase (int purchase_number, float buytime, float total_price) {
        this.purchase_number = purchase_number;
        this.buytime = buytime;
        this.total_price = total_price;
    }
    //voidaan myös toteuttaa erikseen puchase olion luonti ilman vakioconstructoria,
    //eli luodaan esim ensin vaan pelkkä purchase olio ilman mitään parametrejä ja yks kerrallaan
    //lisätään properteihin settereitten kautta noi hinnat ja aika jne, tein kuitenki ton constructorin
    //valmiiks 
    public Arraylist<Ticket> tickets;
}