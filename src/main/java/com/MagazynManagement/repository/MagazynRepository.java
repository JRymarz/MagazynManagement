package com.MagazynManagement.repository;

import com.MagazynManagement.entity.Magazyn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazynRepository extends JpaRepository<Magazyn, Long> {

}
