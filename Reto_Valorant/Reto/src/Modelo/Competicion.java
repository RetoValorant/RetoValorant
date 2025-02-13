package Modelo;

public class Competicion {

    private int codCompeticion;
    private char estado;

    public Competicion() {
    }

    public Competicion(int codCompeticion, char estado) {
        this.codCompeticion = codCompeticion;
        this.estado = estado;
    }

    public int getCodCompeticion() {
        return codCompeticion;
    }

    public void setCodCompeticion(int codCompeticion) {
        this.codCompeticion = codCompeticion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
