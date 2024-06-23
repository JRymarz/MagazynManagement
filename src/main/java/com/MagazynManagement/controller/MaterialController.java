package com.MagazynManagement.controller;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.KontoRepository;
import com.MagazynManagement.repository.MagazynRepository;
import com.MagazynManagement.repository.MaterialRepozytory;
import com.MagazynManagement.repository.SektorRepository;
import com.MagazynManagement.service.MaterialService;
import com.MagazynManagement.service.StanMagazynuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    StanMagazynuService stanMagazynuService;

    @Autowired
    MaterialRepozytory materialRepozytory;

    @Autowired
    MagazynRepository magazynRepository;

    @Autowired
    SektorRepository sektorRepository;

    private final KontoRepository kontoRepository;

    @GetMapping("/produkty")
    public ModelAndView getAllMaterial(){
        List<Material> list = materialService.getAllMaterial();
        return new ModelAndView("produkty", "material", list);
    }

    @GetMapping("/manager/add-material")
    public String addMaterialForm(Model model, Principal principal){
        Konto kontoManagera = kontoRepository.findByLogin(principal.getName());
        Pracownik manager = kontoManagera.getPracownik();
        Long idMagazynu = manager.getIdMagazynu();

        model.addAttribute("material", new Material());
        List<Sektor> sektors = sektorRepository.findByMagazynIdMagazynu(idMagazynu);
        model.addAttribute("sektory", sektors);

        return "add-material";
    }

    @PostMapping("/manager/save-material")
    public String addMaterial(@Valid @ModelAttribute("material") Material material,
                              @RequestParam("idSektora") Long idSektora,
                              BindingResult bindingResult,
                              Model model){

        try {
            if (material.getCena() <= 0) {
                bindingResult.rejectValue("cena", "cena", "Cena musi być nieujemną liczbą.");

                List<Sektor> sektors = sektorRepository.findAll();
                model.addAttribute("sektory", sektors);
                return "add-material";
            }
        } catch (NumberFormatException e) {
            bindingResult.rejectValue("cena", "cena", "Cena musi być liczbą.");

            List<Sektor> sektors = sektorRepository.findAll();
            model.addAttribute("sektory", sektors);
            return "add-material";
        }

        materialRepozytory.save(material);

        Sektor sektor = sektorRepository.findById(idSektora).orElse(null);

        StanMagazynu stanMagazynu = new StanMagazynu();
        StanMagazynuId stanMagazynuId = new StanMagazynuId(sektor.getMagazyn().getIdMagazynu(), material.getIdProduktu());

        stanMagazynu.setId(stanMagazynuId);
        stanMagazynu.setIlosc(0);
        stanMagazynu.setSektor(sektor);
        stanMagazynu.setMagazyn(sektor.getMagazyn());
        stanMagazynu.setMaterial(material);

        stanMagazynuService.saveStanMagazynu(stanMagazynu);

        return "redirect:/manager/add-material";
    }
}
