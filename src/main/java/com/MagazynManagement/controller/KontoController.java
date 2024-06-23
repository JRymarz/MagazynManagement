package com.MagazynManagement.controller;

import com.MagazynManagement.service.KontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class KontoController {

    private final KontoService kontoService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/403")
    public String handleForbidden(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Nie masz dostępu do tej strony.");
        return "redirect:/";
    }
}
