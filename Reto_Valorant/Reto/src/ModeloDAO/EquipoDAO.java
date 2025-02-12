package ModeloDAO;

import Modelo.Equipo;

import java.util.Optional;

public class EquipoDAO {


    public int readCodEquipo(int cod){
        return Optional<Equipo> codEncontrado =
            equipos.stream()
                .filter(e -> e.getCodEquipo() == cod)
                        .findFirst().orElse(null);
    }
  //funcion paara leer UNICAMENTE codEqupo, es funcion secundaria de otra para autogenerar cdodigos de equipo
}
