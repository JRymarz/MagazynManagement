package com.MagazynManagement.controller;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.KontoRepository;
import com.MagazynManagement.service.RozpiskaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RozpiskaController {

    private final RozpiskaService rozpiskaService;

    private final UserDetailsService userDetailsService;

    private final KontoRepository kontoRepository;

    @GetMapping("/manager/rozpiska")
    public String getEmptyRozpiska(Model model, Principal principal){
        Konto kontoManagera = kontoRepository.findByLogin(principal.getName());
        Pracownik manager = kontoManagera.getPracownik();
        Long idMagazynu = manager.getIdMagazynu();

        List<Rozpiska> list = rozpiskaService.getRozpiskaByMagazyn(idMagazynu);
        List<Pracownik> listP = rozpiskaService.getPracownikByMagazyn(idMagazynu);
        List<SamochodDostawczy> listS = rozpiskaService.getSamochodByMagazyn(idMagazynu);

        model.addAttribute("pracownicy", listP);
        model.addAttribute("rozpiska", list);
        model.addAttribute("samochody", listS);
        return "rozpiska";
    }

    @PostMapping("/manager/rozpiskaUpdate")
    public String rozpiskaUpdate(@ModelAttribute Rozpiska rozpiska){
        rozpiskaService.updateRozpiska(rozpiska);
        return "redirect:/manager/rozpiska";
    }

    @PostMapping("/pracownik/harmonogram/wykonane/{id}")
    public String wykonajZadanie(@ModelAttribute Rozpiska rozpsika, @PathVariable("id") Long idpracownika){
        rozpiskaService.wykonajZadanie(rozpsika.getIdRozpiski());
        return "redirect:/pracownik/harmonogram/"+idpracownika;
    }

    @GetMapping("/pracownik/harmonogram/{id}")
    public String pokazStanMagazynu(@PathVariable("id") Long id, Model model){
        List<Rozpiska> rozpiskaList = rozpiskaService.getRozpiskaByPracownikId(id);
        model.addAttribute("idpracownika", id);
        model.addAttribute("harmonogram", rozpiskaList);
        return "harmonogram";
    }
}
