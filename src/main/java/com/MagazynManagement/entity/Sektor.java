package com.MagazynManagement.entity;

import jakarta.persistence.*;

@Entity
public class Sektor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSektora;

    @ManyToOne
    @JoinColumn(name = "id_magazynu")
    private Magazyn magazyn;

    private String nazwa;

    public Sektor() {
        super();
    }

    public Sektor(Magazyn magazyn, String nazwa) {
        this.magazyn = magazyn;
        this.nazwa = nazwa;
    }

    public void setIdSektora(Long idSektora) {
        this.idSektora = idSektora;
    }

    public void setMagazyn(Magazyn magazyn) {
        this.magazyn = magazyn;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Long getIdSektora() {
        return idSektora;
    }

    public Magazyn getMagazyn() {
        return magazyn;
    }

    public String getNazwa() {
        return nazwa;
    }
}
