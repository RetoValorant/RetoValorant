import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

        private List<Jugador> jugadores;


        public JugadorDAO() {
            this.jugadores = new ArrayList<>();
        }


        public void agregar(Jugador jugador) {
            jugadores.add(jugador);
        }


        public void modificar(String codJugador, Jugador nuevoJugador) {
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                if (jugador.getCodJugador().equals(codJugador)) {
                    jugadores.set(i, nuevoJugador); // Reemplaza el jugador viejo por el nuevo
                    return;
                }
            }
        }


        public void eliminar(String codJugador) {
            jugadores.removeIf(jugador -> jugador.getCodJugador().equals(codJugador));
        }


        public List<Jugador> obtenerTodos() {
            return new ArrayList<>(jugadores);
        }


        public Jugador obtenerPorCodigo(String codJugador) {
            for (Jugador jugador : jugadores) {
                if (jugador.getCodJugador().equals(codJugador)) {
                    return jugador;
                }
            }
            return null;
        }


        public List<Jugador> obtenerPorEquipo(int codEquipo) {
            List<Jugador> jugadoresEquipo = new ArrayList<>();
            for (Jugador jugador : jugadores) {
                if (jugador.getEquipo().getCodEquipo() == codEquipo) {
                    jugadoresEquipo.add(jugador);
                }
            }
            return jugadoresEquipo;
        }


}
