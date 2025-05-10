package vista;

import controlador.CitaControlador;
import controlador.MascotaControlador;
import controlador.VeterinarioControlador;
import modelo.Cita;
import modelo.Mascota;
import modelo.Veterinario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class FormularioCita extends JDialog {
    private JComboBox<Mascota> cbMascota;
    private JComboBox<Veterinario> cbVeterinario;
    private JSpinner spFechaHora;
    private JTextArea txtMotivo;

    public FormularioCita(JFrame parent) {
        super(parent, "Agendar Cita", true);
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 250, 255)); // Fondo suave

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Componentes
        cbMascota = new JComboBox<>();
        cargarMascotas();

        cbVeterinario = new JComboBox<>();
        cargarVeterinarios();

        spFechaHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spFechaHora, "yyyy-MM-dd HH:mm");
        spFechaHora.setEditor(editor);

        txtMotivo = new JTextArea(4, 20);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        JScrollPane motivoScroll = new JScrollPane(txtMotivo);
        motivoScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Agrega etiquetas y campos
        formPanel.add(new JLabel("Mascota:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbMascota, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Veterinario:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbVeterinario, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Fecha y Hora:"), gbc);
        gbc.gridx = 1;
        formPanel.add(spFechaHora, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1;
        formPanel.add(motivoScroll, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Botones
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        btnPanel.setOpaque(false);
        btnPanel.add(btnGuardar);
        btnPanel.add(btnCancelar);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // Acciones
        btnGuardar.addActionListener(e -> {
            Mascota mascotaSeleccionada = (Mascota) cbMascota.getSelectedItem();
            Veterinario veterinarioSeleccionado = (Veterinario) cbVeterinario.getSelectedItem();

            if (mascotaSeleccionada != null && veterinarioSeleccionado != null) {
                Cita cita = new Cita(
                        new Timestamp(((java.util.Date) spFechaHora.getValue()).getTime()),
                        txtMotivo.getText(),
                        mascotaSeleccionada.getMascotaId(),
                        veterinarioSeleccionado.getVeterinarioId()
                );

                CitaControlador.guardarCita(cita);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una mascota y un veterinario.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        add(mainPanel);
    }

    private void cargarMascotas() {
        ArrayList<Mascota> mascotas = MascotaControlador.obtenerMascotas();
        for (Mascota m : mascotas) {
            cbMascota.addItem(m);
        }
    }

    private void cargarVeterinarios() {
        ArrayList<Veterinario> veterinarios = VeterinarioControlador.obtenerVeterinarios();
        for (Veterinario v : veterinarios) {
            cbVeterinario.addItem(v);
        }
    }
}

