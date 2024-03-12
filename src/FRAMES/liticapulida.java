/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class liticapulida extends javax.swing.JFrame {

    /**
     * Creates new form liticaTallada
     */
    public liticapulida() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public void mostrarLiticapulida() {
    Connection conn = null;
    String SQL = "SELECT * FROM liticapulida";
    Statement st;
    CONECTOR con = new CONECTOR();
    conn =  con.getConexion();
    DefaultTableModel model = new DefaultTableModel();

    // Agrega las columnas que deseas mostrar en la tabla
    String[] columnNames = {"ID", "Sito", "Numero_de_bolsa", "Estructura", "Locus", "E", "N", "Completo", "Fracturado", "Metate", "Mortero", "Mano_metate", "Mano_Mortero", "Pulidor", "Hacha", "Otro", "Materia_Prima", "Caras_pulidas", "Largo", "Ancho", "Grosor", "Registro", "Analizo", "Observaciones"};
    for (String columnName : columnNames) {
        model.addColumn(columnName);
    }

    tbLiticaPu.setModel(model);
    String[] datos = new String[columnNames.length];

    try {
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            for (int i = 1; i <= columnNames.length; i++) {
                datos[i - 1] = rs.getString(i);
            }
            model.addRow(datos);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  public void eliminarRegistro() {
    int filaSeleccionada = tbLiticaPu.getSelectedRow();
    Connection conn = null;
    String SQL = "SELECT * FROM liticapulida";
    Statement st;
    CONECTOR con = new CONECTOR();
    conn = con.getConexion();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return; 
    }

    // Obtiene el ID del registro a eliminar
    String id = tbLiticaPu.getValueAt(filaSeleccionada, 0).toString();
    String sqlDelete = "DELETE FROM liticapulida WHERE ID=?";

  
    
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
        preparedStatement.setString(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
            mostrarLiticapulida();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
    }
}


public void descargarLiticaPulidaXLSX() throws Exception {
    String SQL = "SELECT * FROM liticapulida";
    CONECTOR con = new CONECTOR();
    Connection conn = con.getConexion();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(SQL);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("liticapulida");

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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLiticaPu = new javax.swing.JTable();
        btnEliminar1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        tbLiticaPu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbLiticaPu);

        btnEliminar1.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete_6070129 (2).png"))); // NOI18N
        btnEliminar1.setText("ELIMINAR");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/devolver.png"))); // NOI18N
        jButton4.setText("REGRESAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/file_1091007.png"))); // NOI18N
        jButton3.setText("DESCARGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEliminar1)
                        .addGap(34, 34, 34)
                        .addComponent(jButton4)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(btnEliminar1)
                    .addComponent(jButton3))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        eliminarRegistro();
   
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        FramePrincipal fra=new FramePrincipal();
        fra.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            descargarLiticaPulidaXLSX();
        } catch (Exception ex) {
            Logger.getLogger(liticaTallada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(liticapulida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(liticapulida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(liticapulida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(liticapulida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new liticapulida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbLiticaPu;
    // End of variables declaration//GEN-END:variables
}
