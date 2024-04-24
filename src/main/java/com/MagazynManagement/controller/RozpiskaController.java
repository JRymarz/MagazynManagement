package com.MagazynManagement.controller;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.service.RozpiskaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RozpiskaController {

    private final RozpiskaService rozpiskaService;

    @GetMapping("/manager/rozpiska")
    public String getEmptyRozpiska(Model model){
        List<Rozpiska> list = rozpiskaService.getRozpiska();
        List<Pracownik> listP = rozpiskaService.getPracownikPracownik();
        List<SamochodDostawczy> listS = rozpiskaService.getAllSamochodDostawczy();
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
