package ModeloController;

import Modelo.Equipo;
import Modelo.Jugador;
import ModeloDAO.EquipoDAO;

import java.time.LocalDate;
import java.util.List;

public class EquipoController {

    private EquipoDAO equipoDAO;

    public EquipoController() {
        this.equipoDAO = new EquipoDAO();
    }

    public boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty() && nombre.matches("^[A-Za-zÀ-ÿ\\s]{3,30}$");
    }

    public boolean validarFechaFundacion(LocalDate fecha) {
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }

    public boolean validarJugador(Jugador jugador) {
        return jugador != null && jugador.getNombre() != null && validarNombre(jugador.getNombre());
    }

    public String generarCodEquipo() {
        List<Equipo> equipos = equipoDAO.obtenerTodosLosEquipos();
        if (equipos.isEmpty()) {
            return "EQ001";
        } else {
            int maxCod = 0;
            for (Equipo equipo : equipos) {
                int cod = Integer.parseInt(equipo.getCodEquipo().substring(2));
                if (cod > maxCod) {
                    maxCod = cod;
                }
            }
            return String.format("EQ%03d", maxCod + 1);
        }
    }

    public void agregarEquipo(Equipo equipo) {
        String codEquipo = generarCodEquipo();
        equipo.setCodEquipo(codEquipo);
        equipoDAO.crearEquipo(equipo);
    }

    public Equipo buscarEquipoPorCodigo(String codEquipo) {
        return equipoDAO.obtenerEquipoPorCodigo(codEquipo);
    }

    public void actualizarEquipo(Equipo equipo) {
        equipoDAO.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(String codEquipo) {
        equipoDAO.eliminarEquipo(codEquipo);
    }
}