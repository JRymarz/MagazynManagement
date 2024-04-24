package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {

    Optional <Klient> findByEmail(String email);
}
