package vista;

import dao.CatalogoDao;
import dao.RecetaDao;
import modelo.Catalogo;
import modelo.Receta;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaReceta extends JFrame {
    private JComboBox<Catalogo> catalogoCombo;
    private JTextArea observacionArea;
    private JButton guardarBtn;
    private int ejecucionCitaId;

    public VentanaReceta(int ejecucionCitaId) {
        this.ejecucionCitaId = ejecucionCitaId;

        setTitle("Agregar Receta");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con borde redondeado y fondo
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(245, 250, 255));
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 240), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y combo box de catálogo
        JLabel productoLabel = new JLabel("Producto o Servicio:");
        productoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        catalogoCombo = new JComboBox<>();
        cargarCatalogo();
        catalogoCombo.setBackground(Color.white);
        catalogoCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(productoLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelPrincipal.add(catalogoCombo, gbc);

        // Etiqueta y área de observaciones
        JLabel observacionLabel = new JLabel("Observaciones:");
        observacionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        observacionArea = new JTextArea(5, 30);
        observacionArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        observacionArea.setWrapStyleWord(true);
        observacionArea.setLineWrap(true);
        observacionArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "Escriba aquí", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.ITALIC, 12)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelPrincipal.add(observacionLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = new JScrollPane(observacionArea);
        scrollPane.setBorder(null);
        panelPrincipal.add(scrollPane, gbc);

        // Botón guardar
        guardarBtn = new JButton("Guardar Receta");
        guardarBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        guardarBtn.setBackground(new Color(66, 135, 245));
        guardarBtn.setForeground(Color.white);
        guardarBtn.setFocusPainted(false);
        guardarBtn.setPreferredSize(new Dimension(180, 40));

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(guardarBtn, gbc);

        add(panelPrincipal, BorderLayout.CENTER);

        // Acción del botón
        guardarBtn.addActionListener((ActionEvent e) -> {
            Catalogo seleccionado = (Catalogo) catalogoCombo.getSelectedItem();
            if (seleccionado != null) {
                int catalogoId = seleccionado.getCatalogoId();
                String observacion = observacionArea.getText().trim();

                Receta receta = new Receta(catalogoId, ejecucionCitaId, observacion);
                boolean exito = RecetaDao.insertarReceta(receta);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Receta guardada correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar receta.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto o servicio.");
            }
        });

        setVisible(true);
    }

    private void cargarCatalogo() {
        for (Catalogo item : CatalogoDao.obtenerCatalogo()) {
            catalogoCombo.addItem(item);
        }
    }
}
