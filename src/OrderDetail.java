import java.sql.Date;

public class OrderDetail {
    private String oId;
    private int pId;
    private int num;
    private double unitPrice;

    private String pName;
    private Date date;
    private int total;
    private String cusId;

    //construtor
    public OrderDetail(String oId, int pId, int num, double unitPrice) {
        this.oId = oId;
        this.pId = pId;
        this.num = num;
        this.unitPrice = unitPrice;
    }
    //SQL查詢加入JOIN後的constructor
    public OrderDetail(String oId, String cusId, Date date, 
                       String pName, int unitPrice, int num, int total) {
        this.oId = oId;
        this.cusId = cusId;
        this.date = date;
        this.pName = pName;
        this.unitPrice = unitPrice;
        this.num = num;
        this.total = total;
    }


    //getter
    public String getOId(){
        return oId;
    }
    public int getPId(){
        return pId;
    }
    public int getNum(){
        return num;
    }
    public double getUnitPrice(){
        return unitPrice;
    }
    public String getPName(){
        return pName;

    }
    public Date getDate(){
        return date;
    }
    public int getTotal(){
        return total;
    }
    public String getCusId(){
        return cusId;
    }
}