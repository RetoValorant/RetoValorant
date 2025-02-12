package ModeloDAO;

import Modelo.Jornada;

import java.util.ArrayList;

public class JornadaDAO {
    private static ArrayList<Jornada> jornadas;
    public static void anadirJornada(Jornada j) {
        try {
            jornadas.add(j);
        }catch(NullPointerException e) {
            jornadas = new ArrayList<>();
            jornadas.add(j);
        }
    }
    public static ArrayList<Jornada> getJornadas() {
        return jornadas;
    }
}
