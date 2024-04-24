package com.MagazynManagement.service;


import com.MagazynManagement.entity.Zaopatrzenie;
import com.MagazynManagement.repository.ZaopatrzenieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZaopatrzenieService {

    @Autowired
    private ZaopatrzenieRepository zaopatrzenieRepository;

    public void zaopatrzenieDodaj(Zaopatrzenie zaopatrzenie){
        zaopatrzenie.setWykonane(0);
        zaopatrzenieRepository.save(zaopatrzenie);
    }
}
