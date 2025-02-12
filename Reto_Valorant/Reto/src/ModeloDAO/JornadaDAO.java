package ModeloDAO;

import Modelo.Jornada;

import java.util.ArrayList;

public class JornadaDAO {
    private ArrayList<Jornada> jornadas;
    public void anadirJornada(Jornada j) {
        try {
            jornadas.add(j);
        }catch(NullPointerException e) {
            jornadas = new ArrayList<>();
            jornadas.add(j);
        }
    }
    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }
}
