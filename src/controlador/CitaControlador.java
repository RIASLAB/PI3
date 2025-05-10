package controlador;

import dao.CitaDao;
import modelo.Cita;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class CitaControlador {

    // Método para guardar una cita
    public static void guardarCita(Cita cita) {
        boolean exito = CitaDao.insertarCita(cita);
        if (exito) {
            JOptionPane.showMessageDialog(null, "Cita agendada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al agendar la cita.");
        }
    }

    public static ArrayList<Cita> obtenerCitas() {
        return CitaDao.obtenerCitas();  // Devolver la lista de citas
    }
}

