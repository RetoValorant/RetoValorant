package ModeloDAO;

import Modelo.Equipo;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    private List<Equipo> listaEquipos;

    public EquipoDAO() {
        this.listaEquipos = new ArrayList<>();
    }

    public void crearEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
        System.out.println("Equipo agregado.");
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        return listaEquipos;
    }

    public Equipo obtenerEquipoPorCodigo(String codEquipo) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getCodEquipo().equalsIgnoreCase(codEquipo)) {
                return equipo;
            }
        }
        return null;
    }

    public void actualizarEquipo(Equipo equipo) {
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getCodEquipo().equalsIgnoreCase(equipo.getCodEquipo())) {
                listaEquipos.set(i, equipo);
                System.out.println("Equipo actualizado.");
                return; // No es necesario seguir buscando
            }
        }
        System.out.println("Equipo no encontrado.");
    }

    public void eliminarEquipo(String codEquipo) {
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getCodEquipo().equalsIgnoreCase(codEquipo)) {
                listaEquipos.remove(i);
                System.out.println("Equipo eliminado.");
                return; // No es necesario seguir buscando
            }
        }
        System.out.println("Equipo no encontrado.");
    }
}