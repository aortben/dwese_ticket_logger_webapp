package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * La clase `Region` representa una entidad que modela una región dentro de la
 base de datos.
 * Contiene tres campos: `id`, `code` y `name`, donde `id` es el identificador
 único de la región,
 * `code` es un código asociado a la región, y `name` es el nombre de la región.*
 * Las anotaciones de Lombok ayudan a reducir el código repetitivo al generar
 automáticamente
 * métodos comunes como getters, setters, constructores, y otros métodos estándar
 de los objetos.
 */
@Data // Esta anotación de Lombok genera automáticamente los siguientesmétodos:
// - Getters y setters para todos los campos (id, code, name).
// - Los métodos `equals()` y `hashCode()` basados en todos los campos notransitorios.
// - El método `toString()` que incluye todos los campos.
// - Un método `canEqual()` que verifica si una instancia puede ser igual aotra.
// Esto evita tener que escribir manualmente todos estos métodos y mejora lamantenibilidad del código.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos(constructor vacío),
// es útil cuando quieres crear un objeto `Region` sin inicializarloinmediatamente
// con valores. Esto es muy útil en frameworks como Hibernate o JPA,
// que requieren un constructor vacío para la creación de entidades.
@AllArgsConstructor // Esta anotación genera un constructor que acepta todoslos campos como parámetros (id, code, name).
// Este constructor es útil cuando necesitas crear una instancia completamenteinicializada de `Region`.
// Ejemplo: new Region(1, "01", "Andalucía");
public class Region {
    // Campo que almacena el identificador único de la región. Este campo sueleser autogenerado
    // por la base de datos, lo que lo convierte en un buen candidato para unaclave primaria.
    private Long id;
    // Campo que almacena el código de la región, normalmente una cadena cortaque identifica la región.
    // Ejemplo: "01" para Andalucía.
    private String code;
    // Campo que almacena el nombre completo de la región, como "Andalucía" o"Cataluña".
    private String name;
    /**
     * Este es un constructor personalizado que no incluye el campo `id`.
     * Se utiliza para crear instancias de `Region` cuando no es necesario o no
     se conoce el `id` de la región
     * (por ejemplo, antes de insertar la región en la base de datos, donde el
     `id` es autogenerado).
     * @param code Código de la región.
     * @param name Nombre de la región.
     */
    public Region(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
