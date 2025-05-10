package modelo;

public class Catalogo {
    private int catalogoId;
    private String descripcion;

    public Catalogo(int catalogoId, String descripcion) {
        this.catalogoId = catalogoId;
        this.descripcion = descripcion;
    }

    public int getCatalogoId() { return catalogoId; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return catalogoId + " - " + descripcion;
    }
}
