package ModeloController;

import Modelo.Jornada;
import ModeloDAO.JornadaDAO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class JornadaController {
    private static Jornada jornada;
    private static JornadaDAO jornadaDAO;
    private static final int[] meses31 = {1,3,5,7,8,10,12};
    public boolean validarCreacionJornada(){
        crearJornada();
        return true;
    }
    public void crearJornada(){
        /*
        ArrayList<Equipo> equipos = EquipoDAO.VerEquipos();
        for (int i = 0; i < equipos.size(); i++){
        */
        for (int i = 0; i < 6; i++){
            jornada = new Jornada();
            jornada.setNumJornada(elegirNumJornada());
            jornada.setFechaInicio(elegirFecha());
            //EnfrentamientoController.crearEnfrentamiento();
            JornadaDAO.anadirJornada(jornada);
        }
        ArrayList<Jornada> jornadas = JornadaDAO.getJornadas();
        for (Jornada jornada : jornadas){
            System.out.println(jornada.getNumJornada() + " " + jornada.getFechaInicio());
        }
    }
    public int elegirNumJornada(){
        int numJornada = 0;
        try {
            ArrayList<Jornada> jornadas = JornadaDAO.getJornadas();
            numJornada = jornadas.getLast().getNumJornada()+1;
        }catch (NullPointerException e){
            numJornada = 1;
        }
        return numJornada;
    }
    public LocalDate elegirFecha(){
        int mes = 1;
        int dia = 1;
        int year = 1;
        try {
            ArrayList<Jornada> jornadas = JornadaDAO.getJornadas();
            LocalDate fecha = elegirDia(jornadas);
            mes = fecha.getMonthValue();
            dia = fecha.getDayOfMonth();
            year = fecha.getYear();
        }catch (NullPointerException e){
            mes = elegirMes();
            dia = elegirDiaInicial(mes);
            year = 2025;
        }
        return LocalDate.of(year,mes,dia);
    }
    private int elegirMes(){
        Random random = new Random();
        return random.nextInt(11)+1;
    }
    private int elegirDiaInicial(int mes){
        Random random = new Random();
        int randomDia = 0;
        if (mes == 2)
            randomDia = random.nextInt(27)+1;
        else{
            for (int i : meses31) {
                if (mes == i)
                    randomDia = random.nextInt(30)+1;
                else
                    randomDia = random.nextInt(29)+1;
            }
        }
        return randomDia;
    }
    private LocalDate elegirDia(ArrayList<Jornada> jornadas){
        Random random = new Random();
        int randomDia = 0;
        randomDia = random.nextInt(7);
        DayOfWeek diaJornada = jornadas.getLast().getFechaInicio().getDayOfWeek();
        int diasHastaDomingo = DayOfWeek.SUNDAY.getValue() - diaJornada.getValue();
        // Si el día actual no es domingo, suma los días necesarios
        if (diasHastaDomingo < 0) {
            diasHastaDomingo += 7;
        }
        LocalDate fecha = jornadas.getLast().getFechaInicio().plusDays(diasHastaDomingo).plusDays(randomDia);
        return fecha;
    }

}
