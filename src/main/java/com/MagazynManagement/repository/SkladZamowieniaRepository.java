package com.MagazynManagement.repository;

import com.MagazynManagement.entity.SkladZamowienia;
import com.MagazynManagement.entity.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkladZamowieniaRepository extends JpaRepository<SkladZamowienia, Long> {

    List<SkladZamowienia> findByZamowienie(Zamowienie zamowienie);
}
