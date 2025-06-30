import java.sql.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            Connection conn = DBConnection.getConnection();
            SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
