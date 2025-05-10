package controlador;

import dao.MascotaDao;
import modelo.Mascota;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class MascotaControlador {

    public static void guardarMascota(String nombre, String especie, String raza, Date fechaNacimiento, String sexo, int clienteId) {
        Mascota mascota = new Mascota(nombre, especie, raza, fechaNacimiento, sexo, clienteId);
        boolean exito = MascotaDao.insertarMascota(mascota);

        if (exito) {
            JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la mascota.");
        }
    }

    // Retorna la lista de mascotas
    public static ArrayList<Mascota> obtenerMascotas() {
        return MascotaDao.obtenerMascotas();
    }

    // Elimina una mascota por su ID
    public static boolean eliminarMascota(int mascotaId) {
        return MascotaDao.eliminarMascota(mascotaId);
    }
}


