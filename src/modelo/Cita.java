package modelo;

import java.sql.Timestamp;

public class Cita {
    private int citaId;
    private Timestamp fechaHora;  // Timestamp para manejar fecha y hora
    private String motivo;
    private int mascotaId;
    private int veterinarioId;

    // Constructor
    public Cita(Timestamp fechaHora, String motivo, int mascotaId, int veterinarioId) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
    }

    // Getters y Setters
    public int getCitaId() { return citaId; }
    public void setCitaId(int citaId) { this.citaId = citaId; }

    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp fechaHora) { this.fechaHora = fechaHora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int mascotaId) { this.mascotaId = mascotaId; }

    public int getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(int veterinarioId) { this.veterinarioId = veterinarioId; }
}
