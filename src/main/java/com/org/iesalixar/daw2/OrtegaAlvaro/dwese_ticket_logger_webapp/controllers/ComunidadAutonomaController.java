package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ComunidadAutonomaDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.RegionDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
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
 * Controlador que maneja las operaciones CRUD para la entidad `ComunidadAutonoma`.
 * Utiliza `ComunidadAutonomaDAO` para interactuar con la base de datos.
 */
@Controller
@RequestMapping("/comunidades")
public class ComunidadAutonomaController {

    private static final Logger logger = LoggerFactory.getLogger(ComunidadAutonomaController.class);

    @Autowired
    private ComunidadAutonomaDAO comunidadDAO;

    @Autowired
    private RegionDAO regionDAO;

    @GetMapping
    public String listComunidades(Model model) {
        logger.info("Solicitando la lista de todas las comunidades autónomas...");
        try {
            List<ComunidadAutonoma> listComunidades = comunidadDAO.listAllComunidades();
            model.addAttribute("listComunidades", listComunidades);
            logger.info("Se han cargado {} comunidades autónomas.", listComunidades.size());
        } catch (SQLException e) {
            logger.error("Error al listar comunidades autónomas: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las comunidades autónomas.");
        }
        return "/comunidad";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva comunidad autónoma.");
        model.addAttribute("comunidad", new ComunidadAutonoma());
        try {
            model.addAttribute("regions", regionDAO.listAllRegions());
        } catch (SQLException e) {
            logger.error("Error al cargar las regiones: {}", e.getMessage());
        }
        return "/comunidad-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la comunidad con ID {}", id);
        try {
            ComunidadAutonoma comunidad = comunidadDAO.getComunidadById(id);
            model.addAttribute("comunidad", comunidad);
            model.addAttribute("regions", regionDAO.listAllRegions());
        } catch (SQLException e) {
            logger.error("Error al obtener la comunidad: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la comunidad.");
        }
        return "/comunidad-form";
    }

    @PostMapping("/insert")
    public String insertComunidad(@ModelAttribute("comunidad") ComunidadAutonoma comunidad,
                                  RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva comunidad con código {}", comunidad.getCode());
        try {
            if (comunidadDAO.existsComunidadByCode(comunidad.getCode())) {
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la comunidad ya existe.");
                return "redirect:/comunidades/new";
            }
            comunidadDAO.insertComunidad(comunidad);
            logger.info("Comunidad insertada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al insertar comunidad: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la comunidad.");
        }
        return "redirect:/comunidades";
    }

    @PostMapping("/update")
    public String updateComunidad(@ModelAttribute("comunidad") ComunidadAutonoma comunidad,
                                  RedirectAttributes redirectAttributes) {
        logger.info("Actualizando comunidad con ID {}", comunidad.getId());
        try {
            if (comunidadDAO.existsComunidadByCodeAndNotId(comunidad.getCode(), comunidad.getId())) {
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la comunidad ya existe para otra comunidad.");
                return "redirect:/comunidades/edit?id=" + comunidad.getId();
            }
            comunidadDAO.updateComunidad(comunidad);
            logger.info("Comunidad actualizada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al actualizar comunidad: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la comunidad.");
        }
        return "redirect:/comunidades";
    }

    @PostMapping("/delete")
    public String deleteComunidad(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando comunidad con ID {}", id);
        try {
            comunidadDAO.deleteComunidad(id);
            logger.info("Comunidad eliminada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al eliminar comunidad: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la comunidad.");
        }
        return "redirect:/comunidades";
    }
}
