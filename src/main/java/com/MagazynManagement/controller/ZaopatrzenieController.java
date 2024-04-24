package com.MagazynManagement.controller;

import com.MagazynManagement.dto.ZaopatrzenieDto;
import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.*;
import com.MagazynManagement.service.ZaopatrzenieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ZaopatrzenieController {

    private final ZaopatrzenieService zaopatrzenieService;

    private final MaterialRepozytory materialRepozytory;

    private final MagazynRepository magazynRepository;

    private final PracownikRepository pracownikRepository;

    private final ZaopatrzenieRepository zaopatrzenieRepository;

    private final StanMagazynuRepository stanMagazynuRepository;

    @PostMapping("/manager/zaopatrzenieDodaj")
    public String zaopatrzenieDodaj(@ModelAttribute Zaopatrzenie zaopatrzenie){
        zaopatrzenieService.zaopatrzenieDodaj(zaopatrzenie);
        return "redirect:/manager/zaopatrzenie";
    }

    @GetMapping("/manager/zaopatrzenie")
    public String zaopatrzenie(Model model){
        List<Magazyn> listM = magazynRepository.findAll();
        List<Material> listMa = materialRepozytory.findAll();
        List<Pracownik> listP = pracownikRepository.findByStanowisko("Pracownik");
        model.addAttribute("pracownicy", listP);
        model.addAttribute("magazyn", listM);
        model.addAttribute("produkty", listMa);
        return "zaopatrzenie";
    }

    @GetMapping("/pracownik/zaopatrzenie-pracownik/{id}")
    public String zaopatrzeniepracownik(@PathVariable("id") Long idPracownika, Model model){
        List<Zaopatrzenie> listZ = zaopatrzenieRepository.findByIdpracownika(idPracownika);
        List<ZaopatrzenieDto> listD = new ArrayList<>();
        for (Zaopatrzenie z : listZ){
            Material m = materialRepozytory.getReferenceById(z.getId_produktu());
            Magazyn ma = magazynRepository.getReferenceById(z.getId_magazynu());
            ZaopatrzenieDto d = new ZaopatrzenieDto(z.getId_zaopatrzenia(), ma.getAdres(), m.getNazwa(), z.getIlosc(), z.getWykonane());
            listD.add(d);
        }
        model.addAttribute("idpracownika", idPracownika);
        model.addAttribute("wpis", listD);
        return "zaopatrzenie-pracownik";
    }

    @PostMapping("/pracownik/zaopatrzenie-pracownik/wykonane/{id}")
    public String zaopatrzenieWykonaj(@ModelAttribute Zaopatrzenie zaopatrzenie, @PathVariable("id") Long idPracownika){
        Optional<Zaopatrzenie> z = zaopatrzenieRepository.findById(zaopatrzenie.getId_zaopatrzenia());
        z.get().setWykonane(1);
        zaopatrzenieRepository.save(z.get());
        Optional<StanMagazynu> s = stanMagazynuRepository.findById_IdMagazynuAndId_IdProduktu(z.get().getId_magazynu(), z.get().getId_produktu());
        int x = s.get().getIlosc();
        x+=z.get().getIlosc();
        s.get().setIlosc(x);
        stanMagazynuRepository.save(s.get());
        return "redirect:/pracownik/zaopatrzenie-pracownik/"+idPracownika;
    }
}
