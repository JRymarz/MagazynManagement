package com.MagazynManagement.controller;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.MagazynRepository;
import com.MagazynManagement.repository.MaterialRepozytory;
import com.MagazynManagement.repository.SektorRepository;
import com.MagazynManagement.service.MaterialService;
import com.MagazynManagement.service.StanMagazynuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/produkty")
    public ModelAndView getAllMaterial(){
        List<Material> list = materialService.getAllMaterial();
        return new ModelAndView("produkty", "material", list);
    }

    @GetMapping("/manager/add-material")
    public String addMaterialForm(Model model){
        model.addAttribute("material", new Material());
        List<Sektor> sektors = sektorRepository.findAll();
        model.addAttribute("sektory", sektors);

        return "add-material";
    }

    @PostMapping("/manager/save-material")
    public String addMaterial(@ModelAttribute("material") Material material,
                              @RequestParam("idSektora") Long idSektora){
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
