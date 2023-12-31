package project;
import java.util.ArrayList;

class Shoppingcart
{
    public ArrayList<Ticket> pending_tickets = new ArrayList<>();
    public double total_price;
    public boolean hasNormalTicket;
    public int normalTicketAmount;
    public int ticketPosition;
    public boolean hasChildTicket;
    public int childTicketAmount;
    public boolean hasDiscountTicket;
    public int discountTicketAmount;
    public boolean ticketFound;

    void empty_Shopping_cart()
    {
        pending_tickets.clear();
        total_price = 0;
        hasNormalTicket = false;
        normalTicketAmount = 0;
        ticketPosition = 0;
        hasChildTicket = false;
        childTicketAmount = 0;
        hasDiscountTicket = false;
        discountTicketAmount = 0;
        ticketFound = false;
    }
    
    void count_Total_price()
    {
        total_price = 0;
        for (int i = 0; i < pending_tickets.size(); i++) {
            total_price += pending_tickets.get(i).price;
        }

    }

    double get_Total_price()
    {
        return total_price;
    }

    void setHasNormalTicket(boolean new_hasNormalTicket)
    {
        this.hasNormalTicket = new_hasNormalTicket;
    }

    void setNormalTicketAmount(int new_normalTicketAmount)
    {
        this.normalTicketAmount = new_normalTicketAmount;
    }

    void setTicketPosition(int new_ticketPosition)
    {
        this.ticketPosition = new_ticketPosition;
    }

    void setHasChildTicket(boolean new_hasChildTicket)
    {
        this.hasChildTicket = new_hasChildTicket;
    }

    void setChildTicketAmount(int new_childTicketAmount)
    {
        this.childTicketAmount = new_childTicketAmount;
    }

    void setHasDiscountTicket(boolean new_hasDiscountTicket)
    {
        this.hasDiscountTicket = new_hasDiscountTicket;
    }

    void setDiscountTicketAmount(int new_discountTicketAmount)
    {
        this.discountTicketAmount = new_discountTicketAmount;
    }

    void setTicketFound(boolean new_ticketFound)
    {
        this.ticketFound = new_ticketFound;
    }

    boolean getHasNormalTicket()
    {
        return hasNormalTicket;
    }

    int getNormalTicketAmount()
    {
        return normalTicketAmount;
    }

    int getTicketPosition()
    {
        return ticketPosition;
    }

    boolean getHasChildTicket()
    {
        return hasChildTicket;
    }

    int getChildTicketAmount()
    {
        return childTicketAmount;
    }

    boolean getHasDiscountTicket()
    {
        return hasDiscountTicket;
    }

    int getDiscountTicketAmount()
    {
        return discountTicketAmount;
    }

    boolean getTicketFound()
    {
        return ticketFound;
    }
}