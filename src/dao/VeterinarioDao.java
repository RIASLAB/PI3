package dao;

import modelo.Veterinario;
import modelo.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class VeterinarioDao {

    // Insertar un nuevo veterinario en la base de datos
    public static boolean insertarVeterinario(Veterinario veterinario) {
        String sql = "INSERT INTO Veterinario (nombre, apellido, especialidad, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getApellido());
            stmt.setString(3, veterinario.getEspecialidad());
            stmt.setString(4, veterinario.getTelefono());
            stmt.setString(5, veterinario.getEmail());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todos los veterinarios de la base de datos
    public static ArrayList<Veterinario> obtenerVeterinarios() {
        ArrayList<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM Veterinario";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario veterinario = new Veterinario(
                        rs.getInt("veterinario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veterinarios;
    }
}
