package com.MagazynManagement.controller;

import com.MagazynManagement.service.KontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KontoController {

    private final KontoService kontoService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping("/konta")
//    public String getKonta(Model model){
//        model.addAttribute("konto", kontoService.getAllKonto());
//        return "konta";
//    }
}
