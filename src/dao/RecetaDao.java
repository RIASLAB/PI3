package dao;

import modelo.Conexion;
import modelo.Receta;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RecetaDao {
    public static boolean insertarReceta(Receta receta) {
        String sql = "{CALL insertar_receta(?, ?, ?)}";
        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, receta.getCatalogoId());
            stmt.setInt(2, receta.getEjecucionCitaId());
            stmt.setString(3, receta.getObservacion());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar receta:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
