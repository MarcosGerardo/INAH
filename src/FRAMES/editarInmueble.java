/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;


import java.awt.Checkbox;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author matam
 */
public class editarInmueble extends javax.swing.JFrame {

private Checkbox checkboxConjunto;
    public editarInmueble() {
        initComponents();
         this.setLocationRelativeTo(null);
         cbNaturaleza();
         cargarComboSitios();
         cbEvidencia();
         cbForma();
    }
  public void setId(String id) {
        txtid.setText(id);
    }
     public void setTxtNombreE(String txtNombreE) {
  editarInmueble.txtNombreE.setText(txtNombreE);
    }
         public void setTxtSector(String txtSector) {
     editarInmueble.txtSector.setText(txtSector);
    }
   
            public void setTxtExtensionE(String txtExtensionE) {
editarInmueble.txtExtensionE.setText(txtExtensionE);
            }
       public void setTxtConjunto(String txtConjunto) {
       editarInmueble.txtConjunto.setText(txtConjunto);
    }
        public void setTxtOri(String txtOri) {
       editarInmueble.txtOri.setText(txtOri);
    }
         public void setTxtAzimut(String txtAzimut) {
        editarInmueble.txtAzimut.setText(txtAzimut);
    }
        public void setTxtDescripcionE(String txtDescripcionE) {
       editarInmueble.txtDescripcionE.setText(txtDescripcionE);
    }
 

    public void setComboBoxE(JComboBox<String> ComboBoxE) {
        this.ComboBoxE = ComboBoxE;
    }

    public void setCbEvi(JComboBox<String> cbEvi) {
        this.cbEvi = cbEvi;
    }

    public void setCbNatu(String bbNatu) {
  editarInmueble.txtAnteriorNaturaleza.setText(bbNatu);
    }
     public void setCbSitio(String cbSiti) {
  editarInmueble.txtAnteriorSitio.setText(cbSiti);
    }
      public void setCbEvidencia(String cbEvi) {
  editarInmueble.txtAnteriorEvidencia.setText(cbEvi);
    }
     public void setCbForma(String cbForm) {
  editarInmueble.txtAnteriorForma.setText(cbForm);
    }
   

    // Método para establecer el estado del checkbox
  public void setConjunto(boolean estado) {
    if (ea1 != null) {
        ea1.setSelected(estado);
    }
}
public void setConjunto2(boolean estado) {
    if (ea2 != null) {
        ea2.setSelected(estado);
    }
}
public void cbNaturaleza() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT nombre FROM naturalezainmueble";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
      
        while (rs.next()) {
            String nombreNaturaleza = rs.getString("nombre"); // Corrección aquí
            cbNatu.addItem(nombreNaturaleza);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar naturaleza: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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
void cargarComboSitios()  {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT nombresitio FROM SITIO ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
   
        while (rs.next()) {
            String nombreSitio = rs.getString("nombresitio");
          
       
            ComboBoxE.addItem(nombreSitio);
    
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los sitios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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
void cbEvidencia()  {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
           String sql= "SELECT tipoevidencia FROM tipoevidencia";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
   
        while (rs.next()) {
            String nombreSitio = rs.getString("tipoevidencia");
          
            cbEvi.addItem(nombreSitio);
           
   
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los sitios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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
public void cbForma() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT nombre FROM formageneral";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
      
        while (rs.next()) {
            String nombreNaturaleza = rs.getString("nombre"); // Corrección aquí
           cbFormaG.addItem(nombreNaturaleza);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar naturaleza: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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





  



 

    public void setTxtFormaG(JComboBox<String> txtFormaG) {
        this.cbFormaG = txtFormaG;
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
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNombreE = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcionE = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        ComboBoxE = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtSector = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        txtExtensionE = new javax.swing.JTextField();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        ea1 = new javax.swing.JCheckBox();
        ea2 = new javax.swing.JCheckBox();
        jLabel197 = new javax.swing.JLabel();
        txtConjunto = new javax.swing.JTextField();
        jLabel198 = new javax.swing.JLabel();
        txtOri = new javax.swing.JTextField();
        jLabel199 = new javax.swing.JLabel();
        txtAzimut = new javax.swing.JTextField();
        jLabel200 = new javax.swing.JLabel();
        cbNatu = new javax.swing.JComboBox<>();
        jLabel201 = new javax.swing.JLabel();
        cbEvi = new javax.swing.JComboBox<>();
        jSeparator27 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();
        jSeparator32 = new javax.swing.JSeparator();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jSeparator36 = new javax.swing.JSeparator();
        jSeparator38 = new javax.swing.JSeparator();
        jSeparator39 = new javax.swing.JSeparator();
        txtid = new javax.swing.JLabel();
        sitio_id = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtAnteriorSitio = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAnteriorForma = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAnteriorNaturaleza = new javax.swing.JLabel();
        txtAnteriorEvidencia = new javax.swing.JLabel();
        cbFormaG = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 420));

        jPanel1.setBackground(new java.awt.Color(245, 225, 206));

        jPanel4.setBackground(new java.awt.Color(245, 225, 206));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setText("Nombre del Elemento:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel20.setText("DESCRIPCIÓN:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));
        jPanel4.add(txtNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 170, -1));

        txtDescripcionE.setColumns(20);
        txtDescripcionE.setRows(5);
        jScrollPane4.setViewportView(txtDescripcionE);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 230, -1));

        jLabel22.setText("Nombre del Sitio :");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        ComboBoxE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEActionPerformed(evt);
            }
        });
        jPanel4.add(ComboBoxE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 170, -1));

        jLabel11.setText("Sector:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));
        jPanel4.add(txtSector, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 170, -1));

        jLabel194.setText("Extensión en m2:");
        jPanel4.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));
        jPanel4.add(txtExtensionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 170, -1));

        jLabel195.setText("Forma General: ");
        jPanel4.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel196.setText("Elemento Aislado:");
        jPanel4.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        ea1.setText("SÍ");
        jPanel4.add(ea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        ea2.setText("No");
        jPanel4.add(ea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, -1, -1));

        jLabel197.setText("Conjunto #:");
        jPanel4.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));
        jPanel4.add(txtConjunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 170, -1));

        jLabel198.setText("Orientación:");
        jPanel4.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));
        jPanel4.add(txtOri, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 260, -1));

        jLabel199.setText("Azimut :");
        jPanel4.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));
        jPanel4.add(txtAzimut, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 260, -1));

        jLabel200.setText("Naturaleza del inmueble:");
        jPanel4.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        jPanel4.add(cbNatu, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 200, -1));

        jLabel201.setText("Tipo de evidencia:");
        jPanel4.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jPanel4.add(cbEvi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 200, -1));
        jPanel4.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 170, 10));
        jPanel4.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 170, 10));
        jPanel4.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 260, 10));
        jPanel4.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 260, 10));

        jSeparator34.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 40, 280));
        jPanel4.add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 170, 10));
        jPanel4.add(jSeparator38, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 260, 10));
        jPanel4.add(jSeparator39, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 260, 10));

        txtid.setText("jLabel1");
        jPanel4.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        sitio_id.setText("jLabel1");
        jPanel4.add(sitio_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 10, -1));

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 10, -1));

        jLabel5.setBackground(new java.awt.Color(255, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 10, -1));

        jLabel7.setBackground(new java.awt.Color(255, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 10, -1));

        jLabel8.setBackground(new java.awt.Color(255, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 10, -1));

        jLabel1.setText("Naturaleza Anterior:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        txtAnteriorSitio.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorSitio.setText("jLabel9");
        jPanel4.add(txtAnteriorSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jLabel9.setText("Forma Anterior:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        txtAnteriorForma.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorForma.setText("jLabel9");
        jPanel4.add(txtAnteriorForma, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jLabel10.setText("Evidencia Anterior:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        jLabel12.setText("Sitio Anterior:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        txtAnteriorNaturaleza.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorNaturaleza.setText("jLabel13");
        jPanel4.add(txtAnteriorNaturaleza, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        txtAnteriorEvidencia.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorEvidencia.setText("jLabel13");
        jPanel4.add(txtAnteriorEvidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        jPanel4.add(cbFormaG, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 170, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void ComboBoxEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEActionPerformed

    }//GEN-LAST:event_ComboBoxEActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        consultarInmuebles con=new consultarInmuebles();
        con.setVisible(true);
        con.mostrarInmueble();
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
            java.util.logging.Logger.getLogger(editarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarInmueble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editarInmueble().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> ComboBoxE;
    private javax.swing.JComboBox<String> cbEvi;
    private javax.swing.JComboBox<String> cbFormaG;
    private javax.swing.JComboBox<String> cbNatu;
    public static javax.swing.JCheckBox ea1;
    private javax.swing.JCheckBox ea2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JLabel sitio_id;
    private static javax.swing.JLabel txtAnteriorEvidencia;
    private static javax.swing.JLabel txtAnteriorForma;
    private static javax.swing.JLabel txtAnteriorNaturaleza;
    private static javax.swing.JLabel txtAnteriorSitio;
    public static javax.swing.JTextField txtAzimut;
    public static javax.swing.JTextField txtConjunto;
    public static javax.swing.JTextArea txtDescripcionE;
    public static javax.swing.JTextField txtExtensionE;
    public static javax.swing.JTextField txtNombreE;
    public static javax.swing.JTextField txtOri;
    public static javax.swing.JTextField txtSector;
    private javax.swing.JLabel txtid;
    // End of variables declaration//GEN-END:variables
}
