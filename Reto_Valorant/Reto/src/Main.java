import Modelo.Juego;
import ModeloController.*;
import ModeloDAO.JuegoDAO;

import javax.swing.*;
import java.util.NoSuchElementException;

public class Main {
    public static JuegoController juegoController;
    public static JugadorController jugadorController;
    public static EquipoController equipoController;
    public static JornadaController jornadaController;
    public static CompeticionController competicionController;
    public static EnfrentamientoController enfrentamientoController;
    public static String[] opcionesSinJuego;
    public static String[] opcionesSinJornadas;
    public static String[] opcionesConJornadas;

    public static void main(String[] args) {
        declararVariables();
        opcionesSinJuego();
        eligeElJuego();
        opcionesSinJornadas();
        opcionesConJornadas();
    }
    public static void declararVariables() {
        juegoController = new JuegoController();
        jugadorController = new JugadorController();
        equipoController = new EquipoController();
        jornadaController = new JornadaController();
        competicionController = new CompeticionController();
        enfrentamientoController = new EnfrentamientoController();

        opcionesSinJuego = new String[]{
                "1.Añadir un juego nuevo",
                "2.Eliminar un juego",
                "3.Modificar un juego",
                "4.Ver los juegos que hay",
                "5.Continuar a la competicion"
        };

        opcionesSinJornadas = new String[]{
                "01. Crear un Jugador",
                "02. Crear un Equipo",
                "03. Modificar un Jugador",
                "04. Modificar un Equipo",
                "05. Eliminar un Jugador",
                "06. Eliminar un Equipo",
                "07. Ver todos los jugadores",
                "08. Ver todos los equipos",
                "09. Ver informacion de un jugador en concreto",
                "10. Ver informacion de un equipo en concreto",
                "11. Ver los jugadores de un equipo",
                "12. Crear las jornadas"
        };

        opcionesConJornadas = new String[]{
                "01. Modificar un Jugador",
                "02. Modificar un Equipo",
                "03. Ver todos los jugadores",
                "04. Ver todos los equipos",
                "05. Ver informacion de un jugador en concreto",
                "06. Ver informacion de un equipo en concreto",
                "07. Ver los jugadores de un equipo",
                "08. Ver los enfrentamientos de una jornada",
                "09. Ver los enfrentamientos de un equipo",
                "10. Añadir un resultado a un enfrentamiento",
                "11. Ver la puntuacion de un equipo"
        };

    }

    public static void opcionesSinJuego() {
        boolean yes = true;
        do {
            try {
                String o = (String) JOptionPane.showInputDialog(
                        null,
                        "Elige las opciones",
                        "",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesSinJuego,
                        opcionesSinJuego[0]
                );
                int opcion = Integer.parseInt(o.substring(0, 1));

                switch (opcion) {
                    case 1 -> juegoController.anadirJuego();
                    case 2 -> juegoController.eliminarJuego();
                    case 3 -> juegoController.modificarJuego();
                    case 4 -> juegoController.verTodosJuegos();
                    default -> yes = validarCrearCompeticion(); //Cuando tengamos competicion pasarlo ahi
                }

            }catch (NoSuchElementException e){
                System.out.println("Teclee una opcion valida por favor su opcion es: " + e.getMessage()+"\n");
                // para notificar que ha pasado, ocurre cuando se 'lia' el Scanner
            }catch (NullPointerException e){
                System.out.println("La opcion es nula, aconsejamos crear antes para despues modificar\n");
            }catch (NumberFormatException e){
                System.out.println("No se acepta ese numero " +e.getMessage() +"\n");
            }
        }while(yes);
    }

    public static boolean validarCrearCompeticion(){
        boolean yes = true;
        JuegoDAO juegoDAO = new JuegoDAO();
        if (juegoDAO.obtenerTodosJuegos().isEmpty())
            JOptionPane.showMessageDialog(null, "¡No puedes continuar porque no hay ningun juego!",
                    "No hay juegos", JOptionPane.WARNING_MESSAGE);
        else
            yes = false;
        return yes;
    }

    public static void eligeElJuego(){
        Juego juego = juegoController.elegirElJuego();
        equipoController.definirFechaFundacion(juego);
        competicionController.crearCompeticion(juego);
    }

    public static void opcionesSinJornadas() {
        boolean yes=true;
        do {
            try {

                String o = (String) JOptionPane.showInputDialog(
                        null,
                        "Elige las opciones",
                        "",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesSinJornadas,
                        opcionesSinJornadas[0]
                );

                int opcion = Integer.parseInt(o.substring(0, 2));

                switch (opcion) {
                    case 1 -> jugadorController.dataValidation();
                    case 2 -> equipoController.validarDatosEquipo();
                    case 3 -> jugadorController.modificarJugador();
                    case 4 -> equipoController.modificarEquipo();
                    case 5 -> jugadorController.eliminarJugador();
                    case 6 -> equipoController.eliminarEquipo();
                    case 7 -> jugadorController.verTodosJugadores();
                    case 8 -> equipoController.verTodosEquipos();
                    case 9 -> jugadorController.verPorNombre();
                    case 10 -> equipoController.verPorNombre();
                    case 11 -> equipoController.verJugadores();
                    case 12 -> {
                        yes = jornadaController.validarCreacionJornada();
                        if (!yes)
                            enfrentamientoController.crearEnfrentamientos();
                    }
                    default -> JOptionPane.showMessageDialog(null,"Opcion no permitida");
                }
            }catch (NoSuchElementException e){
                System.out.println("Teclee una opcion valida por favor su opcion es: " + e.getMessage()+"\n");
                //Es porque el IDE no conoce al objeto y no sabe darle excepcion
            } catch (NullPointerException e){
                System.out.println("La opcion es nula, aconsejamos crear antes para despues modificar\n");
                yes = true;
            }catch (NumberFormatException e){
                System.out.println("No se acepta ese numero " +e.getMessage() +"\n");
            }
        }while(yes);
    }

    public static void opcionesConJornadas() {
        boolean yes = true;
        do {
            String o = (String) JOptionPane.showInputDialog(
                    null,
                    "Elige las opciones",
                    "",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcionesConJornadas,
                    opcionesConJornadas[0]
            );

            int opcion = Integer.parseInt(o.substring(0, 2));

            switch (opcion) {
                case 1 -> jornadaController.validarCreacionJornada();
                case 2 -> equipoController.modificarEquipo();
                case 3 -> jugadorController.verTodosJugadores();
                case 4 -> equipoController.verTodosEquipos();
                case 5 -> jugadorController.verPorNombre();
                case 6 -> equipoController.verPorNombre();
                case 7 -> equipoController.verJugadores();
                case 8 -> enfrentamientoController.verEnfrentamientosJornada();
                case 9 -> enfrentamientoController.verEnfrentamientosEquipo();
                case 10 -> enfrentamientoController.anadirResultado();
                case 11 -> equipoController.verPuntuacionEquipo();
                default -> yes = false;
            }
        }while(yes);
    }
}