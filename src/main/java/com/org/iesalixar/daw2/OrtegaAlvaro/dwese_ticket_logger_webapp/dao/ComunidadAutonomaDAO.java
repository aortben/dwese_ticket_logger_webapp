package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
import java.util.List;

public interface ComunidadAutonomaDAO {

    List<ComunidadAutonoma> listAllComunidades();
    void insertComunidad(ComunidadAutonoma comunidad);
    void updateComunidad(ComunidadAutonoma comunidad);
    void deleteComunidad(int id); // Cambiado a int
    ComunidadAutonoma getComunidadById(int id); // Cambiado a int
    boolean existsComunidadByCode(String code);
    boolean existsComunidadByCodeAndNotId(String code, int id); // Cambiado a int
}

