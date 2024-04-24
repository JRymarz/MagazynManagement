package com.MagazynManagement.service;

import com.MagazynManagement.entity.StanMagazynu;
import com.MagazynManagement.repository.StanMagazynuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StanMagazynuService {

    private final StanMagazynuRepository stanMagazynuRepository;

    public List<StanMagazynu> pobierzStanMagazynuDlaMagazynu(Long idMagazynu){
        return stanMagazynuRepository.findByMagazyn_IdMagazynu(idMagazynu);
    }

    public List<StanMagazynu> pobierzStanMagazynuDlaMaterialu(Long idMaterialu){
        return stanMagazynuRepository.findByMaterial_IdProduktu(idMaterialu);
    }

    public StanMagazynu pobierzStanMagazynu(Long idMagazynu, Long idProduktu){
        return stanMagazynuRepository.findById_IdMagazynuAndId_IdProduktu(idMagazynu, idProduktu)
                .orElseThrow(() -> new RuntimeException("Taki stan magazynowy nie istnieje"));
    }

    public void saveStanMagazynu(StanMagazynu stanMagazynu){
        stanMagazynuRepository.save(stanMagazynu);
    }
}
