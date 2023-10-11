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
import java.awt.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class FramePrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
         JOptionPane.showMessageDialog(null, "CONEXIÓN ESTABLECIDA BIENVENIDO");
      this.setTitle("Automatización y Modernización de Procesos en la Sección de Arqueología del Centro INAH Durango");
        this.setLocationRelativeTo(null);
          //cargarDatosDesdeTabla();
       llenarCamposDesdeTabla();
        mostrar("sitios");
        mostrarEstructura("estructuras");
        Autocompleter(ComboBoxCarta);
        AutocompleterTwo(ComboBoxCarta1);
        obtenerIdSitioPorNombre();
      
       // AutoCompleteDecorator.decorate(ComboBoxCarta);
    }
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
              System.out.println(rs.toString());
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
              System.out.println(rs.toString());
              while (rs.next()) {      
                  txtCarta.addItem(rs.getString("Tipo"));
                  
              }
          
      } catch (SQLException e) {
              System.err.println(e.toString());
      }
 
    
}
private void Insertar(String nombre, String descripcion, String referencia, String coordenadas, String carta, String tipo) {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs;

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
private String obtenerIdSitioPorNombre() {
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = cn.getConexion();

        // Consulta SQL para obtener los nombres de todos los sitios
        String sql = "SELECT nombre FROM SITIOS";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        // Limpiar el ComboBox antes de cargar los nombres
        ComboBoxE.removeAllItems();

        while (rs.next()) {
            String nombreSitio = rs.getString("nombre");
            ComboBoxE.addItem(nombreSitio);
        }

        // Verificar si hay un elemento seleccionado en el ComboBox
        if (ComboBoxE.getSelectedItem() != null) {
            // Obtener el nombre del sitio seleccionado en el ComboBox
            String nombreSitioSeleccionado = ComboBoxE.getSelectedItem().toString();

            // Consulta SQL para obtener el ID del sitio seleccionado por su nombre
            String sqlIdSitio = "SELECT id FROM SITIOS WHERE nombre = ?";
            ps = conn.prepareStatement(sqlIdSitio);
            ps.setString(1, nombreSitioSeleccionado);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener el ID del sitio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    return null;
}




// Método para registrar una estructura
private void registrarEstructura() {
    
    Connection conn = null;
    CONECTOR cn = new CONECTOR();
    PreparedStatement ps = null;
    ResultSet rs;

    try {
        conn = cn.getConexion();
        String sql = "INSERT INTO sitios (nombre, descripcion, referencia, coordenadas, Carta) VALUES (?, ?, ?, ?)";
        String elementoSeleccionado = ComboBoxE.getSelectedItem().toString();
        // Obtén los valores de los campos de texto u otras fuentes
        String nombre = txtNombreE.getText();
        String descripcion = txtDescripcionE.getText();
        String referencia = txtReferenciaE.getText();
      
        

        ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setString(3, referencia);
        ps.setString(4, elementoSeleccionado);
   

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









 public void insertarDesdeTextField(JTextField txtNombre, JTextField txtDescripcion, JTextField txtReferencia,  JTextField txtCoordenadas, JComboBox ComboBoxCarta,  JComboBox ComboBoxCarta1) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String referencia = txtReferencia.getText();
        String coordenadas = txtCoordenadas.getText();
        String carta = ComboBoxCarta.getSelectedItem().toString();
        String tipo =  ComboBoxCarta1.getSelectedItem().toString();;

        Insertar(nombre, descripcion, referencia, coordenadas, carta, tipo);
    }
 public void cargarDatosDesdeTabla() {
  Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Conecta a la base de datos SQLite (reemplaza con tu URL y credenciales)
     
      CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        // Prepara la consulta SQL para obtener la primera fila de la tabla (reemplaza "tu_tabla" con el nombre de tu tabla)
        String sql = "SELECT * FROM sitios";
        stmt = conn.prepareStatement(sql);

        // Ejecuta la consulta
        rs = stmt.executeQuery();

        // Verifica si hay datos y carga los valores en los JTextField
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
 public void llenarCamposDesdeTabla() {
    // Obtén la fila seleccionada de la tabla
    int filaSeleccionada = visor.getSelectedRow();

    // Verifica si se ha seleccionado una fila
    if (filaSeleccionada == -1) {
        // Si no se ha seleccionado una fila, no hagas nada
        return;
    }

    // Obtiene los datos de la fila seleccionada
    String nombre = visor.getValueAt(filaSeleccionada, 1).toString();
    String descripcion = visor.getValueAt(filaSeleccionada, 2).toString();
    String referencia = visor.getValueAt(filaSeleccionada, 3).toString();
    String coordenadas = visor.getValueAt(filaSeleccionada, 4).toString();
    String cartaTopografica = visor.getValueAt(filaSeleccionada, 5).toString();
    String tipo = visor.getValueAt(filaSeleccionada, 6).toString();

    // Llena los campos de texto y ComboBox con los valores obtenidos
    txtNombre.setText(nombre);
    txtDescripcion.setText(descripcion);
    txtReferencia.setText(referencia);
    txtCoordenadas.setText(coordenadas);
    ComboBoxCarta.setSelectedItem(cartaTopografica); // Establece el valor seleccionado en el ComboBox
    ComboBoxCarta1.setSelectedItem(tipo); // Establece el valor seleccionado en el ComboBox
}


 public void editarRegistro() {
    // Obtén la fila seleccionada de la tabla
    int filaSeleccionada = visor.getSelectedRow();

    // Verifica si se ha seleccionado una fila
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
        return; // Sal del método si no se ha seleccionado una fila
    }
      Connection conn = null;
      CONECTOR con=new CONECTOR();
        conn = con.getConexion();

    // Obtiene los datos de la fila seleccionada
    String id = visor.getValueAt(filaSeleccionada, 0).toString();
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
   ComboBoxCarta.getSelectedItem();
    ComboBoxCarta1.getSelectedItem();
    

    // Aquí defines la sentencia SQL para actualizar el registro
    String sqlUpdate = "UPDATE sitios SET nombre=?, descripcion=?, referencia=?, coordenadas=?, CartaTopografica=?, tipo=? WHERE id=?";
    
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, descripcion);
        preparedStatement.setString(3, referencia);
        preparedStatement.setString(4, coordenadas);
        preparedStatement.setString(5, cartaTopografica);
        preparedStatement.setString(6, tipo);
        preparedStatement.setString(7, id);
        
        // Ejecuta la sentencia de actualización
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente.");
            
            // Aquí puedes actualizar la tabla nuevamente para reflejar los cambios
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el registro: " + e.getMessage());
    }

    // Ahora puedes utilizar estos valores para editar el registro
    // Puedes abrir un formulario de edición y cargar estos valores en él
    // Luego, actualiza la base de datos con los nuevos valores
}
public void eliminarRegistro() {
    // Obtén la fila seleccionada de la tabla
    int filaSeleccionada = visor.getSelectedRow();
     Connection conn = null;
         String SQL = "SELECT * FROM sitios";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();

    // Verifica si se ha seleccionado una fila
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return; // Sal del método si no se ha seleccionado una fila
    }

    // Obtiene el ID del registro a eliminar
    String id = visor.getValueAt(filaSeleccionada, 0).toString();


    // Aquí defines la sentencia SQL para eliminar el registro
    String sqlDelete = "DELETE FROM sitios WHERE id=?";
    
    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
        preparedStatement.setString(1, id);
        
        // Ejecuta la sentencia de eliminación
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
            
            // Aquí puedes actualizar la tabla nuevamente para reflejar los cambios
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
        System.out.println(SQL);
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
     public void mostrarEstructura( String Tabla){
        Connection conn = null;
         String SQL = "SELECT estructuras.*, sitios.nombre AS NombreSitio FROM estructuras JOIN sitios ON estructuras.sitio_id = sitios.id;";
         Statement st;
         CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        System.out.println(SQL);
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
      public void cargarDatosEstructura() {
  Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Conecta a la base de datos SQLite (reemplaza con tu URL y credenciales)
     
      CONECTOR con=new CONECTOR();
        conn = con.getConexion();
        // Prepara la consulta SQL para obtener la primera fila de la tabla (reemplaza "tu_tabla" con el nombre de tu tabla)
        String sql = "SELECT * FROM sitios";
        stmt = conn.prepareStatement(sql);

        // Ejecuta la consulta
        rs = stmt.executeQuery();

        // Verifica si hay datos y carga los valores en los JTextField
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
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
        btnSitios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnLocus = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEstructuras = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        casa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnLocus1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnLocus2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 183, 164));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 530, -1));

        jLabel3.setText("NOMBRE:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jLabel9.setText("DESCRIPCION:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        jLabel10.setText("REFERENCIA:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        jLabel11.setText("COORDENADAS:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        jLabel12.setText("CARTA TOPOGRAFICA:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));

        jLabel13.setText("TIPO:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, -1, -1));
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 160, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));
        jPanel3.add(txtReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 180, -1));
        jPanel3.add(txtCoordenadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 180, -1));

        ComboBoxCarta.setForeground(new java.awt.Color(255, 204, 0));
        ComboBoxCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCartaActionPerformed(evt);
            }
        });
        jPanel3.add(ComboBoxCarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, -1, -1));
        jPanel3.add(ComboBoxCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, -1, -1));

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
        jPanel3.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

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
        jPanel3.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, -1, -1));

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
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, -1, -1));

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
        jPanel3.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 60, 60));

        vtnVentanas.addTab("SITIOS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        vtnVentanas.addTab("LOCUS", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vtnVentanas, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(vtnVentanas, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 900, 410));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inah.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 210, 70));

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

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/salir.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/usermarcos.png"))); // NOI18N
        jLabel18.setText("MARCOS");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, -1));

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus1MouseMoved

    private void btnLocus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus1MouseClicked

    private void btnLocus1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus1MouseExited

    private void btnLocus2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseMoved
btnLocus2   .setBackground(new Color(255,255,255)); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocus2MouseMoved

    private void btnLocus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocus2MouseClicked
        // TODO add your handling code here:
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
           obtenerIdSitioPorNombre();
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
        editarRegistro();
        mostrar("sitios");
 
// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
btnEliminar.setToolTipText("Eliminar formulario");
        eliminarRegistro();
 mostrar("sitios");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
btnLimpiar.setToolTipText("Limpiar formulario");
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnLimpiar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1MouseMoved

    private void btnLimpiar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiar1MouseExited

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
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
     //   mostrarEstructura(visorEstructuras);
     


        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void btnEditar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1MouseMoved

    private void btnEditar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1MouseExited

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void ComboBoxEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEActionPerformed

    private void visorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorMouseClicked
llenarCamposDesdeTabla();
// TODO add your handling code here:
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
    private javax.swing.JComboBox<String> ComboBoxCarta;
    private javax.swing.JComboBox<String> ComboBoxCarta1;
    private javax.swing.JComboBox<String> ComboBoxE;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JPanel btnEstructuras;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JPanel btnLocus;
    private javax.swing.JPanel btnLocus1;
    private javax.swing.JPanel btnLocus2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrar1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtCoordenadas;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcionE;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtReferenciaE;
    public javax.swing.JTable visor;
    private javax.swing.JTable visorEstructuras;
    private javax.swing.JTabbedPane vtnVentanas;
    // End of variables declaration//GEN-END:variables
}
