

package ModeloController;

import Modelo.Equipo;
import ModeloDAO.EquipoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class EquipoController{

    public static EquipoDAO eDAO;
    private static final LocalDate FECHAFUNDACION = LocalDate.of(2020, 6, 2);
    //fecha fundacion hay que meterla en Juego

    public EquipoController() {
        eDAO = new EquipoDAO();
    }

    public void validarDatosEquipo() {
        Equipo equ = new Equipo();
            equ.setNombre(this.validarDato());
            equ.setCodEquipo(this.generarCodEquipo());
            equ.setFechaFundacion(this.validarFechaFundacion());
        eDAO.crearEquipo(equ);
    }
    private String validarDato() {
        String var = "";
        boolean isFinished = false;
        Pattern p = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{3,15}$"); //15 como mucho como en MER/MR

        do {
            try {
                var = JOptionPane.showInputDialog(null, "Ingrese el nombre del equipo");
                Matcher matcher = p.matcher(var);

                if (var.trim().isBlank()){
                    JOptionPane.showMessageDialog(null, "El nombre del equipo no puede ser nulo");
                } else if (matcher.matches()) {
                    isFinished = true;
                }else {
                    JOptionPane.showMessageDialog(null, var + " tiene un patron inválido");
                }
            } catch (NumberFormatException e) {
                System.out.println("El nombre del equipo NO debe ser menor que 3 o mayor que 15");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "No se admite valor nulo");
            }
        } while(!isFinished);

        return var;
    }
    private int generarCodEquipo() {
        Set<Integer> codigosEquipo = eDAO.obtenerTodosLosEquipos()
                    .stream().map(Equipo::getCodEquipo)
                    .collect(Collectors.toSet());
        int codEquipo = 0;
        while (codigosEquipo.contains(codEquipo)) {
            codEquipo++;
            //hasta que no encuentra un nuevo codigo no sale del loop
        }
        return codEquipo;
    }
    private LocalDate validarFechaFundacion() {
        boolean validFecha = false;
        LocalDate fechaPars = null;
        String fechaNOpars = "";
        do {
            try {
                fechaNOpars = JOptionPane.showInputDialog(null, "Ingrese la fecha de fundacion del equipo");
                if (fechaNOpars.isBlank()) {
                    JOptionPane.showMessageDialog(null, "la fecha no puede estar vacia");
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaPars = LocalDate.parse(fechaNOpars,formatter);
                    if (fechaPars.isBefore(FECHAFUNDACION)) {
                        JOptionPane.showMessageDialog(null, "La fecha de fundacion no puede ser anterior al año de creacion del juego");
                    }
                    validFecha = true;
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println(fechaNOpars + " error al parsear la fecha : " +e.getMessage());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, fechaNOpars + " no puede ser nulo");
            }
        } while(!validFecha);

        return fechaPars;
    }
    public void modificarEquipo(){
        Equipo e = new Equipo();
        ArrayList<Equipo> equipos = eDAO.obtenerTodosLosEquipos();
        do {
            try {
                String opc= (String) JOptionPane.showInputDialog(null,
                        "Que jugador?",
                        "Opciones",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        equipos.stream().map(Equipo::getNombre).toArray(String[]::new),
                        equipos.getFirst().getNombre()
                );
                if (opc==null || opc.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"El jugador no puede ser nulo");
                }else {
                    e = eDAO.obtenerTodosLosEquipos().stream().filter(jugador -> jugador.getNombre().equals(opc)).findFirst().orElse(null);
                    //con solo el nombre obtiene todos los datos de Jugador j
                }
            }catch (NullPointerException _){
                System.out.println("el jugador no puede ser nulo");
            }
        }while (JOptionPane.showConfirmDialog(null,"Quiere continuar modificando equipos=?") == 0);
        //sale de repetitiva
        opcionesModificar(e);
    }
    private void opcionesModificar(Equipo e){
        String[] opc = {"Nombre","Fecha de fundacion","Jugadores"};
        try {
            String opcion = (String) JOptionPane.showInputDialog(null,
                    "Que quieres modificar",
                    "Opciones",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opc,
                    opc[0]
            );
            if (opcion.isBlank()){
                JOptionPane.showMessageDialog(null,"No se permiten opciones nulas");
            }else {
                switch (opcion){
                    case "Nombre" -> e.setNombre(this.validarDato());
                    case "Fecha de fundacion" -> e.setFechaFundacion(this.validarFechaFundacion());
                    //case "Jugadores" -> e.setListaJugadores();
                    default -> JOptionPane.showMessageDialog(null,"No se puede modificar eso en el jugador");
                }
            }
        }catch (NullPointerException _){
            System.out.println("No se aceptan valores nulos");
        }
    }
    public void eliminarEquipo(){
        Equipo e = new Equipo();
        ArrayList<Equipo> equipos = eDAO.obtenerTodosLosEquipos();
        do {
            try {
                String opc= (String) JOptionPane.showInputDialog(null,
                        "Que equipo quiere eliminar?",
                        "Opciones",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        equipos.stream().map(Equipo::getNombre).toArray(String[]::new),
                        equipos.getFirst().getNombre()
                );
                if (opc==null || opc.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"El jugador no puede ser nulo");
                }else {
                    e = eDAO.obtenerTodosLosEquipos().stream().filter(jugador -> jugador.getNombre().equals(opc)).findFirst().orElse(null);
                    //con solo el nombre obtiene todos los datos de Jugador j

                }
            }catch (NullPointerException _){
                System.out.println("el jugador no puede ser nulo");
            }
        }while (JOptionPane.showConfirmDialog(null,"Quiere continuar eliminando equipos?") == 0);
        //sale de repetitiva
        eDAO.eliminarEquipo(Objects.requireNonNull(e).getCodEquipo());
    }
    public void verTodosEquipos(){
        try {
            ArrayList<Equipo> equipos
                    = eDAO.obtenerTodosLosEquipos();
            for (Equipo j : equipos){

                JOptionPane.showMessageDialog(null,j.toString());
                //esto tengo que mejorarlo por que si hay 40 jugadores aparecen tantas veces las ventanas
            }
        }catch (NullPointerException e){
            System.out.println("No hay jugadores para enseñar");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public void verPorNombre(){
        String[] nombres = eDAO.obtenerTodosLosEquipos().stream().map(Equipo::getNombre).toArray(String[]::new);
        do {
            String equipoElegido = (String) JOptionPane.showInputDialog(null,
                    "Elija el nombre del equipo que quiere ver",
                    "Opciones",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombres,
                    nombres[0]
            );
            if (equipoElegido == null || equipoElegido.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,"El nombre no puede ser nulo");
            }else {

                Equipo e = eDAO.obtenerTodosLosEquipos().stream().filter(equipo -> equipo.getNombre().equals(equipoElegido)).findFirst().orElse(null);

                JOptionPane.showMessageDialog(null, Objects.requireNonNull(e).toString());
            }
        }while (JOptionPane.showConfirmDialog(null,"quiere continuar viendo equipos?") == 0);
    }

}
