package com.MagazynManagement.service;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RozpiskaService {

    @Autowired
    private RozpiskaRepozytory rozpiskaRepository;

    @Autowired
    private PracownikRepository pracownikRepository;

    @Autowired
    private SamochodDostawczyRepository samochodDostawczyRepository;

    @Autowired
    private ZamowienieRepository zamowienieRepository;

    @Autowired
    private DostawaRepository dostawaRepository;

    public List<Pracownik> getPracownikPracownik(){
        return pracownikRepository.findByStanowisko("Pracownik");
    }

    public List<Rozpiska> getRozpiska(){
        return rozpiskaRepository.findByIdpracownika(null);
    }

    public void updateRozpiska(Rozpiska rozpiska){
        Rozpiska og = rozpiskaRepository.getReferenceById(rozpiska.getIdRozpiski());
        og.setId_samochodu(rozpiska.getId_samochodu());
        og.setIdpracownika(rozpiska.getIdpracownika());
        rozpiskaRepository.save(og);
    }

    public void wykonajZadanie(Long id){
        Rozpiska rozpiska = rozpiskaRepository.getReferenceById(id);
        rozpiska.setWykonane(1);
        rozpiskaRepository.save(rozpiska);
        Zamowienie zamowienie = zamowienieRepository.getReferenceById(rozpiska.getNr_zamowienia());
        Dostawa dostawa = zamowienie.getDostawa();
        dostawa.setStatus("Dostarczone");
        dostawaRepository.save(dostawa);
    }

    public List<SamochodDostawczy> getAllSamochodDostawczy(){return samochodDostawczyRepository.findAll();}

    public List<Rozpiska> getRozpiskaByPracownikId(Long id){
        return rozpiskaRepository.findByIdpracownika(id);
    }
}
