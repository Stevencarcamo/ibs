package com.ibs.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminController
 */
@Controller
@RequestMapping("/")
public class AdminController {

    @GetMapping("index")
    public String logi() {
        return "/Log/login";
    }

    @GetMapping("inicio")
    public String Dash() {
        return "vistas/Log/index";
    }
}
