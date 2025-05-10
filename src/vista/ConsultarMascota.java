package vista;

import controlador.MascotaControlador;
import modelo.Mascota;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ConsultarMascota extends JDialog {

    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultarMascota(JFrame parent) {
        super(parent, "Consultar Mascotas", true);
        setSize(850, 450);
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(250, 250, 250));
        add(panelPrincipal);

        JLabel lblTitulo = new JLabel("Listado de Mascotas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Crear la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Especie");
        tableModel.addColumn("Raza");
        tableModel.addColumn("Fecha Nacimiento");
        tableModel.addColumn("Sexo");
        tableModel.addColumn("Cliente ID");

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botones
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 0));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(204, 0, 0));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 14));

// Panel de botones
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(250, 250, 250));
        panelBoton.add(btnEliminar);
        panelBoton.add(btnCerrar);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);



        btnCerrar.addActionListener(e -> dispose());

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una mascota para eliminar.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar esta mascota?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int mascotaId = (int) tableModel.getValueAt(filaSeleccionada, 0);  // Columna ID

                boolean eliminado = MascotaControlador.eliminarMascota(mascotaId);
                if (eliminado) {
                    tableModel.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(this, "Mascota eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la mascota.");
                }
            }
        });


        // Cargar datos
        cargarMascotas();
    }

    private void cargarMascotas() {
        ArrayList<Mascota> mascotas = MascotaControlador.obtenerMascotas();
        for (Mascota mascota : mascotas) {
            Object[] rowData = {
                    mascota.getMascotaId(),
                    mascota.getNombre(),
                    mascota.getEspecie(),
                    mascota.getRaza(),
                    mascota.getFechaNacimiento(),
                    mascota.getSexo(),
                    mascota.getClienteId()
            };
            tableModel.addRow(rowData);
        }
    }
}
