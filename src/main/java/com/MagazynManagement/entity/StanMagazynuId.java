package com.MagazynManagement.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StanMagazynuId implements Serializable {
    private Long idMagazynu;

    private Long idProduktu;

    public StanMagazynuId(Long idMagazynu, Long idProduktu) {
        super();
        this.idMagazynu = idMagazynu;
        this.idProduktu = idProduktu;
    }

    public StanMagazynuId() {
        super();
    }

    public Long getIdMagazynu() {
        return idMagazynu;
    }

    public Long getIdProduktu() {
        return idProduktu;
    }

    public void setIdMagazynu(Long idMagazynu) {
        this.idMagazynu = idMagazynu;
    }

    public void setIdProduktu(Long idProduktu) {
        this.idProduktu = idProduktu;
    }
}
