package vista;

import controlador.ClienteControlador;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ConsultarCliente extends JDialog {

    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultarCliente(JFrame parent) {
        super(parent, "Consultar Clientes", true);
        setSize(700, 450);
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(250, 250, 250));
        add(panelPrincipal);

        JLabel lblTitulo = new JLabel("Listado de Clientes", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Crear tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Email");
        tableModel.addColumn("Dirección");

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(250, 250, 250));
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        // Botón Eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(0, 102, 204));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelBoton.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int clienteId = (int) tableModel.getValueAt(selectedRow, 0);
                    if (ClienteControlador.eliminarCliente(clienteId)) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.");
            }
        });

        // Botón Cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(204, 0, 0));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrar.addActionListener(e -> dispose());
        panelBoton.add(btnCerrar);

        // Cargar datos
        cargarClientes();
    }

    private void cargarClientes() {
        ArrayList<Cliente> clientes = ClienteControlador.obtenerClientes();
        for (Cliente cliente : clientes) {
            Object[] rowData = {
                    cliente.getClienteId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getDireccion()
            };
            tableModel.addRow(rowData);
        }
    }
}
