package ModeloDAO;

import Modelo.Enfrentamiento;
import Modelo.Jornada;

import java.util.ArrayList;

public class EnfrentamientoDAO {
    private ArrayList<Enfrentamiento> enfrentamientos;
    public void anadirEnfrentamientos(Enfrentamiento en) {
        try {
            enfrentamientos.add(en);
        }catch(NullPointerException e) {
            enfrentamientos = new ArrayList<>();
            enfrentamientos.add(en);
        }
    }
    public ArrayList<Enfrentamiento> getEnfrentamientos() {
        if (enfrentamientos == null)
            return enfrentamientos = new ArrayList<>();
        else
            return enfrentamientos;
    }
}
