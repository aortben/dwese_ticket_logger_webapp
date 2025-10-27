package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Provincia;

import java.sql.SQLException;
import java.util.List;

public interface ProvinciaDAO {
    List<Provincia> listAllProvincias() throws SQLException;
    void insertProvincia(Provincia provincia) throws SQLException;
    void updateProvincia(Provincia provincia) throws SQLException;
    void deleteProvincia(Long id) throws SQLException;
    Provincia getProvinciaById(Long id) throws SQLException;
    boolean existsProvinciaByCode(String code) throws SQLException;
    boolean existsProvinciaByCodeAndNotId(String code, Long id) throws SQLException;
}

