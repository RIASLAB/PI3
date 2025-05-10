package modelo;

import java.time.LocalDateTime;

public class EjecucionCita {
    private int citaId;
    private String diagnostico;
    private LocalDateTime fechaEjecucion;


    public EjecucionCita(int citaId, String diagnostico, LocalDateTime fechaEjecucion) {
        this.citaId = citaId;
        this.diagnostico = diagnostico;
        this.fechaEjecucion = fechaEjecucion;
    }

    public int getCitaId() { return citaId; }
    public String getDiagnostico() { return diagnostico; }
    public LocalDateTime getFechaEjecucion() { return fechaEjecucion; }
}
