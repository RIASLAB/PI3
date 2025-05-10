package dao;

import modelo.Cita;
import modelo.Conexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CitaDao {


    public static boolean insertarCita(Cita cita) {
        String sql = "{CALL insertar_cita(?, ?, ?, ?)}";
        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setTimestamp(1, cita.getFechaHora());
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agendar cita:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static ArrayList<Cita> obtenerCitas() {
        ArrayList<Cita> citas = new ArrayList<>();
        String sql = "{CALL consultar_citas()}";

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("motivo"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id")
                );
                cita.setCitaId(rs.getInt("cita_id"));
                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
}

