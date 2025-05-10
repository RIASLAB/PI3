package vista;

import controlador.CitaControlador;
import modelo.Cita;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ConsultarCita extends JDialog {

    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultarCita(JFrame parent) {
        super(parent, "Consultar Citas", true);
        setSize(850, 450);
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(250, 250, 250));
        add(panelPrincipal);

        JLabel lblTitulo = new JLabel("Listado de Citas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Cita");
        tableModel.addColumn("Fecha y Hora");
        tableModel.addColumn("Motivo");
        tableModel.addColumn("Mascota ID");
        tableModel.addColumn("Veterinario ID");

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(250, 250, 250));

        JButton btnEjecutarCita = new JButton("Ejecutar Cita");
        btnEjecutarCita.setBackground(new Color(33, 150, 243));
        btnEjecutarCita.setForeground(Color.WHITE);
        btnEjecutarCita.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjecutarCita.setFocusPainted(false);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(204, 0, 0));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);

        panelBotones.add(btnEjecutarCita);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Ejecutar Cita"
        btnEjecutarCita.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {
                int citaId = (int) tableModel.getValueAt(filaSeleccionada, 0);
                new VentanaEjecucionCita(citaId);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una cita para ejecutar.");
            }
        });

        // Acción del botón "Cerrar"
        btnCerrar.addActionListener(e -> dispose());

        // Cargar citas
        cargarCitas();
    }

    private void cargarCitas() {
        ArrayList<Cita> citas = CitaControlador.obtenerCitas();
        tableModel.setRowCount(0); // Limpiar tabla

        for (Cita cita : citas) {
            Object[] rowData = {
                    cita.getCitaId(),
                    cita.getFechaHora(),
                    cita.getMotivo(),
                    cita.getMascotaId(),
                    cita.getVeterinarioId()
            };
            tableModel.addRow(rowData);
        }
    }
}

