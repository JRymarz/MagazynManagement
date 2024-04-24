package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Zaopatrzenie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZaopatrzenieRepository extends JpaRepository<Zaopatrzenie, Long>{
    List<Zaopatrzenie> findByIdpracownika(Long idpracownika);
}
