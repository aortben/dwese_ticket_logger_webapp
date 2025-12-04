package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controlador global para manejar errores HTTP y redirigir a páginas
 * de error personalizadas.
 */
@Controller
public class GlobalErrorController implements ErrorController {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalErrorController.class);

    /**
     * Maneja las solicitudes a "/error" y redirige a la página adecuada según
     * el código HTTP.
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.info("Accediendo al método handleError");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // 403 - Forbidden
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                logger.warn("Error 403: Acceso denegado.");
                model.addAttribute("errorCode", "403");
                model.addAttribute("errorMessage", "No tienes permisos para acceder a esta página.");
                return "error/403";
            }

            // 404 - Not Found
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                logger.warn("Error 404: Página no encontrada.");
                model.addAttribute("errorCode", "404");
                model.addAttribute("errorMessage", "La página que buscas no se ha encontrado.");
                return "error/404";
            }

            // 500 - Internal Server Error
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                logger.error("Error 500: Error interno del servidor.");
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "Ha ocurrido un error interno en el servidor.");
                return "error/500";
            }
        }

        // Error desconocido → página genérica
        logger.error("Error desconocido: Redirigiendo a la página de error genérica.");
        model.addAttribute("errorCode", "Error");
        model.addAttribute("errorMessage", "Ha ocurrido un error inesperado.");
        return "error/generic";
    }
}

