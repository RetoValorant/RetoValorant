package Modelo;

import java.util.ArrayList;

public class JuegosRoles {

    private ArrayList<Juegos> listaJuegos;
    private ArrayList<Rol> listaRoles;;

    public JuegosRoles() {
    }

    public JuegosRoles(ArrayList<Juegos> listaJuegos, ArrayList<Rol> listaRoles) {
        this.listaJuegos = listaJuegos;
        this.listaRoles = listaRoles;
    }

    public ArrayList<Juegos> getListaJuegos() {
        return listaJuegos;
    }

    public void setListaJuegos(ArrayList<Juegos> listaJuegos) {
        this.listaJuegos = listaJuegos;
    }

    public ArrayList<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(ArrayList<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }
}
