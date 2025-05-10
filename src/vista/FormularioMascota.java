package vista;

import controlador.ClienteControlador;
import controlador.MascotaControlador;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class FormularioMascota extends JDialog {
    private JTextField txtNombre, txtEspecie, txtRaza;
    private JComboBox<String> cbSexo;
    private JSpinner spFecha;
    private JComboBox<Cliente> cbCliente;

    public FormularioMascota(JFrame parent) {
        super(parent, "Registrar Mascota", true);
        setSize(450, 450);
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        add(panelPrincipal);

        JLabel titulo = new JLabel("Formulario de Mascota", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
        panelCampos.setBackground(new Color(245, 245, 245));
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        txtNombre = new JTextField();
        txtEspecie = new JTextField();
        txtRaza = new JTextField();
        cbSexo = new JComboBox<>(new String[]{"Macho", "Hembra"});

        spFecha = new JSpinner(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spFecha, "yyyy-MM-dd");
        spFecha.setEditor(editor);

        cbCliente = new JComboBox<>();
        cargarClientes();

        panelCampos.add(new JLabel("Nombre:")); panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Especie:")); panelCampos.add(txtEspecie);
        panelCampos.add(new JLabel("Raza:")); panelCampos.add(txtRaza);
        panelCampos.add(new JLabel("Fecha Nacimiento:")); panelCampos.add(spFecha);
        panelCampos.add(new JLabel("Sexo:")); panelCampos.add(cbSexo);
        panelCampos.add(new JLabel("Cliente:")); panelCampos.add(cbCliente);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);

        btnCancelar.setBackground(new Color(204, 0, 0));
        btnCancelar.setForeground(Color.WHITE);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            Cliente clienteSeleccionado = (Cliente) cbCliente.getSelectedItem();
            if (clienteSeleccionado != null) {
                MascotaControlador.guardarMascota(
                        txtNombre.getText(),
                        txtEspecie.getText(),
                        txtRaza.getText(),
                        new Date(((java.util.Date) spFecha.getValue()).getTime()),
                        cbSexo.getSelectedItem().toString(),
                        clienteSeleccionado.getClienteId()
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente válido.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarClientes() {
        ArrayList<Cliente> clientes = ClienteControlador.obtenerClientes();
        for (Cliente c : clientes) {
            cbCliente.addItem(c);
        }
    }
}

