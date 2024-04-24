package com.MagazynManagement.entity;

public class AdresDostawy {
    private String adres;

    public AdresDostawy(String adres) {
        this.adres = adres;
    }

    public AdresDostawy() {
        super();
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
