
package FRAMES;

/**
 *
 * @author matam
 */
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editarLocus extends javax.swing.JFrame {

    public editarLocus() throws HeadlessException {
        initComponents();
        this.setLocationRelativeTo(null);
        cbInmueble(ComboBoxL1);
        cbUbi(cbUbicacionL);
        cbFrmaGral(cbFrmaGral);
    }
    
   public void setTxtNombreLocus(String nombreLocus) {
       txtNombreL.setText(nombreLocus);
    }
     public void setTxtReferencia(String referencia) {
       txtReferenciaL.setText(referencia);
    }
      public void setTxtColor(String color) {
       txtColorL.setText(color);
    }
     public void setTxtDes(String descripcion) {
       txtDescripcionL.setText(descripcion);
    }
       public void setTxtNum(String numero) {
      txtnumero.setText(numero);
    }
       public void setTxtcbUbicacionL(String txtcbUbicacionL) {
      this.txtcbUbicacionL.setText(txtcbUbicacionL);
    }
        public void setTxtComboBoxL1(String txtComboBoxL1) {
     this. txtComboBoxL1.setText(txtComboBoxL1);
    }
    
    public void setTxtcbForma1(String txtComboBoxL1) {
     this.txtcbForma.setText(txtComboBoxL1);
    }
     public void cbInmueble(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT nombreElemento FROM elementoinmueble ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("nombreElemento"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }

}
     public void cbUbi(JComboBox comboBox) {
    Connection conn;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps;
    ResultSet rs;
    String sql= "SELECT nombre  FROM puntoscardinales ";
    try {
        conn = cn.getConexion();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {      
            comboBox.addItem(rs.getString("nombre"));
        }
    } catch (SQLException e) {
       
    }
}
       public void cbFrmaGral(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT formageneral FROM formagenerallocus";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("formageneral"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
             } 
public void actualizarLocus() throws IllegalAccessException {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;

    try {
        conn = cn.getConexion();

        int idDelElementoSeleccionado = Integer.parseInt(txtnumero.getText());
        String txtNombreLocus = txtNombreL.getText();
        String txtReferencia = txtReferenciaL.getText();
        String txtColor = txtColorL.getText();
        String txtDescripcion = txtDescripcionL.getText();

        // Validaciones de campos obligatorios
        if (txtNombreLocus.isEmpty() || txtReferencia.isEmpty() || txtColor.isEmpty() || txtDescripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si algún campo obligatorio está vacío
        }

        // Consulta SQL para actualizar datos en la tabla 'locus'
        String sql = "UPDATE locus SET nombreLocus=?, Ubicacion=?, Referencia=?, Color=?, Descripcion=?, elemento_id=?, formageneral=? WHERE Id=?";

        ps = conn.prepareStatement(sql);
        ps.setString(1, txtNombreLocus);
        ps.setString(2, cbUbicacionL.getSelectedItem().toString());
        ps.setString(3, txtReferencia);
        ps.setString(4, txtColor);
        ps.setString(5, txtDescripcion);
        ps.setString(6, ComboBoxL1.getSelectedItem().toString());
        ps.setString(7, cbFrmaGral.getSelectedItem().toString());
        ps.setInt(8, idDelElementoSeleccionado); // Usar el ID del locus para la actualización

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el locus para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

  



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        txtReferenciaL = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDescripcionL = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombreL = new javax.swing.JTextField();
        ComboBoxL1 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtColorL = new javax.swing.JTextField();
        cbUbicacionL = new javax.swing.JComboBox<>();
        cbFrmaGral = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnumero = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtComboBoxL1 = new javax.swing.JLabel();
        txtcbUbicacionL = new javax.swing.JLabel();
        txtcbForma = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 225, 206));

        jPanel5.setBackground(new java.awt.Color(245, 225, 206));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtReferenciaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReferenciaLActionPerformed(evt);
            }
        });
        jPanel5.add(txtReferenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 210, -1));

        txtDescripcionL.setColumns(20);
        txtDescripcionL.setRows(5);
        jScrollPane5.setViewportView(txtDescripcionL);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 270, 160));

        jLabel23.setText("NOMBRE DEL INMUEBLE:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel24.setText("DESCRIPCIÓN:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel25.setText("NOMBRE :");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel26.setText("REFERENCIA:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));
        jPanel5.add(txtNombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 220, -1));

        ComboBoxL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxL1ActionPerformed(evt);
            }
        });
        jPanel5.add(ComboBoxL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, -1));

        jLabel27.setText("UBICACIÓN:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel28.setText("COLOR:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));
        jPanel5.add(txtColorL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 110, -1));

        jPanel5.add(cbUbicacionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 150, -1));

        jPanel5.add(cbFrmaGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 150, -1));

        jLabel21.setText("FORMA GENERAL:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 10, -1));

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 10, -1));

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 10, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Editando registro número:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtnumero.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtnumero.setText("jLabel5");
        jPanel5.add(txtnumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, -1));

        jLabel5.setText("Anterior:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel6.setText("Anterior:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel7.setText("Anterior:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        txtComboBoxL1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtComboBoxL1.setText("jLabel8");
        jPanel5.add(txtComboBoxL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        txtcbUbicacionL.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtcbUbicacionL.setText("jLabel9");
        jPanel5.add(txtcbUbicacionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        txtcbForma.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtcbForma.setText("jLabel10");
        jPanel5.add(txtcbForma, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxL1ActionPerformed

    }//GEN-LAST:event_ComboBoxL1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            actualizarLocus();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(editarLocus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        consultarLocus con=new consultarLocus();
        con.setVisible(true);
        con.mostrarLocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtReferenciaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferenciaLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaLActionPerformed

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
            java.util.logging.Logger.getLogger(editarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new editarLocus().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxL1;
    private javax.swing.JComboBox<String> cbFrmaGral;
    private javax.swing.JComboBox<String> cbUbicacionL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField txtColorL;
    public static javax.swing.JLabel txtComboBoxL1;
    private javax.swing.JTextArea txtDescripcionL;
    private javax.swing.JTextField txtNombreL;
    private javax.swing.JTextField txtReferenciaL;
    private javax.swing.JLabel txtcbForma;
    private javax.swing.JLabel txtcbUbicacionL;
    private javax.swing.JLabel txtnumero;
    // End of variables declaration//GEN-END:variables
}
