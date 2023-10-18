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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class FramePrincipal extends javax.swing.JFrame {

 

    public FramePrincipal() {
        initComponents();
      JOptionPane.showMessageDialog(null, "CONEXIÓN ESTABLECIDA BIENVENIDO");
      this.setTitle("Automatización y Modernización de Procesos en la Sección de Arqueología del Centro INAH Durango");
        this.setLocationRelativeTo(null);
        llenarCamposDesdeTabla();
        mostrar("sitios");
        mostrarEstructura("estructuras");
        Autocompleter(ComboBoxCarta);
        AutocompleterTwo(ComboBoxCarta1);
        mostrarLocus();
        cargarSitios();
        cargarLocusCombo();
        
        btnLimpiar.setToolTipText("Limpiar formulario");btnEditar.setToolTipText("Editar formulario");btnRegistrar.setToolTipText("Registrar formulario");btnEliminar.setToolTipText("Borrar formulario");
    }
    //METODOSSITIOS
    //METODOPARAREGISTRAR UN NUEVO SITIO
    private void Insertar(String nombre, String descripcion, String referencia, String coordenadas, String carta, String tipo) {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    try {
        conn = cn.getConexion();
        String sql = "INSERT INTO sitios (nombre, descripcion, referencia, coordenadas, CartaTopografica , tipo) VALUES (?, ?, ?, ?, ?, ?)";
        String elementoSeleccionado = ComboBoxCarta.getSelectedItem().toString();
            String elementoSeleccionado1 = ComboBoxCarta1.getSelectedItem().toString();
        ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setString(3, referencia);
        ps.setString(4, coordenadas);
        ps.setString(5, elementoSeleccionado);
        ps.setString(6, elementoSeleccionado1);
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
          JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);;
        } else {
            System.out.println("No se pudieron insertar los datos.");
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        System.out.print(e.toString());
    } finally {
        // Cerrar la conexión y el PreparedStatement
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
}
 // Consulta SQL para obtener los nombres de todos los sitios
    private void cargarSitios() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT id, nombre FROM SITIOS ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        // Limpiar el JComboBox antes de cargar los nombres
        ComboBoxE.removeAllItems();
        while (rs.next()) {
            String nombreSitio = rs.getString("nombre");
            ComboBoxE.addItem(nombreSitio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los sitios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar la conexión y los recursos
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
 public void insertarDesdeTextField(JTextField txtNombre, JTextField txtDescripcion, JTextField txtReferencia,  JTextField txtCoordenadas, JComboBox ComboBoxCarta,  JComboBox ComboBoxCarta1) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String referencia = txtReferencia.getText();
        String coordenadas = txtCoordenadas.getText();
        String carta = ComboBoxCarta.getSelectedItem().toString();
        String tipo =  ComboBoxCarta1.getSelectedItem().toString();
        Insertar(nombre, descripcion, referencia, coordenadas, carta, tipo);
    }
 public void cargarDatosDesdeTabla() {
  Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        String sql = "SELECT * FROM sitios";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String referencia = rs.getString("referencia");
            String coordenadas = rs.getString("coordenadas");
            String cartaTopografica = rs.getString("CartaTopografica");
            String tipo = rs.getString("tipo");
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtReferencia.setText(referencia);
            txtCoordenadas.setText(coordenadas);
            ComboBoxCarta.setSelectedItem(cartaTopografica);
            ComboBoxCarta1.setSelectedItem(tipo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
 private int obtenerClavePrimariaDelSitio(int filaSeleccionada) {
    try {
        Object valorCelda = visor.getValueAt(filaSeleccionada, 0);

        if (valorCelda instanceof Integer) {
            return (Integer) valorCelda;
        } else if (valorCelda instanceof String) {
            try {
                return Integer.parseInt((String) valorCelda);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al obtener la clave primaria del sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("El valor en la celda no es una clave primaria válida.");
            }
        }
    } catch (IndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener la clave primaria del sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        throw new IllegalArgumentException("No se pudo obtener la clave primaria del sitio.");
    }

    JOptionPane.showMessageDialog(null, "No se pudo obtener la clave primaria del sitio.", "Error", JOptionPane.ERROR_MESSAGE);
    throw new IllegalArgumentException("No se pudo obtener la clave primaria del sitio.");
}
  public void llenarCamposDesdeTabla() {
    int filaSeleccionada = visor.getSelectedRow();
    System.out.println(filaSeleccionada);
    if (filaSeleccionada == -1) {
        return;
    }
    String nombre = visor.getValueAt(filaSeleccionada, 1).toString();
    String descripcion = visor.getValueAt(filaSeleccionada, 2).toString();
    String referencia = visor.getValueAt(filaSeleccionada, 3).toString();
    String coordenadas = visor.getValueAt(filaSeleccionada, 4).toString();
    String cartaTopografica = visor.getValueAt(filaSeleccionada, 5).toString();
    String tipo = visor.getValueAt(filaSeleccionada, 6).toString();
    txtNombre.setText(nombre);
    txtDescripcion.setText(descripcion);
    txtReferencia.setText(referencia);
    txtCoordenadas.setText(coordenadas);
    ComboBoxCarta.setSelectedItem(cartaTopografica); 
    ComboBoxCarta1.setSelectedItem(tipo); 
}
public void actualizarSitio() {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        int filaSeleccionada = visor.getSelectedRow();
        int clavePrimaria = obtenerClavePrimariaDelSitio(filaSeleccionada);
        System.out.println(clavePrimaria);
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String referencia = txtReferencia.getText();
        String coordenadas = txtCoordenadas.getText();
        Object cartaseleccionada = ComboBoxCarta.getSelectedItem();
        Object tipoSeleccionado = ComboBoxCarta1.getSelectedItem();

        if (tipoSeleccionado != null && cartaseleccionada != null && !nombre.isEmpty() && !descripcion.isEmpty() && !referencia.isEmpty() && !coordenadas.isEmpty()) {
            String tipo = tipoSeleccionado.toString();
            String carta = cartaseleccionada.toString();

            String sql = "UPDATE sitios SET nombre = ?, descripcion = ?, referencia = ?, coordenadas = ?, CartaTopografica = ?, tipo = ? WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, referencia);
            ps.setString(4, coordenadas);
            ps.setString(5, carta);
            ps.setString(6, tipo);
            ps.setInt(7, clavePrimaria);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos del sitio se han actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron actualizar los datos del sitio.");
                JOptionPane.showMessageDialog(null, "No se pudieron actualizar los datos del sitio.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios y seleccione un tipo antes de actualizar el sitio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar el sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
       
    }
}
private void mostrarMensajeSeleccionFila() {
    JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila en la tabla antes de continuar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
   public void mostrar( String Tabla){
        Connection conn = null;
         String SQL = "SELECT * FROM sitios";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        DefaultTableModel model= new DefaultTableModel();
         model.addColumn("id");
         model.addColumn("nombre");
         model.addColumn("descripcion");
         model.addColumn("referencia");
         model.addColumn("coordenadas");
         model.addColumn("CartaTopografica");
         model.addColumn("tipo");
         visor.setModel(model);
         String [] datos =new String[7];
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
                datos[6]=rs.getString(7);
                model.addRow(datos);
                
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "ERROR" );
        }
         
        
    }
    //METODOSESTRUCTURAS
    private void registrarEstructura() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = cn.getConexion();
        String nombre = txtNombreE.getText();
        String descripcion = txtDescripcionE.getText();
        String referencia = txtReferenciaE.getText();
        Object nombreSitioSeleccionado = ComboBoxE.getSelectedItem();
        if (nombreSitioSeleccionado != null && !nombre.isEmpty() && !descripcion.isEmpty() && !referencia.isEmpty()) {
            String nombreSitio = nombreSitioSeleccionado.toString();
            String sql = "INSERT INTO estructuras (nombre, descripcion, referencia, sitio_id) VALUES (?, ?, ?, (SELECT id FROM SITIOS WHERE nombre = ?))";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, referencia);
            ps.setString(4, nombreSitio);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron insertar los datos.");
                JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios y seleccione un sitio antes de registrar la estructura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
      public void mostrarEstructura( String Tabla){
        Connection conn = null;
         String SQL = "SELECT estructuras.*, sitios.nombre AS NombreSitio FROM estructuras JOIN sitios ON estructuras.sitio_id = sitios.id;";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        DefaultTableModel model= new DefaultTableModel();
         model.addColumn("id");
         model.addColumn("nombre");
         model.addColumn("descripcion");
         model.addColumn("referencia");
         model.addColumn("sitio_id");
          model.addColumn("NombreSitio");
      
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

    public void editarRegistro() {
    int filaSeleccionada = visorEstructuras.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
        return;
    }

    int clavePrimaria = obtenerClavePrimariaDelRegistro(filaSeleccionada);
    String nombre = visorEstructuras.getValueAt(filaSeleccionada, 1).toString();
    String descripcion = visorEstructuras.getValueAt(filaSeleccionada, 2).toString();
    String referencia = visorEstructuras.getValueAt(filaSeleccionada, 3).toString();
    String sitio = visorEstructuras.getValueAt(filaSeleccionada, 5).toString();

    txtNombreE.setText(nombre);
    txtDescripcionE.setText(descripcion);
    txtReferenciaE.setText(referencia);
    ComboBoxE.setSelectedItem(sitio);
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


private void actualizarRegistro() {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        int filaSeleccionada = visorEstructuras.getSelectedRow();
        int clavePrimaria = obtenerClavePrimariaDelRegistro(filaSeleccionada);
        String nombre = txtNombreE.getText();
        String descripcion = txtDescripcionE.getText();
        String referencia = txtReferenciaE.getText();
        Object nombreSitioSeleccionado = ComboBoxE.getSelectedItem();

        if (nombreSitioSeleccionado != null && !nombre.isEmpty() && !descripcion.isEmpty() && !referencia.isEmpty()) {
            String nombreSitio = nombreSitioSeleccionado.toString();

            String sql = "UPDATE estructuras SET nombre = ?, descripcion = ?, referencia = ?, sitio_id = (SELECT id FROM SITIOS WHERE nombre = ?) WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, referencia);
            ps.setString(4, nombreSitio);
            ps.setInt(5, clavePrimaria);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se han actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron actualizar los datos.");
                JOptionPane.showMessageDialog(null, "No se pudieron actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios y seleccione un sitio antes de actualizar la estructura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
private void eliminarRegistroEstrutura() {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();

        int filaSeleccionada = visorEstructuras.getSelectedRow();

        int clavePrimaria = obtenerClavePrimariaDelRegistro(filaSeleccionada);

        if (clavePrimaria != -1) {
            String sql = "DELETE FROM estructuras WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, clavePrimaria);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "El registro se ha eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudo eliminar el registro.");
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila antes de eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

  private void limpiarCamposEstructura() {
    txtNombreE.setText(""); // Limpia el campo de texto
    txtDescripcionE.setText(""); // Limpia el campo de texto
    txtReferenciaE.setText(""); // Limpia el campo de texto
    ComboBoxE.setSelectedIndex(0); // Establece el índice del JComboBox a la opción predeterminada
}

    //METODOSLOCUS
 private int obtenerClavePrimariaDelLocus(int filaSeleccionada) {
    try {
        Object valorCelda = visorLocus.getValueAt(filaSeleccionada, 0);

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


  
  public void mostrarLocus() {
    // Declaración de la conexión y otros objetos
    try (Connection conn = new CONECTOR().getConexion();
         Statement st = conn.createStatement()) {
        String SQL = "SELECT locus.*, estructuras.nombre AS NombreEstructura FROM locus JOIN estructuras ON locus.estructura_id = estructuras.id;";
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("nombre");
        model.addColumn("descripcion");
        model.addColumn("referencia");
        model.addColumn("estructura_id");
        model.addColumn("NombreEstructura");

        visorLocus.setModel(model);
        String[] datos = new String[6];

        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            datos[0] = rs.getString("id");
            datos[1] = rs.getString("nombre");
            datos[2] = rs.getString("descripcion");
            datos[3] = rs.getString("referencia");
            datos[4] = rs.getString("estructura_id");
            datos[5] = rs.getString("NombreEstructura");
            model.addRow(datos);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Muestra detalles del error en la consola.
        JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
    }
}
private void cargarLocusCombo() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = cn.getConexion();
        String sql = "SELECT id, nombre FROM estructuras ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        // Limpiar el JComboBox antes de cargar los nombres
        ComboBoxL1.removeAllItems();
        while (rs.next()) {
            String nombreSitio = rs.getString("nombre");
            ComboBoxL1.addItem(nombreSitio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los sitios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar la conexión y los recursos
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
public void editarLocus() {
    int filaSeleccionada = visorLocus.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila de locus para editar.");
        return;
    }

    // Obtener la clave primaria (ID) del registro de locus seleccionado
    int clavePrimaria = obtenerClavePrimariaDelLocus(filaSeleccionada);
    String nombre = visorLocus.getValueAt(filaSeleccionada, 1).toString();
    String descripcion = visorLocus.getValueAt(filaSeleccionada, 2).toString();
    String ubicacion = visorLocus.getValueAt(filaSeleccionada, 3).toString();
    String referencia = visorLocus.getValueAt(filaSeleccionada, 4).toString();
    String color = visorLocus.getValueAt(filaSeleccionada, 5).toString();

    // Llenar los campos de edición con los valores obtenidos
    txtNombreL.setText(nombre);
    txtDescripcionL.setText(descripcion);
    txtUbicacionL.setText(ubicacion);
    txtReferenciaL.setText(referencia);
    txtColorL.setText(color);
}


private void registrarLocus() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = cn.getConexion();
        String nombre = txtNombreL.getText();
        String descripcion = txtDescripcionL.getText();
        String ubicacion = txtUbicacionL.getText();
        String referencia = txtReferenciaL.getText();
        String color = txtColorL.getText();
        Object nombreLocusSeleccionado = ComboBoxL1.getSelectedItem();
        
        if (nombreLocusSeleccionado != null && !nombre.isEmpty() && !descripcion.isEmpty() && !referencia.isEmpty()) {
            String nombreLocus = nombreLocusSeleccionado.toString();
            String sql = "INSERT INTO locus (nombre, descripcion, ubicacion, referencia, color, estructura_id) VALUES (?, ?, ?, ?, ?, (SELECT id FROM estructuras WHERE nombre = ?))";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, ubicacion);
            ps.setString(4, referencia);
            ps.setString(5, color);
            ps.setString(6, nombreLocus);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se han insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron insertar los datos.");
                JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios y seleccione un sitio antes de registrar la estructura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar la estructura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
private void actualizarLocus() {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        CONECTOR cn = new CONECTOR();
        conn = cn.getConexion();
        int filaSeleccionada = visorLocus.getSelectedRow();
        int clavePrimaria = obtenerClavePrimariaDelRegistro(filaSeleccionada);
        String nombre = txtNombreL.getText();
        String descripcion = txtDescripcionL.getText();
        String ubicacion = txtUbicacionL.getText();
        String referencia = txtReferenciaL.getText();
        String color = txtColorL.getText();
        Object nombreSitioSeleccionado = ComboBoxL1.getSelectedItem();

        if (nombreSitioSeleccionado != null && !nombre.isEmpty() && !descripcion.isEmpty() && !referencia.isEmpty()) {
            String nombreSitio = nombreSitioSeleccionado.toString();

            String sql = "UPDATE locus SET nombre = ?, descripcion = ?, ubicacion = ?, referencia = ?, color = ?, estructura_id = (SELECT id FROM estructuras WHERE nombre = ?) WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, ubicacion);
            ps.setString(4, referencia);
            ps.setString(5, color);
            ps.setString(6, nombreSitio);
            ps.setInt(7, clavePrimaria);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se han actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se pudieron actualizar los datos.");
                JOptionPane.showMessageDialog(null, "No se pudieron actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios y seleccione un sitio antes de actualizar el locus.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar el locus: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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



  
    //METODOS MATERIALES
    //OTROS METODOS
    public void Autocompleter(JComboBox txtCarta){
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
                  txtCarta.addItem(rs.getString("nombre"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
    public void AutocompleterTwo(JComboBox txtCarta){
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
                  txtCarta.addItem(rs.getString("Tipo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnLocus = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSitios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        vtnVentanas = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtReferencia = new javax.swing.JTextField();
        txtCoordenadas = new javax.swing.JTextField();
        ComboBoxCarta = new javax.swing.JComboBox<>();
        ComboBoxCarta1 = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        visorEstructuras = new javax.swing.JTable();
        btnEliminar1 = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombreE = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcionE = new javax.swing.JTextArea();
        txtReferenciaE = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ComboBoxE = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnLimpiarLocus = new javax.swing.JButton();
        txtReferenciaL = new javax.swing.JTextField();
        btnEditarLocus = new javax.swing.JButton();
        btnRegistrarLocus = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDescripcionL = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnEliminarLocus = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        visorLocus = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombreL = new javax.swing.JTextField();
        ComboBoxL1 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txtUbicacionL = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtColorL = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 183, 164));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/salir.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        btnLocus2.setBackground(new java.awt.Color(96, 219, 164));
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

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/materiales.png"))); // NOI18N

        javax.swing.GroupLayout btnLocus2Layout = new javax.swing.GroupLayout(btnLocus2);
        btnLocus2.setLayout(btnLocus2Layout);
        btnLocus2Layout.setHorizontalGroup(
            btnLocus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(26, 26, 26))
        );
        btnLocus2Layout.setVerticalGroup(
            btnLocus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btnLocus2Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(btnLocus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 150, 50));

        btnLocus1.setBackground(new java.awt.Color(96, 219, 164));
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

        jLabel14.setText("LOCUS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Cedarseed-British-Museum-Aztec-mask-of-Xiuhtecuhtli.48.png"))); // NOI18N

        javax.swing.GroupLayout btnLocus1Layout = new javax.swing.GroupLayout(btnLocus1);
        btnLocus1.setLayout(btnLocus1Layout);
        btnLocus1Layout.setHorizontalGroup(
            btnLocus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(26, 26, 26))
        );
        btnLocus1Layout.setVerticalGroup(
            btnLocus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLocus1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btnLocus1Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(btnLocus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 50));

        btnEstructuras.setBackground(new java.awt.Color(96, 219, 164));
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

        jLabel4.setText("ESTRUCTURAS");

        casa.setBackground(new java.awt.Color(96, 219, 164));
        casa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/CASA2.png"))); // NOI18N

        javax.swing.GroupLayout btnEstructurasLayout = new javax.swing.GroupLayout(btnEstructuras);
        btnEstructuras.setLayout(btnEstructurasLayout);
        btnEstructurasLayout.setHorizontalGroup(
            btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEstructurasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(casa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
        );
        btnEstructurasLayout.setVerticalGroup(
            btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEstructurasLayout.createSequentialGroup()
                .addGroup(btnEstructurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnEstructurasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(casa))
                    .addGroup(btnEstructurasLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
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

        btnSitios.setBackground(new java.awt.Color(96, 219, 164));
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

        jLabel5.setText("SITIOS");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Fatcow-Farm-Fresh-Egyptian-pyramid.32.png"))); // NOI18N

        javax.swing.GroupLayout btnSitiosLayout = new javax.swing.GroupLayout(btnSitios);
        btnSitios.setLayout(btnSitiosLayout);
        btnSitiosLayout.setHorizontalGroup(
            btnSitiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSitiosLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(33, 33, 33))
        );
        btnSitiosLayout.setVerticalGroup(
            btnSitiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSitiosLayout.createSequentialGroup()
                .addGroup(btnSitiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnSitiosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5))
                    .addGroup(btnSitiosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(btnSitios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inah.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 190, 70));

        vtnVentanas.setBackground(new java.awt.Color(255, 255, 255));
        vtnVentanas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        vtnVentanas.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(96, 236, 251));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, -1));

        jLabel3.setText("NOMBRE:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, -1));

        jLabel9.setText("DESCRIPCION:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jLabel10.setText("REFERENCIA:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, -1, -1));

        jLabel11.setText("COORDENADAS:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));

        jLabel12.setText("CARTA TOPOGRAFICA:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));

        jLabel13.setText("TIPO:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, -1, -1));
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 230, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, -1, -1));
        jPanel3.add(txtReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 180, -1));
        jPanel3.add(txtCoordenadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 180, -1));

        ComboBoxCarta.setForeground(new java.awt.Color(255, 204, 0));
        ComboBoxCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCartaActionPerformed(evt);
            }
        });
        jPanel3.add(ComboBoxCarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));
        jPanel3.add(ComboBoxCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));

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
        jPanel3.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, -1, -1));

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
        jPanel3.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

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
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, -1, -1));

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
        jPanel3.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 60, 60));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setText("SITIOS");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, -1));

        vtnVentanas.addTab("SITIOS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(78, 218, 254));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/DELETE.png"))); // NOI18N
        btnEliminar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEliminar1MouseMoved(evt);
            }
        });
        btnEliminar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminar1MouseExited(evt);
            }
        });
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, -1, -1));

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
        jPanel4.add(btnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 60, 60));

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
        jPanel4.add(btnRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

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
        jPanel4.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, -1, -1));

        jLabel19.setText("NOMBRE :");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jLabel20.setText("DESCRIPCIÓN:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel21.setText("REFERENCIAS:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));
        jPanel4.add(txtNombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 120, -1));

        txtDescripcionE.setColumns(20);
        txtDescripcionE.setRows(5);
        jScrollPane4.setViewportView(txtDescripcionE);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));
        jPanel4.add(txtReferenciaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 130, -1));

        jLabel22.setText("NOMBRE DEL SITIO:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        ComboBoxE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEActionPerformed(evt);
            }
        });
        jPanel4.add(ComboBoxE, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 120, -1));

        vtnVentanas.addTab("ESTRUCTURAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(96, 219, 164));
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
        jPanel5.add(btnLimpiarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 60, 60));
        jPanel5.add(txtReferenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 200, -1));

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
        jPanel5.add(btnEditarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, -1, -1));

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
        jPanel5.add(btnRegistrarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, -1, -1));

        txtDescripcionL.setColumns(20);
        txtDescripcionL.setRows(5);
        jScrollPane5.setViewportView(txtDescripcionL);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, -1, -1));

        jLabel23.setText("NOMBRE DE ESTRUCTURA:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, -1));

        jLabel24.setText("DESCRIPCIÓN:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, -1, -1));

        btnEliminarLocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/DELETE.png"))); // NOI18N
        btnEliminarLocus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEliminarLocusMouseMoved(evt);
            }
        });
        btnEliminarLocus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarLocusMouseExited(evt);
            }
        });
        btnEliminarLocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLocusActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminarLocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, -1, -1));

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

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel25.setText("NOMBRE :");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel26.setText("REFERENCIA:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, -1));
        jPanel5.add(txtNombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 120, -1));

        ComboBoxL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxL1ActionPerformed(evt);
            }
        });
        jPanel5.add(ComboBoxL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 120, -1));

        jLabel27.setText("UBICACIÓN:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, -1));
        jPanel5.add(txtUbicacionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 110, -1));

        jLabel28.setText("COLOR:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));
        jPanel5.add(txtColorL, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 90, -1));

        vtnVentanas.addTab("LOCUS", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 884, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        vtnVentanas.addTab("MATERIALES", jPanel6);

        jPanel1.add(vtnVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 890, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 890, 410));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/vecteezy_abstract-gradient-background-with-green-and-blue-colors_6895305.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, 0, 2010, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSitiosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseMoved
     btnSitios.setBackground(new Color(255,255,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSitiosMouseMoved

    private void btnSitiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseExited
btnSitios.setBackground(new Color(96,219,164 ));       // TODO add your handling code here:
    }//GEN-LAST:event_btnSitiosMouseExited

    private void btnEstructurasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseMoved
btnEstructuras.setBackground(new Color(255,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstructurasMouseMoved

    private void btnEstructurasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseExited
      btnEstructuras.setBackground(new Color(96,219,164 ));      // TODO add your handling code here:
    }//GEN-LAST:event_btnEstructurasMouseExited

    private void btnLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseMoved
btnLocus.setBackground(new Color(255,255,255)); 
// TODO add your handling code here:
    }//GEN-LAST:event_btnLocusMouseMoved

    private void btnLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseExited
       btnLocus.setBackground(new Color(96,219,164 ));   
       // TODO add your handling code here:
    }//GEN-LAST:event_btnLocusMouseExited

    private void btnSitiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitiosMouseClicked
vtnVentanas.setSelectedIndex(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSitiosMouseClicked

    private void btnEstructurasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstructurasMouseClicked
vtnVentanas.setSelectedIndex(1);
    }//GEN-LAST:event_btnEstructurasMouseClicked

    private void btnLocusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocusMouseClicked
      vtnVentanas.setSelectedIndex(2);
      // TODO add your handling code here:
    }//GEN-LAST:event_btnLocusMouseClicked

    private void btnLocus1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseMoved
        btnLocus1.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnLocus1MouseMoved

    private void btnLocus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseClicked
      vtnVentanas.setSelectedIndex(2);
    }//GEN-LAST:event_btnLocus1MouseClicked

    private void btnLocus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseExited
        btnLocus1.setBackground(new Color(96,219,164 ));
    }//GEN-LAST:event_btnLocus1MouseExited

    private void btnLocus2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseMoved
btnLocus2   .setBackground(new Color(255,255,255)); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus2MouseMoved

    private void btnLocus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseClicked
        vtnVentanas.setSelectedIndex(3);
    }//GEN-LAST:event_btnLocus2MouseClicked

    private void btnLocus2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseExited
btnLocus2.setBackground(new Color(96,219,164 ));
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus2MouseExited

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
btnRegistrar.setToolTipText("REGISTRAR NUEVO SITIO");
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String referencia = txtReferencia.getText();
        String coordenadas = txtCoordenadas.getText();
        String carta = ComboBoxCarta.getName();
        String tipo = ComboBoxCarta1.getName();  
        // Aquí llama a la función para insertar datos en la base de datos
        Insertar(nombre, descripcion, referencia, coordenadas, carta, tipo);
         mostrar("sitios");
        // cargarSitios();
          
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void ComboBoxCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCartaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Hasta luego.");
                    System.exit(0); // Cierra la aplicación
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        btnEditar.setToolTipText("Modificar formulario");
        ////
         int filaSeleccionada = visor.getSelectedRow();
    if (filaSeleccionada == -1) {
        mostrarMensajeSeleccionFila();
        return; // Detiene la ejecución del método si no hay una fila seleccionada
    }

    llenarCamposDesdeTabla(); // Llena los campos desde la tabla
    actualizarSitio();
    mostrar("sitios");
       // cargarSitios();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
btnEliminar.setToolTipText("Eliminar formulario");
 eliminarRegistro();
 mostrar("sitios");
//cargarSitios();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

    txtNombre.setText("");
    txtDescripcion.setText("");
    txtReferencia.setText("");
    txtCoordenadas.setText("");
    ComboBoxCarta.setSelectedIndex(0);
    ComboBoxCarta1.setSelectedIndex(0);

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited

 btnLimpiar.setBackground(new Color(255,255,255)); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnLimpiarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseMoved
btnLimpiar.setBackground(new Color(96,219,164 ));
    }//GEN-LAST:event_btnLimpiarMouseMoved

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
       btnRegistrar.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnRegistrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseMoved
      btnRegistrar.setBackground(new Color(96,219,164 ));
    }//GEN-LAST:event_btnRegistrarMouseMoved

    private void btnEditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseExited
        btnEditar.setBackground(new Color(255,255,255)); 
    }//GEN-LAST:event_btnEditarMouseExited

    private void btnEditarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseMoved
        btnEditar.setBackground(new Color(96,219,164 ));// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarMouseMoved

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
     btnEliminar.setBackground(new Color(255,255,255)); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnEliminarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseMoved
 btnEliminar.setBackground(new Color(96,219,164 ));//         
// TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseMoved

    private void btnEliminar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1MouseMoved

    private void btnEliminar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1MouseExited

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
 int filaSeleccionada = visorEstructuras.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de eliminar el registro.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    } else {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            eliminarRegistroEstrutura(); // Llama al método eliminarRegistro si el usuario confirma
              mostrarEstructura("estructuras");
        }
    }      
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnLimpiar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1MouseMoved

    private void btnLimpiar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1MouseExited

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
limpiarCamposEstructura();
btnLimpiar1.setToolTipText("Limpiar formulario");
    txtNombreE.setText("");
    txtDescripcionE.setText("");
    txtReferenciaE.setText("");
    ComboBoxE.setSelectedIndex(0);
   
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnRegistrar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrar1MouseMoved

    private void btnRegistrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrar1MouseExited

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
registrarEstructura(); 
 mostrarEstructura("estructuras");
       
     


        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void btnEditar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1MouseMoved

    private void btnEditar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1MouseExited

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
actualizarRegistro();
mostrarEstructura("estructuras");
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void ComboBoxEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEActionPerformed

    private void visorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorMouseClicked
llenarCamposDesdeTabla();
// TODO add your handling code here:
    }//GEN-LAST:event_visorMouseClicked

    private void visorEstructurasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorEstructurasMouseClicked
editarRegistro();

        // TODO add your handling code here:
    }//GEN-LAST:event_visorEstructurasMouseClicked

    private void btnLimpiarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarLocusMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarLocusMouseMoved

    private void btnLimpiarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarLocusMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarLocusMouseExited

    private void btnLimpiarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLocusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarLocusActionPerformed

    private void btnEditarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarLocusMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarLocusMouseMoved

    private void btnEditarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarLocusMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarLocusMouseExited

    private void btnEditarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarLocusActionPerformed
actualizarLocus();
mostrarLocus();
// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarLocusActionPerformed

    private void btnRegistrarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarLocusMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarLocusMouseMoved

    private void btnRegistrarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarLocusMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarLocusMouseExited

    private void btnRegistrarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLocusActionPerformed
editarLocus();
mostrarLocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarLocusActionPerformed

    private void btnEliminarLocusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarLocusMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarLocusMouseMoved

    private void btnEliminarLocusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarLocusMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarLocusMouseExited

    private void btnEliminarLocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLocusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarLocusActionPerformed

    private void visorLocusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorLocusMouseClicked
editarLocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_visorLocusMouseClicked

    private void ComboBoxL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxL1ActionPerformed

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
    private javax.swing.JComboBox<String> ComboBoxCarta;
    private javax.swing.JComboBox<String> ComboBoxCarta1;
    private javax.swing.JComboBox<String> ComboBoxE;
    private javax.swing.JComboBox<String> ComboBoxL1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEditarLocus;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnEliminarLocus;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField txtColorL;
    private javax.swing.JTextField txtCoordenadas;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcionE;
    private javax.swing.JTextArea txtDescripcionL;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtNombreL;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtReferenciaE;
    private javax.swing.JTextField txtReferenciaL;
    private javax.swing.JTextField txtUbicacionL;
    public javax.swing.JTable visor;
    private javax.swing.JTable visorEstructuras;
    private javax.swing.JTable visorLocus;
    private javax.swing.JTabbedPane vtnVentanas;
    // End of variables declaration//GEN-END:variables
}
