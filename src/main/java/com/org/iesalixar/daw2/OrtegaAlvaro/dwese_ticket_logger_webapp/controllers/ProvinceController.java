package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;


import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.repositories.ProvinceRepository;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.repositories.RegionRepository;
import com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.entities.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador que maneja las operaciones CRUD para la entidad `Province`.
 * Utiliza `ProvinceDAO` y `RegionDAO` para interactuar con la base de datos.
 */
@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    public int currentPage = 1;
    public String sort = "idAsc";
    public String search = "";

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping()
    public String listProvinces(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String search, @RequestParam(required = false) String sort, Model model) {
        logger.info("Solicitando la lista de todas las provincias..." + search);
        Pageable pageable = PageRequest.of(page - 1, 5, getSort(sort));
        Page<Province> provinces;
        int totalPages = 0;
        if (search != null && !search.isBlank()) {
            provinces = provinceRepository.findByNameContainingIgnoreCase(search, pageable);
            totalPages = (int) Math.ceil((double) regionRepository.countByNameContainingIgnoreCase(search) / 5);
        } else {
            provinces = provinceRepository.findAll(pageable);
            totalPages = (int) Math.ceil((double) regionRepository.count() / 5);
        }
        logger.info("Se han cargado {} provincias.", provinces.toList().size());
        model.addAttribute("listProvinces", provinces.toList());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);
        return "province"; // Nombre de la plantilla Thymeleaf a renderizar
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva provincia.");
        model.addAttribute("province", new Province());
        model.addAttribute("regions", regionRepository.findAll());
        return "/province-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para provincia con ID {}", id);
        Province province = provinceRepository.findById(id).orElse(null);
        model.addAttribute("province", province);
        model.addAttribute("regions", regionRepository.findAll());
        return "/province-form";
    }

    @PostMapping("/insert")
    public String insertProvince(@ModelAttribute("province") Province province,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva provincia con código {}", province.getCode());
        if (provinceRepository.existsByCode(province.getCode())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código de la provincia ya existe.");
            return "redirect:/provinces/new";
        }
        provinceRepository.save(province);
        logger.info("Provincia insertada correctamente.");
        return "redirect:/provinces";
    }

    @PostMapping("/update")
    public String updateProvince(@ModelAttribute("province") Province province,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Actualizando provincia con ID {}", province.getId());
        if (provinceRepository.existsByCodeAndIdNot(province.getCode(), province.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código de la provincia ya existe para otra provincia.");
            return "redirect:/provinces/edit?id=" + province.getId();
        }
        provinceRepository.save(province);
        logger.info("Provincia actualizada correctamente.");
        return "redirect:/provinces";
    }


    @PostMapping("/delete")
    public String deleteProvince(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando provincia con ID {}", id);
        provinceRepository.deleteById(id);
        logger.info("Provincia eliminada correctamente.");
        return "redirect:/provinces";
    }

    private Sort getSort(String sort) {
        if (sort == null) {
            return Sort.by("id").ascending();
        }
        return switch (sort) {
            case "nameAsc" -> Sort.by("name").ascending();
            case "nameDesc" -> Sort.by("name").descending();
            case "codeAsc" -> Sort.by("code").ascending();
            case "codeDesc" -> Sort.by("code").descending();
            case "idDesc" -> Sort.by("id").descending();
            default -> Sort.by("id").ascending();
        };
    }
}


