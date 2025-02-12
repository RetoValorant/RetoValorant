package ModeloController;

import Modelo.Equipo;
import Modelo.Jugador;
import ModeloDAO.EquipoDAO;
import ModeloDAO.JugadorDAO;
import Nacionalidades.Country;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class JugadorController {
    public static JugadorDAO jugadorDAO;
    private static EquipoDAO equipoDAO;

    public JugadorController() {
        equipoDAO = new EquipoDAO();
        jugadorDAO = new JugadorDAO();
    }

    public void dataValidation(){
        Jugador j = new Jugador();
            j.setCodJugador(generarCodJugador());
            j.setNombre(this.validarNomApeNik("Nombre","Ingresa el nombre del jugador"));
            j.setApellido(this.validarNomApeNik("Apellido","Ingresa el apellido del jugador"));
            j.setNacionalidad(this.validarNacionalidad());
            j.setEquipo(this.validarEquipos());
            //Si este metodo devuelve null hay que dar una opcion de modificar jugador para dar de alta en un equipo
        jugadorDAO.agregar(j);
    }

    private String validarNomApeNik(String dato,String msj){
        boolean isValid = false;
        Pattern pattern = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+ [a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+$");
        String var="";
        do {
            try {
                var = JOptionPane.showInputDialog(null,msj);
                Matcher matcher = pattern.matcher(var);

                if (matcher.matches()) {
                    isValid = true;
                }else {
                    System.out.println(dato + " no utiliza un formato valido");
                }

            }catch (NullPointerException e){
                System.out.println("No se puede ingresar el " + dato + " vacio");
            }
        }while (!isValid);
        return var;
    }
    private String validarNacionalidad(){
        boolean isValid = false;
        Pattern pattern = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+(?:-[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)*(?: [A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+(?:-[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)*)*$");
        //generado por ChatGPT
        String var="";
        do {
            try {
                var= JOptionPane.showInputDialog(null,"En que pais nacio el jugador?");
                Matcher matcher = pattern.matcher(var);

                if (matcher.matches()) {
                    var = getCodigoOSI(var);
                    if (var == null) {
                        System.out.println("Nacionalidad no encontrada");
                    }else {
                        isValid = true;
                    }
                }else {
                    System.out.println("Nacionalidad no utiliza un formato valido");
                }

            }catch (NullPointerException e){
                System.out.println("No se puede ingresar el" + "Nacionalidad" + "vacio");
            }
        }while (!isValid);
        return var;
    }
    private String getCodigoOSI(String nombrePais) {
        for (Country pais : Country.values()) {
            if (pais.getName().equalsIgnoreCase(nombrePais)) {
                return pais.getThreeDigitsCode();
                // asi todos los jugadores tienen nacionalidad OSI (esp,fra,cro...)
            }
        }
        return null; //en caso de no encontrar devuelve null
    }
    private int generarCodJugador(){
        Set<Integer> codExistentes = jugadorDAO.obtenerTodos() .stream().map(Jugador::getCodJugador).collect(toSet());
        //recoge todos los codigos de los jugadores

        int codJugador = 0;
        while (codExistentes.contains(codJugador)) {
            codJugador++;
            //hasta que no sea mas grande el nuevo codigo, no sale del bucle
        }
        return codJugador;
    }

    private Optional<Equipo> validarEquipos() {
        List<Equipo> equiposDisponibles = equipoDAO.obtenerTodosLosEquipos();

        if (equiposDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede ingresar el equipo porque no existe");
            return Optional.empty();
        }

        String nombre = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona el equipo al que pertenece el Jugador",
                "Opciones",
                JOptionPane.PLAIN_MESSAGE,
                null,
                equiposDisponibles.stream().map(Equipo::getNombre).toArray(String[]::new),
                equiposDisponibles.getFirst().getNombre()
        );

        if (nombre == null) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún equipo");
            return Optional.empty();
        }else {
            return equiposDisponibles.stream()
                    .filter(e -> e.getNombre().equals(nombre))
                    .findFirst();
        }

    }

}
