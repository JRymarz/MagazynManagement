package com.MagazynManagement.controller;

import com.MagazynManagement.entity.AdresDostawy;
import com.MagazynManagement.entity.Material;
import com.MagazynManagement.entity.PozycjaKoszyka;
import com.MagazynManagement.repository.MaterialRepozytory;
import com.MagazynManagement.service.ZamowienieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class KoszykController {

    private final MaterialRepozytory materialRepozytory;

    private final ZamowienieService zamowienieService;

    @PostMapping("/user/dodaj-do-koszyka")
    public String dodajDoKoszyka(@RequestParam Long idMaterialu, @RequestParam int ilosc, HttpSession session, HttpServletRequest request){
        List<PozycjaKoszyka> koszyk = (List<PozycjaKoszyka>) session.getAttribute("koszyk");
        if(koszyk == null){
            koszyk = new ArrayList<>();
            session.setAttribute("koszyk", koszyk);
        }

        Material material = materialRepozytory.findById(idMaterialu).orElse(null);
        if(material != null){
            PozycjaKoszyka istniejacaPozycja = znajdzPozycjeWKoszyku(material, koszyk);
            if(istniejacaPozycja != null){
                istniejacaPozycja.setIlosc(istniejacaPozycja.getIlosc() + ilosc);
            } else {
                PozycjaKoszyka nowaPozycja = new PozycjaKoszyka(material, ilosc);
                koszyk.add(nowaPozycja);
            }
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
        //return "redirect:/user/koszyk";
    }

    private PozycjaKoszyka znajdzPozycjeWKoszyku(Material material, List<PozycjaKoszyka> koszyk){
        for(PozycjaKoszyka pozycja : koszyk){
            if(pozycja.getMaterial().equals(material)){
                return pozycja;
            }
        }
        return null;
    }

    @GetMapping("/user/koszyk")
    public String wyswietlKoszyk(Model model, HttpSession session){
        List<PozycjaKoszyka> koszyk = (List<PozycjaKoszyka>) session.getAttribute("koszyk");
        if(koszyk == null){
            koszyk = new ArrayList<>();
            session.setAttribute("koszyk", koszyk);
        }

        model.addAttribute("koszyk", koszyk);
        model.addAttribute("adresDostawy", new AdresDostawy());
        return "koszyk";
    }

    @PostMapping("/user/zloz-zamowienie")
    public String zlozZamowienie(@ModelAttribute AdresDostawy adresDostawy,
                                 Model model,
                                 HttpSession session){
        List<PozycjaKoszyka> koszyk = (List<PozycjaKoszyka>) session.getAttribute("koszyk");
        if (koszyk == null || koszyk.isEmpty()){
            return "redirect:/user/koszyk";
        }

        float kwota = obliczKwoteZamowienia(koszyk);
        zamowienieService.zlozNoweZamowienie(kwota, koszyk, adresDostawy);

        zamowienieService.odejmijMaterialyZeStanuMagazynowego(koszyk);

        koszyk.clear();
        session.setAttribute("koszyk", koszyk);

        return "redirect:/user";
    }

    private float obliczKwoteZamowienia(List<PozycjaKoszyka> koszyk){
        float kwota = 0;
        for(PozycjaKoszyka pozycja : koszyk){
            kwota += pozycja.getMaterial().getCena() * pozycja.getIlosc();
        }
        return kwota;
    }
}
