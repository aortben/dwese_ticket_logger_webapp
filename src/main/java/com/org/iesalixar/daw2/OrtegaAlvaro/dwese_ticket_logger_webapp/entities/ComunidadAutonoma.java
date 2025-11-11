package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "comunidad_autonoma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComunidadAutonoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    // Relación ManyToOne hacia Region
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    // Relación uno a muchos con Provinces
    @OneToMany(mappedBy = "comunidadAutonoma", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Province> provinces;

    public ComunidadAutonoma(String code, String name, Region region) {
        this.code = code;
        this.name = name;
        this.region = region;
    }
}

