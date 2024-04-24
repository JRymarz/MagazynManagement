package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Magazyn;
import com.MagazynManagement.entity.Sektor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SektorRepository extends JpaRepository<Sektor, Long> {

    public Sektor findByMagazynAndNazwa(Magazyn magazyn, String nazwa);

    @Query("SELECT DISTINCT nazwa from Sektor")
    public List<String> findAllDistinct();

    public List<Sektor> findByNazwa(String nazwa);

    public List<Sektor> findBymagazyn(Magazyn magazyn);
}
