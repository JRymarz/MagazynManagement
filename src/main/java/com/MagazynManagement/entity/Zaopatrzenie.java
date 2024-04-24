package com.MagazynManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Zaopatrzenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_zaopatrzenia;
    private Long id_magazynu;
    private Long id_produktu;
    private int ilosc;
    private Long idpracownika;
    private int wykonane;

    public Zaopatrzenie(Long idMagazynu, Long idProduktu, int ilosc, Long idpracownika, int wykonane) {
        id_magazynu = idMagazynu;
        id_produktu = idProduktu;
        this.ilosc = ilosc;
        this.idpracownika = idpracownika;
        this.wykonane = wykonane;
    }

    public Zaopatrzenie(){
        super();
    }

    public int getWykonane() {
        return wykonane;
    }

    public void setWykonane(int wykonane) {
        this.wykonane = wykonane;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public Long getId_produktu() {
        return id_produktu;
    }

    public void setId_produktu(Long id_produktu) {
        this.id_produktu = id_produktu;
    }

    public Long getId_magazynu() {
        return id_magazynu;
    }

    public void setId_magazynu(Long id_magazynu) {
        this.id_magazynu = id_magazynu;
    }

    public Long getId_zaopatrzenia() {
        return id_zaopatrzenia;
    }

    public void setId_zaopatrzenia(Long id_zaopatrzenia) {
        this.id_zaopatrzenia = id_zaopatrzenia;
    }

    public Long getIdpracownika() {
        return idpracownika;
    }

    public void setIdpracownika(Long idpracownika) {
        this.idpracownika = idpracownika;
    }
}
