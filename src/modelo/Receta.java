package modelo;

public class Receta {
    private int catalogoId;
    private int ejecucionCitaId;
    private String observaciones;

    public Receta(int catalogoId, int ejecucionCitaId, String observaciones) {
        this.catalogoId = catalogoId;
        this.ejecucionCitaId = ejecucionCitaId;
        this.observaciones = observaciones;
    }

    public int getCatalogoId() { return catalogoId; }
    public int getEjecucionCitaId() { return ejecucionCitaId; }
    public String getObservacion() { return observaciones; }
}
