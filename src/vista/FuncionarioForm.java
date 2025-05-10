package vista;

import dao.FuncionarioDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Funcionario; 

public class FuncionarioForm extends JFrame {
    private JTextField txtTipoId, txtNumId, txtNombres, txtApellidos, txtEstadoCivil, txtSexo, txtDireccion, txtTelefono, txtFechaNacimiento;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tabla;
    private FuncionarioDAO dao = new FuncionarioDAO();
    private DefaultTableModel modeloTabla;

    public FuncionarioForm() {
        setTitle("Registro de Funcionario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
   
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo ID:"), gbc);
        gbc.gridx = 1;
        txtTipoId = new JTextField(15);
        panel.add(txtTipoId, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Número ID:"), gbc);
        gbc.gridx = 3;
        txtNumId = new JTextField(15);
        panel.add(txtNumId, gbc);

        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        txtNombres = new JTextField(15);
        panel.add(txtNombres, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 3;
        txtApellidos = new JTextField(15);
        panel.add(txtApellidos, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Estado civil:"), gbc);
        gbc.gridx = 1;
        txtEstadoCivil = new JTextField(15);
        panel.add(txtEstadoCivil, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 3;
        txtSexo = new JTextField(15);
        panel.add(txtSexo, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(15);
        panel.add(txtDireccion, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 3;
        txtTelefono = new JTextField(15);
        panel.add(txtTelefono, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        panel.add(new JLabel("Fecha Nacimiento:"), gbc);
        gbc.gridx = 1;
        txtFechaNacimiento = new JTextField(15);
        panel.add(txtFechaNacimiento, gbc);

        gbc.gridy = 5; gbc.gridx = 1;
        btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar, gbc);
        gbc.gridx = 2;
        btnActualizar = new JButton("Actualizar");
        panel.add(btnActualizar, gbc);
        gbc.gridx = 3;
        btnEliminar = new JButton("Eliminar");
        panel.add(btnEliminar, gbc);

        add(panel, BorderLayout.NORTH);

        String[] columnas = {"ID", "Tipo ID", "Número", "Nombres", "Apellidos", "Estado Civil", "Sexo", "Dirección", "Teléfono", "Nacimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                txtTipoId.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
                txtNumId.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
                txtNombres.setText(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
                txtApellidos.setText(tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
                txtEstadoCivil.setText(tabla.getValueAt(tabla.getSelectedRow(), 5).toString());
                txtSexo.setText(tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
                txtDireccion.setText(tabla.getValueAt(tabla.getSelectedRow(), 7).toString());
                txtTelefono.setText(tabla.getValueAt(tabla.getSelectedRow(), 8).toString());
                txtFechaNacimiento.setText(tabla.getValueAt(tabla.getSelectedRow(), 9).toString());
    }
});

        //boton guardar
        btnGuardar.addActionListener((ActionEvent e) -> {
            Funcionario f = new Funcionario(
                0,
                txtTipoId.getText(),
                txtNumId.getText(),
                txtNombres.getText(),
                txtApellidos.getText(),
                txtEstadoCivil.getText(),
                txtSexo.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(),
                txtFechaNacimiento.getText()
                
            );

             int id = dao.insertarFuncionario(f);

            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Funcionario insertado con ID: " + id);
                limpiarCampos();
                cargarFuncionariosEnTabla(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar funcionario");
            }
        }); 

        //boton eliminar  

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar funcionario?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = dao.eliminarFuncionario(id);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(this, "Funcionario eliminado.");
                        cargarFuncionariosEnTabla();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un funcionario de la tabla.");
            }
        });

        //boton actualizar

        btnActualizar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());

            Funcionario actualizado = new Funcionario(
            id,
            txtTipoId.getText(),
            txtNumId.getText(),
            txtNombres.getText(),
            txtApellidos.getText(),
            txtEstadoCivil.getText(),
            txtSexo.getText(),
            txtDireccion.getText(),
            txtTelefono.getText(),
            txtFechaNacimiento.getText()
        );

            boolean exito = dao.actualizarFuncionario(actualizado);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente.");
                cargarFuncionariosEnTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el funcionario.");
            }

            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un funcionario para actualizar.");
            }
        });

        cargarFuncionariosEnTabla();
}
    
        private void cargarFuncionariosEnTabla() {
        modeloTabla.setRowCount(0); 
        List<Funcionario> lista = dao.listarFuncionarios();
        for (Funcionario f : lista) {
            modeloTabla.addRow(new Object[]{
            f.getIdFuncionario(),
            f.getTipoIdentificacion(),
            f.getNumeroIdentificacion(),
            f.getNombres(),
            f.getApellidos(),
            f.getEstadoCivil(),
            f.getSexo(),
            f.getDireccion(),
            f.getTelefono(),
            f.getFechaNacimiento()
        });
        }
    }

        private void limpiarCampos() {
            txtTipoId.setText("");
            txtNumId.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            txtEstadoCivil.setText("");
            txtSexo.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new FuncionarioForm().setVisible(true));
        }
        
    }
