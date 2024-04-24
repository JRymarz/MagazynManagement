package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long> {

    Optional<Pracownik> findByEmail(String email);
    List<Pracownik> findByStanowisko(String stanowisko);
}
