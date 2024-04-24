package com.MagazynManagement.service;

import com.MagazynManagement.entity.Material;
import com.MagazynManagement.repository.MagazynRepository;
import com.MagazynManagement.repository.MaterialRepozytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepozytory materialRepozytory;

    public List<Material> getAllMaterial(){
        return materialRepozytory.findAll();
    }

    public Material getMaterialPoId(Long id){
        return materialRepozytory.findById(id)
                .orElseThrow(() -> new RuntimeException("Taki materiał nie istnieje"));
    }
}
