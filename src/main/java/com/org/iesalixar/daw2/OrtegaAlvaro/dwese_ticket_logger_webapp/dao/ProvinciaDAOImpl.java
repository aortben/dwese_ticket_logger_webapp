package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Provincia;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Repository
public class ProvinciaDAOImpl implements ProvinciaDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    // Inyección de JdbcTemplate
    public ProvinciaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Lista todas las provincias de la base de datos.
     * @return Lista de provincias
     */
    @Override
    public List<Provincia> listAllProvincias() {
        logger.info("Listing all provincias from the database.");
        String sql = "SELECT * FROM provincias";
        List<Provincia> provincias = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Provincia.class));
        logger.info("Retrieved {} provincias from the database.", provincias.size());
        return provincias;
    }

    /**
     * Inserta una nueva provincia en la base de datos.
     * @param provincia Provincia a insertar
     */
    @Override
    public void insertProvincia(Provincia provincia) {
        logger.info("Inserting provincia with code: {} and name: {}", provincia.getCode(), provincia.getName());
        String sql = "INSERT INTO provincias (code, name, comunidad_id) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, provincia.getCode(), provincia.getName(), provincia.getComunidadId());
        logger.info("Inserted provincia. Rows affected: {}", rowsAffected);
    }

    /**
     * Actualiza una provincia existente.
     * @param provincia Provincia a actualizar
     */
    @Override
    public void updateProvincia(Provincia provincia) {
        logger.info("Updating provincia with id: {}", provincia.getId());
        String sql = "UPDATE provincias SET code = ?, name = ?, comunidad_id = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, provincia.getCode(), provincia.getName(), provincia.getComunidadId(), provincia.getId());
        logger.info("Updated provincia. Rows affected: {}", rowsAffected);
    }

    /**
     * Elimina una provincia de la base de datos.
     * @param id ID de la provincia
     */
    @Override
    public void deleteProvincia(Long id) {
        logger.info("Deleting provincia with id: {}", id);
        String sql = "DELETE FROM provincias WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted provincia. Rows affected: {}", rowsAffected);
    }

    /**
     * Recupera una provincia por su ID.
     * @param id ID de la provincia
     * @return Provincia encontrada o null si no existe
     */
    @Override
    public Provincia getProvinciaById(Long id) {
        logger.info("Retrieving provincia by id: {}", id);
        String sql = "SELECT * FROM provincias WHERE id = ?";
        try {
            Provincia provincia = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Provincia.class), id);
            logger.info("Provincia retrieved: {} - {}", provincia.getCode(), provincia.getName());
            return provincia;
        } catch (Exception e) {
            logger.warn("No provincia found with id: {}", id);
            return null;
        }
    }

    /**
     * Verifica si una provincia con el código especificado ya existe.
     * @param code Código de la provincia
     * @return true si existe, false si no
     */
    @Override
    public boolean existsProvinciaByCode(String code) {
        logger.info("Checking if provincia with code: {} exists", code);
        String sql = "SELECT COUNT(*) FROM provincias WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Provincia with code: {} exists: {}", code, exists);
        return exists;
    }

    /**
     * Verifica si una provincia con el código especificado ya existe excluyendo un ID concreto.
     * @param code Código de la provincia
     * @param id ID a excluir
     * @return true si ya existe otra con el mismo código, false de lo contrario
     */
    @Override
    public boolean existsProvinciaByCodeAndNotId(String code, Long id) {
        logger.info("Checking if provincia with code: {} exists excluding id: {}", code, id);
        String sql = "SELECT COUNT(*) FROM provincias WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Provincia with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}
