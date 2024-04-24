package com.MagazynManagement.controller;

import com.MagazynManagement.entity.CustomUserDetails;
import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.SkladZamowienia;
import com.MagazynManagement.entity.Zamowienie;
import com.MagazynManagement.service.SkladZamowieniaService;
import com.MagazynManagement.service.ZamowienieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ZamowienieController {

    private final ZamowienieService zamowienieService;

    private final SkladZamowieniaService skladZamowieniaService;

    @GetMapping("/user/zamowienia")
    public String listaZamowien(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Klient klient = customUserDetails.getKonto().getKlient();

        List<Zamowienie> zamowienia = zamowienieService.znajdzZamowienieKlienta(klient);
        model.addAttribute("zamowienia", zamowienia);
        return "zamowienia-user";
    }

    @GetMapping("/user/{id}/szczegoly")
    public String szczegolyZamowienia(@PathVariable Long id, Model model){
        Zamowienie zamowienie = zamowienieService.znajdzZamowienieById(id);
        List<SkladZamowienia> skladZamowienia = skladZamowieniaService.znajdzSkladZamowienia(zamowienie);

        model.addAttribute("zamowienie", zamowienie);
        model.addAttribute("skladZamowienia", skladZamowienia);
        return "szczegoly-zamowienia-user";
    }

    @GetMapping("/manager/zamowienia")
    public String listaZamowien(Model model){
        List<Zamowienie> zamowienia = zamowienieService.getAllZamowienia();
        model.addAttribute("zamowienia", zamowienia);
        return "zamowienia-manager";
    }

    @GetMapping("/manager/{id}/szczegoly")
    public String szczegolyZamowieniaManger(@PathVariable Long id, Model model){
        Zamowienie zamowienie = zamowienieService.znajdzZamowienieById(id);
        List<SkladZamowienia> skladZamowienia = skladZamowieniaService.znajdzSkladZamowienia(zamowienie);

        model.addAttribute("zamowienie", zamowienie);
        model.addAttribute("skladZamowienia", skladZamowienia);
        return "szczegoly-zamowienia-manager";
    }
}
