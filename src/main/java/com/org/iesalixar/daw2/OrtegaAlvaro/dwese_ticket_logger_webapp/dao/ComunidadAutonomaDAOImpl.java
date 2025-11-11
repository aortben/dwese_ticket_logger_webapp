package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ComunidadAutonomaDAOImpl implements ComunidadAutonomaDAO {

    private static final Logger logger = LoggerFactory.getLogger(ComunidadAutonomaDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ComunidadAutonoma> listAllComunidades() {
        logger.info("Listing all comunidades from the database.");
        String query = "SELECT c FROM ComunidadAutonoma c";
        List<ComunidadAutonoma> comunidades = entityManager.createQuery(query, ComunidadAutonoma.class).getResultList();
        logger.info("Retrieved {} comunidades from the database.", comunidades.size());
        return comunidades;
    }

    @Override
    public void insertComunidad(ComunidadAutonoma comunidad) {
        logger.info("Inserting comunidad with code: {} and name: {}", comunidad.getCode(), comunidad.getName());
        entityManager.persist(comunidad);
        logger.info("Inserted comunidad with ID: {}", comunidad.getId());
    }

    @Override
    public void updateComunidad(ComunidadAutonoma comunidad) {
        logger.info("Updating comunidad with id: {}", comunidad.getId());
        entityManager.merge(comunidad);
        logger.info("Updated comunidad with id: {}", comunidad.getId());
    }

    @Override
    public void deleteComunidad(int id) {
        logger.info("Deleting comunidad with id: {}", id);
        ComunidadAutonoma comunidad = entityManager.find(ComunidadAutonoma.class, id);
        if (comunidad != null) {
            entityManager.remove(comunidad);
            logger.info("Deleted comunidad with id: {}", id);
        } else {
            logger.warn("Comunidad with id: {} not found.", id);
        }
    }

    @Override
    public ComunidadAutonoma getComunidadById(int id) {
        logger.info("Retrieving comunidad by id: {}", id);
        ComunidadAutonoma comunidad = entityManager.find(ComunidadAutonoma.class, id);
        if (comunidad != null) {
            logger.info("Comunidad retrieved: {} - {}", comunidad.getCode(), comunidad.getName());
        } else {
            logger.warn("No comunidad found with id: {}", id);
        }
        return comunidad;
    }

    @Override
    public boolean existsComunidadByCode(String code) {
        logger.info("Checking if comunidad with code: {} exists", code);
        String query = "SELECT COUNT(c) FROM ComunidadAutonoma c WHERE UPPER(c.code) = :code";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("code", code.toUpperCase())
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Comunidad with code: {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsComunidadByCodeAndNotId(String code, int id) {
        logger.info("Checking if comunidad with code: {} exists excluding id: {}", code, id);
        String query = "SELECT COUNT(c) FROM ComunidadAutonoma c WHERE UPPER(c.code) = :code AND c.id != :id";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("code", code.toUpperCase())
                .setParameter("id", id)
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Comunidad with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}

