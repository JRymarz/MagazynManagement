package com.MagazynManagement.controller;

import com.MagazynManagement.dto.MaterialDto;
import com.MagazynManagement.dto.PracownikDto;
import com.MagazynManagement.dto.SektorDto;
import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.KontoRepository;
import com.MagazynManagement.repository.MagazynRepository;
import com.MagazynManagement.repository.SektorRepository;
import com.MagazynManagement.repository.StanMagazynuRepository;
import com.MagazynManagement.service.KontoService;
import com.MagazynManagement.service.PracownikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Controller
@RequiredArgsConstructor
public class PracownikController {

    private final PracownikService pracownikService;

    private final UserDetailsService userDetailsService;

    private final KontoService kontoService;

    private final SektorRepository sektorRepository;

    private final MagazynRepository magazynRepository;

    private final StanMagazynuRepository stanMagazynuRepository;

    private final KontoRepository kontoRepository;

    @GetMapping("/manager/pracownicy")
    public ModelAndView getPracownicyByManager(Model model, Principal principal){
        String username = principal.getName();

        List<Pracownik> allList = pracownikService.getAllPracownik();
        Konto zalogowaneKonto = kontoRepository.findByLogin(username);

        List<Pracownik> list = allList.stream()
                                .filter(pracownik -> !pracownik.equals(zalogowaneKonto.getPracownik()))
                                .collect(Collectors.toList());

        model.addAttribute("pracownicy", list);
        return new ModelAndView("pracownicy", "pracownik", list);
    }

    @PostMapping("/manager/usun-pracownika")
    public String usunPracownikaByManager(@RequestParam Long idPracownika){
        pracownikService.usunPracownika(idPracownika);
        return "redirect:/manager/pracownicy";
    }

    @GetMapping("/manager/edytuj-pracownika")
    public String edytujPracownikaFormByManager(@RequestParam Long idPracownika, Model model){
        Pracownik pracownik = pracownikService.getPracownikById(idPracownika);
        model.addAttribute("pracownik", pracownik);
        Magazyn magazyn = pracownik.getMagazyn();
        model.addAttribute("magazyn", magazyn);
        List<Magazyn> dostepneMagazyny = magazynRepository.findAll();
        model.addAttribute("dostepneMagazyny", dostepneMagazyny);
        return "edytuj-pracownikaByManager";
    }

    @PostMapping("/manager/edytuj-pracownika")
    public String edytujPracownikaByManager(@Valid @ModelAttribute("pracownik") Pracownik pracownik, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<Magazyn> dostepneMagazyny = magazynRepository.findAll();
            model.addAttribute("dostepneMagazyny", dostepneMagazyny);
            return "edytuj-pracownikaByManager";
        }

        try {
            if (pracownik.getPensja() < 0) {
                bindingResult.rejectValue("pensja", "pensja", "Pensja musi być nieujemną liczbą.");

                List<Magazyn> listaMagazynow = magazynRepository.findAll();
                model.addAttribute("dostepneMagazyny", listaMagazynow);
                return "edytuj-pracownikaByManager";
            }
        } catch (NumberFormatException e) {
            bindingResult.rejectValue("pensja", "pensja", "Pensja musi być liczbą.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("dostepneMagazyny", listaMagazynow);
            return "edytuj-pracownikaByManager";
        }

        if (!kontoService.isValidAddressFormat(pracownik.getAdres())) {
            bindingResult.rejectValue("adres", "adres", "Nieprawidłowy format adresu. Poprawny format: nazwa ulicy numer");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("dostepneMagazyny", listaMagazynow);
            return "edytuj-pracownikaByManager";
        }

        if (!StringUtils.hasLength(pracownik.getTelefon()) || pracownik.getTelefon().length() != 9 || !pracownik.getTelefon().matches("\\d{9}")) {
            bindingResult.rejectValue("telefon", "telefon", "Numer telefonu musi składać się z 9 cyfr.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("dostepneMagazyny", listaMagazynow);
            return "edytuj-pracownikaByManager";
        }

        pracownikService.aktualizujPracownika(pracownik);
        return "redirect:/manager/pracownicy";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("admin", userDetails);
        return "admin-main";
    }

    @GetMapping("/manager")
    public String manager(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("manager", userDetails);
        return "manager-main";
    }

    @GetMapping("/pracownik")
    public String pracownik(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("pracownik", userDetails);
        return "pracownik-main";
    }

//    @GetMapping("/admin/add-pracownik")
//    public String addPracownikPage(Model model){
//        model.addAttribute("pracownikDto", new PracownikDto());
//        return "add-pracownik";
//    }

    @GetMapping("/pracownik/wyszukaj-sektor")
    public String wyszukajSektor(Model model){
        List<Magazyn> lm = magazynRepository.findAll();
        List<String> lp = sektorRepository.findAllDistinct();
        model.addAttribute("magazyn", lm);
        model.addAttribute("sektory", lp);
        return "wyszukaj-sektor";
    }

    @PostMapping("/pracownik/wyszukaj-sektor")
    public String wyszukajSekotrPost(@ModelAttribute MaterialDto materialDto, Model model){
        List<Sektor> ls = sektorRepository.findByNazwa(materialDto.getName());
        System.out.println(materialDto.getName());
        Long x = null;
        for (Sektor s : ls){
            if (s.getMagazyn().getIdMagazynu().equals(materialDto.getIdmagazynu())){
                x=s.getIdSektora();
                break;
            }
        }
        if (x == null){
            model.addAttribute("wynik", "W tym magazynie nie ma wyznaczonego sektora dla tego produktu!");
        }
        else model.addAttribute("wynik", "Ten typ produktu znajduje sie w sektorze : "+x);
        List<Magazyn> lm = magazynRepository.findAll();
        List<String> lp = sektorRepository.findAllDistinct();
        model.addAttribute("magazyn", lm);
        model.addAttribute("sektory", lp);
        return "wyszukaj-sektor";
    }

    @GetMapping("/manager/zmien-sektor/{id}")
    public String zmienSektor(Model model, @PathVariable("id") Long idMagazynu){
        List<StanMagazynu> ls = stanMagazynuRepository.findByMagazyn_IdMagazynu(idMagazynu);
        Magazyn m = magazynRepository.getReferenceById(idMagazynu);
        List<Sektor> lsek = sektorRepository.findBymagazyn(m);
        if (ls.isEmpty()){
            model.addAttribute("czyWyniki", 0);
        }
        else model.addAttribute("czyWyniki", 1);
        model.addAttribute("stan", ls);
        model.addAttribute("sektor", lsek);
        model.addAttribute("idMagazynu", idMagazynu);
        return "zmien-sektor";
    }

    @PostMapping("/manager/zmien-sektor/{id}")
    public String zmienSektorPost(@ModelAttribute SektorDto sektorDto, @PathVariable("id") Long idMagazynu){
        Optional<StanMagazynu> s = stanMagazynuRepository.findById_IdMagazynuAndId_IdProduktu(idMagazynu, sektorDto.getIdMaterialu());
        Sektor x = sektorRepository.getReferenceById(sektorDto.getIdSektora());
        s.get().setSektor(x);
        stanMagazynuRepository.save(s.get());
        return "redirect:/manager/zmien-sektor/"+idMagazynu;
    }

    @GetMapping("manager/add-pracownik")
    public String addPracownikManagerPage(Model model){
        List<Magazyn> listaMagazynow = magazynRepository.findAll();
        model.addAttribute("pracownikDto", new PracownikDto());
        model.addAttribute("listaMagazynow", listaMagazynow);
        return "add-pracownikByManager";
    }

    @PostMapping("/manager/add-pracownik")
    public String addPracownik(@Valid @ModelAttribute("pracownikDto") PracownikDto pracownikDto, Model model, BindingResult bindingResult){
        if(!kontoService.isLoginUnique(pracownikDto.getLogin())){
            bindingResult.rejectValue("login", "login", "Konto z tym loginem już istnieje");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        if (!StringUtils.hasLength(pracownikDto.getTelefon()) || pracownikDto.getTelefon().length() != 9 || !pracownikDto.getTelefon().matches("\\d{9}")) {
            bindingResult.rejectValue("telefon", "telefon", "Numer telefonu musi składać się z 9 cyfr.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        if (!kontoService.isValidAddressFormat(pracownikDto.getAdres())) {
            bindingResult.rejectValue("adres", "adres", "Nieprawidłowy format adresu. Poprawny format: nazwa ulicy numer");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        if (!pracownikDto.getImie().matches("[A-ZŁŚĆŻ][a-ząćęłńóśźż]*")) {
            bindingResult.rejectValue("imie", "imie", "Nieprawidłowy format imienia.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        if (!pracownikDto.getNazwisko().matches("^[A-ZŁŚĆŻ][a-ząćęłńóśźż]*(-[A-ZŁŚĆŻ][a-ząćęłńóśźż]*)?$")) {
            bindingResult.rejectValue("nazwisko", "nazwisko", "Nieprawidłowy format nazwiska.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        try {
            if (pracownikDto.getPensja() < 0) {
                bindingResult.rejectValue("pensja", "pensja", "Pensja musi być nieujemną liczbą.");

                List<Magazyn> listaMagazynow = magazynRepository.findAll();
                model.addAttribute("listaMagazynow", listaMagazynow);
                return "add-pracownikByManager";
            }
        } catch (NumberFormatException e) {
            bindingResult.rejectValue("pensja", "pensja", "Pensja musi być liczbą.");

            List<Magazyn> listaMagazynow = magazynRepository.findAll();
            model.addAttribute("listaMagazynow", listaMagazynow);
            return "add-pracownikByManager";
        }

        if (bindingResult.hasErrors()) {
            return "add-pracownikByManager";
        } else {
            Pracownik savedPracownik = pracownikService.save(pracownikDto);
            kontoService.save(pracownikDto, savedPracownik);
            model.addAttribute("message", "Pracownik dodany");
            return "add-pracownikByManager";
        }
    }
}
