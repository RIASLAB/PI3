package modelo;

public class Cliente {
    private int clienteId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;

    public Cliente(int clienteId, String nombre, String apellido, String telefono, String email, String direccion) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public Cliente(String nombre, String apellido, String telefono, String email, String direccion) {
        this(0, nombre, apellido, telefono, email, direccion);
    }

    // Getters y Setters
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // Método toString para mostrar Cliente en JComboBox
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
