package com.MagazynManagement.service;

import com.MagazynManagement.dto.KlientDto;
import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.repository.KlientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KlientService{

    private final KlientRepository klientRepo;

    public List<Klient> getAllKlients(){
        return klientRepo.findAll();
    }

    public Klient findByEmail(String email){
        return klientRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Klient nie znaleziony"));
    }

//    public void save(Klient k){
//        klientRepo.save(k);
//    }

    @Transactional
    public Klient save(KlientDto klientDto){
        Klient klient = new Klient(klientDto.getImie(),
                klientDto.getNazwisko(),
                klientDto.getEmail(),
                klientDto.getAdres(),
                klientDto.getTelefon());
        return klientRepo.save(klient);
    }


}
