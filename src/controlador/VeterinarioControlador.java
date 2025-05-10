package controlador;

import dao.VeterinarioDao;
import modelo.Veterinario;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class VeterinarioControlador {

    // Guardar un nuevo veterinario
    public static void guardarVeterinario(String nombre, String apellido, String especialidad, String telefono, String email) {
        Veterinario veterinario = new Veterinario(0, nombre, apellido, especialidad, telefono, email); // El ID será auto-incrementado
        boolean exito = VeterinarioDao.insertarVeterinario(veterinario);
        if (exito) {
            JOptionPane.showMessageDialog(null, "Veterinario registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar veterinario.");
        }
    }

    // Obtener todos los veterinarios
    public static ArrayList<Veterinario> obtenerVeterinarios() {
        return VeterinarioDao.obtenerVeterinarios();
    }
}
