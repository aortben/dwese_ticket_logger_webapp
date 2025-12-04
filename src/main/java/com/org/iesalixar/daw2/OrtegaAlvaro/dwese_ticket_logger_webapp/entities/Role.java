package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

/**
 * La clase `Role` representa un rol o autoridad en el sistema.
 * Se usa junto con `User` para gestionar permisos y control de acceso.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
public class Role {

    // Identificador único del rol (autogenerado)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del rol: ROLE_ADMIN, ROLE_USER, ROLE_MANAGER...
    @NotEmpty(message = "{msg.role.name.notEmpty}")
    @Size(max = 50, message = "{msg.role.name.size}")
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    // Relación muchos a muchos con usuarios
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    /**
     * Constructor útil cuando se quiere crear un rol sin especificar el id.
     */
    public Role(String name) {
        this.name = name;
    }
}

