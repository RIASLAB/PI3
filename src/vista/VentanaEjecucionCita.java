package vista;

import dao.EjecucionCitaDao;
import modelo.EjecucionCita;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class VentanaEjecucionCita extends JFrame {

    private JTextArea diagnosticoArea;
    private JButton guardarBtn;
    private int citaId;

    public VentanaEjecucionCita(int citaId) {
        this.citaId = citaId;

        setTitle("Ejecutar Cita");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(250, 250, 250));
        add(panelPrincipal);

        JLabel titulo = new JLabel("Diagnóstico de la Cita", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        diagnosticoArea = new JTextArea(8, 30);
        diagnosticoArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        diagnosticoArea.setLineWrap(true);
        diagnosticoArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(diagnosticoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Escriba el diagnóstico aquí"));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        guardarBtn = new JButton("Guardar Ejecución");
        guardarBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        guardarBtn.setBackground(new Color(33, 150, 243));
        guardarBtn.setForeground(Color.WHITE);
        guardarBtn.setFocusPainted(false);
        guardarBtn.setPreferredSize(new Dimension(180, 40));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(250, 250, 250));
        panelBoton.add(guardarBtn);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        guardarBtn.addActionListener(e -> {
            String diagnostico = diagnosticoArea.getText().trim();
            if (!diagnostico.isEmpty()) {
                EjecucionCita ejecucion = new EjecucionCita(citaId, diagnostico, LocalDateTime.now());
                Integer ejecucionId = EjecucionCitaDao.insertarEjecucion(ejecucion);
                if (ejecucionId != null) {
                    JOptionPane.showMessageDialog(this, "Cita ejecutada correctamente.\nID ejecución: " + ejecucionId);
                    int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas agregar una receta?", "Agregar Receta", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        new VentanaReceta(ejecucionId);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al ejecutar la cita.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El diagnóstico no puede estar vacío.");
            }
        });

        setVisible(true);
    }
}
