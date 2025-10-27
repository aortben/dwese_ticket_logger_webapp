package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Repository
public class ComunidadAutonomaDAOImpl implements ComunidadAutonomaDAO {

    private static final Logger logger = LoggerFactory.getLogger(ComunidadAutonomaDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    // Inyección de JdbcTemplate
    public ComunidadAutonomaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ComunidadAutonoma> listAllComunidades() {
        logger.info("Listing all comunidades from the database.");
        String sql = "SELECT * FROM comunidades";
        List<ComunidadAutonoma> comunidades = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComunidadAutonoma.class));
        logger.info("Retrieved {} comunidades from the database.", comunidades.size());
        return comunidades;
    }

    @Override
    public void insertComunidad(ComunidadAutonoma comunidad) {
        logger.info("Inserting comunidad with code: {} and name: {}", comunidad.getCode(), comunidad.getName());
        String sql = "INSERT INTO comunidades (code, name, region_id) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, comunidad.getCode(), comunidad.getName(), comunidad.getRegionId());
        logger.info("Inserted comunidad. Rows affected: {}", rowsAffected);
    }

    @Override
    public void updateComunidad(ComunidadAutonoma comunidad) {
        logger.info("Updating comunidad with id: {}", comunidad.getId());
        String sql = "UPDATE comunidades SET code = ?, name = ?, region_id = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, comunidad.getCode(), comunidad.getName(), comunidad.getRegionId(), comunidad.getId());
        logger.info("Updated comunidad. Rows affected: {}", rowsAffected);
    }

    @Override
    public void deleteComunidad(Long id) {
        logger.info("Deleting comunidad with id: {}", id);
        String sql = "DELETE FROM comunidades WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted comunidad. Rows affected: {}", rowsAffected);
    }

    @Override
    public ComunidadAutonoma getComunidadById(Long id) {
        logger.info("Retrieving comunidad by id: {}", id);
        String sql = "SELECT * FROM comunidades WHERE id = ?";
        try {
            ComunidadAutonoma comunidad = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ComunidadAutonoma.class), id);
            logger.info("Comunidad retrieved: {} - {}", comunidad.getCode(), comunidad.getName());
            return comunidad;
        } catch (Exception e) {
            logger.warn("No comunidad found with id: {}", id);
            return null;
        }
    }

    @Override
    public boolean existsComunidadByCode(String code) {
        logger.info("Checking if comunidad with code: {} exists", code);
        String sql = "SELECT COUNT(*) FROM comunidades WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Comunidad with code: {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsComunidadByCodeAndNotId(String code, Long id) {
        logger.info("Checking if comunidad with code: {} exists excluding id: {}", code, id);
        String sql = "SELECT COUNT(*) FROM comunidades WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Comunidad with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}

