/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author matam
 */
public class consultarSitio extends javax.swing.JFrame {

    /**
     * Creates new form consultarSitio
     */
    public consultarSitio() {
        initComponents();
        this.setLocationRelativeTo(null);
       
    }
    
    public void mostraSitio() {
        Connection conn = null;
        String SQL = "SELECT * FROM sitio"; // Cambia "sitio" al nombre real de tu tabla
        Statement st;
        CONECTOR con = new CONECTOR(); // Asegúrate de tener la clase CONECTOR
        conn = con.getConexion();
        DefaultTableModel model = new DefaultTableModel();

        // Agrega las columnas que deseas mostrar en la tabla
        String[] columnNames = {
            "ID", "Sitio", "Tipo", "Region", "NombreProyecto",
            "Fecha", "Registró", "Localidad", "Municipio",
            "Este", "Norte", "Altitud", "Datum", "extension",
            "Topoforma", "Cronologia", "Carta", "Descripcion"
        };

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        tbSitio.setModel(model);

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
    private String obtenerNombreSeleccionado(String nombreTabla, String columnaTexto, JComboBox<?> comboBox) {
    // Obtener el texto seleccionado del JComboBox
    String textoSeleccionado = comboBox.getSelectedItem().toString();
    
    // Inicializar la conexión y el PreparedStatement
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        // Establecer la conexión
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        // Consulta SQL para obtener el nombre correspondiente al texto seleccionado
        String sql = "SELECT " + columnaTexto + " FROM " + nombreTabla + " WHERE " + columnaTexto + " = ?";
        
        // Preparar la consulta
        ps = conn.prepareStatement(sql);
        ps.setString(1, textoSeleccionado);
        
        // Ejecutar la consulta
        rs = ps.executeQuery();
        
        // Verificar si se encontró el nombre y devolverlo
        if (rs.next()) {
            return rs.getString(1);
        } else {
            // Manejar el caso donde no se encontró el nombre correspondiente al texto seleccionado
            return null; // Indicación de que no se pudo obtener el nombre
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción adecuadamente según tus necesidades
        return null; // Indicación de que no se pudo obtener el nombre
    } finally {
        // Cerrar los recursos
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar errores de cierre si es necesario
        }
    }
}

     public void eliminarRegistro() {
        int filaSeleccionada = tbSitio.getSelectedRow();
        Connection conn = null;
        String SQL = "SELECT * FROM sitio"; // Cambia "sitio" al nombre real de tu tabla
        CONECTOR con = new CONECTOR(); // Asegúrate de tener la clase CONECTOR
        conn = con.getConexion();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
            return;
        }

        // Obtiene el ID del registro a eliminar
        String id = tbSitio.getValueAt(filaSeleccionada, 0).toString();
        String sqlDelete = "DELETE FROM sitio WHERE Id=?"; // Cambia "sitio" al nombre real de tu tabla

        try {
            java.sql.PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                mostraSitio(); // Llama al método para actualizar la tabla visual
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
        }
    }
    public void descargarSitio() throws Exception {
    String SQL = "SELECT * FROM sitio";
    CONECTOR con = new CONECTOR();
    Connection conn = con.getConexion();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(SQL);
    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    Workbook workbook = new XSSFWorkbook(); 
    Sheet sheet = workbook.createSheet("sitio");

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
public int contarRegistrosPorId(String id) {
    // Supongamos que tienes una conexión a la base de datos llamada "conexion"
    String consulta = "SELECT COUNT(*) FROM tu_tabla WHERE id = ?";
    try (PreparedStatement statement = CONECTOR.prepareStatement(consulta)) {
        statement.setString(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Si ocurre algún error o no se encuentra el ID, devuelve 0
}

    public void mostrarSitios(){
  int filaSeleccionada = tbSitio.getSelectedRow();
   if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila antes de mostrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Añade esta línea para salir del método si no hay ninguna fila seleccionada
        }
  
String idDelElementoSeleccionado = tbSitio.getValueAt(filaSeleccionada, 0).toString();
String txtNombreSitio = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 1)); // Columna 1: nombre del sitio
String cbTipo = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 2)); // Columna 2: tipo
String cbRegionesSitios = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 3)); // Columna 3: regiones de sitios
String txtNombreProyecto = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 4)); // Columna 4: nombre del proyecto
String txtFecha = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 5)); // Columna 5: fecha
String txtPersona = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 6)); // Columna 6: persona
String txtLocalidad = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 7));
String cbMunicipio = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 8)); 
String txtEste = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 9));
String txtNorte = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 10));
String txtAltitud = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 11)); // Columna 12: altitud
String txtDatum = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 12)); // Columna 9: datum
String txtExtension = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 13)); 
String cbTopo = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 14));
String cbCronos = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 15)); // Columna 14: cronos
String nombreCartaSeleccionada = tbSitio.getValueAt(filaSeleccionada, 16).toString();
//JComboBox<String> comboBoxCarta = new JComboBox<>(new String[]{nombreCartaSeleccionada});// Columna 15: carta
String txtDescripcion = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 17)); // Columna 15: carta
mostrarSitio sit = new mostrarSitio();
sit.setId(idDelElementoSeleccionado);
sit.setTxtNombreSitio(txtNombreSitio);
sit.setCbTopo(cbTopo);
sit.setCbTipo(cbTipo);
sit.setCbRegionesSitios(cbRegionesSitios);
sit.setTxtNombreProyecto(txtNombreProyecto);
sit.setTxtFecha(txtFecha);
sit.setTxtPersona(txtPersona);
sit.setCbMunicipio(cbMunicipio);
sit.setTxtLocalidad(txtLocalidad);
sit.setTxtDatum(txtDatum);
sit.setTxtEste(txtEste);
sit.setTxtNorte(txtNorte);
sit.setTxtAltitud(txtAltitud);
sit.setExtension(txtExtension);
sit.setCbTopo(cbTopo);
sit.setCbCronos(cbCronos);
sit.setCarta(nombreCartaSeleccionada);
sit.setTxtDescripcion(txtDescripcion);
dispose();
sit.setVisible(true);

   
    
}
    
  public void editarrSitios(){
  int filaSeleccionada = tbSitio.getSelectedRow();
   if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila antes de editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Añade esta línea para salir del método si no hay ninguna fila seleccionada
        }
   dispose();
   String idDelElementoSeleccionado = tbSitio.getValueAt(filaSeleccionada, 0).toString();
   String txtNombreSitio = tbSitio.getValueAt(filaSeleccionada, 1).toString();
      String txtTipo = tbSitio.getValueAt(filaSeleccionada, 2).toString();
       String txtRegion = tbSitio.getValueAt(filaSeleccionada, 3).toString();
   String txtNombreProyecto = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 4)); 
   String txtFecha = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 5)); // Columna 5: fecha
String txtPersona = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 6)); // Columna 6: persona
String txtLocalidad = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 7));
String cbMunicipio = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 8)); 
String txtEste = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 9));
String txtNorte = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 10));
String txtAltitud = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 11)); // Columna 12: altitud
String txtDatum = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 12));
String txtExtension = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 13)); 
String txtTopo = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 14)); 
String txtCronos= String.valueOf(tbSitio.getValueAt(filaSeleccionada, 15)); 
String txtCartas= String.valueOf(tbSitio.getValueAt(filaSeleccionada, 16)); 
String txtDescripcion = String.valueOf(tbSitio.getValueAt(filaSeleccionada, 17)); // Columna 15: carta
   
   editarSitio edi = new editarSitio(this, null);

   edi.setId(idDelElementoSeleccionado);
   edi.setTxtNombreSitio(txtNombreSitio);
   edi.setTxtNombreProyecto(txtNombreProyecto);
   edi.setTxtFecha(txtFecha);
edi.setTxtPersona(txtPersona);
edi.setTxtMunicipio(cbMunicipio);
edi.setTxtLocalidad(txtLocalidad);
edi.setTxtDatum(txtDatum);
edi.setTxtEste(txtEste);
edi.setTxtNorte(txtNorte);
edi.setTxtAltitud(txtAltitud);
edi.setExtension(txtExtension);
edi.setTxtTopoForma(txtTopo);
edi.setTxtCronos(txtCronos);
edi.setTxtCartas(txtCartas);
edi.setTxtDescripcion(txtDescripcion);

edi.setTxtTipo(txtTipo);
edi.setTxtRegionesSitios(txtRegion);

   edi.setVisible(true);
   
  
 
  

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
        tbSitio = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        tbSitio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbSitio);

        btnEliminar.setBackground(new java.awt.Color(102, 0, 102));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete_6070129 (2).png"))); // NOI18N
        btnEliminar.setText("ELIMINAR REGISTRO ");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/file_1091007.png"))); // NOI18N
        jButton1.setText("DESCARGAR TABLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/devolver.png"))); // NOI18N
        jButton2.setText("REGRESAR A LA PAGINA PRINCIPAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/search_607554 (2).png"))); // NOI18N
        jButton3.setText("MOSTRAR REGISTRO ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/editar-codigo.png"))); // NOI18N
        jButton4.setText("EDITAR REGISTRO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jButton1))
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnEliminar)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarRegistro();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            descargarSitio();
            // TODO add your handling code here:
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(consultarSitio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(consultarSitio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        FramePrincipal fra=new FramePrincipal();
        fra.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     mostrarSitios();
   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        editarrSitios();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(consultarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultarSitio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSitio;
    // End of variables declaration//GEN-END:variables
}
