package com.MagazynManagement.controller;

import com.MagazynManagement.entity.Magazyn;
import com.MagazynManagement.entity.StanMagazynu;
import com.MagazynManagement.service.MagazynService;
import com.MagazynManagement.service.StanMagazynuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StanMagazynuController {

    private final StanMagazynuService stanMagazynuService;
    private final MagazynService magazynService;

    @GetMapping("/stan-magazynu/{id}")
    public String pokazStanMagazynu(@PathVariable("id") Long id, Model model){
        List<StanMagazynu> stanMagazynuList = stanMagazynuService.pobierzStanMagazynuDlaMagazynu(id);
        model.addAttribute("stanMagazynu", stanMagazynuList);
        Magazyn magazyn = magazynService.getMagazyn(id);
        if(magazyn != null)
            model.addAttribute("adresMagazynu", magazyn.getAdres());
        return "stan-magazynu";
    }
}
