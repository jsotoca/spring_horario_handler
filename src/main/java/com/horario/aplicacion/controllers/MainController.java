package com.horario.aplicacion.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${config.horario.apertura}")
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;

    @GetMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("titulo", "Bienvenido a atención al usuario");
        return "index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model){
        model.addAttribute("titulo","Fuera de atencion al usuario");
        model.addAttribute("mensaje", String.format("Apertura: %d - Cierre: %d", apertura, cierre));
        return "cerrado";
    }
    
}
