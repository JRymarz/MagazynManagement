package com.MagazynManagement.service;

import com.MagazynManagement.entity.*;
import com.MagazynManagement.repository.MaterialRepozytory;
import com.MagazynManagement.repository.SkladZamowieniaRepository;
import com.MagazynManagement.repository.StanMagazynuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkladZamowieniaService {

    private final SkladZamowieniaRepository skladZamowieniaRepository;

    private final StanMagazynuRepository stanMagazynuRepository;

    public List<SkladZamowienia> znajdzSkladZamowienia(Zamowienie zamowienie){
        return skladZamowieniaRepository.findByZamowienie(zamowienie);
    }
}
