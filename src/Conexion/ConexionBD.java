
package Conexion;

import java.sql.*;
import javax.swing.*;

public class ConexionBD {
    Connection conectar = null;
    public Connection conexion(){
        try {
           Class.forName("com.mysql.jdbc.Driver");
           conectar = DriverManager.getConnection("jdbc:mysql://localhost/inventario","root","123456789");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        } return conectar;
    }
}
