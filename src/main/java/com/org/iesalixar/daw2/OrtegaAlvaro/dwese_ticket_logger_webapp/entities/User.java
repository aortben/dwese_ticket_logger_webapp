package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * La clase `User` representa una entidad que modela un usuario en el sistema.
 * Contiene campos para autenticación, información de perfil y auditoría.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@EntityListeners(AuditingEntityListener.class)
public class User {

    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username único
    @NotEmpty(message = "{msg.user.username.notEmpty}")
    @Size(max = 50, message = "{msg.user.username.size}")
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    // Contraseña encriptada
    @NotEmpty(message = "{msg.user.password.notEmpty}")
    @Size(min = 8, message = "{msg.user.password.size}")
    @Column(name = "password", nullable = false)
    private String password;

    // Si el usuario está habilitado
    @NotNull(message = "{msg.user.enabled.notNull}")
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // Nombre
    @NotEmpty(message = "{msg.user.firstName.notEmpty}")
    @Size(max = 50, message = "{msg.user.firstName.size}")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    // Apellido
    @NotEmpty(message = "{msg.user.lastName.notEmpty}")
    @Size(max = 50, message = "{msg.user.lastName.size}")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    // Imagen de perfil (ruta)
    @Size(max = 255, message = "{msg.user.image.size}")
    @Column(name = "image", length = 255)
    private String image;

    // Fecha de creación (auditoría)
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    // Fecha de última modificación (auditoría)
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    // Fecha del último cambio de contraseña
    @Column(name = "last_password_change_date")
    private LocalDateTime lastPasswordChangeDate;

    // Relación muchos a muchos con roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Establece la contraseña y actualiza la fecha del último cambio.
     */
    public void setPassword(String password) {
        this.password = password;
        this.lastPasswordChangeDate = LocalDateTime.now();
    }
}

