public class Category {
    private String cId;
    private String cName;

    public Category(String cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    //getter
    public String getCId(){
        return cId;
    }
    public String getCName(){
        return cName;
    }
}