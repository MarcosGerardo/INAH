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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OTROS extends javax.swing.JFrame {

    /**
     * Creates new form liticaTallada
     */
    public OTROS() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
public void mostrar() {
    Connection conn = null;
    String SQL = "SELECT * FROM otromaterial";
    Statement st;
    CONECTOR con = new CONECTOR();
    conn = con.getConexion();
    DefaultTableModel model = new DefaultTableModel();

    // Agrega las columnas que deseas mostrar en la tabla
    model.addColumn("id");
    model.addColumn("Sitio");
    model.addColumn("Estructura");
    model.addColumn("Locus");
    model.addColumn("Numero_de_bolsa");
    model.addColumn("Unidad");
    model.addColumn("Cuarto");
    model.addColumn("E");
    model.addColumn("N");
    model.addColumn("Estrato");
    model.addColumn("Total");
    model.addColumn("RT");
    model.addColumn("clase");
    model.addColumn("tipo");
    model.addColumn("materiaprima");
    model.addColumn("cortex");
    model.addColumn("largo");
    model.addColumn("ancho");
    model.addColumn("grosor");
    model.addColumn("observacion");
    model.addColumn("Registro");
    model.addColumn("Analizo");

    OTRO.setModel(model);
    String[] datos = new String[21]; // Ajusta el tamaño al número de columnas en tu tabla

    try {
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            for (int i = 1; i <= 21; i++) { // Ajusta el rango al número de columnas en tu tabla
                datos[i - 1] = rs.getString(i);
            }
            model.addRow(datos);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  public void eliminarRegistro() {
    int filaSeleccionada = OTRO.getSelectedRow();
     Connection conn = null;
     String SQL = "SELECT * otromaterial";
     Statement st;
     CONECTOR con=new CONECTOR();
     conn = con.getConexion();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return; 
    }

    // Obtiene el ID del registro a eliminar
    String id = OTRO.getValueAt(filaSeleccionada, 0).toString();
    String sqlDelete = "DELETE FROM otromaterial WHERE id=?";
    
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
        preparedStatement.setString(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
    }

 
}
  public void descargar() throws Exception {
    String SQL = "SELECT * FROM otromaterial";
    CONECTOR con = new CONECTOR();
    Connection conn = con.getConexion();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(SQL);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("otromaterial");

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
        OTRO = new javax.swing.JTable();
        btnEliminar4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        OTRO.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(OTRO);

        btnEliminar4.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete_6070129 (2).png"))); // NOI18N
        btnEliminar4.setText("ELIMINAR");
        btnEliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar4ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 204, 255));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/devolver.png"))); // NOI18N
        jButton9.setText("REGRESAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 255, 102));
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/file_1091007.png"))); // NOI18N
        jButton10.setText("DESCARGAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jButton10)
                        .addGap(31, 31, 31)
                        .addComponent(jButton9)
                        .addGap(33, 33, 33)
                        .addComponent(btnEliminar4)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(btnEliminar4)
                    .addComponent(jButton10))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar4ActionPerformed
eliminarRegistro();
mostrar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    dispose();
        FramePrincipal fra=new FramePrincipal();
        fra.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(OTROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OTROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OTROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OTROS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OTROS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable OTRO;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnEliminar2;
    private javax.swing.JButton btnEliminar3;
    private javax.swing.JButton btnEliminar4;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbLiticaPu;
    private javax.swing.JTable tbLiticaPu1;
    private javax.swing.JTable tbLiticaPu2;
    // End of variables declaration//GEN-END:variables
}
