/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Himanshu
 */
public class dbc {
 final static String driver="com.mysql.jdbc.Driver";
 final static String DB_URL="jdbc:mysql://localhost:3306/canteen scheduler";
 final static String USER ="root";
 final static String PASS ="";
 public static Connection connection()
 {
     try {
      
// loading driver
Class.forName(driver);
// Connection set up with database named as user
Connection c = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
System.out.println("connected");
return c;
 }catch(ClassNotFoundException | SQLException e)
 {
     
     JOptionPane.showMessageDialog(null,e);
     return null;
 }
    
 }
}
