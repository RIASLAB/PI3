package dao;

import modelo.EjecucionCita;
import modelo.Conexion; // <-- este es el que faltaba

import javax.swing.*;
import java.sql.*;

public class EjecucionCitaDao {
    public static Integer insertarEjecucion(EjecucionCita ejecucion) {
        String sql = "{CALL insertar_ejecucion_cita(?, ?, ?, ?)}";
        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, ejecucion.getCitaId());
            stmt.setString(2, ejecucion.getDiagnostico());
            stmt.setTimestamp(3, Timestamp.valueOf(ejecucion.getFechaEjecucion()));
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.execute();
            return stmt.getInt(4);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar cita:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

}}