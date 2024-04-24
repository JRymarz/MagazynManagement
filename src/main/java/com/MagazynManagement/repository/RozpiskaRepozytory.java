package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.Rozpiska;
import com.MagazynManagement.entity.SamochodDostawczy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RozpiskaRepozytory extends JpaRepository<Rozpiska, Long>{
    List<Rozpiska> findByIdpracownika(Long idpracownika);

}
