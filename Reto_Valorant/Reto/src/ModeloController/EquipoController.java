

package ModeloController;

import Modelo.Equipo;
import ModeloDAO.EquipoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class EquipoController {

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
                System.out.println("No se acepta ese formato para el nombre del equipo");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Nombre" + " no puede ser nulo");
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

}
