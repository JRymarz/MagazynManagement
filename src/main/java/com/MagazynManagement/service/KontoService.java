package com.MagazynManagement.service;

import com.MagazynManagement.dto.KlientDto;
import com.MagazynManagement.dto.PracownikDto;
import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.Konto;
import com.MagazynManagement.entity.Pracownik;
import com.MagazynManagement.repository.KontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KontoService {

    private final KontoRepository kontoRepository;

    private final PasswordEncoder passwordEncoder;

    private final String rola = "USER";

    public List<Konto> getAllKonto(){
        return kontoRepository.findAll();
    }

//    public Konto findByLogin(String login){
//        return kontoRepository.findByLogin(login)
//                .orElseThrow(() -> new UsernameNotFoundException("Konto nie znalezione"));
//    }


    @Transactional
    public Konto save(KlientDto klientDto, Klient savedKlient){
        Konto konto = new Konto(klientDto.getLogin(),
                passwordEncoder.encode(klientDto.getHaslo()),
                savedKlient,
                null,
                rola);
        return kontoRepository.save(konto);
    }

    @Transactional
    public Konto save(PracownikDto pracownikDto, Pracownik savedPracownik){
        Konto konto = new Konto(pracownikDto.getLogin(),
                passwordEncoder.encode(pracownikDto.getHaslo()),
                null,
                savedPracownik,
                pracownikDto.utworzRole());
        return kontoRepository.save(konto);
    }
}
