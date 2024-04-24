package com.MagazynManagement.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SkladZamowieniaId implements Serializable {

    private Long nrZamowienia;
    private Long idProduktu;

    public SkladZamowieniaId(Long nrZamowienia, Long idProduktu) {
        super();
        this.nrZamowienia = nrZamowienia;
        this.idProduktu = idProduktu;
    }

    public SkladZamowieniaId() {
        super();
    }

    public Long getNrZamowienia() {
        return nrZamowienia;
    }

    public Long getIdProduktu() {
        return idProduktu;
    }

    public void setNrZamowienia(Long nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    public void setIdProduktu(Long idProduktu) {
        this.idProduktu = idProduktu;
    }
}
