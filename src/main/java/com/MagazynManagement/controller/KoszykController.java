package com.MagazynManagement.controller;

import com.MagazynManagement.entity.AdresDostawy;
import com.MagazynManagement.entity.Material;
import com.MagazynManagement.entity.PozycjaKoszyka;
import com.MagazynManagement.repository.MaterialRepozytory;
import com.MagazynManagement.repository.StanMagazynuRepository;
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

    private final StanMagazynuRepository stanMagazynuRepository;

    @PostMapping("/user/dodaj-do-koszyka")
    public String dodajDoKoszyka(@RequestParam Long idMaterialu,
                                 @RequestParam int ilosc,
                                 @RequestParam Long idMagazynu,
                                 HttpSession session,
                                 HttpServletRequest request){

        List<PozycjaKoszyka> koszyk = (List<PozycjaKoszyka>) session.getAttribute("koszyk");
        if(koszyk == null){
            koszyk = new ArrayList<>();
            session.setAttribute("koszyk", koszyk);
        }

        Long pierwszyIdMagazynu = (Long) session.getAttribute("pierwszyIdMagazynu");
        if(pierwszyIdMagazynu == null){
            session.setAttribute("pierwszyIdMagazynu", idMagazynu);
        } else {
            if(!idMagazynu.equals(pierwszyIdMagazynu)){
                String url = "/stan-magazynu/" + pierwszyIdMagazynu;
                return "redirect:" + url;
            }
        }

        Integer dostepnaIloscWMagazynie = stanMagazynuRepository.znajdzIloscWMagazynie(idMaterialu, idMagazynu);
        if(dostepnaIloscWMagazynie != null){
            int lacznaIloscWKoszyku = koszyk.stream()
                    .filter(p -> p.getMaterial().getIdProduktu().equals(idMaterialu))
                    .mapToInt(PozycjaKoszyka::getIlosc)
                    .sum();
            if(lacznaIloscWKoszyku + ilosc <= dostepnaIloscWMagazynie){
                Material material = materialRepozytory.findById(idMaterialu).orElse(null);
                if(material != null){
                    boolean znaleziono = false;
                    for(PozycjaKoszyka pozycja : koszyk){
                        if(pozycja.getMaterial().getIdProduktu().equals(material.getIdProduktu())){
                            pozycja.setIlosc(pozycja.getIlosc() + ilosc);
                            znaleziono = true;
                            System.out.println("znaleziono");
                            break;
                        }
                    }
                    if(!znaleziono){
                        PozycjaKoszyka nowaPozycja = new PozycjaKoszyka(material, ilosc);
                        koszyk.add(nowaPozycja);
                        System.out.println("nie znalezniono");
                    }
                }
            } else {
                String message = "Przekroczono dostępną ilość w magazynie.";
                String url = "/stan-magazynu/" + idMagazynu;

                session.setAttribute("errorMessage", message);
                return "redirect:" + url;
            }
        } else {
            String message = "Produkt nie istnieje w magazynie.";
            String url = "/stan-magazynu/" + idMagazynu;

            session.setAttribute("errorMessage", message);
            return "redirect:" + url;
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

//        Material material = materialRepozytory.findById(idMaterialu).orElse(null);
//        if(material != null){
//            boolean znaleziono = false;
//            for(PozycjaKoszyka pozycja : koszyk){
//                if(pozycja.getMaterial().getIdProduktu().equals(material.getIdProduktu())){
//                    pozycja.setIlosc(pozycja.getIlosc() + ilosc);
//                    znaleziono = true;
//                    System.out.println("znaleziono");
//                    break;
//                }
//            }
//            if(!znaleziono){
//                PozycjaKoszyka nowaPozycja = new PozycjaKoszyka(material, ilosc);
//                koszyk.add(nowaPozycja);
//                System.out.println("nie znalezniono");
//            }
//        }
//        String referer = request.getHeader("Referer");
//        return "redirect:" + referer;
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

        Long idMagazynu = (Long) session.getAttribute("pierwszyIdMagazynu");
        System.out.println(idMagazynu);

        float kwota = obliczKwoteZamowienia(koszyk);
        zamowienieService.zlozNoweZamowienie(kwota, koszyk, adresDostawy, idMagazynu);


        zamowienieService.odejmijMaterialyZeStanuMagazynowego(koszyk, idMagazynu);

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

    @PostMapping("/user/usun-z-koszyka")
    public String uzunZKoszyka(@RequestParam("pozycjaIndex") int pozycjaIndex, HttpSession session){
        List<PozycjaKoszyka> koszyk = (List<PozycjaKoszyka>) session.getAttribute("koszyk");
        if(koszyk != null && pozycjaIndex >= 0 && pozycjaIndex < koszyk.size()){
            koszyk.remove(pozycjaIndex);
            session.setAttribute("koszyk", koszyk);
        }

        return "redirect:/user/koszyk";
    }
}
