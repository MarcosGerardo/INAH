/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRAMES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CONECTOR {

    public static final String URL = "jdbc:mysql://localhost:3306/sarin";
    public static final String USER = "root";
   // public static final String CLAVE = "root";
     public static final String CLAVE = "";

    static PreparedStatement prepareStatement(String consulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: UPPS!! ALGO SALIÓ MAL, REVISA TÚ CONEXIÓN A INTERNET Ó CONTACTA AL ADMINISTRADOR " + e.getMessage());
            System.exit(0);
        }
        return con;
    }
}
