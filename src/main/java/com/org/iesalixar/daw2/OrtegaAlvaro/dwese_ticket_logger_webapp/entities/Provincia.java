package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase `Provincia` representa una entidad que modela una provincia dentro de la base de datos.
 * Contiene cuatro campos: `id`, `code`, `name` y `comunidadId`, donde:
 * - `id` es el identificador único de la provincia.
 * - `code` es un código asociado a la provincia.
 * - `name` es el nombre de la provincia.
 * - `comunidadId` representa la comunidad autónoma a la que pertenece esta provincia.
 *
 * Las anotaciones de Lombok ayudan a reducir el código repetitivo al generar automáticamente
 * getters, setters, constructores y otros métodos estándar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    // Identificador único de la provincia (clave primaria).
    private Long id;

    // Código corto que identifica la provincia (por ejemplo, "SE" para Sevilla).
    private String code;

    // Nombre completo de la provincia (por ejemplo, "Sevilla", "Granada").
    private String name;

    // Identificador de la comunidad autónoma a la que pertenece esta provincia.
    // Es una clave foránea hacia la tabla `ComunidadAutonoma`.
    private Long comunidadId;

    /**
     * Constructor personalizado sin el campo `id`.
     * Se utiliza para crear instancias antes de insertar en la base de datos.
     *
     * @param code Código de la provincia.
     * @param name Nombre de la provincia.
     * @param comunidadId Identificador de la comunidad autónoma a la que pertenece.
     */
    public Provincia(String code, String name, Long comunidadId) {
        this.code = code;
        this.name = name;
        this.comunidadId = comunidadId;
    }
}
