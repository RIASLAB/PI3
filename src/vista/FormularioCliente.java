package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class FormularioCliente extends JDialog {
    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;

    public FormularioCliente(JFrame parent) {
        super(parent, "Registrar Cliente", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        add(panelPrincipal);

        JLabel titulo = new JLabel("Formulario de Cliente", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampos.setBackground(new Color(245, 245, 245));
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelCampos.add(txtApellido);

        panelCampos.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

        panelCampos.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelCampos.add(txtEmail);

        panelCampos.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion);

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
            controlador.ClienteControlador.guardarCliente(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    txtDireccion.getText()
            );
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
