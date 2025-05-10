package vista;

import dao.CatalogoDao;
import dao.ClienteDao;
import dao.FacturaDao;
import modelo.Catalogo;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaFactura extends JFrame {
    private JComboBox<Cliente> clienteCombo;
    private JComboBox<Catalogo> catalogoCombo;
    private JTextField cantidadField;
    private JTextArea resumenArea;
    private List<DetalleFacturaTemp> detalles;

    public VentanaFactura() {
        setTitle("Generar Factura");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        detalles = new ArrayList<>();

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(250, 250, 250));
        add(panelPrincipal);

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(250, 250, 250));

        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        clienteCombo = new JComboBox<>();

        JLabel productoLabel = new JLabel("Producto/Servicio:");
        productoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        catalogoCombo = new JComboBox<>();

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cantidadField = new JTextField();

        inputPanel.add(clienteLabel);
        inputPanel.add(clienteCombo);
        inputPanel.add(productoLabel);
        inputPanel.add(catalogoCombo);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadField);

        cargarClientes();
        cargarCatalogo();

        // Botones
        JButton agregarBtn = new JButton("Agregar Detalle");
        JButton generarBtn = new JButton("Generar Factura");

        configurarBoton(agregarBtn, new Color(76, 175, 80)); // Verde
        configurarBoton(generarBtn, new Color(33, 150, 243)); // Azul

        inputPanel.add(agregarBtn);
        inputPanel.add(generarBtn);

        panelPrincipal.add(inputPanel, BorderLayout.NORTH);

        // Área de resumen
        resumenArea = new JTextArea();
        resumenArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resumenArea.setEditable(false);
        resumenArea.setBorder(BorderFactory.createTitledBorder("Resumen de Factura"));

        JScrollPane scrollResumen = new JScrollPane(resumenArea);
        panelPrincipal.add(scrollResumen, BorderLayout.CENTER);

        // Acciones
        agregarBtn.addActionListener(e -> agregarDetalle());
        generarBtn.addActionListener(e -> generarFactura());

        setVisible(true);
    }

    private void configurarBoton(JButton boton, Color color) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(160, 35));
    }

    private void cargarClientes() {
        for (Cliente c : ClienteDao.obtenerClientes()) {
            clienteCombo.addItem(c);
        }
    }

    private void cargarCatalogo() {
        for (Catalogo c : CatalogoDao.obtenerCatalogo()) {
            catalogoCombo.addItem(c);
        }
    }

    private void agregarDetalle() {
        Catalogo seleccionado = (Catalogo) catalogoCombo.getSelectedItem();
        if (seleccionado != null) {
            try {
                int cantidad = Integer.parseInt(cantidadField.getText().trim());
                detalles.add(new DetalleFacturaTemp(seleccionado.getCatalogoId(), cantidad));
                resumenArea.append(seleccionado + " | Cantidad: " + cantidad + "\n");
                cantidadField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida.");
            }
        }
    }

    private void generarFactura() {
        Cliente clienteSeleccionado = (Cliente) clienteCombo.getSelectedItem();
        if (clienteSeleccionado != null && !detalles.isEmpty()) {
            boolean ok = FacturaDao.crearFactura(clienteSeleccionado.getClienteId(), detalles);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Factura generada correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al generar la factura.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona cliente y agrega al menos un detalle.");
        }
    }

    public static class DetalleFacturaTemp {
        public int catalogoId;
        public int cantidad;

        public DetalleFacturaTemp(int catalogoId, int cantidad) {
            this.catalogoId = catalogoId;
            this.cantidad = cantidad;
        }
    }
}
