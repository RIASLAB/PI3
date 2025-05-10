package dao;

import modelo.Conexion;
import vista.VentanaFactura.DetalleFacturaTemp;

import java.sql.*;
import java.util.List;

public class FacturaDao {
    public static boolean crearFactura(int clienteId, List<DetalleFacturaTemp> detalles) {
        Connection conn = null;
        PreparedStatement stmtFactura = null;
        PreparedStatement stmtDetalle = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false); // Transacción

            String sqlFactura = "INSERT INTO Factura (fecha, total, cliente_id) VALUES (NOW(), 0, ?)";
            stmtFactura = conn.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            stmtFactura.setInt(1, clienteId);
            stmtFactura.executeUpdate();

            rs = stmtFactura.getGeneratedKeys();
            if (rs.next()) {
                int facturaId = rs.getInt(1);
                double total = 0.0;

                for (DetalleFacturaTemp d : detalles) {
                    // Obtener precio actual
                    String sqlPrecio = "SELECT precio FROM Catalogo WHERE catalogo_id = ?";
                    double precio = 0;
                    try (PreparedStatement ps = conn.prepareStatement(sqlPrecio)) {
                        ps.setInt(1, d.catalogoId);
                        ResultSet r = ps.executeQuery();
                        if (r.next()) {
                            precio = r.getDouble("precio");
                        }
                    }

                    // Insertar detalle
                    String sqlDetalle = "INSERT INTO Detalle_factura (factura_id, catalogo_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
                    stmtDetalle = conn.prepareStatement(sqlDetalle);
                    stmtDetalle.setInt(1, facturaId);
                    stmtDetalle.setInt(2, d.catalogoId);
                    stmtDetalle.setInt(3, d.cantidad);
                    stmtDetalle.setDouble(4, precio);
                    stmtDetalle.executeUpdate();

                    total += precio * d.cantidad;
                }

                // Actualizar total
                String sqlUpdate = "UPDATE Factura SET total = ? WHERE factura_id = ?";
                try (PreparedStatement update = conn.prepareStatement(sqlUpdate)) {
                    update.setDouble(1, total);
                    update.setInt(2, facturaId);
                    update.executeUpdate();
                }

                conn.commit();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtFactura != null) stmtFactura.close();
                if (stmtDetalle != null) stmtDetalle.close();
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
        return false;
    }
}
