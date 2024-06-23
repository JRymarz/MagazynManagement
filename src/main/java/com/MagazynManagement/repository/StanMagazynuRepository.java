package com.MagazynManagement.repository;

import com.MagazynManagement.entity.StanMagazynu;
import com.MagazynManagement.entity.StanMagazynuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StanMagazynuRepository extends JpaRepository<StanMagazynu, StanMagazynuId> {

    List<StanMagazynu> findByMagazyn_IdMagazynu(Long idMagazynu);

    List<StanMagazynu> findByMaterial_IdProduktu(Long idMaterialu);

    Optional<StanMagazynu> findById_IdMagazynuAndId_IdProduktu(Long idMagazynu, Long idProduktu);

    @Modifying
    @Transactional
    @Query(value = "UPDATE stanmagazynu SET ilosc = ilosc - :ilosc WHERE id_produktu = :materialId AND id_magazynu = :idMagazynu", nativeQuery = true)
    void odejmijMaterial(@Param("materialId") Long materialId, @Param("ilosc") int ilosc, @Param("idMagazynu") Long idMagazynu);


    @Query(value = "SELECT ilosc FROM stanmagazynu WHERE id_produktu = :materialId AND id_magazynu = :magazynId", nativeQuery = true)
    Integer znajdzIloscWMagazynie(@Param("materialId") Long materialId, @Param("magazynId") Long magazynId);
}
