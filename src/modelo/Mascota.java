package modelo;

import java.sql.Date;

public class Mascota {
    private int mascotaId;
    private String nombre;
    private String especie;
    private String raza;
    private Date fechaNacimiento;
    private String sexo;
    private int clienteId;

    public Mascota(String nombre, String especie, String raza, Date fechaNacimiento, String sexo, int clienteId) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.clienteId = clienteId;
    }
    public String toString() {
        return this.nombre;  // Puedes cambiar esto a cualquier atributo que desees mostrar
    }
    // Getters y Setters
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int mascotaId) { this.mascotaId = mascotaId; }

    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public String getSexo() { return sexo; }
    public int getClienteId() { return clienteId; }

}
