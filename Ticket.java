package project;

class Ticket {
    public int amount;
    public String type;
    public double price;
    public String customer_name;
    public String phone_number;
    public String discountType;
    //jotta sai ton shoppincart luokassa yhteishinnan laskun toimii, täyty tehä vähä temppuja
    //niin tein täst luokast nyt sellasen et täl ei oo vakioconstructoria vaa settereiden
    //kautta sit asettaa oliolle ne arvot properteihin

    /*Ticket(int amount, String type, float price) {
        this.amount = amount;
        this.type = type;
        this.price = price;
    }*/

    void setAmount(int amount) {
        this.amount = amount;
    }

    void setType(String type) {
        this.type = type;
    }

    void setPrice(double price) {
        this.price = price;
    }

    void setCustomer_name(String new_customer_name) {
        this.customer_name = new_customer_name;
    }

    void setPhone_number(String new_phone_number) {
        this.phone_number = new_phone_number;
    }

    void setDiscountType(String new_discount_type)
    {
        this.discountType = new_discount_type;
    }

    double getPrice() {
        return price;
    }

    String getType()
    {
        return type;
    }

    int getAmount() {
        return amount;
    }

    String getDiscountType()
    {
        return discountType;
    }

    String getCustomer_name()
    {
        return customer_name;
    }

    String getPhone_number()
    {
        return phone_number;
    }

    void addAmount(int add_amount) {
        amount += add_amount;
    }

    void addPrice(double add_price) {
        price += add_price;
    }

    //tän avulla pystyy tulostamaan kuittiin ja muihin lipun tietoja
    public String ticket_toString() {
        return "Amount / Määrä: " + amount + ", Type / Lipputyyppi: " + type + ", Price / hinta: " + price;
    }


    //aattelin luoda noi setterit sitä lastenlippua varten myös, voi muokata jos tää tyyli ei sovi.
    //periaattees tällä voi luua tän tiksun ilman et on pakko laittaa siihen olioon myös nimi
    //ja puhnro
}