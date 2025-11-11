package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;

import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ProvinceDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.dao.ComunidadAutonomaDAO;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Province;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.ComunidadAutonoma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador que maneja las operaciones CRUD para la entidad `Province`.
 * Utiliza `ProvinceDAO` para interactuar con la base de datos.
 */
@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    @Autowired
    private ProvinceDAO provinceDAO;

    @Autowired
    private ComunidadAutonomaDAO comunidadDAO;

    @GetMapping
    public String listProvinces(Model model) {
        logger.info("Solicitando la lista de todas las provinces...");
        List<Province> listProvinces = provinceDAO.listAllProvinces();
        model.addAttribute("listProvinces", listProvinces);
        logger.info("Se han cargado {} provinces.", listProvinces.size());
        return "/province";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva province.");
        model.addAttribute("province", new Province());
        model.addAttribute("comunidades", comunidadDAO.listAllComunidades());
        return "/province-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        logger.info("Mostrando formulario de edición para province con ID {}", id);
        Province province = provinceDAO.getProvinceById(id);
        model.addAttribute("province", province);
        model.addAttribute("comunidades", comunidadDAO.listAllComunidades());
        return "/province-form";
    }

    @PostMapping("/insert")
    public String insertProvince(@ModelAttribute("province") Province province,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva province con código {}", province.getCode());
        if (provinceDAO.existsProvinceByCode(province.getCode())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código de la province ya existe.");
            return "redirect:/provinces/new";
        }
        provinceDAO.insertProvince(province);
        logger.info("Province insertada correctamente.");
        return "redirect:/provinces";
    }

    @PostMapping("/update")
    public String updateProvince(@ModelAttribute("province") Province province,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Actualizando province con ID {}", province.getId());
        if (provinceDAO.existsProvinceByCodeAndNotId(province.getCode(), province.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código de la province ya existe para otra province.");
            return "redirect:/provinces/edit?id=" + province.getId();
        }
        provinceDAO.updateProvince(province);
        logger.info("Province actualizada correctamente.");
        return "redirect:/provinces";
    }

    @PostMapping("/delete")
    public String deleteProvince(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando province con ID {}", id);
        provinceDAO.deleteProvince(id);
        logger.info("Province eliminada correctamente.");
        return "redirect:/provinces";
    }
}

