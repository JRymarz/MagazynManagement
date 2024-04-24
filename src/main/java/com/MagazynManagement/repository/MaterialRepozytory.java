package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepozytory extends JpaRepository<Material, Long> {
    Material findByNazwa(String nazwa);

}
