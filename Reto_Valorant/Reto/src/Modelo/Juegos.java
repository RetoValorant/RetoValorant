package Modelo;

import java.time.LocalDate;

public class Juegos {

    private int codjuego;
    private LocalDate fechaSalida;

    public Juegos() {
    }

    public Juegos(int codjuego, LocalDate fechaSalida) {
        this.codjuego = codjuego;
        this.fechaSalida = fechaSalida;
    }

    public int getCodjuego() {
        return codjuego;
    }

    public void setCodjuego(int codjuego) {
        this.codjuego = codjuego;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
