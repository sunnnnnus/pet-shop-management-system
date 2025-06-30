import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminWindow extends JFrame {
    private JButton btnAllProducts;
    private JButton btnAllOrders;
    private JButton btnTopProducts;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public AdminWindow() {
        setTitle("管理員查詢功能");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLayout(new BorderLayout());

        // 上方按鈕
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        btnAllProducts = new JButton("查詢所有商品");
        btnAllOrders = new JButton("查詢所有訂單總覽");
        btnTopProducts = new JButton("查詢熱門商品前3名");
        buttonPanel.add(btnAllProducts);
        buttonPanel.add(btnAllOrders);
        buttonPanel.add(btnTopProducts);
        add(buttonPanel, BorderLayout.NORTH);

        // 中下方表格
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        // 事件監聽
        btnAllProducts.addActionListener(e -> showAllProducts());

        btnAllOrders.addActionListener(e -> showAllOrders());

        btnTopProducts.addActionListener(e -> showTopProducts());

        setVisible(true);
    }

    // function
    private void showAllProducts() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"商品編號", "商品名稱", "商品類別", "商品價格", "商品庫存"});

        QueryDAO dao = new QueryDAO();
        try{
            List<Product> products = dao.getAllProducts();
            for (Product p : products) {
                tableModel.addRow(new Object[]{
                        p.getPId(), p.getPName(), p.getCId(), p.getPrice(), p.getStock()
                });
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void showAllOrders() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"訂單編號", "顧客編號", "訂單日期", "商品名稱", "訂單數量", "商品單價", "訂單總額"});

        QueryDAO dao = new QueryDAO();
        try {
            List<OrderDetail> orders = dao.getAllOrdersDetail(); 
            for (OrderDetail od : orders) {
                tableModel.addRow(new Object[]{
                        od.getOId(),
                        od.getCusId(),
                        od.getDate(),
                        od.getPName(),
                        od.getNum(),
                        od.getUnitPrice(),
                        od.getTotal()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showTopProducts() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"商品名稱", "總銷售數量", "總銷售金額"});

        QueryDAO dao = new QueryDAO();
        try{
            List<Product> topProducts = dao.getTopSellingProducts(3);
            for (Product ps : topProducts) {
                tableModel.addRow(new Object[]{
                        ps.getPName(), ps.getTotalSold(), ps.getTotalRevenue()
                });
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
