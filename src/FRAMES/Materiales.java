package FRAMES;

import FRAMES.CONECTOR;
import com.mysql.cj.xdevapi.Statement;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import FRAMES.FramePrincipal;

public class Materiales {

public static void MaterialNuevo() {
        // Pregunta al usuario si desea trabajar con materiales
        int deseaTrabajarConMateriales = JOptionPane.showConfirmDialog(null, "¿Desea trabajar con materiales nuevos?", "Pregunta", JOptionPane.YES_NO_OPTION);

        if (deseaTrabajarConMateriales == JOptionPane.YES_OPTION) {
            Connection conn;
            CONECTOR cn = new CONECTOR();
            conn = cn.getConexion();

            String[] materiales = obtenerMaterialesDesdeBaseDeDatos(conn);

            // Crear un JComboBox con los materiales
            JComboBox<String> comboBox = new JComboBox<>(materiales);

            // Configurar el JComboBox para que esté centrado y más grande
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
                        // Ejecutar el switch en base a la selección
                        switch (selectedMaterial) {
                            case "Ceramica":
                                abrirFrameCeramica();
                                break;
                            case "Litica Tallada":
                                abrirFrameLiticaTallada();
                                break;
                            case "Litica Pulida":
                                abrirFrameLiticaPulida();
                                break;
                            // Agregar casos para otros materiales aquí
                        }
                        // Cerrar el JFrame personalizado al hacer clic en "Aceptar"
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
            // Continuar con otras acciones si el usuario no desea trabajar con materiales
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


    // Métodos para abrir frames específicos para cada material
    private static void abrirFrameCeramica() {
        // Lógica para abrir el frame de Ceramica
    }

    private static void abrirFrameLiticaTallada() {
        // Lógica para abrir el frame de Litica Tallada
    }

 public static void abrirFrameLiticaPulida() {
     
  
}


    // Agregar métodos similares para otros materiales
}
