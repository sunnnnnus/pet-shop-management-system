import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("寵物用品管理系統");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("請選擇使用者身份", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton cusBtn = new JButton("顧客查詢");
        JButton adminBtn = new JButton("管理員查詢");
        cusBtn.setPreferredSize(new Dimension(120, 40));
        adminBtn.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(cusBtn);
        buttonPanel.add(adminBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // 點擊事件
        cusBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CusWindow(); 
            }
        });

        adminBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminWindow(); 
            }
        });

        setVisible(true);
    }
}
