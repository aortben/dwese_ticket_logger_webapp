package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "region")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "{msg.region.code.notEmpty}")
    @Size(max = 2, message = "{msg.region.code.size}")
    @Column(name = "code", nullable = false, length = 2)
    private String code;

    @NotEmpty(message = "{msg.region.name.notEmpty}")
    @Size(max = 100, message = "{msg.region.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Relación uno a muchos con Comunidades Autónomas
    //@OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //private List<ComunidadAutonoma> comunidades;

    public Region(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

