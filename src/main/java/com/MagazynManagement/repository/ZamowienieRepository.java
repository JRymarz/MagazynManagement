package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Klient;
import com.MagazynManagement.entity.SkladZamowienia;
import com.MagazynManagement.entity.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

    List<Zamowienie> findByKlient(Klient klient);
}
