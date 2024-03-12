/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author matam
 */
public class ceramicaDecorada extends javax.swing.JFrame {


    public ceramicaDecorada() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
public void mostrarCeramicaDecorada() {
    Connection conn = null;
    String SQL = "SELECT * FROM ceramicadecorada";
    Statement st;
    CONECTOR con = new CONECTOR();
    conn = con.getConexion();
    DefaultTableModel model = new DefaultTableModel();


    model.addColumn("id");
    model.addColumn("Sitio");
    model.addColumn("Bolsa");
    model.addColumn("Unidad");
    model.addColumn("Estructura");
    model.addColumn("Cuarto_o_subestructura");
    model.addColumn("E");
    model.addColumn("N");
    model.addColumn("RT");
    model.addColumn("Estrato");
    model.addColumn("Total");
    model.addColumn("Suchil");
    model.addColumn("Vesuvio");
    model.addColumn("Michilia");
    model.addColumn("Amaro");
    model.addColumn("Mercado");
    model.addColumn("Neveria");
    model.addColumn("Refugio");
    model.addColumn("Lolandis");
    model.addColumn("Otinapa");
    model.addColumn("Morcillo");
    model.addColumn("Nayar");
    model.addColumn("Canatlan");
    model.addColumn("Madero_Fluted");
    model.addColumn("De_la_costa");
    model.addColumn("NI");
    model.addColumn("plato");
    model.addColumn("cajete");
    model.addColumn("olla");
    model.addColumn("vaso");
    model.addColumn("jarra");
    model.addColumn("copa");
    model.addColumn("cajete_asa_de_canasta");
    model.addColumn("molcajete");
    model.addColumn("NId");
    model.addColumn("borde");
    model.addColumn("cuerpo");
    model.addColumn("asa");
    model.addColumn("soportes");
    model.addColumn("Registro");
    model.addColumn("Analizo");

    tbDetallada.setModel(model);
    String[] datos = new String[40]; 

    try {
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            for (int i = 1; i <= 40; i++) { 
                datos[i - 1] = rs.getString(i);
            }
            model.addRow(datos);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void eliminarRegistroDetallada() {
    int filaSeleccionada = tbDetallada.getSelectedRow();
    Connection conn = null;
    String SQL = "SELECT * FROM ceramicadecorada";
    Statement st;
    CONECTOR con = new CONECTOR();
    conn = con.getConexion();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return; 
    }

    // Obtiene el ID del registro a eliminar
    String id = tbDetallada.getValueAt(filaSeleccionada, 0).toString();
    String sqlDelete = "DELETE FROM ceramicadecorada WHERE id=?";

    try {
        java.sql.PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
        preparedStatement.setString(1, id);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
        e.toString();
        System.out.print(e.toString());
    }
}
  public void descargar() throws Exception {
    String SQL = "SELECT * FROM ceramicadecorada";
    CONECTOR con = new CONECTOR();
    Connection conn = con.getConexion();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(SQL);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("ceramicadecorada");

    Row header = sheet.createRow(0);
    for (int i = 1; i <= columnCount; i++) {
        String columnName = rsmd.getColumnName(i);
        Cell headerCell = header.createCell(i-1);
        headerCell.setCellValue(columnName);
    }

    int rowIndex = 1;
    while (rs.next()) {
        Row row = sheet.createRow(rowIndex++);

        for (int i = 1; i <= columnCount; i++) {
            Cell cell = row.createCell(i-1);
            cell.setCellValue(rs.getString(i));
        }
    }

    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        FileOutputStream fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetallada = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        tbDetallada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbDetallada);

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete_6070129 (2).png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/devolver.png"))); // NOI18N
        jButton2.setText("REGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/file_1091007.png"))); // NOI18N
        jButton1.setText("DESCARGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1320, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnEliminar)
                    .addComponent(jButton1))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
       FramePrincipal fra=new FramePrincipal();
        fra.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       eliminarRegistroDetallada();
       mostrarCeramicaDecorada();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            descargar();
        } catch (Exception ex) {
            Logger.getLogger(ceramicaDecorada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ceramicaDecorada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ceramicaDecorada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ceramicaDecorada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ceramicaDecorada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ceramicaDecorada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbDetallada;
    // End of variables declaration//GEN-END:variables
}
