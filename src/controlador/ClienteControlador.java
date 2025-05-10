package controlador;

import dao.ClienteDao;
import modelo.Cliente;

import javax.swing.*;
import java.util.ArrayList;

public class ClienteControlador {

    public static void guardarCliente(String nombre, String apellido, String telefono, String email, String direccion) {
        Cliente cliente = new Cliente(nombre, apellido, telefono, email, direccion);
        boolean exito = ClienteDao.insertarCliente(cliente);

        if (exito) {
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente.");
        }
    }

    public static ArrayList<Cliente> obtenerClientes() {
        return ClienteDao.obtenerClientes();
    }

    public static boolean eliminarCliente(int clienteId) {
        return ClienteDao.eliminarCliente(clienteId);
    }
}
