package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;

import java.sql.SQLException;
import java.util.List;

public interface ComunidadAutonomaDAO {
    List<ComunidadAutonoma> listAllComunidades() throws SQLException;
    void insertComunidad(ComunidadAutonoma comunidad) throws SQLException;
    void updateComunidad(ComunidadAutonoma comunidad) throws SQLException;
    void deleteComunidad(Long id) throws SQLException;
    ComunidadAutonoma getComunidadById(Long id) throws SQLException;
    boolean existsComunidadByCode(String code) throws SQLException;
    boolean existsComunidadByCodeAndNotId(String code, Long id) throws SQLException;
}
