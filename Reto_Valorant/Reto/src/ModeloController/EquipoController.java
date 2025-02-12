package ModeloController;

import Modelo.Equipo;
import Modelo.Jugador;
import ModeloDAO.EquipoDAO;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class EquipoController {

    private EquipoDAO equipoDAO;

    public EquipoController() {
        this.equipoDAO = new EquipoDAO();
    }


    // Validar el nombre del equipo

    public boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches("^[A-Za-zÀ-ÿ\\s]{3,30}$", nombre);
    }


    // Validar fecha de fundacion

    public boolean validarFechaFundacion(LocalDate fecha) {
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }

    // Validar jugador

    public boolean validarJugador(Jugador jugador) {
        if (jugador == null || jugador.getNombre() == null) {
            return false;
        }
        return validarNombre(jugador.getNombre());
    }

    // Agregar equipo

    public void agregarEquipo(Equipo equipo) {
        equipoDAO.crearEquipo(equipo);
    }

    // Buscar equipo por codigo

    public Equipo buscarEquipoPorCodigo(String codEquipo) {
        return equipoDAO.obtenerEquipoPorCodigo(codEquipo);
    }

    // Actualizar o modificar un equipo

    public void actualizarEquipo(Equipo equipo) {
        equipoDAO.actualizarEquipo(equipo);
    }

    // Eliminar un equipo

    public void eliminarEquipo(String codEquipo) {
        equipoDAO.eliminarEquipo(codEquipo);
    }
}
