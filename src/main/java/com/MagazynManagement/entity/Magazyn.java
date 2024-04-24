package com.MagazynManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Magazyn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMagazynu;
    private String adres;

    public Magazyn(long idMagazynu, String adres) {
        super();
        this.idMagazynu = idMagazynu;
        this.adres = adres;
    }

    public Magazyn(){
        super();
    }

    public Long getIdMagazynu() {
        return idMagazynu;
    }

    public String getAdres() {
        return adres;
    }

    public void setIdMagazynu(Long idMagazynu) {
        this.idMagazynu = idMagazynu;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
