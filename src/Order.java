import java.util.Date;

public class Order {
    private String oId;
    private String cusId;
    private Date date;
    private int total;

    //constructor
    public Order(String oId, String cusId, Date date,int total) {
        this.oId = oId;
        this.cusId = cusId;
        this.date = date;
    }

    //getter
    public String getOId(){
        return oId;
    }
    public String getCusId(){
        return cusId;
    }
    public Date getDate(){
        return date;
    }
    public int getTotal(){
        return total;
    }
}