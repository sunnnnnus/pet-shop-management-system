public class Product {
    private int pId;
    private String pName;
    private int price;
    private int stock;
    private String cId;

    private int totalSold;
    private double totalRevenue;

    //constructor
    public Product() {}

    public Product(int pId, String pName, int price, int stock, String cId) {
        this.pId = pId;
        this.pName = pName;
        this.price = price;
        this.stock = stock;
        this.cId = cId;
    }
    public Product(String pName, int totalSold, double totalRevenue) {
    this.pName = pName;
    this.totalSold = totalSold;
    this.totalRevenue = totalRevenue;
    }

    //getter & setter
    public int getPId() { return pId; }
    public void setPId(int pId) { this.pId = pId; }

    public String getPName() { return pName; }
    public void setPName(String pName) { this.pName = pName; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCId() { return cId; }
    public void setCId(String cId) { this.cId = cId; }  

    public int getTotalSold() { return totalSold; }
    public void setTotalSold(int totalSold) { this.totalSold = totalSold; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

}
