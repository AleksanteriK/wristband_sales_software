package wristband_sales_software;

class Ticket {
    public int amount;
    public String type;
    public float price;
    public String customer_name;
    public String phone_number;
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

    void setPrice(float price) {
        this.price = price;
    }

    void setCustomer_name(String new_customer_name) {
        customer_name = new_customer_name;
    }

    void setPhone_number(String new_phone_number) {
        phone_number = new_phone_number;
    }

    float getPrice() {
        return price;
    }

    //aattelin luoda noi setterit sitä lastenlippua varten myös, voi muokata jos tää tyyli ei sovi.
    //periaattees tällä voi luua tän tiksun ilman et on pakko laittaa siihen olioon myös nimi
    //ja puhnro
}