import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class CusWindow extends JFrame {
    private JTextField nameField, categoryField, customerIdField;
    private JTextArea resultArea;

    public CusWindow() {
        setTitle("顧客查詢功能");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 5, 5));

        // 商品名稱查詢
        inputPanel.add(new JLabel("依商品名稱查詢："));
        nameField = new JTextField();
        inputPanel.add(nameField);
        JButton searchByNameBtn = new JButton("查詢🔍");
        inputPanel.add(searchByNameBtn);
        searchByNameBtn.addActionListener(e -> searchProductByName());

        // 商品分類查詢
        inputPanel.add(new JLabel("依商品分類查詢："));
        categoryField = new JTextField();
        inputPanel.add(categoryField);
        JButton searchByCategoryBtn = new JButton("查詢🔍");
        inputPanel.add(searchByCategoryBtn);
        searchByCategoryBtn.addActionListener(e -> searchProductByCategory());

        // 顧客訂單查詢
        inputPanel.add(new JLabel("輸入顧客編號查詢訂單："));
        customerIdField = new JTextField();
        inputPanel.add(customerIdField);
        JButton orderSearchBtn = new JButton("查詢🔍");
        inputPanel.add(orderSearchBtn);
        orderSearchBtn.addActionListener(e -> searchOrders());

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    // function
    private void searchProductByName(){
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入商品名稱");
            return;
        }
        QueryDAO dao = new QueryDAO();
        try{
            List<Product> products = dao.searchProductByName(name);
            showProductResult(products);
        }catch(SQLException e){
            e.printStackTrace();
        }
        // 清空輸入
        nameField.setText("");    
    }

    private void searchProductByCategory() {
        String category = categoryField.getText().trim();
        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入商品分類");
            return;
        }
        QueryDAO dao = new QueryDAO();
        try{
            List<Product> products = dao.searchProductByCategoryName(category);
            showProductResult(products);
        }catch(SQLException e){
            e.printStackTrace();
        }
        // 清空輸入
        categoryField.setText("");
    }

    private void searchOrders() {
        String id = customerIdField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入顧客編號");
            return;
        }
        QueryDAO dao = new QueryDAO();
        try{
            List<String> orderDetails = dao.getOrderDetailsByCustomerId(id);
            resultArea.setText("");
        
            if (orderDetails.isEmpty()) {
                resultArea.setText("查無訂單紀錄");
            } else {
                for (String line : orderDetails) {
                    resultArea.append(line + "\n");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        // 清空輸入
        customerIdField.setText("");
    }

    private void showProductResult(List<Product> products) {
        resultArea.setText("");
        if (products.isEmpty()) {
            resultArea.setText("查無商品資料");
        } else {
            for (Product p : products) {
                resultArea.append("名稱：" + p.getPName() +
                        ", 價格：" + p.getPrice() +
                        ", 庫存：" + p.getStock() + "\n");
            }
        }
    }
}
