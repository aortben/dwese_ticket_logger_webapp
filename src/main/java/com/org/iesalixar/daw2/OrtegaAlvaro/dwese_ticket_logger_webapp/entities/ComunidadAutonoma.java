package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase `ComunidadAutonoma` representa una entidad que modela una comunidad autónoma dentro de la base de datos.
 * Contiene cuatro campos: `id`, `code`, `name` y `regionId`, donde:
 * - `id` es el identificador único de la comunidad autónoma.
 * - `code` es un código asociado a la comunidad.
 * - `name` es el nombre de la comunidad.
 * - `regionId` representa la región a la que pertenece esta comunidad.
 *
 * Las anotaciones de Lombok ayudan a reducir el código repetitivo al generar automáticamente
 * métodos comunes como getters, setters, constructores y otros métodos estándar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComunidadAutonoma {

    // Campo que almacena el identificador único de la comunidad autónoma.
    // Normalmente es autogenerado por la base de datos.
    private Long id;

    // Campo que almacena el código de la comunidad autónoma, normalmente una cadena corta.
    // Ejemplo: "AND" para Andalucía.
    private String code;

    // Campo que almacena el nombre completo de la comunidad autónoma.
    // Ejemplo: "Andalucía", "Cataluña".
    private String name;

    // Campo que almacena el identificador de la región a la que pertenece esta comunidad.
    // Es una clave foránea hacia la tabla `Region`.
    private Long regionId;

    /**
     * Constructor personalizado sin el campo `id`.
     * Se utiliza para crear instancias antes de insertar en la base de datos.
     *
     * @param code Código de la comunidad autónoma.
     * @param name Nombre de la comunidad autónoma.
     * @param regionId Identificador de la región a la que pertenece.
     */
    public ComunidadAutonoma(String code, String name, Long regionId) {
        this.code = code;
        this.name = name;
        this.regionId = regionId;
    }
}
