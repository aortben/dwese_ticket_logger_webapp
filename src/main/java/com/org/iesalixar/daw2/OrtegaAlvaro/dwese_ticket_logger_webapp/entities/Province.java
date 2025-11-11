package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "province")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "{msg.province.code.notEmpty}")
    @Size(max = 2, message = "{msg.province.code.size}")
    @Column(name = "code", nullable = false, length = 2)
    private String code;

    @NotEmpty(message = "{msg.province.name.notEmpty}")
    @Size(max = 100, message = "{msg.province.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Relación ManyToOne con ComunidadAutonoma
    @NotNull(message = "{msg.province.comunidadAutonoma.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comunidad_id", nullable = false)
    private ComunidadAutonoma comunidadAutonoma;

    // Relación opcional con Location
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Location> locations;

    public Province(String code, String name, ComunidadAutonoma comunidadAutonoma) {
        this.code = code;
        this.name = name;
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public int getComunidadId() {
        return comunidadAutonoma != null ? comunidadAutonoma.getId() : 0;
    }
}
