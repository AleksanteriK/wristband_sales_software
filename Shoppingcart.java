package project;
import javax.swing.*;
import java.util.ArrayList;

class Shoppingcart {
    public ArrayList<Ticket> pending_tickets = new ArrayList<>();
    public double total_price;

    //aattelin et ei tee constructoria tälle jos luo tän
    //olion mainin alussa niin ei tuu ns ain sit luotua
    //uutta ostoskoria

    //tällä voi sit tyhjentää ostoskorin arraylistin lipuista
    //ja nollata total_pricen
    void empty_Shopping_cart() {

    }

    /*void set_Pending_tickets(ArrayList<Ticket> pending_tickets) {
        for (int i = 0; i < new_pending_tickets.size(); i++) {
            pending_tickets.add(new_pending_tickets.get(i));
        }
    }*/
    
    void count_Total_price() {
        for (int i = 0; i < pending_tickets.size(); i++) {
            Ticket ticket = pending_tickets.get(i);
            //kertoo sen hinnan jokasen lippuolion amountin arvolla
            total_price += ticket.price * ticket.amount;
        }

        //tähän voit tehä vaik swingille sen tulostusominaisuuden esim.
    }

    void printShoppingcart() {
        //jos haluut tehä erikseen metodin tän tulostukselle esim
        //sen totalpricen täytyy olla sit property
    }
}