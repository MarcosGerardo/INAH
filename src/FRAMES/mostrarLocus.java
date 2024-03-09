
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

public class mostrarLocus extends javax.swing.JFrame {

    public mostrarLocus() throws HeadlessException {
         initComponents();
        this.setLocationRelativeTo(null);
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
      //txtnumero.setText(numero);
    }
        public void setTxtInm(String inmueble) {
      ComboBoxL1.setText(inmueble);
    }
         public void setTxtUbi(String ubicacion) {
  cbUbicacionL.setText(ubicacion);
    }
         public void setTxtFor(String form) {
      cbFrmaGral.setText(form);
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
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtColorL = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        ComboBoxL1 = new javax.swing.JTextField();
        cbUbicacionL = new javax.swing.JTextField();
        cbFrmaGral = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 225, 206));

        jPanel5.setBackground(new java.awt.Color(245, 225, 206));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(txtReferenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 210, -1));

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
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        jPanel5.add(txtNombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 220, -1));

        jLabel27.setText("UBICACIÓN:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        jLabel28.setText("COLOR:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));
        jPanel5.add(txtColorL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 110, -1));

        jLabel21.setText("FORMA GENERAL:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));
        jPanel5.add(ComboBoxL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 150, -1));
        jPanel5.add(cbUbicacionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 150, -1));
        jPanel5.add(cbFrmaGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 150, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        consultarLocus con=new consultarLocus();
        con.setVisible(true);
        con.mostrarLocus();
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
            java.util.logging.Logger.getLogger(mostrarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrarLocus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new mostrarLocus().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ComboBoxL1;
    private javax.swing.JTextField cbFrmaGral;
    private javax.swing.JTextField cbUbicacionL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField txtColorL;
    private javax.swing.JTextArea txtDescripcionL;
    private javax.swing.JTextField txtNombreL;
    private javax.swing.JTextField txtReferenciaL;
    // End of variables declaration//GEN-END:variables
}
