package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Province;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProvinceDAOImpl implements com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ProvinceDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Lista todas las provincias, cargando también ComunidadAutonoma y Region.
     */
    @Override
    public List<Province> listAllProvinces() {
        logger.info("Listing all provinces from the database.");
        String query = "SELECT p FROM Province p JOIN FETCH p.comunidadAutonoma c JOIN FETCH c.region";
        List<Province> provinces = entityManager.createQuery(query, Province.class).getResultList();
        logger.info("Retrieved {} provinces from the database.", provinces.size());
        return provinces;
    }

    /**
     * Inserta una nueva provincia.
     */
    @Override
    public void insertProvince(Province province) {
        logger.info("Inserting province with code: {} and name: {}", province.getCode(), province.getName());
        entityManager.persist(province);
        logger.info("Inserted province with ID: {}", province.getId());
    }

    /**
     * Actualiza una provincia existente.
     */
    @Override
    public void updateProvince(Province province) {
        logger.info("Updating province with id: {}", province.getId());
        entityManager.merge(province);
        logger.info("Updated province with id: {}", province.getId());
    }

    /**
     * Elimina una provincia por su ID.
     */
    @Override
    public void deleteProvince(int id) {
        logger.info("Deleting province with id: {}", id);
        Province province = entityManager.find(Province.class, id);
        if (province != null) {
            entityManager.remove(province);
            logger.info("Deleted province with id: {}", id);
        } else {
            logger.warn("Province with id: {} not found.", id);
        }
    }

    /**
     * Obtiene una provincia por su ID, incluyendo ComunidadAutonoma y Region.
     */
    @Override
    public Province getProvinceById(int id) {
        logger.info("Retrieving province by id: {}", id);
        String query = "SELECT p FROM Province p JOIN FETCH p.comunidadAutonoma c JOIN FETCH c.region WHERE p.id = :id";
        Province province = entityManager.createQuery(query, Province.class)
                .setParameter("id", id)
                .getSingleResult();
        logger.info("Province retrieved: {} - {}", province.getCode(), province.getName());
        return province;
    }

    /**
     * Comprueba si existe una provincia con un código específico.
     */
    @Override
    public boolean existsProvinceByCode(String code) {
        logger.info("Checking if province with code: {} exists", code);
        String query = "SELECT COUNT(p) FROM Province p WHERE UPPER(p.code) = :code";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("code", code.toUpperCase())
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Province with code: {} exists: {}", code, exists);
        return exists;
    }

    /**
     * Comprueba si existe una provincia con un código específico, excluyendo un ID.
     */
    @Override
    public boolean existsProvinceByCodeAndNotId(String code, int id) {
        logger.info("Checking if province with code: {} exists excluding id: {}", code, id);
        String query = "SELECT COUNT(p) FROM Province p WHERE UPPER(p.code) = :code AND p.id != :id";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("code", code.toUpperCase())
                .setParameter("id", id)
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Province with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}
