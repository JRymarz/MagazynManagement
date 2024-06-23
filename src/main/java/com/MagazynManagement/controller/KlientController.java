package com.MagazynManagement.controller;

import com.MagazynManagement.dto.KlientDto;
import com.MagazynManagement.entity.CustomUserDetails;
import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.Konto;
import com.MagazynManagement.entity.Magazyn;
import com.MagazynManagement.service.KlientService;
import com.MagazynManagement.service.KontoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
    public String saveKlient(@Valid @ModelAttribute("klientDto")KlientDto klientDto, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!klientDto.getImie().matches("[A-ZŁŚĆŻ][a-ząćęłńóśźż]*")) {
            bindingResult.rejectValue("imie", "imie", "Nieprawidłowy format imienia.");

            return "register";
        }

        if (!klientDto.getNazwisko().matches("^[A-ZŁŚĆŻ][a-ząćęłńóśźż]*(-[A-ZŁŚĆŻ][a-ząćęłńóśźż]*)?$")) {
            bindingResult.rejectValue("nazwisko", "nazwisko", "Nieprawidłowy format nazwiska.");

            return "register";
        }

        if (!kontoService.isValidAddressFormat(klientDto.getAdres())) {
            bindingResult.rejectValue("adres", "adres", "Nieprawidłowy format adresu. Poprawny format: nazwa ulicy numer");

            return "register";
        }

        if (!StringUtils.hasLength(klientDto.getTelefon()) || klientDto.getTelefon().length() != 9 || !klientDto.getTelefon().matches("\\d{9}")) {
            bindingResult.rejectValue("telefon", "telefon", "Numer telefonu musi składać się z 9 cyfr.");

            return "register";
        }

        if(!kontoService.isLoginUnique(klientDto.getLogin())){
            bindingResult.rejectValue("login", "login", "Konto z tym loginem już istnieje");

            return "register";
        }

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
