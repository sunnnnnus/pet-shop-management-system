public class Customers {
    private String cusName;
    private String cusId;
    private String phone;

    public Customers(String cusName, String cusId, String phone) {
        this.cusName = cusName;
        this.cusId = cusId;
        this.phone = phone;
    }

    //getter
    public String getCusName(){
        return cusName;
    }
    public String getCusId(){
        return cusId;
    }
    public String getPhone(){
        return phone;
    }
}