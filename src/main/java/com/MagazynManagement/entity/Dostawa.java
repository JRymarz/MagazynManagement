package com.MagazynManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Dostawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrDostawy;

    private String adres;

    private String status;

    private Date oczekiwanaDataDostawy;

    @ManyToOne
    @JoinColumn(name = "idSamochodu")
    private SamochodDostawczy samochodDostawczy;

    public Dostawa(Long nrDostawy,
                   String adres,
                   String status,
                   Date oczekiwanaDataDostawy,
                   SamochodDostawczy samochodDostawczy) {
        super();
        this.nrDostawy = nrDostawy;
        this.adres = adres;
        this.status = status;
        this.oczekiwanaDataDostawy = oczekiwanaDataDostawy;
        this.samochodDostawczy = samochodDostawczy;
    }

    public Dostawa() {
        super();
    }

    public Long getNrDostawy() {
        return nrDostawy;
    }

    public String getAdres() {
        return adres;
    }

    public String getStatus() {
        return status;
    }

    public Date getOczekiwanaDataDostawy() {
        return oczekiwanaDataDostawy;
    }

    public SamochodDostawczy getSamochodDostawczy() {
        return samochodDostawczy;
    }

    public void setNrDostawy(Long nrDostawy) {
        this.nrDostawy = nrDostawy;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOczekiwanaDataDostawy(Date oczekiwanaDataDostawy) {
        this.oczekiwanaDataDostawy = oczekiwanaDataDostawy;
    }

    public void setSamochodDostawczy(SamochodDostawczy samochodDostawczy) {
        this.samochodDostawczy = samochodDostawczy;
    }
}
