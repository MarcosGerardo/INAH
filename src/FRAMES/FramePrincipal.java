/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FRAMES;

/**
 *
 * @author matam
 */
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class FramePrincipal extends javax.swing.JFrame {



    public FramePrincipal() {
        initComponents();
        this.setTitle("Automatización y Modernización de Procesos en la Sección de Arqueología del Centro INAH Durango");
        this.setLocationRelativeTo(null);           
        vtnVentanas.setBackground(Color.BLUE);
        cbCartas(ComboBoxCarta);
        cbTipoSito(cbTipo);
        cbRegiones(cbRegionesSitios);
        cbMunicipio(cbMunicipio);
        cbTopoForma(cbTopo);
        cbCronos(cbCronos);
        cbEvi(cbEvi);
        cbUbi(cbUbicacionL);
        cbFrmaGral(cbFrmaGral);
        //Combosprincipales
        cargarComboSitios();
         cbInmueble();
         cbLocus();
       // cotros
       cbNaturaleza();
       cbForma();
       
       //panel para que se inizialice sinn seleccionar
        PanellCeramica.setVisible(false);
        PanelCeramicaDetallada.setVisible(false);
        PanelLiticaPu.setVisible(false);
        PanelLiticaTa.setVisible(false);
        PanelOtro.setVisible(false);
        visor.setVisible(false);
        btnLimpiar.setToolTipText("Limpiar formulario");btnEditar.setToolTipText("Editar formulario");btnRegistrar.setToolTipText("Registrar formulario");btnEliminar.setToolTipText("Borrar formulario");
    }
    //metodo para obtener el id foraneo
public int obtenerIdSeleccionado(String nombreTabla, String columnaTexto, String columnaId, JComboBox<?> comboBox) {

    String var = comboBox.getSelectedItem().toString();
    
    // Inicializar la conexión y el PreparedStatement
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        // Establecer la conexión
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        // Consulta SQL para obtener el ID correspondiente al texto seleccionado
        String sql = "SELECT " + columnaId + " FROM " + nombreTabla + " WHERE " + columnaTexto + " = ?";
        
        // Preparar la consulta
        ps = conn.prepareStatement(sql);
        ps.setString(1, var);
        
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


public String obtenerNombreDesdeId(String nombreTabla, String columnaTexto, String columnaId, JComboBox<?> comboBox) {
    // Obtener el ID seleccionado del JComboBox
    int idSeleccionado = (int) comboBox.getSelectedItem(); // Supongo que el JComboBox está lleno de objetos de tipo Integer

    // Inicializar la conexión y el PreparedStatement
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Establecer la conexión
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();

        // Consulta SQL para obtener el nombre correspondiente al ID seleccionado
        String sql = "SELECT " + columnaTexto + " FROM " + nombreTabla + " WHERE " + columnaId + " = ?";

        // Preparar la consulta
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idSeleccionado);

        // Ejecutar la consulta
        rs = ps.executeQuery();

        // Verificar si se encontró el nombre y devolverlo
        if (rs.next()) {
            return rs.getString(1);
        } else {
            // Manejar el caso donde no se encontró el nombre correspondiente al ID seleccionado
            return null; // Otra indicación de que no se pudo obtener el nombre
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción adecuadamente según tus necesidades
        return null; // Otra indicación de que no se pudo obtener el nombre
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

public void registrarSitios() throws IllegalAccessException {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;

    try {
        conn = cn.getConexion();

        
        String nombreSitio = txtNombreSitio.getText();
          String tipoSitio = cbTipo.getSelectedItem().toString();
        //int tipoSitio = obtenerIdSeleccionado("tipo","nombreTipo", "Id", cbTipo);
        String regionSitio = cbRegionesSitios.getSelectedItem().toString();
 ;
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
          String topoForma = cbTopo.getSelectedItem().toString();
   
       String cronologiaSitio = cbCronos.getSelectedItem().toString();
        String  cartastopograficas = ComboBoxCarta.getSelectedItem().toString();
        String descripcion = txtDescripcion.getText();

        // Validaciones de campos obligatorios
        if (nombreSitio.isEmpty() || nombreProyecto.isEmpty() || fechaRegistro.isEmpty() || nombreRegistro.isEmpty() || localidadSitio.isEmpty() || esteSitio.isEmpty() || norteSitio.isEmpty() || altitudSitio.isEmpty() || datumSitio.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si algún campo obligatorio está vacío
        }

        // Consulta SQL para insertar datos en la tabla 'sitio'
        String sql = "INSERT INTO sitio (nombreSitio, tipoSitio, regionSitio, nombreProyecto, fechaRegistro, nombreRegistro, localidadSitio, municipioSitio, esteSitio, norteSitio, altitudSitio, datumSitio, extensionEstim, topoForma, cronologiaSitio, cartastopograficas, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ps = conn.prepareStatement(sql);
        ps.setString(1, nombreSitio);
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

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("No se pudieron insertar los datos.");
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ERROR);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al registrar el sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
        }
    }
}



  public void registrarLocus() throws IllegalAccessException {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;

    try {
        conn = cn.getConexion();

        String nombreLocus = txtNombreL.getText();
        String Ubicacion = cbUbicacionL.getSelectedItem().toString();
        String Referencia= txtReferenciaL.getText();
        String Color = txtColorL.getText();
        String Descripcion = txtDescripcionL.getText();
        String elemento_id = ComboBoxL1.getSelectedItem().toString();
       String formageneral = cbFrmaGral.getSelectedItem().toString();
        
        // Consulta SQL para insertar datos en la tabla 'locus'
        String sql = "INSERT INTO locus (nombreLocus, Ubicacion, Referencia, Color, Descripcion, elemento_id, formageneral) VALUES (?, ?, ?, ?, ?, ?,?)";

        ps = conn.prepareStatement(sql);
        ps.setString(1, nombreLocus);
        ps.setString(2, Ubicacion);
        ps.setString(3, Referencia);
        ps.setString(4, Color);
        ps.setString(5, Descripcion);
        ps.setString(6, elemento_id);
        ps.setString(7, formageneral);

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("No se pudieron insertar los datos.");
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar el locus: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar errores de cierre si es necesario
        }
    }
}

  
    public String obtenerNombreSeleccionado(String nombreTabla, String columnaTexto, JComboBox<?> comboBox) {
    // Obtener el texto seleccionado del JComboBox
    String var = comboBox.getSelectedItem().toString();
    
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
        ps.setString(1, var);
        
        // Ejecutar la consulta
        rs = ps.executeQuery();
        
        // Verificar si se encontró el nombre y devolverlo
        if (rs.next()) {
            return rs.getString(1);
        } else {
            // Manejar el caso donde no se encontró el nombre correspondiente al texto seleccionado
            return null; 
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null; // Indicación de que no se pudo obtener el nombre
    } finally {
        // Cerrar los recursos
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
           
        }
    }
}


    private void limpiarSitios() {
    txtNombreSitio.setText("");
    cbTipo.setSelectedIndex(0);
    cbRegionesSitios.setSelectedIndex(0);
    txtNombreProyecto.setText("");
    txtFecha.setText("");
    txtPersona.setText("");
    txtLocalidad.setText("");
    cbMunicipio.setSelectedIndex(0); 
    txtEste.setText("");
    txtNorte.setText("");
    txtAltitud.setText("");
    txtDatum.setText("");
    txtExtension.setText("");
    cbTopo.setSelectedIndex(0); 
    cbCronos.setSelectedIndex(0); 
    ComboBoxCarta.setSelectedIndex(0);
    txtDescripcion.setText("");
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
          
            cbSitioDC.addItem(nombreSitio);
            ComboBoxE.addItem(nombreSitio);
            cbSitioCM.addItem(nombreSitio);
            cbSitio1.addItem(nombreSitio);
            cbSitio2.addItem(nombreSitio);
            cbSitio3.addItem(nombreSitio);
   
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

public void cbCartasTopo(JComboBox comboBox) {
    
    Connection conn;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps;
    ResultSet rs;
    String sql= "SELECT nombre FROM cartastopograficas ";
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
public void cbCartas(JComboBox str){
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
            str.addItem(rs.getString("nombre"));
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
public void cbInmueble() {
  
     Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT id, nombreelemento FROM elementoinmueble";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            String nombreLocus = rs.getString("nombreelemento");
                 // ComboBoxE.addItem(nombreLocus);
                 cbEstructuraDC.addItem(nombreLocus);
                 ComboBoxL1.addItem(nombreLocus);
                 cbEstructuraCM.addItem(nombreLocus);
                 cbEstructura1.addItem(nombreLocus);
                 cbEstructura2.addItem(nombreLocus);
                 cbEstructura3.addItem(nombreLocus);
                 
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar las estructuras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
public void cbLocus() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT id, nombrelocus FROM locus";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
      
        while (rs.next()) {
            String nombreLocus = rs.getString("nombrelocus");
            cbLocusDC.addItem(nombreLocus);
            cbCuarto.addItem(nombreLocus);
            cbLocus2.addItem(nombreLocus);
            cbLocus1.addItem(nombreLocus);
            cbLocus3.addItem(nombreLocus);
       
          

            
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar locus: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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



public int obtenerClavePrimariaDelRegistro() {
    int filaSeleccionada = visorLocus.getSelectedRow();
    if (filaSeleccionada != -1) {
        return Integer.parseInt(visorLocus.getValueAt(filaSeleccionada, 0).toString());
    } else {
        return -1;
    }
}

  public void eliminarRegistro() {
    int filaSeleccionada = visor.getSelectedRow();
     Connection conn = null;
     String SQL = "SELECT * FROM sitios";
     Statement st;
     CONECTOR con=new CONECTOR();
     conn = con.getConexion();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return; 
    }

    // Obtiene el ID del registro a eliminar
    String id = visor.getValueAt(filaSeleccionada, 0).toString();
    String sqlDelete = "DELETE FROM sitios WHERE id=?";
    
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
   public static void mostrar( String Tabla){
        Connection conn = null;
         String SQL = "SELECT * FROM sitios";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        DefaultTableModel model= new DefaultTableModel();
         model.addColumn("ID");
         model.addColumn("Nombre");
         model.addColumn("Descripcion");
         model.addColumn("Referencia");
         model.addColumn("Coordenadas");
         model.addColumn("CartaTopografica");
       
         visor.setModel(model);
         String [] datos =new String[6];
         try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
            
                model.addRow(datos);
                
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "ERROR" );
        }
         
        
    }
    //METODOSElementoInmueble
  
   private void registrarElementoInmueble() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;

    try {
        conn = cn.getConexion();
        Object nombreSitioSeleccionado = ComboBoxE.getSelectedItem();
        String sitio_id = nombreSitioSeleccionado.toString();
        System.out.println(sitio_id);
        String nombreElemento = txtNombreE.getText();
        String nombreSitio = ComboBoxE.getSelectedItem().toString();
        String sectorElemento = txtSector.getText();
        String extensionElemento = txtExtensionE.getText();
        String formaGeneral = cbFormaG.getSelectedItem().toString();
        boolean elementoAislado = ea1.isSelected();
        boolean elementoAisladoInt;
        int conjuntoElemeto;

        if (elementoAislado) {
            elementoAisladoInt = false; // Si es verdadero, asignamos falso
            conjuntoElemeto = 0;
        } else {
            String conjuntoElementoStr = txtConjunto.getText();
            if (!conjuntoElementoStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor entero para el campo 'Conjunto Elemento'.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método porque el valor de conjuntoElemento no es válido
            }
            elementoAisladoInt = true; // Si es falso, asignamos verdadero
            conjuntoElemeto = Integer.parseInt(conjuntoElementoStr);
        }

        // Obtener el valor numérico
        String orientacionElemento = txtOri.getText();
        String Azimut = txtAzimut.getText();
        String naturalezaInmueble = cbNatu.getSelectedItem().toString();
        String tipoEvidencia = cbEvi.getSelectedItem().toString();
        String Descripcion = txtDescripcionE.getText();

        if (!nombreElemento.isEmpty() && !nombreSitio.isEmpty() && !sectorElemento.isEmpty()
                && !extensionElemento.isEmpty() && !formaGeneral.isEmpty() && !orientacionElemento.isEmpty()
                && !Azimut.isEmpty()) {
            String sql = "INSERT INTO ELEMENTOINMUEBLE (nombreElemento, nombreSitio, sectorElemento, extensionElemento, formaGeneral, elementoAislado, conjuntoElemeto, orientacionElemento, Azimut, naturalezaInmueble, tipoEvidencia, Descripcion, sitio_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id FROM sitio WHERE nombreSitio = ?))";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombreElemento);
            ps.setString(2, nombreSitio);
            ps.setString(3, sectorElemento);
            ps.setString(4, extensionElemento);
            ps.setString(5, formaGeneral);
            ps.setBoolean(6, elementoAisladoInt);
            ps.setInt(7, conjuntoElemeto);
            ps.setString(8, orientacionElemento);
            ps.setString(9, Azimut);
            ps.setString(10, naturalezaInmueble);
            ps.setString(11, tipoEvidencia);
            ps.setString(12, Descripcion);
            ps.setString(13, sitio_id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron insertar los datos.");
                JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios antes de registrar el elemento inmueble.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar el elemento inmueble: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}



  
      public void mostrarEstructura( String Tabla){
        Connection conn = null;
         String SQL = "SELECT estructuras.*, sitios.nombre AS NombreSitio FROM estructuras JOIN sitios ON estructuras.sitio_id = sitios.id;";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        DefaultTableModel model= new DefaultTableModel();
         model.addColumn("ID");
         model.addColumn("Nombre");
         model.addColumn("Descripcion");
         model.addColumn("Referencia");
         model.addColumn("sitio_id");
         model.addColumn("Sitio");
      
         visorEstructuras.setModel(model);
         String [] datos =new String[6];
         try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                 datos[5]=rs.getString(6);
                model.addRow(datos);
                
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "ERROR" );
        }
         
        
    }

  

private int obtenerClavePrimariaDelRegistro(int filaSeleccionada) {
    try {
        Object valorCelda = visorEstructuras.getValueAt(filaSeleccionada, 0);

        if (valorCelda instanceof Integer) {
            return (Integer) valorCelda;
        } else if (valorCelda instanceof String) {
            try {
                return Integer.parseInt((String) valorCelda);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al obtener la clave primaria: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("El valor en la celda no es una clave primaria válida.");
            }
        }
    } catch (IndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener la clave primaria: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        throw new IllegalArgumentException("No se pudo obtener la clave primaria.");
    }

    JOptionPane.showMessageDialog(null, "No se pudo obtener la clave primaria.", "Error", JOptionPane.ERROR_MESSAGE);
    throw new IllegalArgumentException("No se pudo obtener la clave primaria.");
}


void cargarEstructuraCombo() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT id, nombre FROM estructuras ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        ComboBoxL1.removeAllItems();
        while (rs.next()) {
            String nombreSitio = rs.getString("nombre");
           // ComboBoxL1.addItem(nombreSitio);
            cbEstructuraCM.addItem(nombreSitio);
            cbEstructura1.addItem(nombreSitio);
           cbEstructura3.addItem(nombreSitio);
           cbEstructuraDC.addItem(nombreSitio);
           cbEstructura2.addItem(nombreSitio);
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


  
    //METODOS MATERIALES

    //OTROS METODOS
 
      public void cbTipoSito(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT nombreTipo FROM tipo ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("nombreTipo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
       public void cbRegiones(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT nombreRegion FROM region ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("nombreRegion"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
        public void cbMunicipio(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT nombreMunicipio FROM municipio ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("nombreMunicipio"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
         public void cbTopoForma(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT nombre FROM topoforma ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("nombre"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
             public void cbCronos(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT tiempoTentativo FROM cronologia";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("tiempoTentativo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
             }
             
                         public void cbEvi(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT tipoevidencia FROM tipoevidencia";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("tipoevidencia"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
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
            
      
 
    public void AutocompleterTwo(JComboBox str){
       Connection conn;
       CONECTOR cn = new CONECTOR();
       PreparedStatement ps;
       ResultSet rs;
          String sql= "SELECT Tipo FROM tipos ";
          try {
              conn = cn.getConexion();
              ps = conn.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {      
                  str.addItem(rs.getString("Tipo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}

    
private void registrarLiticatallada() {
    Connection conn = null;
    PreparedStatement ps = null;
    
    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        String sitio = cbSitio1.getSelectedItem().toString();
        String bolsa = jTextField67.getText();
        String unidad = jTextField68.getText();
        String estructura = cbEstructura1.getSelectedItem().toString();
        String cuarto_o_subestructura = cbLocus1.getSelectedItem().toString();
        String e = jTextField69.getText();
        String n = jTextField73.getText();
        String rt = jTextFieldRT.getText();
        String estrato = jTextField70.getText();
        String clase_litica = jTextField711.getText();
        String tipo_litico = jTextField72.getText();
        String materia_prima = jTextField74.getText();
        String cortex = jTextField75.getText();
        String largo = jTextField76.getText();
        String ancho = jTextField77.getText();
        String grosor = jTextField81.getText();
        String observaciones = jTextArea1.getText();
        String excavo_o_registro = jTextField82.getText();
        String analizo = jTextField83.getText();
        
        String sql = "INSERT INTO liticatallada (Sitio, Bolsa, Unidad, Estructura, Cuarto_o_subestructura, E, N, RT, Estrato, Clase_litica, Tipo_litico, Materia_Prima, Cortex, Largo, Ancho, Grosor, Observaciones, Excavo_o_Registro, Analizo) VALUES "
               + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, sitio);
        ps.setString(2, bolsa);
        ps.setString(3, unidad);
        ps.setString(4, estructura);
        ps.setString(5, cuarto_o_subestructura);
        ps.setString(6, e);
        ps.setString(7, n);
        ps.setString(8, rt);
        ps.setString(9, estrato);
        ps.setString(10, clase_litica);
        ps.setString(11, tipo_litico);
        ps.setString(12, materia_prima);
        ps.setString(13, cortex);
        ps.setString(14, largo);
        ps.setString(15, ancho);
        ps.setString(16, grosor);
        ps.setString(17, observaciones);
        ps.setString(18, excavo_o_registro);
        ps.setString(19, analizo);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println(e.toString());
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
private void limpiarCampostallada() {
    cbSitio1.setSelectedIndex(0);
    jTextField67.setText("");
    jTextField68.setText("");
    cbEstructura1.setSelectedIndex(0);
    cbLocus1.setSelectedIndex(0);
    jTextField69.setText("");
    jTextField73.setText("");
    jTextFieldRT.setText("");
    jTextField70.setText("");
    jTextField711.setText("");
    jTextField72.setText("");
    jTextField74.setText("");
    jTextField75.setText("");
    jTextField76.setText("");
    jTextField77.setText("");
    jTextField81.setText("");
    jTextArea1.setText("");
    jTextField82.setText("");
    jTextField83.setText("");
}
private void limpiarCamposPu() {
cbSitio3.setSelectedIndex(0);
    nobolsa.setText("");
    cbEstructura3.setSelectedIndex(0);
    cbLocus3.setSelectedIndex(0);
    EE.setText("");
    NN.setText("");
    COMPLETADO.setText("");
    FRACTURADO.setText("");
    METATE.setText("");
    MORTERO.setText("");
    MMETATE.setText("");
    MMORTERO.setText("");
    PULIDOR.setText("");
    HACHA.setText("");
    OTRO.setText("");
    MATERIA.setText("");
    CARAS.setText("");
    LARGO.setText("");
    ANCHO.setText("");
    GROSOR.setText("");
    REGIS.setText("");
    ANA.setText("");
    OBSERVACIONES.setText("");
}
  public void getCbCronos(String cronos) {
        cbCronos.setSelectedItem(cronos);
    }



private void limpiarCamposEditarsitios() {
      
       
    cbSitioCM.setSelectedIndex(0);
    txtnumeroBolsa.setText("");
    txtUnidad.setText("");
    cbEstructuraCM.setSelectedIndex(0);
    cbCuarto.setSelectedIndex(0);
    txtE.setText("");
    txtN.setText("");
    txtRT.setText("");
    txtEstrato.setText("");
    txtTotal.setText("");
    txtRojo.setText("");
    txtNegro.setText("");
    txtCafe.setText("");
    txtBurdoLiso.setText("");
    txtUña.setText("");
    txtPeinado.setText("");
    txtTexturizado.setText("");
    txtTierraBatida.setText("");
    txtOtro.setText("");
    txtPlato.setText("");
    txtCajete.setText("");
    txtOlla.setText("");
    txtVaso.setText("");
    txtJarra.setText("");
    txtMolcajete.setText("");
    txtNoId.setText("");
    txtBorde.setText("");
    txtCuerpo.setText("");
    txtAsa.setText("");
    txtSoporte.setText("");
    txtRegistro.setText("");
    txtAnalizo.setText("");
}

   private void registrarCeramicaDecorada() {
    Connection conn = null;
    PreparedStatement ps = null;
    
    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        String sitio = cbSitioDC.getSelectedItem().toString();
        String numeroBolsa = txtBolsadec.getText();
        String unidad = txtUni.getText();
        String estructura = cbEstructuraDC.getSelectedItem().toString();
        String cuarto = cbLocusDC.getSelectedItem().toString();
        String e = txtEdec.getText();
        String n = txtNdec.getText();
        String rt = txtRtDec.getText();
        String estrato = txtEstratodec.getText();
        String total = txtTotaldec.getText();
        String suchil = txtSuchil.getText();
        String vesuvio = txtVesuvio.getText();
        String michilia = txtMichi.getText();
        String amaro = txtAmaro.getText();
        String mercado = txtMer.getText();
        String neveria = txtNev.getText();
        String refugio = txtRef.getText();
        String lolandis = txtLol.getText();
        String otinapa = txtOti.getText();
        String morcillo = txtMor.getText();
        String nayar = txtNay.getText();
        String canatlan = txtCan.getText();
        String madero_fluted = txtMad.getText();
        String de_la_costa = txtCost.getText();
        String ni = txtNoI.getText();
        String plato = txtPlat.getText();
        String cajete = txtCaj.getText();
        String olla = txtOlladec.getText();
        String vaso = txtVas.getText();
        String jarra = txtJarr.getText();
        String copa = txtCop.getText();
        String cajete_asa_de_canasta = txtAsadec.getText();
        String molcajete = txtMol.getText();
        String ni2 = txtNoII.getText();
        String borde = txtBord.getText();
        String cuerpo = txtBody.getText();
        String asa = txtAs.getText();
        String soportes = txtSop.getText();
        String registro = txtReg.getText();
        String analizo = txtAnali.getText();

        String sql = "INSERT INTO ceramicadecorada (Sitio, Bolsa, Unidad, Estructura, Cuarto_o_subestructura, E, N, RT, Estrato, Total, Suchil, Vesuvio, Michilia, Amaro, Mercado, Neveria, Refugio, Lolandis, Otinapa, Morcillo, Nayar, Canatlan, Madero_Fluted, De_la_costa, NI, plato, cajete, olla, vaso, jarra, copa, cajete_asa_de_canasta, molcajete, Nid, borde, cuerpo, asa, soportes, Registro, Analizo) VALUES "
               + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, sitio);
        ps.setString(2, numeroBolsa);
        ps.setString(3, unidad);
        ps.setString(4, estructura);
        ps.setString(5, cuarto);
        ps.setString(6, e);
        ps.setString(7, n);
        ps.setString(8, rt);
        ps.setString(9, estrato);
        ps.setString(10, total);
        ps.setString(11, suchil);
        ps.setString(12, vesuvio);
        ps.setString(13, michilia);
        ps.setString(14, amaro);
        ps.setString(15, mercado);
        ps.setString(16, neveria);
        ps.setString(17, refugio);
        ps.setString(18, lolandis);
        ps.setString(19, otinapa);
        ps.setString(20, morcillo);
        ps.setString(21, nayar);
        ps.setString(22, canatlan);
        ps.setString(23, madero_fluted);
        ps.setString(24, de_la_costa);
        ps.setString(25, ni);
        ps.setString(26, plato);
        ps.setString(27, cajete);
        ps.setString(28, olla);
        ps.setString(29, vaso);
        ps.setString(30, jarra);
        ps.setString(31, copa);
        ps.setString(32, cajete_asa_de_canasta);
        ps.setString(33, molcajete);
        ps.setString(34, ni2);
        ps.setString(35, borde);
        ps.setString(36, cuerpo);
        ps.setString(37, asa);
        ps.setString(38, soportes);
        ps.setString(39, registro);
        ps.setString(40, analizo);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println(e.toString());
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
   private void registrarCeramicaMonocroma() {
    Connection conn = null;
    PreparedStatement ps = null;
    
    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        String sitio = cbSitioCM.getSelectedItem().toString();
        String numeroBolsa = txtnumeroBolsa.getText();
        String unidad = txtUnidad.getText();
        String estructura = cbEstructuraCM.getSelectedItem().toString();
        String cuarto = cbCuarto.getSelectedItem().toString(); 
        String e = txtE.getText();
        String n = txtN.getText();
        String rt = txtRT.getText();
        String estrato = txtEstrato.getText();
        String total = txtTotal.getText();
        String rojo = txtRojo.getText();
        String negro = txtNegro.getText();
        String cafe = txtCafe.getText();
        String burdoLiso = txtBurdoLiso.getText();
        String impresionUna = txtUña.getText();
        String peinado = txtPeinado.getText();
        String texturizado = txtTexturizado.getText();
        String conTierraBatida = txtTierraBatida.getText();
        String otro = txtOtro.getText();
        String plato = txtPlato.getText();
        String cajete = txtCajete.getText();
        String olla = txtOlla.getText();
        String vaso = txtVaso.getText();
        String jarra = txtJarra.getText();
        String molcajete = txtMolcajete.getText();
        String no_id = txtNoId.getText(); // Ahora No_id es de tipo String
        String borde = txtBorde.getText();
        String cuerpo = txtCuerpo.getText();
        String asa = txtAsa.getText();
        String soportes = txtSoporte.getText();
        String registro = txtRegistro.getText();
        String analizo = txtAnalizo.getText();
        
        String sql = "INSERT INTO ceramicamonocroma (Sitio, Numero_de_bolsa, Unidad, Estructura, Cuarto, E, N, RT, Estrato, Total, Rojo, Negro, Cafe, Burdo_liso, Impresion_una, Peinado, Texturizado, Con_tierra_batida, Otro, Plato, Cajete, Olla, Vaso, Jarra, Molcajete, No_id, Borde, Cuerpo, Asa, Soportes, Registro, Analizo) VALUES "
               + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, sitio);
        ps.setString(2, numeroBolsa);
        ps.setString(3, unidad);
        ps.setString(4, estructura);
        ps.setString(5, cuarto);
        ps.setString(6, e);
        ps.setString(7, n);
        ps.setString(8, rt);
        ps.setString(9, estrato);
        ps.setString(10, total);
        ps.setString(11, rojo);
        ps.setString(12, negro);
        ps.setString(13, cafe);
        ps.setString(14, burdoLiso);
        ps.setString(15, impresionUna);
        ps.setString(16, peinado);
        ps.setString(17, texturizado);
        ps.setString(18, conTierraBatida);
        ps.setString(19, otro);
        ps.setString(20, plato);
        ps.setString(21, cajete);
        ps.setString(22, olla);
        ps.setString(23, vaso);
        ps.setString(24, jarra);
        ps.setString(25, molcajete);
        ps.setString(26, no_id); // Ahora No_id es de tipo String
        ps.setString(27, borde);
        ps.setString(28, cuerpo);
        ps.setString(29, asa);
        ps.setString(30, soportes);
        ps.setString(31, registro);
        ps.setString(32, analizo);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println(e.toString());
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
private void limpiarCamposDecorada() {
    cbSitioDC.setSelectedIndex(0);
    txtBolsadec.setText("");
    txtUni.setText("");
    cbEstructuraDC.setSelectedIndex(0);
    cbLocusDC.setSelectedIndex(0);
    txtEdec.setText("");
    txtNdec.setText("");
    txtRtDec.setText("");
    txtEstratodec.setText("");
    txtTotaldec.setText("");
    txtSuchil.setText("");
    txtVesuvio.setText("");
    txtMichi.setText("");
    txtAmaro.setText("");
    txtMer.setText("");
    txtNev.setText("");
    txtRef.setText("");
    txtLol.setText("");
    txtOti.setText("");
    txtMor.setText("");
    txtNay.setText("");
    txtCan.setText("");
    txtMad.setText("");
    txtCost.setText("");
    txtNoI.setText("");
    txtPlat.setText("");
    txtCaj.setText("");
    txtOlladec.setText("");
    txtVas.setText("");
    txtJarr.setText("");
    txtCop.setText("");
    txtAsadec.setText("");
    txtMol.setText("");
    txtNoII.setText("");
    txtBord.setText("");
    txtBody.setText("");
    txtAs.setText("");
    txtSop.setText("");
    txtReg.setText("");
    txtAnali.setText("");
}

   private void registrarLiticapulida() {
    Connection conn = null;
    PreparedStatement ps = null;
    
    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        String sitio = cbSitio3.getSelectedItem().toString();
        String numeroBolsa = nobolsa.getText();
        String estructura = cbEstructura3.getSelectedItem().toString();
        String cuarto = cbLocus3.getSelectedItem().toString();
        String e = EE.getText();
        String n = NN.getText();
        String completo = COMPLETADO.getText();
        String fracturado = FRACTURADO.getText();
        String metate = METATE.getText();
        String mortero = MORTERO.getText();
        String mano_metate = MMETATE.getText();
        String mano_mortero = MMORTERO.getText();
        String pulidor = PULIDOR.getText();
        String hacha = HACHA.getText();
        String otro = OTRO.getText();
        String materia_prima = MATERIA.getText();
        String caras_pulidas = CARAS.getText();
        String largo = LARGO.getText();
        String ancho = ANCHO.getText();
        String grosor = GROSOR.getText();
        String registro = REGIS.getText();
        String analizo = ANA.getText();
        String observaciones = OBSERVACIONES.getText();

        String sql = "INSERT INTO liticapulida (Sito, Numero_de_bolsa, Estructura, Locus, E, N, Completo, Fracturado, Metate, Mortero, Mano_metate, Mano_Mortero, Pulidor, Hacha, Otro, Materia_Prima, Caras_pulidas, Largo, Ancho, Grosor, Registro, Analizo, Observaciones) VALUES "
               + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, sitio);
        ps.setString(2, numeroBolsa);
        ps.setString(3, estructura);
        ps.setString(4, cuarto);
        ps.setString(5, e);
        ps.setString(6, n);
        ps.setString(7, completo);
        ps.setString(8, fracturado);
        ps.setString(9, metate);
        ps.setString(10, mortero);
        ps.setString(11, mano_metate);
        ps.setString(12, mano_mortero);
        ps.setString(13, pulidor);
        ps.setString(14, hacha);
        ps.setString(15, otro);
        ps.setString(16, materia_prima);
        ps.setString(17, caras_pulidas);
        ps.setString(18, largo);
        ps.setString(19, ancho);
        ps.setString(20, grosor);
        ps.setString(21, registro);
        ps.setString(22, analizo);
        ps.setString(23, observaciones);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println(e.toString());
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
   private void registrarOtroMaterial() {
    Connection conn = null;
    PreparedStatement ps = null;
    
    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        
        String sitio = cbSitio2.getSelectedItem().toString();
        String estructura = cbEstructura2.getSelectedItem().toString();
        String locus = cbLocus2.getSelectedItem().toString();
        String numeroBolsa = jTextField80.getText();
        String unidad = jTextField84.getText();
        String cuarto = jTextField85.getText();
        String e = jTextField89.getText();
        String n = jTextField86.getText();
        String estrato = jTextField87.getText();
        String total = jTextField88.getText();
        String rt = jTextField1.getText();
        String clase = jTextField90.getText();
        String tipo = jTextField91.getText();
        String materiaPrima = jTextField92.getText();
        String cortex = jTextField93.getText();
        String largo = jTextField94.getText();
        String ancho = jTextField95.getText();
        String grosor = jTextField96.getText();
        String observacion = jTextArea2.getText();
        String registro = jTextField97.getText();
        String analizo = jTextField98.getText();
        
        String sql = "INSERT INTO otromaterial (Sitio, Estructura, Locus, Numero_de_bolsa, Unidad, Cuarto, E, N, Estrato, Total, RT, clase, tipo, materiaprima, cortex, largo, ancho, grosor, observacion, Registro, Analizo) VALUES "
               + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, sitio);
        ps.setString(2, estructura);
        ps.setString(3, locus);
        ps.setString(4, numeroBolsa);
        ps.setString(5, unidad);
        ps.setString(6, cuarto);
        ps.setString(7, e);
        ps.setString(8, n);
        ps.setString(9, estrato);
        ps.setString(10, total);
        ps.setString(11, rt);
        ps.setString(12, clase);
        ps.setString(13, tipo);
        ps.setString(14, materiaPrima);
        ps.setString(15, cortex);
        ps.setString(16, largo);
        ps.setString(17, ancho);
        ps.setString(18, grosor);
        ps.setString(19, observacion);
        ps.setString(20, registro);
        ps.setString(21, analizo);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println(e.toString());
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
   private void limpiarCamposoTRO() {
    cbSitio2.setSelectedIndex(0);
    cbEstructura2.setSelectedIndex(0);
    cbLocus2.setSelectedIndex(0);
    jTextField80.setText("");
    jTextField84.setText("");
    jTextField85.setText("");
    jTextField89.setText("");
    jTextField86.setText("");
    jTextField87.setText("");
    jTextField88.setText("");
    jTextField1.setText("");
    jTextField90.setText("");
    jTextField91.setText("");
    jTextField92.setText("");
    jTextField93.setText("");
    jTextField94.setText("");
    jTextField95.setText("");
    jTextField96.setText("");
    jTextArea2.setText("");
    jTextField97.setText("");
    jTextField98.setText("");
}






    public static void MaterialNuevo() {
     
        int deseaTrabajarConMateriales = JOptionPane.showConfirmDialog(null, "¿Desea trabajar con materiales nuevos?", "Pregunta", JOptionPane.YES_NO_OPTION);

        if (deseaTrabajarConMateriales == JOptionPane.YES_OPTION) {
            Connection conn;
            CONECTOR cn = new CONECTOR();
            conn = cn.getConexion();
            String[] materiales = obtenerMaterialesDesdeBaseDeDatos(conn);
            JComboBox<String> comboBox = new JComboBox<>(materiales);
            comboBox.setPreferredSize(new Dimension(400, 50));
            comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Crear botones "Aceptar" y "Cancelar"
            JButton aceptarButton = new JButton("Aceptar");
            JButton cancelarButton = new JButton("Cancelar");

            // Crear un JFrame personalizado
            JFrame customFrame = new JFrame("Selección de Material");
            customFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            customFrame.setLayout(new BoxLayout(customFrame.getContentPane(), BoxLayout.Y_AXIS));

            // Agregar el JComboBox al JFrame personalizado
            customFrame.add(comboBox);

            // Crear un JPanel para contener los botones y configurar el diseño en fila
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            // Agregar los botones al JPanel de botones
            buttonPanel.add(aceptarButton);
            buttonPanel.add(cancelarButton);

            // Agregar el JPanel de botones al JFrame personalizado
            customFrame.add(buttonPanel);

            // Agregar un ActionListener al botón "Aceptar" para manejar la selección del usuario
            aceptarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedMaterial = (String) comboBox.getSelectedItem();
                    if (selectedMaterial != null) {

                        if ("Ceramica Monocroma ".equals(selectedMaterial)) {
                            System.out.println("Ceramica Monocroma");
                            PanelCeramicaDetallada.setVisible(false);
                             PanelLiticaTa.setVisible(false);
                              PanelLiticaPu.setVisible(false);
                              PanelOtro.setVisible(false);
                            PanellCeramica.setVisible(true);
                        } else if ("Cerámica Decorada ".equals(selectedMaterial)) {
                            System.out.println("Cerámica Decorada");
                            PanellCeramica.setVisible(false);
                             PanelLiticaTa.setVisible(false);
                              PanelLiticaPu.setVisible(false);
                               PanelOtro.setVisible(false);
                            PanelCeramicaDetallada.setVisible(true);
                        } else if ("Lítica Tallada".equals(selectedMaterial)) {
                              PanellCeramica.setVisible(false);
                              PanelCeramicaDetallada.setVisible(false);
                               PanelLiticaPu.setVisible(false);
                                PanelOtro.setVisible(false);
                            PanelLiticaTa.setVisible(true);
                            System.out.println("Lítica Tallada");
                        } else if ("Lítica Pulida ".equals(selectedMaterial)) {
                             PanellCeramica.setVisible(false);
                             PanelLiticaTa.setVisible(false);
                             PanelCeramicaDetallada.setVisible(false);
                            PanelLiticaPu.setVisible(true);
                             PanelOtro.setVisible(false);
                            System.out.println("Lítica Pulida");
                        }
                        else if ("Otro".equals(selectedMaterial)) {
                              PanellCeramica.setVisible(false);
                             PanelLiticaTa.setVisible(false);
                             PanelCeramicaDetallada.setVisible(false);
                            PanelLiticaPu.setVisible(false);
                             PanelOtro.setVisible(true);
                        }
                        else {
                            System.out.println("Otro material");
                        }

                        customFrame.dispose();
                    }
                }
            });

            // Agregar un ActionListener al botón "Cancelar" para cancelar la selección
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cerrar el JFrame personalizado al hacer clic en "Cancelar"
                    customFrame.dispose();
                }
            });

            // Centrar el JFrame en la pantalla
            customFrame.pack();
            customFrame.setLocationRelativeTo(null);

            // Hacer visible el JFrame personalizado
            customFrame.setVisible(true);
        } else {
      
        }
    }
 

    

    private static String[] obtenerMaterialesDesdeBaseDeDatos(Connection conn) {
        String[] materiales = null;

        try {
            String query = "SELECT nombre_material FROM Materiales";
            PreparedStatement statement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            materiales = new String[rowCount];

            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                materiales[i] = rs.getString("nombre_material");
                i++;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiales;
    }
;
    
    //registrar materiales
    
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        visorEstructuras = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        visorLocus = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        btnLocus2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnLocus1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnEstructuras = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        casa = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        btnLocus = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSitios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        vtnVentanas = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreSitio = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        ComboBoxCarta = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        cbRegionesSitios = new javax.swing.JComboBox<>();
        jLabel107 = new javax.swing.JLabel();
        txtNombreProyecto = new javax.swing.JTextField();
        jLabel184 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel185 = new javax.swing.JLabel();
        txtPersona = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        cbMunicipio = new javax.swing.JComboBox<>();
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
        cbTopo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbCronos = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar1 = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNombreE = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcionE = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        ComboBoxE = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
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
        jSeparator28 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();
        jSeparator30 = new javax.swing.JSeparator();
        jSeparator31 = new javax.swing.JSeparator();
        jSeparator32 = new javax.swing.JSeparator();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jSeparator35 = new javax.swing.JSeparator();
        jSeparator36 = new javax.swing.JSeparator();
        jSeparator37 = new javax.swing.JSeparator();
        jSeparator38 = new javax.swing.JSeparator();
        jSeparator39 = new javax.swing.JSeparator();
        jSeparator40 = new javax.swing.JSeparator();
        cbFormaG = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnLimpiarLocus = new javax.swing.JButton();
        txtReferenciaL = new javax.swing.JTextField();
        btnEditarLocus = new javax.swing.JButton();
        btnRegistrarLocus = new javax.swing.JButton();
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
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        cbUbicacionL = new javax.swing.JComboBox<>();
        cbFrmaGral = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        Contenedor = new javax.swing.JPanel();
        PanelOtro = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        jTextField80 = new javax.swing.JTextField();
        jLabel152 = new javax.swing.JLabel();
        jTextField84 = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        jTextField85 = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jTextField86 = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jTextField87 = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        jTextField88 = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jTextField89 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel30 = new javax.swing.JPanel();
        cbSitio2 = new javax.swing.JComboBox<>();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        cbEstructura2 = new javax.swing.JComboBox<>();
        cbLocus2 = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jTextField90 = new javax.swing.JTextField();
        jLabel163 = new javax.swing.JLabel();
        jTextField91 = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        jTextField92 = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jTextField93 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jTextField94 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jTextField95 = new javax.swing.JTextField();
        jLabel168 = new javax.swing.JLabel();
        jTextField96 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jTextField97 = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        PanellCeramica = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cbSitioCM = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbEstructuraCM = new javax.swing.JComboBox<>();
        cbCuarto = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtN = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtEstrato = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        txtRT = new javax.swing.JTextField();
        txtnumeroBolsa = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtRojo = new javax.swing.JTextField();
        txtNegro = new javax.swing.JTextField();
        txtCafe = new javax.swing.JTextField();
        txtBurdoLiso = new javax.swing.JTextField();
        txtUña = new javax.swing.JTextField();
        txtPeinado = new javax.swing.JTextField();
        txtTexturizado = new javax.swing.JTextField();
        txtTierraBatida = new javax.swing.JTextField();
        txtOtro = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtPlato = new javax.swing.JTextField();
        txtOlla = new javax.swing.JTextField();
        txtCajete = new javax.swing.JTextField();
        txtVaso = new javax.swing.JTextField();
        txtJarra = new javax.swing.JTextField();
        txtMolcajete = new javax.swing.JTextField();
        jLabel172 = new javax.swing.JLabel();
        txtNoId = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtBorde = new javax.swing.JTextField();
        txtCuerpo = new javax.swing.JTextField();
        txtAsa = new javax.swing.JTextField();
        txtSoporte = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtRegistro = new javax.swing.JTextField();
        txtAnalizo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        PanelCeramicaDetallada = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        cbSitioDC = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        cbEstructuraDC = new javax.swing.JComboBox<>();
        cbLocusDC = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        txtBolsadec = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtUni = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtNdec = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtEstratodec = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtTotaldec = new javax.swing.JTextField();
        txtEdec = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtRtDec = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtNoI = new javax.swing.JTextField();
        txtSuchil = new javax.swing.JTextField();
        txtVesuvio = new javax.swing.JTextField();
        txtMichi = new javax.swing.JTextField();
        txtAmaro = new javax.swing.JTextField();
        txtMer = new javax.swing.JTextField();
        txtNev = new javax.swing.JTextField();
        txtRef = new javax.swing.JTextField();
        txtLol = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        txtOti = new javax.swing.JTextField();
        txtMor = new javax.swing.JTextField();
        txtNay = new javax.swing.JTextField();
        txtCan = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        txtNoII = new javax.swing.JTextField();
        txtPlat = new javax.swing.JTextField();
        txtOlladec = new javax.swing.JTextField();
        txtCaj = new javax.swing.JTextField();
        txtVas = new javax.swing.JTextField();
        txtJarr = new javax.swing.JTextField();
        txtMol = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        txtAsadec = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtCop = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        txtMad = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        txtBord = new javax.swing.JTextField();
        txtBody = new javax.swing.JTextField();
        txtAs = new javax.swing.JTextField();
        txtSop = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        txtReg = new javax.swing.JTextField();
        txtAnali = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        PanelLiticaTa = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cbSitio1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbEstructura1 = new javax.swing.JComboBox<>();
        cbLocus1 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jTextField67 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jTextField68 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jTextField73 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jTextField70 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextFieldRT = new javax.swing.JTextField();
        jLabel173 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        jTextField711 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jTextField72 = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jTextField74 = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jTextField75 = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel23 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        jTextField76 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jTextField77 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jTextField81 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jTextField82 = new javax.swing.JTextField();
        jTextField83 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        PanelLiticaPu = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        REGIS = new javax.swing.JTextField();
        ANA = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        OBSERVACIONES = new javax.swing.JTextArea();
        jLabel150 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        cbSitio3 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        cbEstructura3 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        cbLocus3 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nobolsa = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        EE = new javax.swing.JTextField();
        NN = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        COMPLETADO = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        FRACTURADO = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        METATE = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        MORTERO = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        MMETATE = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        MMORTERO = new javax.swing.JTextField();
        jLabel140 = new javax.swing.JLabel();
        PULIDOR = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        HACHA = new javax.swing.JTextField();
        OTRO = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        MATERIA = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        CARAS = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        LARGO = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        ANCHO = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        GROSOR = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/DELETE.png"))); // NOI18N
        btnEliminar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEliminarMouseMoved(evt);
            }
        });
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        visor.setModel(new javax.swing.table.DefaultTableModel(
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
        visor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(visor);

        visorEstructuras.setModel(new javax.swing.table.DefaultTableModel(
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
        visorEstructuras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visorEstructurasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(visorEstructuras);

        visorLocus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        visorLocus.setForeground(new java.awt.Color(0, 0, 0));
        visorLocus.setModel(new javax.swing.table.DefaultTableModel(
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
        visorLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visorLocusMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(visorLocus);

        jButton1.setText("jButton1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 183, 164));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(151, 112, 112));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(100, 124, 108));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/salir.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        btnLocus2.setBackground(new java.awt.Color(232, 195, 158));
        btnLocus2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocus2MouseMoved(evt);
            }
        });
        btnLocus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocus2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocus2MouseExited(evt);
            }
        });

        jLabel16.setText("MATERIALES");

        jLabel17.setBackground(new java.awt.Color(232, 195, 158));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/materiales.png"))); // NOI18N

        javax.swing.GroupLayout btnLocus2Layout = new javax.swing.GroupLayout(btnLocus2);
        btnLocus2.setLayout(btnLocus2Layout);
        btnLocus2Layout.setHorizontalGroup(
            btnLocus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(38, 38, 38))
        );
        btnLocus2Layout.setVerticalGroup(
            btnLocus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(15, 15, 15))
        );

        jPanel1.add(btnLocus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 150, 50));

        btnLocus1.setBackground(new java.awt.Color(232, 195, 158));
        btnLocus1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocus1MouseMoved(evt);
            }
        });
        btnLocus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocus1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocus1MouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel14.setText("LOCUS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/locus.png"))); // NOI18N

        javax.swing.GroupLayout btnLocus1Layout = new javax.swing.GroupLayout(btnLocus1);
        btnLocus1.setLayout(btnLocus1Layout);
        btnLocus1Layout.setHorizontalGroup(
            btnLocus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        btnLocus1Layout.setVerticalGroup(
            btnLocus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLocus1Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(btnLocus1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(btnLocus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 50));

        btnEstructuras.setBackground(new java.awt.Color(232, 195, 158));
        btnEstructuras.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEstructurasMouseMoved(evt);
            }
        });
        btnEstructuras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstructurasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEstructurasMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("ELEMENTOS ");

        casa.setBackground(new java.awt.Color(96, 219, 164));
        casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/estructura.png"))); // NOI18N

        jLabel202.setText("INMUEBLES");

        javax.swing.GroupLayout btnEstructurasLayout = new javax.swing.GroupLayout(btnEstructuras);
        btnEstructuras.setLayout(btnEstructurasLayout);
        btnEstructurasLayout.setHorizontalGroup(
            btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEstructurasLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(casa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel202, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        btnEstructurasLayout.setVerticalGroup(
            btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEstructurasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(casa)
                    .addGroup(btnEstructurasLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel202)))
                .addGap(9, 9, 9))
        );

        jPanel1.add(btnEstructuras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 50));

        btnLocus.setBackground(new java.awt.Color(96, 219, 164));
        btnLocus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLocusMouseMoved(evt);
            }
        });
        btnLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocusMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocusMouseExited(evt);
            }
        });

        jLabel6.setText("LOCUS");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Cedarseed-British-Museum-Aztec-mask-of-Xiuhtecuhtli.48.png"))); // NOI18N

        javax.swing.GroupLayout btnLocusLayout = new javax.swing.GroupLayout(btnLocus);
        btnLocus.setLayout(btnLocusLayout);
        btnLocusLayout.setHorizontalGroup(
            btnLocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(26, 26, 26))
        );
        btnLocusLayout.setVerticalGroup(
            btnLocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocusLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btnLocusLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(btnLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 50));

        btnSitios.setBackground(new java.awt.Color(232, 195, 158));
        btnSitios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnSitiosMouseMoved(evt);
            }
        });
        btnSitios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSitiosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSitiosMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("SITIOS");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/SITIO2.png"))); // NOI18N

        javax.swing.GroupLayout btnSitiosLayout = new javax.swing.GroupLayout(btnSitios);
        btnSitios.setLayout(btnSitiosLayout);
        btnSitiosLayout.setHorizontalGroup(
            btnSitiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSitiosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(60, 60, 60))
        );
        btnSitiosLayout.setVerticalGroup(
            btnSitiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSitiosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSitiosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(btnSitios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 50));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inah.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 80));

        vtnVentanas.setBackground(new java.awt.Color(151, 112, 112));
        vtnVentanas.setForeground(new java.awt.Color(255, 204, 204));
        vtnVentanas.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        vtnVentanas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vtnVentanasMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(245, 225, 206));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText(" Nombre de Sitio:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel9.setText("DESCRIPCION:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, -1, -1));

        jLabel12.setText("CARTA TOPOGRAFICA:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, -1, -1));

        txtNombreSitio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jPanel3.add(txtNombreSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 230, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 270, 180));

        ComboBoxCarta.setForeground(new java.awt.Color(255, 204, 0));
        ComboBoxCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCartaActionPerformed(evt);
            }
        });
        jPanel3.add(ComboBoxCarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 100, -1));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/REGISTER.png"))); // NOI18N
        btnRegistrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseMoved(evt);
            }
        });
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, -1));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/EDIT.png"))); // NOI18N
        btnEditar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEditarMouseMoved(evt);
            }
        });
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarMouseExited(evt);
            }
        });
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/edit-clear-broom-icon.png"))); // NOI18N
        btnLimpiar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseMoved(evt);
            }
        });
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 60, 60));

        jLabel176.setText("LIMPIAR CAMPOS");
        jPanel3.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, -1, -1));

        jLabel177.setText("ACEPTAR");
        jPanel3.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));

        jLabel178.setText("MOSTRAR REGISTROS");
        jPanel3.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));
        jPanel3.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        jLabel13.setText("Tipo de Sitio: ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jPanel3.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 230, -1));

        jLabel98.setText("Regiiones Fisiograficas: ");
        jPanel3.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel3.add(cbRegionesSitios, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 120, 180, -1));

        jLabel107.setText("Nombre del Proyecto: ");
        jPanel3.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        jPanel3.add(txtNombreProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 200, -1));

        jLabel184.setText("Fecha de registro: ");
        jPanel3.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 190, -1));

        jLabel185.setText("Persona que Registró:");
        jPanel3.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));
        jPanel3.add(txtPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 190, -1));

        jLabel186.setText("Municipio:");
        jPanel3.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jPanel3.add(cbMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 130, -1));

        jLabel187.setText("Localidad: ");
        jPanel3.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jLabel188.setText("Datum: ");
        jPanel3.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));
        jPanel3.add(txtLocalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 130, -1));
        jPanel3.add(txtDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 130, -1));

        jLabel189.setText("Este: ");
        jPanel3.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));
        jPanel3.add(txtEste, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 130, -1));

        jLabel190.setText("Norte: ");
        jPanel3.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));
        jPanel3.add(txtNorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 130, -1));

        jLabel191.setText("Altitud:");
        jPanel3.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));
        jPanel3.add(txtAltitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 230, 120, -1));

        jLabel192.setText("Extension en m2:  ");
        jPanel3.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));
        jPanel3.add(txtExtension, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 140, -1));

        jLabel193.setText("Topoforma: ");
        jPanel3.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, -1, -1));

        jPanel3.add(cbTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 160, -1));

        jLabel10.setText("Cronologia: ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        jPanel3.add(cbCronos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 150, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 230, 10));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 230, 10));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 230, 10));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 240, 10));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 230, 10));
        jPanel3.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 230, 10));
        jPanel3.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 190, 10));
        jPanel3.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 190, 10));
        jPanel3.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 190, 10));
        jPanel3.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 190, 10));
        jPanel3.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 190, 20));

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 20, 380));
        jPanel3.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 230, 10));
        jPanel3.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 240, 10));
        jPanel3.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 240, 10));
        jPanel3.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 240, 10));
        jPanel3.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 190, 10));

        jSeparator26.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 20, 260));

        vtnVentanas.addTab("SITIOS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(245, 225, 206));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/edit-clear-broom-icon.png"))); // NOI18N
        btnLimpiar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLimpiar1MouseMoved(evt);
            }
        });
        btnLimpiar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiar1MouseExited(evt);
            }
        });
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 60, 60));

        btnRegistrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/REGISTER.png"))); // NOI18N
        btnRegistrar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrar1MouseMoved(evt);
            }
        });
        btnRegistrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar1MouseExited(evt);
            }
        });
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/EDIT.png"))); // NOI18N
        btnEditar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEditar1MouseMoved(evt);
            }
        });
        btnEditar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditar1MouseExited(evt);
            }
        });
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, -1, -1));

        jLabel19.setText("Nombre del Elemento:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel20.setText("DESCRIPCIÓN:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));
        jPanel4.add(txtNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 170, -1));

        txtDescripcionE.setColumns(20);
        txtDescripcionE.setRows(5);
        jScrollPane4.setViewportView(txtDescripcionE);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 230, -1));

        jLabel22.setText("Nombre del Sitio :");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        ComboBoxE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEActionPerformed(evt);
            }
        });
        jPanel4.add(ComboBoxE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 170, -1));

        jLabel18.setText("LIMPIAR");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, -1, -1));

        jLabel125.setText("ACEPTAR");
        jPanel4.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, -1, -1));

        jLabel174.setText("MOSTRAR REGISTROS");
        jPanel4.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, -1, -1));

        jLabel11.setText("Sector:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));
        jPanel4.add(txtSector, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 170, -1));

        jLabel194.setText("Extensión en m2:");
        jPanel4.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));
        jPanel4.add(txtExtensionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 170, -1));

        jLabel195.setText("Forma General: ");
        jPanel4.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel196.setText("Elemento Aislado:");
        jPanel4.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        ea1.setText("SÍ");
        jPanel4.add(ea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        ea2.setText("No");
        jPanel4.add(ea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));

        jLabel197.setText("Conjunto #:");
        jPanel4.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));
        jPanel4.add(txtConjunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 170, -1));

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
        jPanel4.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jPanel4.add(cbEvi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 200, -1));
        jPanel4.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 170, 10));
        jPanel4.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 170, 10));
        jPanel4.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 170, 10));
        jPanel4.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 170, 10));
        jPanel4.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 170, 10));
        jPanel4.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 170, 10));
        jPanel4.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 260, 10));

        jSeparator34.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 30, 280));
        jPanel4.add(jSeparator35, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 310, 10));
        jPanel4.add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 170, 10));
        jPanel4.add(jSeparator37, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 170, 10));
        jPanel4.add(jSeparator38, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 260, 10));
        jPanel4.add(jSeparator39, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 260, 10));
        jPanel4.add(jSeparator40, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 310, 10));

        jPanel4.add(cbFormaG, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 170, -1));

        vtnVentanas.addTab("ELEMENTOS INMUEBLES", jPanel4);

        jPanel5.setBackground(new java.awt.Color(245, 225, 206));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiarLocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/edit-clear-broom-icon.png"))); // NOI18N
        btnLimpiarLocus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnLimpiarLocusMouseMoved(evt);
            }
        });
        btnLimpiarLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarLocusMouseExited(evt);
            }
        });
        btnLimpiarLocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLocusActionPerformed(evt);
            }
        });
        jPanel5.add(btnLimpiarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 60, 60));
        jPanel5.add(txtReferenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 210, -1));

        btnEditarLocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/EDIT.png"))); // NOI18N
        btnEditarLocus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEditarLocusMouseMoved(evt);
            }
        });
        btnEditarLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarLocusMouseExited(evt);
            }
        });
        btnEditarLocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarLocusActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        btnRegistrarLocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/REGISTER.png"))); // NOI18N
        btnRegistrarLocus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrarLocusMouseMoved(evt);
            }
        });
        btnRegistrarLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarLocusMouseExited(evt);
            }
        });
        btnRegistrarLocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarLocusActionPerformed(evt);
            }
        });
        jPanel5.add(btnRegistrarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        txtDescripcionL.setColumns(20);
        txtDescripcionL.setRows(5);
        jScrollPane5.setViewportView(txtDescripcionL);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 300, 110));

        jLabel23.setText("NOMBRE DEL INMUEBLE:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel24.setText("DESCRIPCIÓN:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jLabel25.setText("NOMBRE :");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel26.setText("REFERENCIA:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));
        jPanel5.add(txtNombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, -1));

        ComboBoxL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxL1ActionPerformed(evt);
            }
        });
        jPanel5.add(ComboBoxL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 150, -1));

        jLabel27.setText("UBICACIÓN:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel28.setText("COLOR:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));
        jPanel5.add(txtColorL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 230, -1));

        jLabel180.setText("LIMPIAR");
        jPanel5.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jLabel181.setText("INSERTAR");
        jPanel5.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        jLabel182.setText("MOSTRAR REGISTROS");
        jPanel5.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, -1, -1));

        jPanel5.add(cbUbicacionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 150, -1));

        jPanel5.add(cbFrmaGral, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 140, -1));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 390, 10));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 300, 10));
        jPanel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 123, 300, 10));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 173, 300, 10));

        jLabel21.setText("FORMA GENERAL:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 380, 10));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 510, 10));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 510, 20));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 10, 320));

        vtnVentanas.addTab("LOCUS", jPanel5);

        Contenedor.setBackground(new java.awt.Color(245, 225, 206));
        Contenedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PanelOtro.setBackground(new java.awt.Color(245, 225, 206));
        PanelOtro.setPreferredSize(new java.awt.Dimension(872, 447));
        PanelOtro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(245, 225, 206));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel28.setForeground(new java.awt.Color(0, 0, 0));

        jLabel151.setText("N# BOLSA:");

        jLabel152.setText("UNIDAD:");

        jLabel153.setText("E:");

        jLabel154.setText("N:");

        jLabel155.setText("ESTRATO:");

        jLabel156.setText("TOTAL:");

        jLabel157.setText("CUARTO:");

        jLabel124.setText("RT");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addComponent(jLabel152)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField84)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel157)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField85, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel155)
                    .addComponent(jLabel156)
                    .addComponent(jLabel154)
                    .addComponent(jLabel153)
                    .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField86, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField88, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField87)
                    .addComponent(jTextField89))
                .addGap(77, 77, 77))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151)
                    .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel152)
                    .addComponent(jTextField84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157)
                    .addComponent(jTextField85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel153, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField89, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel154)
                    .addComponent(jTextField86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel155, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField87, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel156))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel124))
                .addGap(4, 4, 4))
        );

        PanelOtro.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 210, -1));

        jPanel29.setBackground(new java.awt.Color(245, 225, 206));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel29.setForeground(new java.awt.Color(0, 0, 0));

        jLabel158.setText("OBSERVACIONES:");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane9.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel158)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PanelOtro.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        jPanel30.setBackground(new java.awt.Color(245, 225, 206));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel30.setForeground(new java.awt.Color(0, 0, 0));

        jLabel159.setText("Sitio:");

        jLabel160.setText("Estructura:");

        jLabel161.setText("Locus:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel159)
                        .addGap(47, 47, 47)
                        .addComponent(cbSitio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel161)
                        .addGap(40, 40, 40)
                        .addComponent(cbLocus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel160)
                        .addGap(18, 18, 18)
                        .addComponent(cbEstructura2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSitio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160)
                    .addComponent(cbEstructura2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161)
                    .addComponent(cbLocus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        PanelOtro.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, -1));

        jPanel31.setBackground(new java.awt.Color(245, 225, 206));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel31.setForeground(new java.awt.Color(0, 0, 0));

        jLabel162.setText("CLASE:");

        jLabel163.setText("TIPO:");

        jLabel164.setText("MATERIA PMA:");

        jLabel165.setText("CORTEX:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel165)
                        .addGap(11, 11, 11)
                        .addComponent(jTextField93))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField90, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel164)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField92, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel163)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel162)
                    .addComponent(jTextField90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel163)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel164)
                    .addComponent(jTextField92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel165)
                    .addComponent(jTextField93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(168, 168, 168))
        );

        PanelOtro.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, 160));

        jPanel32.setBackground(new java.awt.Color(245, 225, 206));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel32.setForeground(new java.awt.Color(0, 0, 0));

        jLabel166.setText("LARGO:");

        jLabel167.setText("ANCHO:");

        jLabel168.setText("GROSOR:");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField94, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel168)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel167)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField95, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel166)
                    .addComponent(jTextField94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel167)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(jTextField95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel168)
                    .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        PanelOtro.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 180, -1));

        jPanel33.setBackground(new java.awt.Color(245, 225, 206));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel33.setForeground(new java.awt.Color(0, 0, 0));

        jLabel169.setText("REGISTRÓ:");

        jLabel170.setText("ANALIZÓ:");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel169)
                    .addComponent(jLabel170))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel169)
                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel170)
                    .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        PanelOtro.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 350, -1));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel171.setText("OTRO MATERIAL");
        PanelOtro.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, -1));

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setForeground(new java.awt.Color(0, 0, 0));
        jButton23.setText("CONSULTAR");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        PanelOtro.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, -1, -1));

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setForeground(new java.awt.Color(0, 0, 0));
        jButton24.setText("REGISTRAR");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        PanelOtro.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, -1));

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setForeground(new java.awt.Color(0, 0, 0));
        jButton25.setText("LIMPIAR");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        PanelOtro.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, -1, -1));

        PanellCeramica.setBackground(new java.awt.Color(245, 225, 206));

        jPanel6.setBackground(new java.awt.Color(245, 225, 206));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setForeground(new java.awt.Color(0, 102, 102));

        jLabel29.setText("Sitio:");

        jLabel30.setText("Estructura:");

        jLabel31.setText("Cuarto");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(47, 47, 47)
                        .addComponent(cbSitioCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(40, 40, 40)
                        .addComponent(cbCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(cbEstructuraCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSitioCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbEstructuraCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cbCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setText("CERAMICA MONOCROMA");

        jPanel10.setBackground(new java.awt.Color(245, 225, 206));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel45.setText("N# BOLSA:");

        jLabel46.setText("UNIDAD:");

        jLabel47.setText("E:");

        jLabel48.setText("N:");

        jLabel49.setText("ESTRATO:");

        jLabel50.setText("TOTAL:");

        jLabel136.setText("RT:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnumeroBolsa)
                            .addComponent(txtUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel48)
                                        .addComponent(jLabel47)
                                        .addComponent(jLabel49)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel50)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel136)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRT)
                            .addComponent(txtEstrato, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtN, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtE)
                            .addComponent(txtTotal))))
                .addGap(6, 6, 6))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtnumeroBolsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(txtRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(245, 225, 206));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel52.setText("ROJO:");

        jLabel53.setText("NEGRO");

        jLabel54.setText("CAFE:");

        jLabel55.setText("BURDO LISO:");

        jLabel56.setText("IMP UÑA:");

        jLabel57.setText("PEINADO:");

        jLabel58.setText("TEXTURIZADO:");

        jLabel59.setText("TIERRA BATIDA:");

        jLabel60.setText("OTRO:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtBurdoLiso, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtUña, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtPeinado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(txtTexturizado, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txtTierraBatida, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtRojo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtNegro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtCafe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(txtBurdoLiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(txtUña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(txtPeinado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txtTexturizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtTierraBatida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(245, 225, 206));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setForeground(new java.awt.Color(0, 0, 0));

        jLabel64.setText("PLATO:");

        jLabel65.setText("CAJETE:");

        jLabel66.setText("OLLA:");

        jLabel67.setText("VASO:");

        jLabel68.setText("JARRA:");

        jLabel69.setText("MOLCAJETE:");

        jLabel172.setText("NO_IDN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel172))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCajete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOlla, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVaso, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJarra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMolcajete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtCajete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtOlla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(txtVaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(txtJarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(txtMolcajete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel172)
                    .addComponent(txtNoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(245, 225, 206));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setForeground(new java.awt.Color(0, 0, 0));

        jLabel72.setText("BORDE:");

        jLabel73.setText("CUERPO:");

        jLabel74.setText("ASA:");

        jLabel75.setText("SOPORTE:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txtSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAsa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBorde, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtBorde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addComponent(txtAsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(txtSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(245, 225, 206));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setForeground(new java.awt.Color(0, 0, 0));

        jLabel76.setText("REGISTRÓ:");

        jLabel77.setText("ANALIZÓ:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel77))
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAnalizo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txtRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txtAnalizo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("CONSULTAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 255, 102));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("REGISTRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 255, 204));
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("LIMPIAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanellCeramicaLayout = new javax.swing.GroupLayout(PanellCeramica);
        PanellCeramica.setLayout(PanellCeramicaLayout);
        PanellCeramicaLayout.setHorizontalGroup(
            PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanellCeramicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanellCeramicaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanellCeramicaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanellCeramicaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))))
                    .addGroup(PanellCeramicaLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel41)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanellCeramicaLayout.setVerticalGroup(
            PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanellCeramicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanellCeramicaLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanellCeramicaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanellCeramicaLayout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanellCeramicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton3)
                                    .addComponent(jButton11))))))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        PanelCeramicaDetallada.setBackground(new java.awt.Color(245, 225, 206));
        PanelCeramicaDetallada.setPreferredSize(new java.awt.Dimension(872, 447));

        jButton14.setBackground(new java.awt.Color(153, 255, 153));
        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("REGISTRAR");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setText("CERAMICA DECORADA");

        jPanel15.setBackground(new java.awt.Color(245, 225, 206));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setForeground(new java.awt.Color(0, 102, 102));

        jLabel78.setText("Sitio:");

        jLabel79.setText("Inmueble:");

        jLabel80.setText("Locus:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(47, 47, 47)
                        .addComponent(cbSitioDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(jLabel79))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEstructuraDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbLocusDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSitioDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(cbEstructuraDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(cbLocusDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(245, 225, 206));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setForeground(new java.awt.Color(0, 102, 102));

        jLabel81.setText("N# BOLSA:");

        jLabel82.setText("UNIDAD:");

        jLabel83.setText("E:");

        jLabel84.setText("N:");

        jLabel85.setText("ESTRATO:");

        jLabel86.setText("TOTAL:");

        jLabel35.setText("RT:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(18, 18, 18)
                        .addComponent(txtUni))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel83)
                            .addComponent(jLabel84)
                            .addComponent(jLabel85))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNdec, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEdec)
                            .addComponent(txtEstratodec, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBolsadec))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel86))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtTotaldec, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtRtDec))))
                .addGap(49, 49, 49))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(txtBolsadec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addComponent(txtUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNdec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstratodec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotaldec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtRtDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(245, 225, 206));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setForeground(new java.awt.Color(0, 0, 0));

        jLabel89.setText("N/I:");

        jLabel90.setText("SUCHIL:");

        jLabel91.setText("VESUVIO:");

        jLabel92.setText("MICHILIA:");

        jLabel93.setText("AMARO:");

        jLabel94.setText("MERCADO:");

        jLabel95.setText("NEVERIA:");

        jLabel96.setText("REFUGIO:");

        jLabel97.setText("LOLANDIS:");

        jLabel114.setText("OTINAPA:");

        jLabel115.setText("MORCILLO");

        jLabel116.setText("NAYAR:");

        jLabel117.setText("CANATLAN:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89)
                            .addComponent(jLabel90)
                            .addComponent(jLabel91)
                            .addComponent(jLabel92)
                            .addComponent(jLabel93)
                            .addComponent(jLabel94)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96)
                            .addComponent(jLabel97))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRef)
                                    .addComponent(txtLol)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(txtMer, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAmaro, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMichi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtVesuvio, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSuchil, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNoI, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114)
                            .addComponent(jLabel115)
                            .addComponent(jLabel116)
                            .addComponent(jLabel117))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOti, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCan, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(txtNoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(txtSuchil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(txtVesuvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92)
                    .addComponent(txtMichi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93)
                    .addComponent(txtAmaro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94)
                    .addComponent(txtMer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(txtLol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(txtOti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel115)
                    .addComponent(txtMor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(txtNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel117)
                    .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel18.setBackground(new java.awt.Color(245, 225, 206));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setForeground(new java.awt.Color(0, 0, 0));

        jLabel99.setText("N/I:");

        jLabel100.setText("PLATO:");

        jLabel101.setText("CAJETE:");

        jLabel102.setText("OLLA:");

        jLabel103.setText("VASO:");

        jLabel104.setText("JARRA:");

        jLabel105.setText("MOLCAJETE:");

        jLabel106.setText("Asa de canasta:");

        jLabel36.setText("COPA:");

        jLabel118.setText("MADERO:");

        jLabel119.setText("DE COSTA:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel105))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel100)
                                    .addComponent(jLabel101)
                                    .addComponent(jLabel106)
                                    .addComponent(jLabel104)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel102)
                                    .addComponent(jLabel99))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaj, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJarr, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAsadec, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCop, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOlladec, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoII, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel119)
                            .addComponent(jLabel118))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtMad, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(txtMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(txtNoII, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(txtPlat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(txtOlladec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103)
                    .addComponent(txtVas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJarr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAsadec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105)))
        );

        jPanel19.setBackground(new java.awt.Color(245, 225, 206));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setForeground(new java.awt.Color(0, 0, 0));

        jLabel108.setText("BORDE:");

        jLabel109.setText("CUERPO:");

        jLabel110.setText("ASA:");

        jLabel111.setText("SOPORTE:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txtSop, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAs, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel109)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBody, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBord, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(txtBord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(txtBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel110)
                    .addComponent(txtAs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111)
                    .addComponent(txtSop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(245, 225, 206));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setForeground(new java.awt.Color(0, 0, 0));

        jLabel112.setText("REGISTRÓ:");

        jLabel113.setText("ANALIZÓ:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel113)
                        .addGap(18, 18, 18)
                        .addComponent(txtAnali, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReg, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtReg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113))
                .addGap(28, 28, 28))
        );

        jButton15.setBackground(new java.awt.Color(255, 255, 204));
        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("LIMPIAR");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(153, 255, 255));
        jButton16.setForeground(new java.awt.Color(0, 0, 0));
        jButton16.setText("CONSULTAR");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCeramicaDetalladaLayout = new javax.swing.GroupLayout(PanelCeramicaDetallada);
        PanelCeramicaDetallada.setLayout(PanelCeramicaDetalladaLayout);
        PanelCeramicaDetalladaLayout.setHorizontalGroup(
            PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15))
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16)))
                    .addComponent(jLabel42))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        PanelCeramicaDetalladaLayout.setVerticalGroup(
            PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel42)
                        .addGap(12, 12, 12)
                        .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelCeramicaDetalladaLayout.createSequentialGroup()
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(PanelCeramicaDetalladaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton14)
                                    .addComponent(jButton15))
                                .addGap(18, 18, 18)
                                .addComponent(jButton16))
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        PanelLiticaTa.setBackground(new java.awt.Color(245, 225, 206));

        jPanel7.setBackground(new java.awt.Color(245, 225, 206));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));

        jLabel32.setText("Sitio:");

        jLabel33.setText("Estructura:");

        jLabel34.setText("Locus:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(47, 47, 47)
                        .addComponent(cbSitio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(40, 40, 40)
                        .addComponent(cbLocus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(cbEstructura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSitio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cbEstructura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cbLocus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setText("LITICA TALLADA");

        jPanel21.setBackground(new java.awt.Color(245, 225, 206));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setForeground(new java.awt.Color(0, 0, 0));

        jLabel88.setText("N# BOLSA:");

        jLabel120.setText("UNIDAD:");

        jLabel121.setText("E:");

        jLabel122.setText("N:");

        jLabel123.setText("ESTRATO:");

        jLabel173.setText("RT:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel173)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldRT, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel120))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField67, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jTextField68))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabel123)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(jLabel121)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel120))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel173))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(245, 225, 206));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel126.setText("CLASE:");

        jLabel127.setText("TIPO:");

        jLabel128.setText("MATERIA PMA:");

        jLabel129.setText("CORTEX:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel128)
                    .addComponent(jLabel126, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel127, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel129, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField711)
                    .addComponent(jTextField72)
                    .addComponent(jTextField75)
                    .addComponent(jTextField74, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(jTextField711, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel127))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(jTextField74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(jTextField75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(168, 168, 168))
        );

        jPanel22.setBackground(new java.awt.Color(245, 225, 206));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setForeground(new java.awt.Color(0, 0, 0));

        jLabel130.setText("OBSERVACIONES:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel130)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(245, 225, 206));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setForeground(new java.awt.Color(0, 0, 0));

        jLabel133.setText("LARGO:");

        jLabel134.setText("ANCHO:");

        jLabel135.setText("GROSOR:");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel135)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel134)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel133)
                    .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel134))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(245, 225, 206));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setForeground(new java.awt.Color(0, 0, 0));

        jLabel131.setText("REGISTRÓ:");

        jLabel132.setText("ANALIZÓ:");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel131)
                    .addComponent(jLabel132))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel131)
                    .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel132)
                    .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton17.setBackground(new java.awt.Color(102, 255, 102));
        jButton17.setForeground(new java.awt.Color(0, 0, 0));
        jButton17.setText("REGISTRAR");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 204));
        jButton18.setForeground(new java.awt.Color(0, 0, 0));
        jButton18.setText("LIMPIAR");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(153, 204, 255));
        jButton19.setForeground(new java.awt.Color(0, 0, 0));
        jButton19.setText("CONSULTAR");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLiticaTaLayout = new javax.swing.GroupLayout(PanelLiticaTa);
        PanelLiticaTa.setLayout(PanelLiticaTaLayout);
        PanelLiticaTaLayout.setHorizontalGroup(
            PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                        .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLiticaTaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addGap(14, 14, 14)
                        .addComponent(jButton18)
                        .addGap(18, 18, 18)
                        .addComponent(jButton19)
                        .addGap(99, 99, 99))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLiticaTaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addGap(335, 335, 335))
        );
        PanelLiticaTaLayout.setVerticalGroup(
            PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                        .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(PanelLiticaTaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19)
                            .addComponent(jButton17)
                            .addComponent(jButton18))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelLiticaTaLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 51, Short.MAX_VALUE))))
        );

        PanelLiticaPu.setBackground(new java.awt.Color(245, 225, 206));
        PanelLiticaPu.setPreferredSize(new java.awt.Dimension(872, 447));

        jPanel9.setBackground(new java.awt.Color(245, 225, 206));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setText("LITICA PULIDA");

        jLabel148.setText("REGISTRÓ:");

        jLabel149.setText("ANALIZÓ");

        OBSERVACIONES.setColumns(20);
        OBSERVACIONES.setRows(5);
        jScrollPane8.setViewportView(OBSERVACIONES);

        jLabel150.setText("OBSERVACIONES:");

        jPanel25.setBackground(new java.awt.Color(245, 225, 206));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setText("Sitio:");

        jLabel39.setText("Estructura:");

        jLabel40.setText("Locus:");

        jLabel2.setText("Numero de bolsa");

        jLabel37.setText("E:");

        jLabel61.setText("N:");

        jLabel63.setText("COMPLETADO:");

        jLabel70.setText("FRACTURADO:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(cbSitio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(cbEstructura3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nobolsa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FRACTURADO, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(cbLocus3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel61))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(COMPLETADO, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cbSitio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(cbEstructura3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(cbLocus3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nobolsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(EE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(COMPLETADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FRACTURADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel26.setBackground(new java.awt.Color(245, 225, 206));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel87.setText("METATE:");

        jLabel137.setText("MORTERO:");

        jLabel138.setText("MANO DE METATE:");

        jLabel139.setText("MANO DE MORTERO");

        jLabel140.setText("PULIDOR:");

        jLabel141.setText("HACHA:");

        jLabel142.setText("OTRO:");

        jLabel143.setText("MATERIA PRIMA:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel137)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MORTERO)
                .addGap(13, 13, 13))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(METATE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel138)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MMETATE)
                        .addContainerGap())
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel139)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MMORTERO)
                        .addContainerGap())
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel140)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PULIDOR, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel141)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HACHA, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OTRO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel143)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MATERIA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(METATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(MORTERO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel138)
                    .addComponent(MMETATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(MMORTERO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel140)
                    .addComponent(PULIDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel141)
                    .addComponent(HACHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OTRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel142))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel143)
                    .addComponent(MATERIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(245, 225, 206));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel144.setText("CARAS PULIDAS:");

        jLabel145.setText("LARGO MAX (CM):");

        jLabel146.setText("ANCHOR MAX (CM):");

        jLabel147.setText("GROSOR MAX (CM):");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel144))
                    .addComponent(jLabel145))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(LARGO, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(CARAS, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel146)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ANCHO, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel147)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GROSOR, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CARAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145)
                    .addComponent(LARGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(ANCHO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel147)
                    .addComponent(GROSOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jButton20.setBackground(new java.awt.Color(153, 255, 153));
        jButton20.setForeground(new java.awt.Color(0, 0, 0));
        jButton20.setText("REGISTRAR");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(153, 204, 255));
        jButton21.setForeground(new java.awt.Color(0, 0, 0));
        jButton21.setText("CONSULTAR");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(255, 255, 204));
        jButton22.setForeground(new java.awt.Color(0, 0, 0));
        jButton22.setText("LIMPIAR");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLiticaPuLayout = new javax.swing.GroupLayout(PanelLiticaPu);
        PanelLiticaPu.setLayout(PanelLiticaPuLayout);
        PanelLiticaPuLayout.setHorizontalGroup(
            PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel44)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                .addComponent(jLabel150)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                        .addComponent(jLabel149)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ANA, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                        .addComponent(jLabel148)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(REGIS, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton20)
                                            .addComponent(jButton22))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton21)))
                                .addGap(0, 11, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLiticaPuLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelLiticaPuLayout.setVerticalGroup(
            PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelLiticaPuLayout.createSequentialGroup()
                                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel148)
                                    .addComponent(REGIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel149)
                                    .addComponent(ANA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel150)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(PanelLiticaPuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton20)
                                    .addComponent(jButton21))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelLiticaTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanellCeramica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(445, 445, 445))
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ContenedorLayout.createSequentialGroup()
                    .addGap(0, 627, Short.MAX_VALUE)
                    .addComponent(PanelCeramicaDetallada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 628, Short.MAX_VALUE)))
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ContenedorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelLiticaPu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1249, Short.MAX_VALUE)))
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorLayout.createSequentialGroup()
                    .addContainerGap(667, Short.MAX_VALUE)
                    .addComponent(PanelOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(588, 588, 588)))
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLiticaTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(PanellCeramica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ContenedorLayout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(PanelCeramicaDetallada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 7, Short.MAX_VALUE)))
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ContenedorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelLiticaPu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelOtro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );

        vtnVentanas.addTab("MATERIALES", Contenedor);

        jPanel1.add(vtnVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 890, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 890, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSitiosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseMoved
     btnSitios.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnSitiosMouseMoved

    private void btnSitiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseExited
btnSitios.setBackground(new Color(232,195,158));      
    }//GEN-LAST:event_btnSitiosMouseExited

    private void btnEstructurasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseMoved
btnEstructuras.setBackground(new Color(255,255,255));       
    }//GEN-LAST:event_btnEstructurasMouseMoved

    private void btnEstructurasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseExited
      btnEstructuras.setBackground(new Color(232,195,158 ));     
    }//GEN-LAST:event_btnEstructurasMouseExited

    private void btnLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseMoved
btnLocus.setBackground(new Color(255,255,255)); 

    }//GEN-LAST:event_btnLocusMouseMoved

    private void btnLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseExited
       btnLocus.setBackground(new Color(232,195,158 ));   
    }//GEN-LAST:event_btnLocusMouseExited

    private void btnSitiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseClicked
vtnVentanas.setSelectedIndex(0);
    }//GEN-LAST:event_btnSitiosMouseClicked

    private void btnEstructurasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseClicked
vtnVentanas.setSelectedIndex(1);
    }//GEN-LAST:event_btnEstructurasMouseClicked

    private void btnLocusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseClicked
      vtnVentanas.setSelectedIndex(2);
    }//GEN-LAST:event_btnLocusMouseClicked

    private void btnLocus1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseMoved
        btnLocus1.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnLocus1MouseMoved

    private void btnLocus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseClicked
      vtnVentanas.setSelectedIndex(2);
    }//GEN-LAST:event_btnLocus1MouseClicked

    private void btnLocus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseExited
        btnLocus1.setBackground(new Color(232,195,158));
    }//GEN-LAST:event_btnLocus1MouseExited

    private void btnLocus2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseMoved
btnLocus2   .setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnLocus2MouseMoved

    private void btnLocus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseClicked
        MaterialNuevo();
        vtnVentanas.setSelectedIndex(3);
    }//GEN-LAST:event_btnLocus2MouseClicked

    private void btnLocus2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseExited
btnLocus2.setBackground(new Color(232,195,158 ));
    }//GEN-LAST:event_btnLocus2MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Hasta luego.");
                    System.exit(0);
                }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void vtnVentanasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vtnVentanasMouseClicked
        if (vtnVentanas.getSelectedIndex() == 3) {
           MaterialNuevo();
          
        }
    }//GEN-LAST:event_vtnVentanasMouseClicked

    private void ComboBoxL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxL1ActionPerformed
       
    }//GEN-LAST:event_ComboBoxL1ActionPerformed

    private void visorLocusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorLocusMouseClicked
        //mostrarLocus("LOCUS");
    }//GEN-LAST:event_visorLocusMouseClicked

    private void btnRegistrarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLocusActionPerformed
     try {
         registrarLocus();
     } catch (IllegalAccessException ex) {
         Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }//GEN-LAST:event_btnRegistrarLocusActionPerformed

    private void btnRegistrarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarLocusMouseExited

    }//GEN-LAST:event_btnRegistrarLocusMouseExited

    private void btnRegistrarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarLocusMouseMoved
  
    }//GEN-LAST:event_btnRegistrarLocusMouseMoved

    private void btnEditarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarLocusActionPerformed
dispose();
consultarLocus loco=new consultarLocus();
loco.mostrarLocus();
loco.setVisible(true);
    }//GEN-LAST:event_btnEditarLocusActionPerformed

    private void btnEditarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarLocusMouseExited

    }//GEN-LAST:event_btnEditarLocusMouseExited

    private void btnEditarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarLocusMouseMoved

    }//GEN-LAST:event_btnEditarLocusMouseMoved

    private void btnLimpiarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLocusActionPerformed

       
    }//GEN-LAST:event_btnLimpiarLocusActionPerformed

    private void btnLimpiarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarLocusMouseExited

    }//GEN-LAST:event_btnLimpiarLocusMouseExited

    private void btnLimpiarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarLocusMouseMoved

    }//GEN-LAST:event_btnLimpiarLocusMouseMoved

    private void ComboBoxEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEActionPerformed

    }//GEN-LAST:event_ComboBoxEActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
      dispose();
        consultarInmuebles elemento =new consultarInmuebles();
      elemento.mostrarInmueble();
      elemento.setVisible(true);
        //cargarSitios();
        
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnEditar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseExited

    }//GEN-LAST:event_btnEditar1MouseExited

    private void btnEditar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseMoved
 
    }//GEN-LAST:event_btnEditar1MouseMoved

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        registrarElementoInmueble();
        //mostrarEstructura("estructuras");
        //cargarEstructuraCombo();
        


    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void btnRegistrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrar1MouseExited

    }//GEN-LAST:event_btnRegistrar1MouseExited

    private void btnRegistrar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrar1MouseMoved
  
    }//GEN-LAST:event_btnRegistrar1MouseMoved

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
 txtNombreE.setText("");
    txtSector.setText("");
    txtExtensionE.setText("");
    txtOri.setText("");
    txtAzimut.setText("");
    txtDescripcionE.setText("");
    ea1.setSelected(false);
    ComboBoxE.setSelectedIndex(0);
    cbFormaG.setSelectedIndex(0);
    cbNatu.setSelectedIndex(0);
    cbEvi.setSelectedIndex(0);

  
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnLimpiar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseExited
       
    }//GEN-LAST:event_btnLimpiar1MouseExited

    private void btnLimpiar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseMoved
     
    }//GEN-LAST:event_btnLimpiar1MouseMoved

    private void visorEstructurasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorEstructurasMouseClicked
      //  editarRegistro();

      
    }//GEN-LAST:event_visorEstructurasMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        limpiarSitios();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited

        btnLimpiar.setBackground(new Color(255,255,255));
       
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnLimpiarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseMoved
        btnLimpiar.setBackground(new Color(96,219,164 ));
    }//GEN-LAST:event_btnLimpiarMouseMoved

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setBackground(new Color(255,255,255));
       
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnEliminarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseMoved
        btnEliminar.setBackground(new Color(96,219,164 ));//
    
    }//GEN-LAST:event_btnEliminarMouseMoved

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      btnEditar.setToolTipText("Modificar formulario");
      dispose();
  consultarSitio con=new consultarSitio();
   con.setVisible(true);
  con.mostraSitio();
 
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseExited
        btnEditar.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnEditarMouseExited

    private void btnEditarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseMoved
        btnEditar.setBackground(new Color(96,219,164 ));// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarMouseMoved

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        btnRegistrar.setToolTipText("REGISTRAR NUEVO SITIO");
      
     try {
         registrarSitios();
     } catch (IllegalAccessException ex) {
         Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnRegistrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseMoved
        btnRegistrar.setBackground(new Color(96,219,164 ));
    }//GEN-LAST:event_btnRegistrarMouseMoved

    private void ComboBoxCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCartaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//
registrarCeramicaMonocroma();



        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
dispose();

ceramicaMono cer = new ceramicaMono();
cer.mostrar();
cer.setVisible(true);
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       
      limpiarCamposEditarsitios();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
 registrarCeramicaDecorada();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
 limpiarCamposDecorada();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
dispose();
        ceramicaDecorada cer = new ceramicaDecorada();
cer.mostrarCeramicaDecorada();
cer.setVisible(true);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       registrarLiticatallada();   
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
           limpiarCampostallada();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       liticaTallada li = new liticaTallada();
        li.mostrarLiticatallada();
        li.setVisible(true);

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
      registrarLiticapulida();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
   liticapulida pu=new liticapulida();
pu.mostrarLiticapulida();
pu.setVisible(true);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
 limpiarCamposPu();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
OTROS otro = new OTROS();
otro.mostrar();
otro.setVisible(true);      
              
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
          registrarOtroMaterial();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
  limpiarCamposoTRO();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void visorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorMouseClicked

    }//GEN-LAST:event_visorMouseClicked

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
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ANA;
    private javax.swing.JTextField ANCHO;
    private javax.swing.JTextField CARAS;
    private javax.swing.JTextField COMPLETADO;
    private javax.swing.JComboBox<String> ComboBoxCarta;
    public javax.swing.JComboBox<String> ComboBoxE;
    private javax.swing.JComboBox<String> ComboBoxL1;
    public static javax.swing.JPanel Contenedor;
    private javax.swing.JTextField EE;
    private javax.swing.JTextField FRACTURADO;
    private javax.swing.JTextField GROSOR;
    private javax.swing.JTextField HACHA;
    private javax.swing.JTextField LARGO;
    private javax.swing.JTextField MATERIA;
    private javax.swing.JTextField METATE;
    private javax.swing.JTextField MMETATE;
    private javax.swing.JTextField MMORTERO;
    private javax.swing.JTextField MORTERO;
    private javax.swing.JTextField NN;
    private javax.swing.JTextArea OBSERVACIONES;
    private javax.swing.JTextField OTRO;
    private javax.swing.JTextField PULIDOR;
    public static javax.swing.JPanel PanelCeramicaDetallada;
    public static javax.swing.JPanel PanelLiticaPu;
    public static javax.swing.JPanel PanelLiticaTa;
    public static javax.swing.JPanel PanelOtro;
    public static javax.swing.JPanel PanellCeramica;
    private javax.swing.JTextField REGIS;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEditarLocus;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel btnEstructuras;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiarLocus;
    private javax.swing.JPanel btnLocus;
    private javax.swing.JPanel btnLocus1;
    private javax.swing.JPanel btnLocus2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JButton btnRegistrarLocus;
    private javax.swing.JPanel btnSitios;
    private javax.swing.JLabel casa;
    private javax.swing.JComboBox<String> cbCronos;
    private javax.swing.JComboBox<String> cbCuarto;
    private javax.swing.JComboBox<String> cbEstructura1;
    private javax.swing.JComboBox<String> cbEstructura2;
    private javax.swing.JComboBox<String> cbEstructura3;
    private javax.swing.JComboBox<String> cbEstructuraCM;
    private javax.swing.JComboBox<String> cbEstructuraDC;
    private javax.swing.JComboBox<String> cbEvi;
    private javax.swing.JComboBox<String> cbFormaG;
    private javax.swing.JComboBox<String> cbFrmaGral;
    private javax.swing.JComboBox<String> cbLocus1;
    private javax.swing.JComboBox<String> cbLocus2;
    private javax.swing.JComboBox<String> cbLocus3;
    private javax.swing.JComboBox<String> cbLocusDC;
    private javax.swing.JComboBox<String> cbMunicipio;
    private javax.swing.JComboBox<String> cbNatu;
    private javax.swing.JComboBox<String> cbRegionesSitios;
    private javax.swing.JComboBox<String> cbSitio1;
    private javax.swing.JComboBox<String> cbSitio2;
    private javax.swing.JComboBox<String> cbSitio3;
    private javax.swing.JComboBox<String> cbSitioCM;
    private javax.swing.JComboBox<String> cbSitioDC;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbTopo;
    private javax.swing.JComboBox<String> cbUbicacionL;
    private javax.swing.JCheckBox ea1;
    private javax.swing.JCheckBox ea2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
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
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField711;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField87;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField90;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextField jTextField92;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField94;
    private javax.swing.JTextField jTextField95;
    private javax.swing.JTextField jTextField96;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JTextField jTextFieldRT;
    private javax.swing.JTextField nobolsa;
    private javax.swing.JTextField txtAltitud;
    private javax.swing.JTextField txtAmaro;
    private javax.swing.JTextField txtAnali;
    private javax.swing.JTextField txtAnalizo;
    private javax.swing.JTextField txtAs;
    private javax.swing.JTextField txtAsa;
    private javax.swing.JTextField txtAsadec;
    private javax.swing.JTextField txtAzimut;
    private javax.swing.JTextField txtBody;
    private javax.swing.JTextField txtBolsadec;
    private javax.swing.JTextField txtBord;
    private javax.swing.JTextField txtBorde;
    private javax.swing.JTextField txtBurdoLiso;
    private javax.swing.JTextField txtCafe;
    private javax.swing.JTextField txtCaj;
    private javax.swing.JTextField txtCajete;
    private javax.swing.JTextField txtCan;
    private javax.swing.JTextField txtColorL;
    private javax.swing.JTextField txtConjunto;
    private javax.swing.JTextField txtCop;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtCuerpo;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcionE;
    private javax.swing.JTextArea txtDescripcionL;
    private javax.swing.JTextField txtE;
    private javax.swing.JTextField txtEdec;
    private javax.swing.JTextField txtEste;
    private javax.swing.JTextField txtEstrato;
    private javax.swing.JTextField txtEstratodec;
    private javax.swing.JTextField txtExtension;
    private javax.swing.JTextField txtExtensionE;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtJarr;
    private javax.swing.JTextField txtJarra;
    private javax.swing.JTextField txtLocalidad;
    private javax.swing.JTextField txtLol;
    private javax.swing.JTextField txtMad;
    private javax.swing.JTextField txtMer;
    private javax.swing.JTextField txtMichi;
    private javax.swing.JTextField txtMol;
    private javax.swing.JTextField txtMolcajete;
    private javax.swing.JTextField txtMor;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtNay;
    private javax.swing.JTextField txtNdec;
    private javax.swing.JTextField txtNegro;
    private javax.swing.JTextField txtNev;
    private javax.swing.JTextField txtNoI;
    private javax.swing.JTextField txtNoII;
    private javax.swing.JTextField txtNoId;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtNombreL;
    private javax.swing.JTextField txtNombreProyecto;
    private javax.swing.JTextField txtNombreSitio;
    private javax.swing.JTextField txtNorte;
    private javax.swing.JTextField txtOlla;
    private javax.swing.JTextField txtOlladec;
    private javax.swing.JTextField txtOri;
    private javax.swing.JTextField txtOti;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtPeinado;
    private javax.swing.JTextField txtPersona;
    private javax.swing.JTextField txtPlat;
    private javax.swing.JTextField txtPlato;
    private javax.swing.JTextField txtRT;
    private javax.swing.JTextField txtRef;
    private javax.swing.JTextField txtReferenciaL;
    private javax.swing.JTextField txtReg;
    private javax.swing.JTextField txtRegistro;
    private javax.swing.JTextField txtRojo;
    private javax.swing.JTextField txtRtDec;
    private javax.swing.JTextField txtSector;
    private javax.swing.JTextField txtSop;
    private javax.swing.JTextField txtSoporte;
    private javax.swing.JTextField txtSuchil;
    private javax.swing.JTextField txtTexturizado;
    private javax.swing.JTextField txtTierraBatida;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotaldec;
    private javax.swing.JTextField txtUni;
    private javax.swing.JTextField txtUnidad;
    private javax.swing.JTextField txtUña;
    private javax.swing.JTextField txtVas;
    private javax.swing.JTextField txtVaso;
    private javax.swing.JTextField txtVesuvio;
    private javax.swing.JTextField txtnumeroBolsa;
    public static javax.swing.JTable visor;
    private javax.swing.JTable visorEstructuras;
    private javax.swing.JTable visorLocus;
    public javax.swing.JTabbedPane vtnVentanas;
    // End of variables declaration//GEN-END:variables
}
