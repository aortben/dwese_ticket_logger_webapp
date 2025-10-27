package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Region;

import java.sql.SQLException;
import java.util.List;
public interface RegionDAO {
    List<Region> listAllRegions() throws SQLException;
    void insertRegion(Region region) throws SQLException;
    void updateRegion(Region region) throws SQLException;
    void deleteRegion(Long id) throws SQLException;
    Region getRegionById(Long id) throws SQLException;
    boolean existsRegionByCode(String code) throws SQLException;
    boolean existsRegionByCodeAndNotId(String code, Long id) throws SQLException;
}

