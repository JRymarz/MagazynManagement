package com.MagazynManagement.controller;

import com.MagazynManagement.dto.KlientDto;
import com.MagazynManagement.entity.CustomUserDetails;
import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.Konto;
import com.MagazynManagement.service.KlientService;
import com.MagazynManagement.service.KontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class KlientController {

    private final KlientService klientService;
    private final KontoService kontoService;
    private final UserDetailsService userDetailsService;

//    @GetMapping
//    public String getKlienci(Model model){
//        model.addAttribute("klienci", klientService.getAllKlients());
//        return "klienci";
//    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("klientDto", new KlientDto());
        return "register";
    }

    @PostMapping("/registration")
    public String saveKlient(@ModelAttribute("klientDto")KlientDto klientDto, Model model){
        Klient savedKlient = klientService.save(klientDto);
        kontoService.save(klientDto, savedKlient);
        model.addAttribute("message", "Zostałeś zarejestrowany");
        return "register";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("klient", userDetails);
        return "user-main";
    }
}
