package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Konto;
import com.MagazynManagement.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface KontoRepository extends JpaRepository<Konto, Long> {

    Konto findByLogin(String login);

    void deleteByPracownik(Pracownik pracownik);

}
