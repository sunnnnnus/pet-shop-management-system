import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBConnection { 
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("連線成功！");
        }catch (FileNotFoundException e) {
            System.out.println("找不到 db.properties 檔案！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("讀取 db.properties 時發生錯誤！");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到 MySQL JDBC 驅動！");
            e.printStackTrace();
        }catch (SQLException e) {
            System.out.println("連接資料庫失敗！");
            e.printStackTrace();
        }
        return conn;
    }

}


