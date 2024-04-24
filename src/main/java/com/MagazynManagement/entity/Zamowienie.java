package com.MagazynManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrZamowienia;

    private float kwota;

    private Date dataZlozenia;

    @OneToOne
    @JoinColumn(name = "nr_dostawy")
    private Dostawa dostawa;

    @OneToOne
    @JoinColumn(name = "id_klienta")
    private Klient klient;

    public Zamowienie(Long nrZamowienia, float kwota, Date dataZlozenia, Dostawa dostawa, Klient klient) {
        super();
        this.nrZamowienia = nrZamowienia;
        this.kwota = kwota;
        this.dataZlozenia = dataZlozenia;
        this.dostawa = dostawa;
        this.klient = klient;
    }

    public Zamowienie() {
        super();
    }

    public Long getNrZamowienia() {
        return nrZamowienia;
    }

    public float getKwota() {
        return kwota;
    }

    public Date getDataZlozenia() {
        return dataZlozenia;
    }

    public Dostawa getDostawa() {
        return dostawa;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setNrZamowienia(Long nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    public void setKwota(float kwota) {
        this.kwota = kwota;
    }

    public void setDataZlozenia(Date dataZlozenia) {
        this.dataZlozenia = dataZlozenia;
    }

    public void setDostawa(Dostawa dostawa) {
        this.dostawa = dostawa;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }


}
