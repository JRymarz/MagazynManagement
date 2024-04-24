package com.MagazynManagement.service;

import com.MagazynManagement.entity.Magazyn;
import com.MagazynManagement.repository.MagazynRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazynService {

    @Autowired
    private MagazynRepository magazynRepository;

    public List<Magazyn> getAllMagazyn(){
        return magazynRepository.findAll();
    }

    public Magazyn getMagazyn(Long id){
        Optional<Magazyn> optionalMagazyn = magazynRepository.findById(id);
        return optionalMagazyn.orElse(null);
    }
}
