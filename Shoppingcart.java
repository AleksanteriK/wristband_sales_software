package wristband_sales_software;
import javax.swing.*;
import java.util.ArrayList;

class Shoppingcart {
    public ArrayList<Ticket> pending_tickets;
    //public total_price;
    void Count_total_price() {
        float total_price;
        for (int i = 0; i < pending_tickets.size(); i++) {
            Ticket ticket = pending_tickets.get(i);
            totalPrice += ticket.getPrice();
        }

        //tähän voit tehä vaik swingille sen tulostusominaisuuden esim.
    }

    void printShoppingcart() {
        //jos haluut tehä erikseen metodin tän tulostukselle esim
        //sen totalpricen täytyy olla sit property
    }
}