package dao;

import modelo.Cliente;
import modelo.Conexion;
import modelo.Mascota;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MascotaDao {
    public static boolean insertarMascota(Mascota mascota) {
        String sql = "{CALL insertar_mascota(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setDate(4, mascota.getFechaNacimiento());
            stmt.setString(5, mascota.getSexo());
            stmt.setInt(6, mascota.getClienteId());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar mascota:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean eliminarMascota(int mascotaId) {
        String sql = "{CALL eliminar_mascota(?)}";
        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, mascotaId);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar mascota:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String sql = "{CALL consultar_mascotas()}";

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mascota mascota = new Mascota(
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("sexo"),
                        rs.getInt("cliente_id")
                );
                mascota.setMascotaId(rs.getInt("mascota_id"));
                mascotas.add(mascota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
}



