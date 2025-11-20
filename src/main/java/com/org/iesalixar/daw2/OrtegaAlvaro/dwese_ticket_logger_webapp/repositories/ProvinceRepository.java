package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.repositories;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Province;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Page<Province> findAll(Pageable pageable);

    Page<Province> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, Long id);

    long countByNameContainingIgnoreCase(String name);
}

