package ModeloDAO;

import Modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JugadorDAO {

        private List<Jugador> jugadores;


        public JugadorDAO() {
            this.jugadores = new ArrayList<>();
        }


        public void agregar(Jugador jugador) {
            jugadores.add(jugador);
        }


        public List<Jugador> obtenerTodos(){
            return new ArrayList<>(jugadores);
        }

        public void modificar(String codJugador, Jugador nuevoJugador) {
            jugadores.replaceAll(j -> j.getCodJugador() == nuevoJugador.getCodJugador() ? nuevoJugador : j);
        }


        public boolean eliminar(int codJugador) {
            return jugadores.removeIf(j -> j.getCodJugador() == codJugador);
        }


        public Optional<Jugador> obtenerPorCodigo(int codJugador) {
            return jugadores.stream().filter(j -> j.getCodJugador() == codJugador).findFirst();
        }


        public List<Jugador> obtenerPorEquipo(int codEquipo) {
            return jugadores.stream().filter(j -> j.getEquipo().get().getCodEquipo() == codEquipo).toList();
            //obtiene los jugadores por Equipo con codigo coincidente y los añade a 'jugadores'

        }


}
