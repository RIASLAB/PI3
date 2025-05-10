package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Clínica Veterinaria - Sistema de Gestión");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Colores
        Color fondo = new Color(240, 248, 255); // Azul muy claro
        Color acento = new Color(70, 130, 180); // Azul acero
        Color botonFondo = new Color(100, 149, 237); // Azul intermedio
        Color botonTexto = Color.WHITE;

        // Fuente
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 20);
        Font fuenteSubtitulo = new Font("Segoe UI", Font.ITALIC, 16);
        Font fuenteBoton = new Font("Segoe UI", Font.PLAIN, 14);

        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout());

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(fondo);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Gestión de la Clínica Veterinaria");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(acento);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblTitulo);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblImagen = new JLabel();
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon("src/img/logo.png");
        Image scaledImage = icon.getImage().getScaledInstance(450, 150, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(scaledImage));
        panelCentral.add(lblImagen);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblSubtitulo = new JLabel("¡Tu mascota en buenas manos!");
        lblSubtitulo.setFont(fuenteSubtitulo);
        lblSubtitulo.setForeground(acento.darker());
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblSubtitulo);

        add(panelCentral, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));
        panelBotones.setBackground(fondo);

        String[] textos = {
                "Registrar Cliente", "Consultar Clientes",
                "Registrar Mascota", "Consultar Mascotas",
                "Agendar Cita", "Listar Citas",
                "Receta", "Imprimir Factura"
        };

        for (String texto : textos) {
            JButton btn = new JButton(texto);
            btn.setFocusPainted(false);
            btn.setBackground(botonFondo);
            btn.setForeground(botonTexto);
            btn.setFont(fuenteBoton);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(acento.darker()),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            panelBotones.add(btn);

            btn.addActionListener(e -> {
                switch (texto) {
                    case "Registrar Cliente":
                        new FormularioCliente(this).setVisible(true);
                        break;
                    case "Consultar Clientes":
                        new ConsultarCliente(this).setVisible(true);
                        break;
                    case "Registrar Mascota":
                        new FormularioMascota(this).setVisible(true);
                        break;
                    case "Consultar Mascotas":
                        new ConsultarMascota(this).setVisible(true);
                        break;
                    case "Agendar Cita":
                        new FormularioCita(this).setVisible(true);
                        break;
                    case "Listar Citas":
                        new ConsultarCita(this).setVisible(true);
                        break;
                    case "Receta":
                        abrirVentanaReceta();
                        break;
                    case "Imprimir Factura":
                        new VentanaFactura().setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, texto + " aún no implementado.");
                }
            });
        }

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void abrirVentanaReceta() {
        try {
            String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la ejecución de cita:");
            if (input != null && !input.trim().isEmpty()) {
                int ejecucionCitaId = Integer.parseInt(input.trim());
                new VentanaReceta(ejecucionCitaId).setVisible(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.");
        }
    }
}
