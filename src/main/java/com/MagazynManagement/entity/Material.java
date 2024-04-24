package com.MagazynManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduktu;

    private String nazwa;

    private String producent;
    private float cena;

    private String typ;

    public Material(Long idProduktu, String nazwa, String producent, float cena, String typ) {
        super();
        this.idProduktu = idProduktu;
        this.nazwa = nazwa;
        this.producent = producent;
        this.cena = cena;
        this.typ = typ;
    }

    public Material() {
        super();
    }

    public Long getIdProduktu() {
        return idProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getProducent() {
        return producent;
    }

    public float getCena() {
        return cena;
    }

    public String getTyp() {
        return typ;
    }

    public void setIdProduktu(Long idProduktu) {
        this.idProduktu = idProduktu;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
