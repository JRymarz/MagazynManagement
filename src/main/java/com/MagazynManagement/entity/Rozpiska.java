package com.MagazynManagement.entity;

import com.MagazynManagement.repository.RozpiskaRepozytory;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Rozpiska {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idRozpiski")
        public Long idRozpiski;

        private String adres;

        private Date data;

        private Long idpracownika;

        private Long nr_zamowienia;

        private Long id_samochodu;

        private int wykonane;

        public Rozpiska(String adres, Date data, Long id_pracownika, Long nr_zamowienia, Long idSamochodu, int wykonane){
            this.data = data;
            this.idpracownika = id_pracownika;
            this.nr_zamowienia = nr_zamowienia;
            this.adres = adres;
            this.id_samochodu = idSamochodu;
            this.wykonane = wykonane;
        }

        public Rozpiska(){
            super();
        }

        public Long getIdRozpiski(){
            return idRozpiski;
        }

        public void setIdRozpiski(Long idRozpiski){
            this.idRozpiski = idRozpiski;
        }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getIdpracownika() {
        return idpracownika;
    }

    public void setIdpracownika(Long id_pracownika) {
        this.idpracownika = id_pracownika;
    }

    public Long getNr_zamowienia() {
        return nr_zamowienia;
    }

    public void setNr_zamowienia(Long nr_zamowienia) {
        this.nr_zamowienia = nr_zamowienia;
    }

    public Long getId_samochodu() {
        return id_samochodu;
    }

    public void setId_samochodu(Long id_samochodu) {
        this.id_samochodu = id_samochodu;
    }

    public int getWykonane() {
        return wykonane;
    }

    public void setWykonane(int wykonane) {
        this.wykonane = wykonane;
    }
}
