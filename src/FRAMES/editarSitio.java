/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;

import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class editarSitio extends javax.swing.JFrame {



    public editarSitio(consultarSitio aThis, Object par1) {
        initComponents();
        this.setLocationRelativeTo(null);
        cbCronos(cbCronos);
        cbTipoSito(txtTipo);
        cbRegiones(cbRegionesSitios);
        cbMunicipio(cbMunicipio);
        cbTopoForma(cbTopo);
        cbCartas(ComboBoxCarta);
        
    
        

    }

 
    editarSitio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


  
    public void setId(String id) {
        txt.setText(id);
    }
   
    public void setTxtNombreSitio(String nombreSitio) {
       txtNombreSitio.setText(nombreSitio);
    }
 

    public void setTxtTipo(String tipo) {
 txtAnteriorTipo.setText(tipo);
    }

    public void setTxtRegionesSitios(String regiones) {
   txtAnteriorRegion.setText(regiones);
    }

    public void setTxtNombreProyecto(String nombreProyecto) {
       txtNombreProyecto.setText(nombreProyecto);
    }

    public void setTxtFecha(String fecha) {
      txtFecha.setText(fecha);
    }
    public void setTxtPersona(String persona) {
        txtPersona.setText(persona);
    }

   

    public void setTxtLocalidad(String localidad) {
        txtLocalidad.setText(localidad);
    }
    
    public void setTxtMunicipio(String municipio) {
    txtAnteriorMunicipio.setText(municipio);
            }
      public void setTxtTopoForma(String forma) {
    txtAnterioTopo.setText(forma);
            }
        public void setTxtCronos(String cronos) {
    txtAnteriorCronos.setText(cronos);
            }
          public void setTxtCartas(String cartas) {
    txtAnteriorTopoGra.setText(cartas);
            }

    public void setTxtDatum(String datum) {
         txtDatum.setText(datum);
    }

    public void setTxtEste(String este) {
        txtEste.setText(este);
    }

    public void setTxtNorte(String norte) {
        txtNorte.setText(norte);
    }

    public void setTxtAltitud(String altitud) {
        txtAltitud.setText(altitud);
    }
 public void cbTipoSito(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT nombreTipo FROM tipo ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("nombreTipo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
   public void cbCronos(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT tiempoTentativo FROM cronologia";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("tiempoTentativo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
             }
      public void cbRegiones(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT nombreRegion FROM region ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("nombreRegion"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
      }
         public void cbMunicipio(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT nombreMunicipio FROM municipio ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("nombreMunicipio"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
         }
      public void cbTopoForma(JComboBox txtCarta){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       java.sql.ResultSet rs;
          String sql= "SELECT nombre FROM topoforma ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("nombre"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
      public void cbCartas(JComboBox txtCarta){
    Connection conn;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps;
    java.sql.ResultSet rs;
    String sql= "SELECT nombre FROM cartastopograficas ORDER BY nombre ASC";
    try {
        conn = cn.getConexion();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {      
            txtCarta.addItem(rs.getString("nombre"));
        }
    } catch (SQLException e) {
        System.err.println(e.toString());
    }
}

 public void setCarta( String ComboBoxCartacarta) {
       // ComboBoxCarta.setText(ComboBoxCartacarta);

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
    public void setTxtDescripcion(String descripcion) {
      txtDescripcion.setText(descripcion);
    }
     public void setExtension(String extension) {
      txtExtension.setText(extension);
      

    }//
     public int obtenerIdSeleccionado(String nombreTabla, String columnaTexto, String columnaId, JComboBox<?> comboBox) {
    // Obtener el texto seleccionado del JComboBox
    String textoSeleccionado = comboBox.getSelectedItem().toString();
    
    // Inicializar la conexión y el PreparedStatement
    Connection conn = null;
    PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    
    try {
        // Establecer la conexión
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        // Consulta SQL para obtener el ID correspondiente al texto seleccionado
        String sql = "SELECT " + columnaId + " FROM " + nombreTabla + " WHERE " + columnaTexto + " = ?";
        
        // Preparar la consulta
        ps = conn.prepareStatement(sql);
        ps.setString(1, textoSeleccionado);
        
        // Ejecutar la consulta
        rs = ps.executeQuery();
        
        // Verificar si se encontró el ID y devolverlo
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            // Manejar el caso donde no se encontró el ID correspondiente al texto seleccionado
            return -1; // Otra indicación de que no se pudo obtener el ID
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción adecuadamente según tus necesidades
        return -1; // Otra indicación de que no se pudo obtener el ID
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
  public String obtenerTexto(String nombreTabla, String columnaTexto, JComboBox<?> comboBox) {
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
        
        // Consulta SQL para obtener el texto correspondiente al texto seleccionado
        String sql = "SELECT " + columnaTexto + " FROM " + nombreTabla + " WHERE " + columnaTexto + " = ?";
        
        // Preparar la consulta
        ps = conn.prepareStatement(sql);
        ps.setString(1, textoSeleccionado);
        
        // Ejecutar la consulta
        rs = ps.executeQuery();
        
        // Verificar si se encontró el texto y devolverlo
        if (rs.next()) {
            return rs.getString(columnaTexto);
        } else {
            // Manejar el caso donde no se encontró el texto correspondiente al texto seleccionado
            return null; // Otra indicación de que no se pudo obtener el texto
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción adecuadamente según tus necesidades
        return null; // Otra indicación de que no se pudo obtener el texto
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


public void actualizarSitio() throws IllegalAccessException {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;

    try {
        conn = cn.getConexion();

        int idSitio = Integer.parseInt(txt.getText()); // Convertir el ID del sitio a entero
        String nombreSitio = txtNombreSitio.getText();
        String tipoSitio = txtTipo.getSelectedItem().toString();
      
         String  regionSitio = cbRegionesSitios.getSelectedItem().toString();
        String nombreProyecto = txtNombreProyecto.getText();
        String fechaRegistro = txtFecha.getText();
        String nombreRegistro = txtPersona.getText();
        String localidadSitio = txtLocalidad.getText();
        String municipioSitio = cbMunicipio.getSelectedItem().toString();
        String esteSitio = txtEste.getText();
        String norteSitio = txtNorte.getText();
        String altitudSitio = txtAltitud.getText();
        String datumSitio = txtDatum.getText();
        String extensionEstimStr = txtExtension.getText();
        int extensionEstim = 0; // Valor por defecto en caso de que el campo esté vacío

        if (!extensionEstimStr.isEmpty()) {
            try {
                extensionEstim = Integer.parseInt(extensionEstimStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La extensión estimada debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si no se puede convertir a un número
            }
        }

       String topoForma =cbTopo.getSelectedItem().toString();
        String  cronologiaSitio = cbCronos.getSelectedItem().toString();
        String cartastopograficas =ComboBoxCarta.getSelectedItem().toString();
        String descripcion = txtDescripcion.getText();

        // Validaciones de campos obligatorios
        if (nombreSitio.isEmpty() || nombreProyecto.isEmpty() || fechaRegistro.isEmpty() || nombreRegistro.isEmpty() || localidadSitio.isEmpty() || esteSitio.isEmpty() || norteSitio.isEmpty() || altitudSitio.isEmpty() || datumSitio.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si algún campo obligatorio está vacío
        }

        // Consulta SQL para actualizar datos en la tabla 'sitio'
        String sql = "UPDATE sitio SET nombreSitio=?, tipoSitio=?, regionSitio=?, nombreProyecto=?, fechaRegistro=?, nombreRegistro=?, localidadSitio=?, municipioSitio=?, esteSitio=?, norteSitio=?, altitudSitio=?, datumSitio=?, extensionEstim=?, topoForma=?, cronologiaSitio=?, cartastopograficas=?, descripcion=? WHERE Id=?";

        ps = conn.prepareStatement(sql);
        ps.setString(1, nombreSitio); // Establecer el valor de nombreSitio
        ps.setString(2, tipoSitio);
        ps.setString(3, regionSitio);
        ps.setString(4, nombreProyecto);
        ps.setString(5, fechaRegistro);
        ps.setString(6, nombreRegistro);
        ps.setString(7, localidadSitio);
        ps.setString(8, municipioSitio);
        ps.setString(9, esteSitio);
        ps.setString(10, norteSitio);
        ps.setString(11, altitudSitio);
        ps.setString(12, datumSitio);
        ps.setInt(13, extensionEstim);
        ps.setString(14, topoForma);
        ps.setString(15, cronologiaSitio);
        ps.setString(16, cartastopograficas);
        ps.setString(17, descripcion);
        ps.setInt(18, idSitio); // Usar el ID del sitio para la actualización

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el sitio para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Manejo de excepciones
        }
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

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreSitio = new javax.swing.JTextField();
        jLabel179 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        txtNombreProyecto = new javax.swing.JTextField();
        jLabel184 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel185 = new javax.swing.JLabel();
        txtPersona = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        txtLocalidad = new javax.swing.JTextField();
        txtDatum = new javax.swing.JTextField();
        jLabel189 = new javax.swing.JLabel();
        txtEste = new javax.swing.JTextField();
        jLabel190 = new javax.swing.JLabel();
        txtNorte = new javax.swing.JTextField();
        jLabel191 = new javax.swing.JLabel();
        txtAltitud = new javax.swing.JTextField();
        jLabel192 = new javax.swing.JLabel();
        txtExtension = new javax.swing.JTextField();
        jLabel193 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ComboBoxCarta = new javax.swing.JComboBox<>();
        cbCronos = new javax.swing.JComboBox<>();
        cbTopo = new javax.swing.JComboBox<>();
        txtTipo = new javax.swing.JComboBox<>();
        cbRegionesSitios = new javax.swing.JComboBox<>();
        cbMunicipio = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        txt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAnteriorTipo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAnterioTopo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtAnteriorMunicipio = new javax.swing.JLabel();
        txtAnteriorCronos = new javax.swing.JLabel();
        txtAnteriorTopoGra = new javax.swing.JLabel();
        txtAnteriorRegion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(245, 225, 206));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText(" Nombre de Sitio:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel9.setText("DESCRIPCION:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, -1));

        jLabel12.setText("CARTA TOPOGRAFICA:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, -1, -1));
        jPanel3.add(txtNombreSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 230, -1));
        jPanel3.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        jLabel13.setText("Tipo de Sitio: ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel98.setText("Regiones Fisiograficas: ");
        jPanel3.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel107.setText("Nombre del Proyecto: ");
        jPanel3.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        jPanel3.add(txtNombreProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 200, -1));

        jLabel184.setText("Fecha de registro: ");
        jPanel3.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 190, -1));

        jLabel185.setText("Persona que Registró:");
        jPanel3.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));
        jPanel3.add(txtPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 190, -1));

        jLabel186.setText("Municipio:");
        jPanel3.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel187.setText("Localidad: ");
        jPanel3.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel188.setText("Datum: ");
        jPanel3.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));
        jPanel3.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 120, -1));
        jPanel3.add(txtDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 120, -1));

        jLabel189.setText("Este: ");
        jPanel3.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));
        jPanel3.add(txtEste, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 120, -1));

        jLabel190.setText("Norte: ");
        jPanel3.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));
        jPanel3.add(txtNorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 120, -1));

        jLabel191.setText("Altitud:");
        jPanel3.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));
        jPanel3.add(txtAltitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 120, -1));

        jLabel192.setText("Extension en m2:  ");
        jPanel3.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));
        jPanel3.add(txtExtension, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 140, -1));

        jLabel193.setText("Topoforma: ");
        jPanel3.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        jLabel10.setText("Cronologia: ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, -1, -1));

        jPanel3.add(ComboBoxCarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 100, -1));

        jPanel3.add(cbCronos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 150, -1));

        jPanel3.add(cbTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 150, -1));

        jPanel3.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 230, -1));

        jPanel3.add(cbRegionesSitios, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, -1));

        jPanel3.add(cbMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 130, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 320, 140));

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        txt.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jPanel3.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 50, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("EDITAR REGISTRO NUMERO:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 10, -1));

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 10, -1));

        jLabel5.setBackground(new java.awt.Color(255, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 10, -1));

        jLabel6.setBackground(new java.awt.Color(255, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 10, -1));

        jLabel7.setBackground(new java.awt.Color(255, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 10, -1));

        jLabel8.setBackground(new java.awt.Color(255, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 10, -1));

        jLabel11.setText("Tipo Anterior:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        txtAnteriorTipo.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorTipo.setText("jLabel14");
        jPanel3.add(txtAnteriorTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel14.setText("Tipo Anterior:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        txtAnterioTopo.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnterioTopo.setText("jLabel15");
        jPanel3.add(txtAnterioTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        jLabel15.setText("Tipo Anterior:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, -1, -1));

        jLabel16.setText("Tipo Anterior:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, -1));

        jLabel17.setText("Tipo Anterior:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, -1, -1));

        jLabel18.setText("Tipo Anterior:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        txtAnteriorMunicipio.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorMunicipio.setText("jLabel15");
        jPanel3.add(txtAnteriorMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        txtAnteriorCronos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorCronos.setText("jLabel15");
        jPanel3.add(txtAnteriorCronos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, -1, -1));

        txtAnteriorTopoGra.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorTopoGra.setText("jLabel15");
        jPanel3.add(txtAnteriorTopoGra, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, -1, -1));

        txtAnteriorRegion.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtAnteriorRegion.setText("jLabel15");
        jPanel3.add(txtAnteriorRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
dispose();
       consultarSitio con=new consultarSitio();
   con.setVisible(true);
  con.mostraSitio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try {
          actualizarSitio();
      } catch (IllegalAccessException ex) {
          Logger.getLogger(editarSitio.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(editarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarSitio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new editarSitio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCarta;
    private javax.swing.JComboBox<String> cbCronos;
    private javax.swing.JComboBox<String> cbMunicipio;
    private javax.swing.JComboBox<String> cbRegionesSitios;
    private javax.swing.JComboBox<String> cbTopo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txt;
    private javax.swing.JTextField txtAltitud;
    private javax.swing.JLabel txtAnterioTopo;
    private javax.swing.JLabel txtAnteriorCronos;
    private javax.swing.JLabel txtAnteriorMunicipio;
    private javax.swing.JLabel txtAnteriorRegion;
    private javax.swing.JLabel txtAnteriorTipo;
    private javax.swing.JLabel txtAnteriorTopoGra;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEste;
    private javax.swing.JTextField txtExtension;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtLocalidad;
    private javax.swing.JTextField txtNombreProyecto;
    public javax.swing.JTextField txtNombreSitio;
    private javax.swing.JTextField txtNorte;
    private javax.swing.JTextField txtPersona;
    private javax.swing.JComboBox<String> txtTipo;
    // End of variables declaration//GEN-END:variables
}
