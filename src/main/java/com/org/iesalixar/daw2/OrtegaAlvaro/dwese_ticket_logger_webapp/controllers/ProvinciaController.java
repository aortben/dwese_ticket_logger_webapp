package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ProvinciaDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ComunidadAutonomaDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Provincia;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
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
 * Controlador que maneja las operaciones CRUD para la entidad `Provincia`.
 * Utiliza `ProvinciaDAO` para interactuar con la base de datos.
 */
@Controller
@RequestMapping("/provinces")
public class ProvinciaController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    @Autowired
    private ProvinciaDAO provinciaDAO;

    @Autowired
    private ComunidadAutonomaDAO comunidadDAO;

    @GetMapping
    public String listProvincias(Model model) {
        logger.info("Solicitando la lista de todas las provincias...");
        try {
            List<Provincia> listProvincias = provinciaDAO.listAllProvincias();
            model.addAttribute("listProvincias", listProvincias);
            logger.info("Se han cargado {} provincias.", listProvincias.size());
        } catch (SQLException e) {
            logger.error("Error al listar provincias: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las provincias.");
        }
        return "/provincia";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva provincia.");
        model.addAttribute("provincia", new Provincia());
        try {
            model.addAttribute("comunidades", comunidadDAO.listAllComunidades());
        } catch (SQLException e) {
            logger.error("Error al cargar comunidades: {}", e.getMessage());
        }
        return "/provincia-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para provincia con ID {}", id);
        try {
            Provincia provincia = provinciaDAO.getProvinciaById(id);
            model.addAttribute("provincia", provincia);
            model.addAttribute("comunidades", comunidadDAO.listAllComunidades());
        } catch (SQLException e) {
            logger.error("Error al obtener provincia: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la provincia.");
        }
        return "/provincia-form";
    }

    @PostMapping("/insert")
    public String insertProvincia(@ModelAttribute("provincia") Provincia provincia,
                                  RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva provincia con código {}", provincia.getCode());
        try {
            if (provinciaDAO.existsProvinciaByCode(provincia.getCode())) {
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la provincia ya existe.");
                return "redirect:/provincias/new";
            }
            provinciaDAO.insertProvincia(provincia);
            logger.info("Provincia insertada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al insertar provincia: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la provincia.");
        }
        return "redirect:/provincias";
    }

    @PostMapping("/update")
    public String updateProvincia(@ModelAttribute("provincia") Provincia provincia,
                                  RedirectAttributes redirectAttributes) {
        logger.info("Actualizando provincia con ID {}", provincia.getId());
        try {
            if (provinciaDAO.existsProvinciaByCodeAndNotId(provincia.getCode(), provincia.getId())) {
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la provincia ya existe para otra provincia.");
                return "redirect:/provincias/edit?id=" + provincia.getId();
            }
            provinciaDAO.updateProvincia(provincia);
            logger.info("Provincia actualizada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al actualizar provincia: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la provincia.");
        }
        return "redirect:/provincias";
    }

    @PostMapping("/delete")
    public String deleteProvincia(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando provincia con ID {}", id);
        try {
            provinciaDAO.deleteProvincia(id);
            logger.info("Provincia eliminada correctamente.");
        } catch (SQLException e) {
            logger.error("Error al eliminar provincia: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la provincia.");
        }
        return "redirect:/provincias";
    }
}

