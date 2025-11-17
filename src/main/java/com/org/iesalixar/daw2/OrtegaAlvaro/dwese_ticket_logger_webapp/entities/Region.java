package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{msg.region.code.notEmpty}")
    @Size(max = 10, message = "{msg.region.code.size}")
    @Column(name = "code", nullable = false, length = 10, unique = true)
    private String code;

    @NotEmpty(message = "{msg.region.name.notEmpty}")
    @Size(max = 100, message = "{msg.region.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Imagen asociada a la región (por ejemplo, bandera o mapa)
    @Column(name = "image")
    private String image;

    // Relación uno a muchos con Province
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Province> provinces;

    public Region(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


