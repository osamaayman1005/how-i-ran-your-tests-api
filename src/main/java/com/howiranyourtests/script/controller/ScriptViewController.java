package com.howiranyourtests.script.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScriptViewController {

    @GetMapping("/view/scripts")
    public String viewScripts(Model model) {
        // You can add any necessary data to the model here
        return "scripts"; // This corresponds to the Thymeleaf template name (scripts.html)
    }
}