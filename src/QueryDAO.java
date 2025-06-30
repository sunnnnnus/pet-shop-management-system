import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDAO {

    private Connection conn;

    public QueryDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1. 依商品名稱查詢
    public List<Product> searchProductByName(String name) throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE pName LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        }
        return list;
    }

    // 2. 依分類查詢
    public List<Product> searchProductByCategoryName(String categoryName) throws SQLException {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT p.pId, p.pName, p.price, p.stock, p.cId " +
                 "FROM Product p JOIN Category c ON p.cId = c.cId " +
                 "WHERE c.cName LIKE ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + categoryName + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Product p = new Product(
                rs.getInt("pId"),
                rs.getString("pName"),
                rs.getInt("price"),
                rs.getInt("stock"),
                rs.getString("cId") 
            );
            list.add(p);
        }
    }
    return list;
}


    // 3. 查詢顧客自己的訂單紀錄
    public List<String> getOrderDetailsByCustomerId(String cusId) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT o.oId, c.cusName, p.pName, o.date, od.num, o.total " +
                     "FROM `Order` o " +
                     "LEFT JOIN OrderDetail od ON o.oId = od.oId " +
                     "LEFT JOIN Product p ON od.pId = p.pId "+
                     "LEFT JOIN Customers c ON o.cusId = c.cusId " +
                     "WHERE o.cusId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cusId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String line = String.format("訂單編號: %s | 顧客姓名: %s | 商品名稱: %s | 日期: %s | 數量: %d | 總額: %d",
                        rs.getString("oId"),
                        rs.getString("cusName"),
                        rs.getString("pName"),
                        rs.getDate("date"),
                        rs.getInt("num"),
                        rs.getInt("total")
                );
                list.add(line);
            }
        }
        return list;
    }

    // 4. 查詢所有商品
    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        }
        return list;
    }

    // 5. 查詢所有訂單
    public List<OrderDetail> getAllOrdersDetail() throws SQLException {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT o.oId, o.cusId, p.pName, o.date, od.unitPrice, od.num, o.total " + 
                     "FROM `Order` o " +
                     "JOIN OrderDetail od ON o.oId = od.oId " +
                     "JOIN Product p ON od.pId = p.pId ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getString("oId"),
                        rs.getString("cusId"),
                        rs.getDate("date"),
                        rs.getString("pName"),
                        rs.getInt("num"),
                        rs.getInt("unitPrice"),
                        rs.getInt("total")
                ));
            }
        }
        return list;
    }

    // 6. 查詢熱門商品前3名（依銷售量與金額排序）
   public List<Product> getTopSellingProducts(int topN) throws SQLException {
    List<Product> list = new ArrayList<>();
    String sql = """
        SELECT p.pId, p.pName, p.price, p.stock, p.cId,
            SUM(od.num) AS totalSold,
            SUM(od.num * od.unitPrice) AS totalRevenue
        FROM Product p
        JOIN OrderDetail od ON p.pId = od.pId
        GROUP BY p.pId, p.pName, p.price, p.stock, p.cId
        ORDER BY totalSold DESC
        LIMIT ?
        """;
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, topN);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setPId(rs.getInt("pId"));
            p.setPName(rs.getString("pName"));
            p.setPrice(rs.getInt("price"));
            p.setStock(rs.getInt("stock"));
            p.setCId(rs.getString("cId"));
            p.setTotalSold(rs.getInt("totalSold"));
            p.setTotalRevenue(rs.getDouble("totalRevenue"));
            list.add(p);
        }
    }
    return list;
}



        // 共用方法：轉換 ResultSet 為 Product
    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("pId"),
                rs.getString("pName"),
                rs.getInt("price"),
                rs.getInt("stock"),
                rs.getString("cId")
        );
    }
}
