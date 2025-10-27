package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;


import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.RegionDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.sql.SQLException;
import java.util.List;
/**
 * Controlador que maneja las operaciones CRUD para la entidad `Region`.
 * Utiliza `RegionDAO` para interactuar con la base de datos.
 */
@Controller
@RequestMapping("/regions")
public class RegionController {
    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
    // DAO para gestionar las operaciones de las regiones en la base de datos
    @Autowired
    private RegionDAO regionDAO;
    /**
     * Lista todas las regiones y las pasa como atributo al modelo para que sean
     * accesibles en la vista `region.html`.
     *
     * @param model Objeto del modelo para pasar datos a la vista.
     * @return El nombre de la plantilla Thymeleaf para renderizar la lista de
    regiones.
     */

    @GetMapping
    public String listRegions(Model model) {
        logger.info("Solicitando la lista de todas las regiones...");
        List<Region> listRegions = null;
        try {
            listRegions = regionDAO.listAllRegions();
            logger.info("Se han cargado {} regiones.", listRegions.size());
        } catch (SQLException e) {
            logger.error("Error al listar las regiones: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las regiones.");
        }
        model.addAttribute("listRegions", listRegions); // Pasar la lista deregiones al modelo
        return "fragments/region"; // Nombre de la plantilla Thymeleaf a renderizar
    }
    /**
     * Muestra el formulario para crear una nueva región.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return El nombre de la plantilla Thymeleaf para el formulario.
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva región.");
        model.addAttribute("region", new Region()); // Crear un nuevo objetoRegion
        return "fragments/region-form"; // Nombre de la plantilla Thymeleaf para elformulario
    }
    /**
     * Muestra el formulario para editar una región existente.
     *
     * @param id ID de la región a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return El nombre de la plantilla Thymeleaf para el formulario.
     */
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la región con ID {}",
                id);
        Region region = null;
        try {
            region = regionDAO.getRegionById(id);
            if (region == null) {
                logger.warn("No se encontró la región con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener la región con ID {}: {}", id,
                    e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la región.");
        }
        model.addAttribute("region", region);
        return "fragments/region-form"; // Nombre de la plantilla Thymeleaf para elformulario
    }
    /**
     * Inserta una nueva región en la base de datos.
     *
     * @param region Objeto que contiene los datos del formulario.
     * @param redirectAttributes Atributos para mensajes flash de redirección.
     * @return Redirección a la lista de regiones.
     */
    @PostMapping("/insert")
    public String insertRegion(@ModelAttribute("region") Region region,
                               RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva región con código {}", region.getCode());
        try {
            if (regionDAO.existsRegionByCode(region.getCode())) {
                logger.warn("El código de la región {} ya existe.",
                        region.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe.");
                return "redirect:/regions/new";
            }
            regionDAO.insertRegion(region);
            logger.info("Región {} insertada con éxito.", region.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar la región {}: {}", region.getCode(),
                    e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la región.");
        }
        return "redirect:/regions"; // Redirigir a la lista de regiones
    }
    /**
     * Actualiza una región existente en la base de datos.
     *
     * @param region Objeto que contiene los datos del formulario.
     * @param redirectAttributes Atributos para mensajes flash de redirección.
     * @return Redirección a la lista de regiones.
     */
    @PostMapping("/update")
    public String updateRegion(@ModelAttribute("region") Region region,
                               RedirectAttributes redirectAttributes) {
        logger.info("Actualizando región con ID {}", region.getId());
        try {
            if (regionDAO.existsRegionByCodeAndNotId(region.getCode(), region.getId())) {
                logger.warn("El código de la región {} ya existe para otra región.", region.getCode());
                        redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe para otra región.");
                return "redirect:/fragments/regions/edit?id=" + region.getId();
            }
            regionDAO.updateRegion(region);
            logger.info("Región con ID {} actualizada con éxito.",
                    region.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar la región con ID {}: {}",
                    region.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la región.");
        }
        return "redirect:/regions"; // Redirigir a la lista de regiones
    }
    /**
     * Elimina una región de la base de datos.
     *
     * @param id ID de la región a eliminar.
     * @param redirectAttributes Atributos para mensajes flash de redirección.
     * @return Redirección a la lista de regiones.
     */
    @PostMapping("/delete")
    public String deleteRegion(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando región con ID {}", id);
        try {
            regionDAO.deleteRegion(id);
            logger.info("Región con ID {} eliminada con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar la región con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la región.");
        }
        return "redirect:/regions"; // Redirigir a la lista de regiones
    }
}
