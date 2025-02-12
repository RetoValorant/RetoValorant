package ModeloController;

import Modelo.Equipo;
import ModeloDAO.EquipoDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquipoController {
    public static Equipo equipo;
    public static EquipoDAO eDAO;

    public EquipoController(Equipo equipo){
        equipo = new Equipo();
        eDAO = new EquipoDAO();
    }

    public EquipoController() {
    }

    /*validaciones :

    *   Fecha Inscripcion
    *   numJugadores
    *   codEquipos
    *   Datos varios

    */
    public Equipo validarDatosEquipo(){
        Equipo equ = new Equipo();
            equ.setNombre(this.validarDato("Nombre","Ingrese el nombre del equipo","[a-zA-Z0-9_- ]+"));
            equ.setCodEquipo(this.generarCodEquipo(0));
            equ.setFechaFundacion(this.validarFechaFundacion("Fecha","Ingrese la fehca de fundacion del equipo",""));
        return equ;
    }

    public String validarDato(String dato,String msj,String patron){
        String var="";
        boolean isFinished=false;
        Pattern p = Pattern.compile(patron);
        do {
            try {
                var = JOptionPane.showInputDialog(msj);

                Matcher matcher = p.matcher(var);
                if (var.isBlank()){
                    JOptionPane.showMessageDialog(null,dato + " no puede ser nulo");
                }else if (matcher.matches()){
                    isFinished=true; //sale de la repetitiva xq es correcto
                }
            }catch (NumberFormatException e){
                System.out.println("No se acepta ese formato para " + dato);
            }
        }while (!isFinished);
        return var;
    }

    public int generarCodEquipo(int codEquipo) {
        while (eDAO.readCodEquipo(codEquipo) != null) {
            //itera sobre DAO hasta econtrar un 'hueco vacio', cuando lo hace lo retorna(asignado)
            codEquipo++;
        }
        return codEquipo;
    }

    public LocalDate validarFechaFundacion(String dato,String msj,String fechaNOpars){
        boolean validFecha=false;
        LocalDate fechaPars=null; LocalDate fechaFund = LocalDate.of(2020,6,2);
        do {
            try {
                fechaNOpars = JOptionPane.showInputDialog(null,msj);
                if (fechaNOpars.isBlank()){
                    JOptionPane.showMessageDialog(null,"la fecha no puede estar vacia");
                }else{
                    fechaPars = LocalDate.parse(fechaNOpars); //se va a dejar como fecha anglosajona para posterior SQL
                    if (fechaPars.isBefore(fechaFund)){
                        JOptionPane.showMessageDialog(null,"La fecha de fundacion no puede ser anterior al año de creacion del juego");
                    }
                    validFecha=true;
                }
            }catch (DateTimeParseException | NumberFormatException e){
                System.out.println(fechaNOpars + " error al parsear la fecha : " + e.getMessage());
            }
        }while (!validFecha);
        return fechaPars;
    }



}
