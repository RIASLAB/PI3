package dao;

import modelo.Catalogo;
import modelo.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDao {
    public static List<Catalogo> obtenerCatalogo() {
        List<Catalogo> lista = new ArrayList<>();
        String sql = "SELECT catalogo_id, descripcion FROM catalogo";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("catalogo_id");
                String descripcion = rs.getString("descripcion");
                lista.add(new Catalogo(id, descripcion));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener catálogo: " + e.getMessage());
        }
        return lista;
    }
}
